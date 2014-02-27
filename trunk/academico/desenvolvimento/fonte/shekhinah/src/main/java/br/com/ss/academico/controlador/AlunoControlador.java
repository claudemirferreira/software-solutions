package br.com.ss.academico.controlador;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.ss.academico.dominio.Aluno;
import br.com.ss.academico.ireport.RelatorioUtil;
import br.com.ss.academico.servico.AlunoServico;

@ManagedBean
@SessionScoped
public class AlunoControlador implements Serializable {

	private static final long serialVersionUID = -6832271293709421841L;

	private Aluno entidade;

	private Aluno pesquisa;

	private List<Aluno> lista;

	private final String TELA_CADASTRO = "paginas/aluno/cadastro.xhtml";
	private final String TELA_PESQUISA = "paginas/aluno/pesquisa.xhtml";

	@ManagedProperty(value = "#{alunoServicoImpl}")
	private AlunoServico servico;

	@ManagedProperty(value = "#{paginaCentralControlador}")
	private PaginaCentralControlador paginaCentralControlador;

	@ManagedProperty(value = "#{relatorioUtil}")
	private RelatorioUtil relatorioUtil;

	@PostConstruct
	public void init() {
		this.lista = servico.listarTodos();
		telaPesquisa();
	}

	public AlunoControlador() {
		this.entidade = new Aluno();
		this.pesquisa = new Aluno();
	}

	public void pesquisar() {
		this.lista = servico.findByNomeLike(this.pesquisa.getNome());
	}

	public void detalhe(Aluno aluno) {
		this.entidade = aluno;
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);
	}

	public void salvar() {
		if (this.entidade.getDataCadastro() == null)
			this.entidade.setDataCadastro(new Date());

		this.servico.salvar(this.entidade);
		this.lista = servico.listarTodos();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public void excluir(Aluno aluno) {
		servico.remover(aluno);
		this.lista = servico.listarTodos();
	}

	public void novo() {
		this.entidade = new Aluno();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);
	}

	public void telaPeaquisa() {
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public void imprimir() throws FileNotFoundException {

		relatorioUtil.gerarRelatorioWeb(this.lista, null, "aluno.jasper");

	}

	public Aluno getEntidade() {
		return entidade;
	}

	public void setEntidade(Aluno entidade) {
		this.entidade = entidade;
	}

	public Aluno getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(Aluno pesquisa) {
		this.pesquisa = pesquisa;
	}

	public List<Aluno> getLista() {
		return lista;
	}

	public void setLista(List<Aluno> lista) {
		this.lista = lista;
	}

	public PaginaCentralControlador getPaginaCentralControlador() {
		return paginaCentralControlador;
	}

	public void setPaginaCentralControlador(
			PaginaCentralControlador paginaCentralControlador) {
		this.paginaCentralControlador = paginaCentralControlador;
	}

	public AlunoServico getServico() {
		return servico;
	}

	public void setServico(AlunoServico servico) {
		this.servico = servico;
	}

	public void telaPesquisa() {
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public RelatorioUtil getRelatorioUtil() {
		return relatorioUtil;
	}

	public void setRelatorioUtil(RelatorioUtil relatorioUtil) {
		this.relatorioUtil = relatorioUtil;
	}

}