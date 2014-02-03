package br.com.ss.data.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.ss.data.entities.Perfil;
import br.com.ss.data.entities.Rotina;
import br.com.ss.data.entities.Sistema;
import br.com.ss.data.servico.RotinaServico;
import br.com.ss.data.util.Arredondar;

@ManagedBean
@SessionScoped
public class RotinaControlador implements Serializable {

	private static final long serialVersionUID = -6561146461243717570L;

	private Rotina entidade;

	private Rotina pesquisa;

	private Sistema sistema;

	private Perfil perfil;

	private List<Rotina> lista;

	private int coluna;

	@ManagedProperty(value = "#{rotinaServicoImpl}")
	private RotinaServico servico;

	@ManagedProperty(value = "#{paginaCentralControlador}")
	private PaginaCentralControlador paginaCentralControlador;

	private final String TELA_CADASTRO = "paginas/rotina/cadastro.xhtml";
	private final String TELA_PESQUISA = "paginas/rotina/pesquisa.xhtml";
	private final String TELA_ROTINA_PERFIL = "paginas/rotina/rotinaperfil.xhtml";

	@PostConstruct
	public void init() {
		this.lista = new ArrayList<Rotina>();
		this.entidade = new Rotina();
		this.pesquisa = new Rotina();
		this.sistema = new Sistema();
		this.perfil = new Perfil();

	}

	public void findBySistema(Sistema sistema) {
		this.lista = servico.findBySistema(sistema);
		this.sistema = sistema;
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public void findByPerfil() {
		this.lista = servico.findByPerfil(1);
		this.paginaCentralControlador.setPaginaCentral(this.TELA_ROTINA_PERFIL);
	}

	public void pesquisar() {
		this.lista = servico.findBySistemaByNomeLike(this.sistema,
				this.pesquisa.getNome());
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

	public void administrativo() {
		this.lista = this.servico.findByPerfil(1);
		this.coluna = Arredondar.arredondar(this.lista.size());
		this.paginaCentralControlador.setPaginaCentral(this.TELA_ROTINA_PERFIL);
	}

	public void lancamento() {
		this.lista = this.servico.findByPerfil(6);
		this.coluna = Arredondar.arredondar(this.lista.size());
		this.paginaCentralControlador.setPaginaCentral(this.TELA_ROTINA_PERFIL);
	}

	public void cadastro() {
		this.lista = this.servico.findByPerfil(5);
		this.coluna = Arredondar.arredondar(this.lista.size());
		this.paginaCentralControlador.setPaginaCentral(this.TELA_ROTINA_PERFIL);
	}

	public void relatorio() {
		this.lista = this.servico.findByPerfil(7);
		this.coluna = Arredondar.arredondar(this.lista.size());
		this.paginaCentralControlador.setPaginaCentral(this.TELA_ROTINA_PERFIL);
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

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
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

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
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