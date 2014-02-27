package br.com.ss.academico.controlador;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.ss.academico.dominio.Professor;
import br.com.ss.academico.servico.ProfessorServico;

@ManagedBean
@SessionScoped
public class ProfessorControlador implements Serializable {

	private static final long serialVersionUID = -6832271293709421841L;

	private Professor entidade;

	private Professor pesquisa;

	private List<Professor> lista;

	private final String TELA_CADASTRO = "paginas/professor/cadastro.xhtml";
	private final String TELA_PESQUISA = "paginas/professor/pesquisa.xhtml";

	@ManagedProperty(value = "#{professorServicoImpl}")
	private ProfessorServico servico;

	@ManagedProperty(value = "#{paginaCentralControlador}")
	private PaginaCentralControlador paginaCentralControlador;

	@PostConstruct
	public void init() {
		this.lista = servico.listarTodos();
		telaPesquisa();
	}

	public ProfessorControlador() {
		this.entidade = new Professor();
		this.pesquisa = new Professor();
	}

	public void pesquisar() {
		this.lista = servico.findByNomeLike(this.pesquisa.getNome());
	}

	public void detalhe(Professor professor) {
		this.entidade = professor;
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);
	}

	public void salvar() {
		if (this.entidade.getDataCadastro() == null)
			this.entidade.setDataCadastro(new Date());

		this.servico.salvar(this.entidade);
		this.lista = servico.listarTodos();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public void excluir(Professor professor) {
		servico.remover(professor);
		this.lista = servico.listarTodos();
	}

	public void novo() {
		this.entidade = new Professor();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);
	}

	public void telaPeaquisa() {
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public Professor getEntidade() {
		return entidade;
	}

	public void setEntidade(Professor entidade) {
		this.entidade = entidade;
	}

	public Professor getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(Professor pesquisa) {
		this.pesquisa = pesquisa;
	}

	public List<Professor> getLista() {
		return lista;
	}

	public void setLista(List<Professor> lista) {
		this.lista = lista;
	}

	public PaginaCentralControlador getPaginaCentralControlador() {
		return paginaCentralControlador;
	}

	public void setPaginaCentralControlador(
			PaginaCentralControlador paginaCentralControlador) {
		this.paginaCentralControlador = paginaCentralControlador;
	}

	public ProfessorServico getServico() {
		return servico;
	}

	public void setServico(ProfessorServico servico) {
		this.servico = servico;
	}

	public void telaPesquisa() {
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

}