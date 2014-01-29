package br.com.ss.data.controlador;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.ss.data.entities.Sistema;
import br.com.ss.data.servico.SistemaServico;

@ManagedBean
@SessionScoped
public class SistemaControlador implements Serializable {

	private static final long serialVersionUID = -6561146461243717570L;

	private Sistema entidade;

	private Sistema pesquisa;

	private List<Sistema> lista;

	@ManagedProperty(value = "#{sistemaServicoImpl}")
	private SistemaServico servico;

	@ManagedProperty(value = "#{paginaCentralControlador}")
	private PaginaCentralControlador paginaCentralControlador;

	private final String TELA_CADASTRO = "paginas/sistema/cadastro.xhtml";
	private final String TELA_PESQUISA = "paginas/sistema/pesquisa.xhtml";

	@PostConstruct
	public void init() {
		this.lista = servico.listarTodos();
		this.entidade = new Sistema();
		this.pesquisa = new Sistema();

	}

	public void pesquisar() {

	}

	public void detalhe(Sistema sistema) {

		this.entidade = sistema;
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);

	}

	public void salvar() {

		servico.salvar(this.entidade);
		this.lista = servico.listarTodos();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public void excluir(Sistema sistema) {
		servico.remover(sistema);
		this.lista = servico.listarTodos();
	}

	public void novo() {

		this.entidade = new Sistema();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);

	}

	public void retornar() {
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	// get e set
	public Sistema getEntidade() {
		return entidade;
	}

	public void setEntidade(Sistema entidade) {
		this.entidade = entidade;
	}

	public Sistema getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(Sistema pesquisa) {
		this.pesquisa = pesquisa;
	}

	public List<Sistema> getLista() {
		return lista;
	}

	public void setLista(List<Sistema> lista) {
		this.lista = lista;
	}

	public SistemaServico getServico() {
		return servico;
	}

	public void setServico(SistemaServico servico) {
		this.servico = servico;
	}

	public PaginaCentralControlador getPaginaCentralControlador() {
		return paginaCentralControlador;
	}

	public void setPaginaCentralControlador(
			PaginaCentralControlador paginaCentralControlador) {
		this.paginaCentralControlador = paginaCentralControlador;
	}

}