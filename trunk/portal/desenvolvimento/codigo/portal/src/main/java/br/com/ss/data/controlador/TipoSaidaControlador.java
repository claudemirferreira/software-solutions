package br.com.ss.data.controlador;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.ss.data.entities.TipoSaida;
import br.com.ss.data.servico.TipoSaidaServico;

@ManagedBean
@SessionScoped
public class TipoSaidaControlador implements Serializable {

	private static final long serialVersionUID = -8110448221899633556L;

	private TipoSaida entidade;

	private TipoSaida pesquisa;

	private List<TipoSaida> lista;

	@ManagedProperty(value = "#{tipoSaidaServicoImpl}")
	private TipoSaidaServico servico;

	@ManagedProperty(value = "#{paginaCentralControlador}")
	private PaginaCentralControlador paginaCentralControlador;

	private final String TELA_CADASTRO = "paginas/tiposaida/cadastro.xhtml";
	private final String TELA_PESQUISA = "paginas/tiposaida/pesquisa.xhtml";

	@PostConstruct
	public void init() {
		this.lista = servico.listarTodos();
		this.entidade = new TipoSaida();
		this.pesquisa = new TipoSaida();

	}

	public void pesquisar() {

	}

	public void detalhe(TipoSaida tipoSaida) {

		this.entidade = tipoSaida;
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);

	}

	public void salvar() {

		servico.salvar(this.entidade);
		this.lista = servico.listarTodos();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public void excluir(TipoSaida tipoSaida) {
		servico.remover(tipoSaida);
		this.lista = servico.listarTodos();
	}

	public void novo() {

		this.entidade = new TipoSaida();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);

	}

	// get e set
	public TipoSaida getEntidade() {
		return entidade;
	}

	public void setEntidade(TipoSaida entidade) {
		this.entidade = entidade;
	}

	public TipoSaida getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(TipoSaida pesquisa) {
		this.pesquisa = pesquisa;
	}

	public List<TipoSaida> getLista() {
		return lista;
	}

	public void setLista(List<TipoSaida> lista) {
		this.lista = lista;
	}

	public TipoSaidaServico getServico() {
		return servico;
	}

	public void setServico(TipoSaidaServico servico) {
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