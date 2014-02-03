package br.com.ss.data.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.toolbar.ToolbarGroup;

import br.com.ss.data.entities.Perfil;
import br.com.ss.data.entities.Sistema;
import br.com.ss.data.servico.PerfilServico;

@ManagedBean
@SessionScoped
public class PerfilControlador implements Serializable {

	private static final long serialVersionUID = -6561146461243717570L;

	private Perfil entidade;

	private Perfil pesquisa;

	private Sistema sistema;

	private List<Perfil> lista;

	@ManagedProperty(value = "#{perfilServicoImpl}")
	private PerfilServico servico;

	@ManagedProperty(value = "#{paginaCentralControlador}")
	private PaginaCentralControlador paginaCentralControlador;

	private final String TELA_CADASTRO = "paginas/perfil/cadastro.xhtml";
	private final String TELA_PESQUISA = "paginas/perfil/pesquisa.xhtml";

	private ToolbarGroup left;

	private int perfilId;

	@PostConstruct
	public void init() {
		this.lista = new ArrayList<Perfil>();
		this.entidade = new Perfil();
		this.pesquisa = new Perfil();
		this.sistema = new Sistema();

	}

	public void findBySistema(Sistema sistema) {
		this.lista = servico.findBySistema(sistema);
		this.sistema = sistema;

		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);

	}

	public void pesquisar() {
		this.lista = servico.findBySistemaByNomeLike(this.sistema,
				this.pesquisa.getNome());
	}

	public void detalhe(Perfil perfil) {
		this.entidade = perfil;
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);
	}

	public void salvar() {
		this.servico.salvar(this.entidade);
		this.lista = servico.listarTodos();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public void excluir(Perfil perfil) {
		servico.remover(perfil);
		this.lista = servico.listarTodos();
	}

	public void novo() {
		this.entidade = new Perfil();
		this.entidade.setSistema(this.sistema);
		this.lista = servico.findBySistema(this.sistema);
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);

	}

	public void retornar() {
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public ToolbarGroup getLeft() {
		this.left = new ToolbarGroup();
		CommandButton salva = new CommandButton();
		salva.setTitle("Salvar");
		salva.setIcon("ui-icon-disk");

		salva.setValue("Salvar");

		return this.left;
	}

	// get e set
	public Perfil getEntidade() {
		return entidade;
	}

	public void setEntidade(Perfil entidade) {
		this.entidade = entidade;
	}

	public Perfil getPesquisa() {
		return pesquisa;
	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	public void setPesquisa(Perfil pesquisa) {
		this.pesquisa = pesquisa;
	}

	public List<Perfil> getLista() {
		return lista;
	}

	public void setLista(List<Perfil> lista) {
		this.lista = lista;
	}

	public int getPerfilId() {
		return perfilId;
	}

	public void setPerfilId(int perfilId) {
		this.perfilId = perfilId;
	}

	public PerfilServico getServico() {
		return servico;
	}

	public void setServico(PerfilServico servico) {
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