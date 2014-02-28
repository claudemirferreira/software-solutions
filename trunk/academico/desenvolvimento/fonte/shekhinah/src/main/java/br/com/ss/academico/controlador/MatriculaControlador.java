package br.com.ss.academico.controlador;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.ss.academico.dominio.Matricula;
import br.com.ss.academico.servico.MatriculaServico;

@ManagedBean
@SessionScoped
public class MatriculaControlador implements Serializable {

	private static final long serialVersionUID = -6832271293709421841L;

	private Matricula entidade;

	private Matricula pesquisa;

	private List<Matricula> lista;

	private final String TELA_CADASTRO = "paginas/matricula/cadastro.xhtml";
	private final String TELA_PESQUISA = "paginas/matricula/pesquisa.xhtml";

	@ManagedProperty(value = "#{matriculaServicoImpl}")
	private MatriculaServico servico;

	@ManagedProperty(value = "#{paginaCentralControlador}")
	private PaginaCentralControlador paginaCentralControlador;

	public void init() {
		this.lista = servico.listarTodos();
		this.telaPesquisa();
	}

	public MatriculaControlador() {
		this.entidade = new Matricula();
		this.pesquisa = new Matricula();
	}

	public void pesquisar() {
//		this.lista = servico.findByNomeLike(this.pesquisa.getNome());
	}

	public void detalhe(Matricula matricula) {
		this.entidade = matricula;
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);
	}

	public void salvar() {
		this.servico.salvar(this.entidade);
		this.lista = servico.listarTodos();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public void excluir(Matricula matricula) {
		servico.remover(matricula);
		this.lista = servico.listarTodos();
	}

	public void novo() {
		this.entidade = new Matricula();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);
	}

	public void telaPeaquisa() {
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public Matricula getEntidade() {
		return entidade;
	}

	public void setEntidade(Matricula entidade) {
		this.entidade = entidade;
	}

	public Matricula getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(Matricula pesquisa) {
		this.pesquisa = pesquisa;
	}

	public List<Matricula> getLista() {
		return lista;
	}

	public void setLista(List<Matricula> lista) {
		this.lista = lista;
	}

	public PaginaCentralControlador getPaginaCentralControlador() {
		return paginaCentralControlador;
	}

	public void setPaginaCentralControlador(
			PaginaCentralControlador paginaCentralControlador) {
		this.paginaCentralControlador = paginaCentralControlador;
	}

	public MatriculaServico getServico() {
		return servico;
	}

	public void setServico(MatriculaServico servico) {
		this.servico = servico;
	}

	public void telaPesquisa() {
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

}