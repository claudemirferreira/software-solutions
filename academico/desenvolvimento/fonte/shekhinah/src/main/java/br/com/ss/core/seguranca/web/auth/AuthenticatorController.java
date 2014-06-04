package br.com.ss.core.seguranca.web.auth;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;

import br.com.ss.core.seguranca.dominio.Usuario;
import br.com.ss.core.seguranca.servico.UsuarioServico;
import br.com.ss.core.web.utils.FacesUtils;

@ManagedBean
@RequestScoped
public class AuthenticatorController implements PhaseListener {

	private static final long serialVersionUID = -7939008427347480748L;
	
	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private UsuarioServico usuarioServico;

	public boolean autenticar() {
		
		String user = null,
				pass = null;
		
		Usuario usuario = usuarioServico.findByLoginAndSenha(user,pass);

		if (usuario == null) {
			FacesUtils.addMessage("Dados incorretos", null, FacesMessage.SEVERITY_ERROR);
//			return "/login.xhtml?faces-redirect=true";
			return true;
		} else {

			// FIXME add usuario logado para o escopo de app.. validar ele no
			// login e redirecionar para o home se ja estiver logado..

//			entidade = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

//			return "/index.xhtml?faces-redirect=true";
			return false;
		}
	}

	public String logout() {
//		SecurityContextHolder.clearContext();
//		entidade = new Usuario();
//		getRequest().getSession().invalidate();
		return "logout";
	}

	
	/**
	 * Redirects the login request directly to spring security check. Leave this
	 * method as it is to properly support spring security.
	 * 
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String doLogin() throws ServletException, IOException {
		
		if (autenticar() ) {
			
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			
			RequestDispatcher dispatcher = ((ServletRequest) context.getRequest())
					.getRequestDispatcher("/j_spring_security_check");
			
			dispatcher.forward( (ServletRequest) context.getRequest(),
					(ServletResponse) context.getResponse() );
			
			FacesContext.getCurrentInstance().responseComplete();
		}
		

		return null;
	}

	public void afterPhase(PhaseEvent event) {
	}

	/*
	 * @see
	 * javax.faces.event.PhaseListener#beforePhase(javax.faces.event.PhaseEvent)
	 * Do something before rendering phase.
	 */
	public void beforePhase(PhaseEvent event) {
		Exception exc = (Exception) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get(WebAttributes.AUTHENTICATION_EXCEPTION);

		if (exc instanceof BadCredentialsException) {
			logger.debug("Found exception in session map: " + exc);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.put(WebAttributes.AUTHENTICATION_EXCEPTION, null);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Usuário ou senha inválido!",
							"Usuário ou senha inválido!" ) );
		}
	}

	/*
	 * @see javax.faces.event.PhaseListener#getPhaseId()
	 * 
	 * In which phase you want to interfere?
	 */
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

}
