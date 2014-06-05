package br.com.ss.core.seguranca.web.auth;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.sun.faces.context.flash.ELFlash;

import br.com.ss.core.seguranca.dominio.Usuario;
import br.com.ss.core.seguranca.servico.UsuarioServico;
import br.com.ss.core.web.utils.FacesUtils;

@ManagedBean
@RequestScoped
public class AuthenticatorController {

	protected final Log logger = LogFactory.getLog(getClass());

	@ManagedProperty(value = "#{usuarioServicoImpl}")
	private UsuarioServico usuarioServico;

	private String username;

	private String password;

	@ManagedProperty(value="#{authenticationManager}")
    private AuthenticationManager authenticationManager = null;

	private static final String HOME = "home";
	

	public String autenticar() {
		String page = HOME;
		try {
			
			logger.info("# Autenticando o usuario: " + username );
			
            Authentication request = new UsernamePasswordAuthenticationToken(username, password);
            Authentication result = authenticationManager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);
            
            // adiciona o usuario logado na sessao
            Usuario usuarioLogado = (Usuario) result.getPrincipal();
            FacesUtils.getRequest().getSession().setAttribute("usuarioLogado", usuarioLogado);
            
            /*
             * se quiser add msg no messages, usar o codigo abaixo (add o <h:messages /> na pg):
             * Prolonga a msg utilizando a classe: br.com.ss.core.web.controlador.listener.MultiPageMessagesSupport 
            FacesUtils.addMessage("Bem Vindo " + usuarioLogado.getNome(), null, FacesMessage.SEVERITY_INFO);
            */
            // msg no flash scope
            ELFlash.getFlash().put("msgBemVindo", "Bem Vindo " + usuarioLogado.getNome() + "!");
            
        } catch (AuthenticationException exc) {
        	logger.error("# ERRO Autenticando o usuario: " + username, exc );
            FacesUtils.addMessage("Usuario ou senha inválido!", null, FacesMessage.SEVERITY_WARN);
            page = null;
        }
		
        return page;
        
	}

	public void logout() {
		 SecurityContextHolder.clearContext();
		 FacesUtils.getRequest().getSession().invalidate();
	}


	/* ------------ Gets/Sets ---------------*/
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UsuarioServico getUsuarioServico() {
		return usuarioServico;
	}

	public void setUsuarioServico(UsuarioServico usuarioServico) {
		this.usuarioServico = usuarioServico;
	}

	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

}
