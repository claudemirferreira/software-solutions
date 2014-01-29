package br.com.ss.data.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.ss.data.entities.Rotina;
import br.com.ss.data.entities.Sistema;
import br.com.ss.data.servico.RotinaServico;

@ManagedBean
@SessionScoped
public class RotinaControlador implements Serializable {

	private static final long serialVersionUID = -6561146461243717570L;

	private Rotina entidade;

	private Rotina pesquisa;

	private Sistema sistema;

	private List<Rotina> lista;

	@ManagedProperty(value = "#{rotinaServicoImpl}")
	private RotinaServico servico;

	@ManagedProperty(value = "#{paginaCentralControlador}")
	private PaginaCentralControlador paginaCentralControlador;

	private final String TELA_CADASTRO = "paginas/rotina/cadastro.xhtml";
	private final String TELA_PESQUISA = "paginas/rotina/pesquisa.xhtml";

	@PostConstruct
	public void init() {
		this.lista = new ArrayList<Rotina>();
		this.entidade = new Rotina();
		this.pesquisa = new Rotina();
		this.sistema = new Sistema();

	}

	public void findBySistema(Sistema sistema) {
		this.lista = servico.findBySistema(sistema);
		this.sistema = sistema;

		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);

	}

	public void pesquisar() {

		System.out.println(this.lista.size());
		this.lista = servico.findBySistemaByNomeLike(this.sistema,
				this.pesquisa.getNome());
		System.out.println(this.lista.size());

	}

	public void detalhe(Rotina rotina) {

		this.entidade = rotina;
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);

	}

	public void salvar() {
		this.servico.salvar(this.entidade);
		this.lista = servico.listarTodos();

		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public void excluir(Rotina rotina) {
		servico.remover(rotina);
		this.lista = servico.listarTodos();
	}

	public void novo() {
		this.entidade = new Rotina();
		this.entidade.setSistema(this.sistema);
		this.lista = servico.findBySistema(this.sistema);
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);

	}

	public void retornar() {
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	// get e set
	public Rotina getEntidade() {
		return entidade;
	}

	public void setEntidade(Rotina entidade) {
		this.entidade = entidade;
	}

	public Rotina getPesquisa() {
		return pesquisa;
	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	public void setPesquisa(Rotina pesquisa) {
		this.pesquisa = pesquisa;
	}

	public List<Rotina> getLista() {
		return lista;
	}

	public void setLista(List<Rotina> lista) {
		this.lista = lista;
	}

	public RotinaServico getServico() {
		return servico;
	}

	public void setServico(RotinaServico servico) {
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