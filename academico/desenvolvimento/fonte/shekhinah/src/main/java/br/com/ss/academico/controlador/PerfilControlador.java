package br.com.ss.academico.controlador;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.ss.academico.dominio.Perfil;
import br.com.ss.academico.dominio.Rotina;
import br.com.ss.academico.dominio.Sistema;
import br.com.ss.academico.dominio.Usuario;
import br.com.ss.academico.servico.PerfilServico;
import br.com.ss.academico.servico.RotinaServico;

@ManagedBean
@SessionScoped
public class PerfilControlador implements Serializable {

	private static final int SISTEMA_IEADAM = 2;

	private static final long serialVersionUID = -6832271293709421841L;

	private Perfil perfil;

	private List<Rotina> rotinas;

	private Perfil entidade;

	private Perfil pesquisa;

	private List<Perfil> lista;

	private List<Perfil> listaPerfilUsuario;

	private Usuario usuario;

	private Sistema sistema;

	private int colunas;

	private String TELA_LISTA = "paginas/perfil/lista.xhtml";

	private final String TELA_CADASTRO = "paginas/perfil/cadastro.xhtml";

	private final String TELA_PESQUISA = "paginas/perfil/pesquisa.xhtml";

	@ManagedProperty(value = "#{perfilServicoImpl}")
	private PerfilServico servico;

	@ManagedProperty(value = "#{rotinaServicoImpl}")
	private RotinaServico rotinaServico;

	@ManagedProperty(value = "#{paginaCentralControlador}")
	private PaginaCentralControlador paginaCentralControlador;

	public void init() {
		this.lista = servico.listarTodos();
		this.listaPerfilPorSistemaPorUsuario();
		telaPesquisa();
	}
	
	public PerfilControlador() {
		this.entidade = new Perfil();
		this.pesquisa = new Perfil();
	}

	public void pesquisar() {
		// this.lista =
		// servico.findBySistemaByNomeLike(this.sistema,this.pesquisa.getNome());
	}

	public void detalhe(Perfil perfil) {
		this.entidade = perfil;
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);
	}

	public void salvar() {
		this.servico.salvar(this.entidade);
		this.lista = servico.listarTodos();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public void excluir(Perfil perfil) {
		servico.remover(perfil);
		this.lista = servico.listarTodos();
	}

	public void novo() {
		this.entidade = new Perfil();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);

	}

	public void retornar() {
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public void telaPerfis() {
		this.paginaCentralControlador.setPaginaCentral(this.TELA_LISTA);
	}

	public void listaPerfilPorSistemaPorUsuario() {
		this.usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		this.listaPerfilUsuario = this.servico.listaPerfilPorSistemaPorUsuario( SISTEMA_IEADAM, usuario.getId() );

		this.telaPerfis();

	}

	public void telaPesquisa() {
		 paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public PaginaCentralControlador getPaginaCentralControlador() {
		return paginaCentralControlador;
	}

	public void setPaginaCentralControlador(
			PaginaCentralControlador paginaCentralControlador) {
		this.paginaCentralControlador = paginaCentralControlador;
	}

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

	public List<Rotina> getRotinas() {
		return rotinas;
	}

	public void setRotinas(List<Rotina> rotinas) {
		this.rotinas = rotinas;
	}

	public List<Perfil> getLista() {
		return lista;
	}

	public void setLista(List<Perfil> lista) {
		this.lista = lista;
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