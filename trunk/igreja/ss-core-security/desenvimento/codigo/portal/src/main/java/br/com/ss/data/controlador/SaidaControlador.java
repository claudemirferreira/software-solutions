package br.com.ss.data.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.ss.data.entities.Saida;
import br.com.ss.data.entities.TipoSaida;
import br.com.ss.data.servico.SaidaServico;
import br.com.ss.data.servico.TipoSaidaServico;

@ManagedBean
@SessionScoped
public class SaidaControlador implements Serializable {

	private static final long serialVersionUID = -6561146461243717570L;

	private Saida entidade;

	private Saida pesquisa;

	private TipoSaida tipoSaida;

	private List<Saida> lista;

	private List<TipoSaida> listaTipoSaida;

	@ManagedProperty(value = "#{saidaServicoImpl}")
	private SaidaServico servico;

	@ManagedProperty(value = "#{tipoSaidaServicoImpl}")
	private TipoSaidaServico tipoSaidaServico;

	@ManagedProperty(value = "#{paginaCentralControlador}")
	private PaginaCentralControlador paginaCentralControlador;

	@PostConstruct
	public void init() {
		this.listaTipoSaida = tipoSaidaServico.listarTodos();
		this.lista = new ArrayList<Saida>();
		this.entidade = new Saida();
		this.pesquisa = new Saida();
		this.tipoSaida = new TipoSaida();

	}

	public void pesquisar() {

	}

	public void detalhe(Saida pastor) {

		this.entidade = pastor;
		this.paginaCentralControlador
				.setPaginaCentral("paginas/pastor/cadastro.xhtml");

	}

	public void salvar() {

		servico.salvar(this.entidade);
		this.lista = servico.listarTodos();
		this.paginaCentralControlador
				.setPaginaCentral("paginas/pastor/pesquisa.xhtml");
	}

	public void excluir(Saida pastor) {
		servico.remover(pastor);
		this.lista = servico.listarTodos();
	}

	public void novo() {

		this.entidade = new Saida();
		this.paginaCentralControlador
				.setPaginaCentral("paginas/pastor/cadastro.xhtml");

	}

	// get e set
	public Saida getEntidade() {
		return entidade;
	}

	public void setEntidade(Saida entidade) {
		this.entidade = entidade;
	}

	public Saida getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(Saida pesquisa) {
		this.pesquisa = pesquisa;
	}

	public List<Saida> getLista() {
		return lista;
	}

	public void setLista(List<Saida> lista) {
		this.lista = lista;
	}

	public TipoSaida getTipoSaida() {
		return tipoSaida;
	}

	public void setTipoSaida(TipoSaida tipoSaida) {
		this.tipoSaida = tipoSaida;
	}

	public List<TipoSaida> getListaTipoSaida() {
		return listaTipoSaida;
	}

	public void setListaTipoSaida(List<TipoSaida> listaTipoSaida) {
		this.listaTipoSaida = listaTipoSaida;
	}

	public TipoSaidaServico getTipoSaidaServico() {
		return tipoSaidaServico;
	}

	public void setTipoSaidaServico(TipoSaidaServico tipoSaidaServico) {
		this.tipoSaidaServico = tipoSaidaServico;
	}

	public SaidaServico getServico() {
		return servico;
	}

	public void setServico(SaidaServico servico) {
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