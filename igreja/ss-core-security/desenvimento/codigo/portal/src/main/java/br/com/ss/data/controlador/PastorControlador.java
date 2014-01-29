package br.com.ss.data.controlador;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.ss.data.entities.Pastor;
import br.com.ss.data.servico.PastorServico;

@ManagedBean
@SessionScoped
public class PastorControlador implements Serializable {

	private static final long serialVersionUID = -6561146461243717570L;

	private Pastor entidade;

	private Pastor pesquisa;

	private List<Pastor> lista;

	@ManagedProperty(value = "#{pastorServicoImpl}")
	private PastorServico servico;

	@ManagedProperty(value = "#{paginaCentralControlador}")
	private PaginaCentralControlador paginaCentralControlador;

	@PostConstruct
	public void init() {
		this.lista = servico.listarTodos();
		this.entidade = new Pastor();
		this.pesquisa = new Pastor();

	}

	public void pesquisar() {

	}

	public void detalhe(Pastor pastor) {

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

	public void excluir(Pastor pastor) {
		servico.remover(pastor);
		this.lista = servico.listarTodos();
	}

	public void novo() {

		this.entidade = new Pastor();
		this.paginaCentralControlador
				.setPaginaCentral("paginas/pastor/cadastro.xhtml");

	}

	// get e set
	public Pastor getEntidade() {
		return entidade;
	}

	public void setEntidade(Pastor entidade) {
		this.entidade = entidade;
	}

	public Pastor getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(Pastor pesquisa) {
		this.pesquisa = pesquisa;
	}

	public List<Pastor> getLista() {
		return lista;
	}

	public void setLista(List<Pastor> lista) {
		this.lista = lista;
	}

	public PastorServico getServico() {
		return servico;
	}

	public void setServico(PastorServico servico) {
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