package br.com.ss.academico.controlador;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.ss.academico.dominio.Disciplina;
import br.com.ss.academico.servico.DisciplinaServico;

@ManagedBean
@SessionScoped
public class DisciplinaControlador implements Serializable {

	private static final long serialVersionUID = -6832271293709421841L;

	private Disciplina entidade;

	private Disciplina pesquisa;

	private List<Disciplina> lista;

	private final String TELA_CADASTRO = "paginas/disciplina/cadastro.xhtml";
	private final String TELA_PESQUISA = "paginas/disciplina/pesquisa.xhtml";

	@ManagedProperty(value = "#{disciplinaServicoImpl}")
	private DisciplinaServico servico;

	@ManagedProperty(value = "#{paginaCentralControlador}")
	private PaginaCentralControlador paginaCentralControlador;

	@PostConstruct
	public void init() {
		this.lista = servico.listarTodos();
		telaPesquisa();
	}

	public DisciplinaControlador() {
		this.entidade = new Disciplina();
		this.pesquisa = new Disciplina();
	}

	public void pesquisar() {
		this.lista = servico.findByNomeLike(this.pesquisa.getNome());
	}

	public void detalhe(Disciplina disciplina) {
		this.entidade = disciplina;
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);
	}

	public void salvar() {
		this.servico.salvar(this.entidade);
		this.lista = servico.listarTodos();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public void excluir(Disciplina disciplina) {
		servico.remover(disciplina);
		this.lista = servico.listarTodos();
	}

	public void novo() {
		this.entidade = new Disciplina();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);
	}

	public void telaPeaquisa() {
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public Disciplina getEntidade() {
		return entidade;
	}

	public void setEntidade(Disciplina entidade) {
		this.entidade = entidade;
	}

	public Disciplina getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(Disciplina pesquisa) {
		this.pesquisa = pesquisa;
	}

	public List<Disciplina> getLista() {
		return lista;
	}

	public void setLista(List<Disciplina> lista) {
		this.lista = lista;
	}

	public PaginaCentralControlador getPaginaCentralControlador() {
		return paginaCentralControlador;
	}

	public void setPaginaCentralControlador(
			PaginaCentralControlador paginaCentralControlador) {
		this.paginaCentralControlador = paginaCentralControlador;
	}

	public DisciplinaServico getServico() {
		return servico;
	}

	public void setServico(DisciplinaServico servico) {
		this.servico = servico;
	}

	public void telaPesquisa() {
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

}