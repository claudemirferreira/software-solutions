package br.com.ss.data.controlador;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.ss.data.entities.TipoEntrada;
import br.com.ss.data.servico.TipoEntradaServico;

@ManagedBean
@SessionScoped
public class TipoEntradaControlador implements Serializable {

	private static final long serialVersionUID = 371678528061300396L;

	private TipoEntrada entidade;

	private TipoEntrada pesquisa;

	private List<TipoEntrada> lista;

	@ManagedProperty(value = "#{tipoEntradaServicoImpl}")
	private TipoEntradaServico servico;

	@ManagedProperty(value = "#{paginaCentralControlador}")
	private PaginaCentralControlador paginaCentralControlador;

	private final String TELA_CADASTRO = "paginas/tipoentrada/cadastro.xhtml";
	private final String TELA_PESQUISA = "paginas/tipoentrada/pesquisa.xhtml";

	@PostConstruct
	public void init() {
		this.lista = servico.listarTodos();
		this.entidade = new TipoEntrada();
		this.pesquisa = new TipoEntrada();

	}

	public void pesquisar() {

	}

	public void detalhe(TipoEntrada tipoEntrada) {

		this.entidade = tipoEntrada;
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);

	}

	public void salvar() {

		servico.salvar(this.entidade);
		this.lista = servico.listarTodos();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public void excluir(TipoEntrada tipoEntrada) {
		servico.remover(tipoEntrada);
		this.lista = servico.listarTodos();
	}

	public void novo() {

		this.entidade = new TipoEntrada();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);

	}

	// get e set
	public TipoEntrada getEntidade() {
		return entidade;
	}

	public void setEntidade(TipoEntrada entidade) {
		this.entidade = entidade;
	}

	public TipoEntrada getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(TipoEntrada pesquisa) {
		this.pesquisa = pesquisa;
	}

	public List<TipoEntrada> getLista() {
		return lista;
	}

	public void setLista(List<TipoEntrada> lista) {
		this.lista = lista;
	}

	public TipoEntradaServico getServico() {
		return servico;
	}

	public void setServico(TipoEntradaServico servico) {
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