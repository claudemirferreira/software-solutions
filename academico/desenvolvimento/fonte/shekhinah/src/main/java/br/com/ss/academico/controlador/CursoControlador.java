package br.com.ss.academico.controlador;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.ss.academico.dominio.Curso;
import br.com.ss.academico.servico.CursoServico;

@ManagedBean
@SessionScoped
public class CursoControlador implements Serializable {

	private static final long serialVersionUID = -6832271293709421841L;

	private Curso entidade;

	private Curso pesquisa;

	private List<Curso> lista;

	private final String TELA_CADASTRO = "paginas/curso/cadastro.xhtml";
	private final String TELA_PESQUISA = "paginas/curso/pesquisa.xhtml";

	@ManagedProperty(value = "#{cursoServicoImpl}")
	private CursoServico servico;

	@ManagedProperty(value = "#{paginaCentralControlador}")
	private PaginaCentralControlador paginaCentralControlador;

	@PostConstruct
	public void init() {
		this.lista = servico.listarTodos();
		telaPesquisa();
	}

	public CursoControlador() {
		this.entidade = new Curso();
		this.pesquisa = new Curso();
	}

	public void pesquisar() {
		this.lista = servico.findByNomeLike(this.pesquisa.getNome());
	}

	public void detalhe(Curso curso) {
		this.entidade = curso;
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);
	}

	public void salvar() {
		this.servico.salvar(this.entidade);
		this.lista = servico.listarTodos();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public void excluir(Curso curso) {
		servico.remover(curso);
		this.lista = servico.listarTodos();
	}

	public void novo() {
		this.entidade = new Curso();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);
	}

	public void telaPeaquisa() {
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public Curso getEntidade() {
		return entidade;
	}

	public void setEntidade(Curso entidade) {
		this.entidade = entidade;
	}

	public Curso getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(Curso pesquisa) {
		this.pesquisa = pesquisa;
	}

	public List<Curso> getLista() {
		return lista;
	}

	public void setLista(List<Curso> lista) {
		this.lista = lista;
	}

	public PaginaCentralControlador getPaginaCentralControlador() {
		return paginaCentralControlador;
	}

	public void setPaginaCentralControlador(
			PaginaCentralControlador paginaCentralControlador) {
		this.paginaCentralControlador = paginaCentralControlador;
	}

	public CursoServico getServico() {
		return servico;
	}

	public void setServico(CursoServico servico) {
		this.servico = servico;
	}

	public void telaPesquisa() {
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

}