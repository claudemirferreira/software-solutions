package br.com.ss.core.web.controlador.seguranca;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.ss.core.seguranca.dominio.Perfil;
import br.com.ss.core.seguranca.dominio.Usuario;
import br.com.ss.core.seguranca.servico.IService;
import br.com.ss.core.seguranca.servico.PerfilServico;
import br.com.ss.core.seguranca.servico.RotinaServico;
import br.com.ss.core.web.controlador.ControladorGenerico;

@ManagedBean
@SessionScoped
public class PerfilControlador extends ControladorGenerico<Perfil> {
	
	// FIXME remover carregamento do perfil do usuario logado..
	
	private static final long serialVersionUID = -6832271293709421841L;

	private static final int SISTEMA_IEADAM = 2;	// FIXME #Peninha: verificar regra 'SISTEMA_IEADAM'

	/** Lista exibida na tela inicial (lista.xhtml). */
	private List<Perfil> listaPerfilUsuario;

	private Usuario usuario;

	private int colunas = 3;

	@ManagedProperty(value = "#{perfilServicoImpl}")
	private PerfilServico servico;

	@ManagedProperty(value = "#{rotinaServicoImpl}")
	private RotinaServico rotinaServico;


	/* --------- Overrides ------------------ */

	@Override
	public void init() {
		carregarPerfis();	// FIXME
	}

	public void carregarPerfis() {
		Object userSession = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if ( userSession instanceof Usuario ) {
			this.usuario = (Usuario) userSession ;
			this.listaPerfilUsuario = this.servico.listaPerfilPorSistemaPorUsuario( SISTEMA_IEADAM, usuario.getId() );
		}
	}


	@Override
	protected String getNomeRelatorio() {
		// FIXME #Peninha
		return null;
	}

	@Override
	protected IService<Perfil, Long> getService() {
		return servico;
	}
	
	@Override
	public String detalhe(Perfil perfil) {
		return super.detalhe(perfil);
	}

	public String salvar() {
		return super.salvar();
	}


	/* --------------- Gets/Sets ----------------------*/

	public PerfilServico getServico() {
		return servico;
	}

	public void setServico(PerfilServico servico) {
		this.servico = servico;
	}

	public RotinaServico getRotinaServico() {
		return rotinaServico;
	}

	public void setRotinaServico(RotinaServico rotinaServico) {
		this.rotinaServico = rotinaServico;
	}

	public int getColunas() {
		return colunas;
	}

	public Perfil getEntidade() {
		return entidade;
	}

	public void setEntidade(Perfil entidade) {
		this.entidade = entidade;
	}

	public Perfil getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(Perfil pesquisa) {
		this.pesquisa = pesquisa;
	}

	public List<Perfil> getListaPerfilUsuario() {
		return listaPerfilUsuario;
	}

	public void setListaPerfilUsuario(List<Perfil> listaPerfilUsuario) {
		this.listaPerfilUsuario = listaPerfilUsuario;
	}

}