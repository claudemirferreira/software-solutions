package br.com.ss.data.controlador;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.ss.data.entities.Membro;
import br.com.ss.data.servico.MembroServico;

@ManagedBean
@SessionScoped
public class MembroControlador implements Serializable {

	private static final long serialVersionUID = -6561146461243717570L;

	private Membro entidade;

	private Membro pesquisa;

	private List<Membro> lista;

	@ManagedProperty(value = "#{membroServicoImpl}")
	private MembroServico servico;

	@ManagedProperty(value = "#{paginaCentralControlador}")
	private PaginaCentralControlador paginaCentralControlador;

	@PostConstruct
	public void init() {
		this.lista = servico.listarTodos();
		this.entidade = new Membro();
		this.pesquisa = new Membro();
	}

	public void pesquisar() {

	}

	public void detalhe(Membro membro) {

		this.entidade = membro;
		this.paginaCentralControlador
				.setPaginaCentral("paginas/membro/cadastro.xhtml");

	}

	public void salvar() {

		servico.salvar(this.entidade);
		this.lista = servico.listarTodos();
		this.paginaCentralControlador
				.setPaginaCentral("paginas/membro/pesquisa.xhtml");
	}

	public void excluir(Membro membro) {
		servico.remover(membro);
		this.lista = servico.listarTodos();
	}

	public void novo() {

		this.entidade = new Membro();
		this.paginaCentralControlador
				.setPaginaCentral("paginas/membro/cadastro.xhtml");

	}

	// get e set
	public Membro getEntidade() {
		return entidade;
	}

	public void setEntidade(Membro entidade) {
		this.entidade = entidade;
	}

	public Membro getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(Membro pesquisa) {
		this.pesquisa = pesquisa;
	}

	public List<Membro> getLista() {
		return lista;
	}

	public void setLista(List<Membro> lista) {
		this.lista = lista;
	}

	public MembroServico getServico() {
		return servico;
	}

	public void setServico(MembroServico servico) {
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