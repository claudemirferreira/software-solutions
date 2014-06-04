package br.com.ss.academico.controlador;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.ss.academico.dominio.Perfil;
import br.com.ss.academico.dominio.Usuario;
import br.com.ss.academico.servico.IService;
import br.com.ss.academico.servico.PerfilServico;
import br.com.ss.academico.servico.RotinaServico;

@ManagedBean
@SessionScoped
// FIXME nao estah redirecionando para o login quando ainda nao logou.. so deve criar o bean se estiver logado
// estah chamando o metodo listaPerfilUsuario ao entrar na tela de login
public class PerfilControlador extends ControladorGenerico<Perfil> {
	
	private static final long serialVersionUID = -6832271293709421841L;

	private static final int SISTEMA_IEADAM = 2;	// FIXME #Peninha: verificar regra 'SISTEMA_IEADAM'

	/** Lista exibida na tela inicial (lista.xhtml). */
	private List<Perfil> listaPerfilUsuario;

	private Usuario usuario;

	private int colunas;	// FIXME #Peninha: é necessário?

	@ManagedProperty(value = "#{perfilServicoImpl}")
	private PerfilServico servico;

	@ManagedProperty(value = "#{rotinaServicoImpl}")
	private RotinaServico rotinaServico;


	/* --------- Overrides ------------------ */

	@Override
	public void init() {
		carregarPerfis();
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

	public void setColunas(int colunas) {
		this.colunas = colunas;
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