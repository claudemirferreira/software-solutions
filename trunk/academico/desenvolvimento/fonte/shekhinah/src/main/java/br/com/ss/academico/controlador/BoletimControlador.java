package br.com.ss.academico.controlador;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.ss.academico.dominio.Boletim;
import br.com.ss.academico.servico.BoletimServico;

@ManagedBean
@SessionScoped
public class BoletimControlador implements Serializable {

	private static final long serialVersionUID = -6832271293709421841L;

	private Boletim entidade;

	private Boletim pesquisa;

	private List<Boletim> lista;

	private final String TELA_CADASTRO = "paginas/boletim/cadastro.xhtml";
	private final String TELA_PESQUISA = "paginas/boletim/pesquisa.xhtml";

	@ManagedProperty(value = "#{boletimServicoImpl}")
	private BoletimServico servico;

	@ManagedProperty(value = "#{paginaCentralControlador}")
	private PaginaCentralControlador paginaCentralControlador;

	@PostConstruct
	public void init() {
		this.lista = servico.listarTodos();
		telaPesquisa();
	}

	public BoletimControlador() {
		this.entidade = new Boletim();
		this.pesquisa = new Boletim();
	}

	public void pesquisar() {
		// this.lista = servico.findByNomeLike(this.pesquisa.getNome());
	}

	public void detalhe(Boletim boletim) {
		this.entidade = boletim;
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);
	}

	public void salvar() {

		this.servico.salvar(this.entidade);
		this.lista = servico.listarTodos();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public void excluir(Boletim boletim) {
		servico.remover(boletim);
		this.lista = servico.listarTodos();
	}

	public void novo() {
		this.entidade = new Boletim();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);
	}

	public void telaPeaquisa() {
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public Boletim getEntidade() {
		return entidade;
	}

	public void setEntidade(Boletim entidade) {
		this.entidade = entidade;
	}

	public Boletim getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(Boletim pesquisa) {
		this.pesquisa = pesquisa;
	}

	public List<Boletim> getLista() {
		return lista;
	}

	public void setLista(List<Boletim> lista) {
		this.lista = lista;
	}

	public PaginaCentralControlador getPaginaCentralControlador() {
		return paginaCentralControlador;
	}

	public void setPaginaCentralControlador(
			PaginaCentralControlador paginaCentralControlador) {
		this.paginaCentralControlador = paginaCentralControlador;
	}

	public BoletimServico getServico() {
		return servico;
	}

	public void setServico(BoletimServico servico) {
		this.servico = servico;
	}

	public void telaPesquisa() {
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

}