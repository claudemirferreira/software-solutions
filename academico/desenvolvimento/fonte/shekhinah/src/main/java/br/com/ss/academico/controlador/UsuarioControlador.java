package br.com.ss.academico.controlador;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.ss.academico.dominio.Perfil;
import br.com.ss.academico.dominio.Sistema;
import br.com.ss.academico.dominio.Usuario;
import br.com.ss.academico.enumerated.StatusUsuario;
import br.com.ss.academico.servico.IService;
import br.com.ss.academico.servico.SistemaServico;
import br.com.ss.academico.servico.UsuarioServico;
import br.com.ss.academico.utils.AuthenticationManager;

@ManagedBean
@SessionScoped
public class UsuarioControlador extends ControladorGenerico<Usuario> {

	private static final long serialVersionUID = -929165489387258837L;

	private Sistema sistema;

	private List<Perfil> perfis = new ArrayList<Perfil>();

	@ManagedProperty(value = "#{usuarioServicoImpl}")
	private UsuarioServico servico;

	@ManagedProperty(value = "#{sistemaServicoImpl}")
	private SistemaServico sistemaServico;

	@ManagedProperty(value = "#{perfilControlador}")
	private PerfilControlador perfilControlador; // FIXME evitar injetar um bean
													// dentro de outro.. bad
													// pattern

	private int colunas;

	@ManagedProperty(value = "#{authenticationManager}")
	private AuthenticationManager authenticationManager;

	protected List<SelectItem> statusUsuarioList;

	public void init() {
		this.sistema = sistemaServico.findByCodigo("IEADAM"); // FIXME mudar
																// codigo do
																// sistema
																// (IEADAM)

		this.statusUsuarioList = new ArrayList<SelectItem>();
		for (StatusUsuario c : StatusUsuario.values()) {
			statusUsuarioList.add(new SelectItem(c, c.getDescricao()));
		}
	}

	@Override
	protected String getNomeRelatorio() {
		// FIXME #Peninha: relatorio
		return null;
	}

	@Override
	protected IService<Usuario, Long> getService() {
		return servico;
	}

	public String logar() {

		Authentication authenticatedUser = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(entidade
						.getLogin(), entidade.getSenha()));

		if (authenticatedUser == null) {
			showMessage("Dados incorretos", FacesMessage.SEVERITY_ERROR);
			entidade = new Usuario();
			return "/login.xhtml?faces-redirect=true";

		} else {

			// FIXME add usuario logado para o escopo de app.. validar ele no
			// login e redirecionar para o home se ja estiver logado..

			entidade = (Usuario) SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();
			this.colunas = 3;
			
			/* FIXME deve ser chamado pelo proprio bean ou pg  */
			perfilControlador.getListaPerfilUsuario(); 
			return "/index.xhtml?faces-redirect=true";
		}
	}

	public String logout() {
		SecurityContextHolder.clearContext();
		entidade = new Usuario();
		getRequest().getSession().invalidate();
		return "logout";
	}

	public String validarUsuarioLogado() {
		Object user = SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		if (user instanceof Usuario) {
			Usuario usuarioLogado = (Usuario) user;
			if (usuarioLogado != null) {

				// FIXME deve redirecionar para o home..
				// return getRequest().getContextPath() +
				// "/index.xhtml?faces-redirect=true";
				return "home";
			}
		}
		return null;
	}

	public String editarSenha() {
		this.entidade = (Usuario) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		return "paginas/usuario/alterarSenha.xhtml?faces-redirect=true";
	}

	/* --------------- Gets/Sets ---------------------- */

	public UsuarioServico getServico() {
		return servico;
	}

	public void setServico(UsuarioServico servico) {
		this.servico = servico;
	}

	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public void setAuthenticationManager(
			AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public int getColunas() {
		return colunas;
	}

	public void setColunas(int colunas) {
		this.colunas = colunas;
	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	public SistemaServico getSistemaServico() {
		return sistemaServico;
	}

	public void setSistemaServico(SistemaServico sistemaServico) {
		this.sistemaServico = sistemaServico;
	}

	public PerfilControlador getPerfilControlador() {
		return perfilControlador;
	}

	public void setPerfilControlador(PerfilControlador perfilControlador) {
		this.perfilControlador = perfilControlador;
	}

	public List<SelectItem> getStatusUsuarioList() {
		return statusUsuarioList;
	}

}