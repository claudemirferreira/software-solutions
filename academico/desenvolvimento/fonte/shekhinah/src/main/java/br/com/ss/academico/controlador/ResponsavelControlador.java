package br.com.ss.academico.controlador;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.ss.academico.dominio.Responsavel;
import br.com.ss.academico.servico.ResponsavelServico;

@ManagedBean
@SessionScoped
public class ResponsavelControlador implements Serializable {

	private static final long serialVersionUID = -6832271293709421841L;

	private Responsavel entidade;

	private Responsavel pesquisa;

	private List<Responsavel> lista;

	private final String TELA_CADASTRO = "paginas/responsavel/cadastro.xhtml";
	private final String TELA_PESQUISA = "paginas/responsavel/pesquisa.xhtml";

	@ManagedProperty(value = "#{responsavelServicoImpl}")
	private ResponsavelServico servico;

	@ManagedProperty(value = "#{paginaCentralControlador}")
	private PaginaCentralControlador paginaCentralControlador;

	@PostConstruct
	public void init() {
		this.lista = servico.listarTodos();
		telaPesquisa();
	}

	public ResponsavelControlador() {
		this.entidade = new Responsavel();
		this.pesquisa = new Responsavel();
	}

	public void pesquisar() {
		this.lista = servico.findByNomeLike(this.pesquisa.getNome());
	}

	public void detalhe(Responsavel responsavel) {
		this.entidade = responsavel;
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);
	}

	public void salvar() {

		this.servico.salvar(this.entidade);
		this.lista = servico.listarTodos();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public void excluir(Responsavel responsavel) {
		servico.remover(responsavel);
		this.lista = servico.listarTodos();
	}

	public void novo() {
		this.entidade = new Responsavel();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);
	}

	public void telaPeaquisa() {
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public Responsavel getEntidade() {
		return entidade;
	}

	public void setEntidade(Responsavel entidade) {
		this.entidade = entidade;
	}

	public Responsavel getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(Responsavel pesquisa) {
		this.pesquisa = pesquisa;
	}

	public List<Responsavel> getLista() {
		return lista;
	}

	public void setLista(List<Responsavel> lista) {
		this.lista = lista;
	}

	public PaginaCentralControlador getPaginaCentralControlador() {
		return paginaCentralControlador;
	}

	public void setPaginaCentralControlador(
			PaginaCentralControlador paginaCentralControlador) {
		this.paginaCentralControlador = paginaCentralControlador;
	}

	public ResponsavelServico getServico() {
		return servico;
	}

	public void setServico(ResponsavelServico servico) {
		this.servico = servico;
	}

	public void telaPesquisa() {
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

}