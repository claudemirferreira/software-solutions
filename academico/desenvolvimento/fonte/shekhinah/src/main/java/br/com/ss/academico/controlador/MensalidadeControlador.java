package br.com.ss.academico.controlador;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.ss.academico.dominio.Aluno;
import br.com.ss.academico.dominio.Mensalidade;
import br.com.ss.academico.enumerated.StatusPagamento;
import br.com.ss.academico.enumerated.Turno;
import br.com.ss.academico.servico.AlunoServico;
import br.com.ss.academico.servico.MensalidadeServico;

@ManagedBean
@SessionScoped
public class MensalidadeControlador implements Serializable {

	private static final long serialVersionUID = -6832271293709421841L;

	private Mensalidade entidade;

	private Mensalidade pesquisa;

	private List<Mensalidade> lista;

	private List<Aluno> alunos;

	private Turno[] turnos;
	
	private StatusPagamento[] status;

	private final String TELA_CADASTRO = "paginas/mensalidade/cadastro.xhtml";
	private final String TELA_PESQUISA = "paginas/mensalidade/pesquisa.xhtml";

	@ManagedProperty(value = "#{mensalidadeServicoImpl}")
	private MensalidadeServico servico;

	@ManagedProperty(value = "#{alunoServicoImpl}")
	private AlunoServico alunoServico;

	@ManagedProperty(value = "#{paginaCentralControlador}")
	private PaginaCentralControlador paginaCentralControlador;

	public void init() {
		this.lista = servico.listarTodos();
		this.alunos = alunoServico.listarTodos();
		this.telaPesquisa();
	}

	public MensalidadeControlador() {
		this.entidade = new Mensalidade();
		this.pesquisa = new Mensalidade();
	}

	public void pesquisar() {
		// this.lista = servico.findByNomeLike(this.pesquisa.getNome());
	}

	public void detalhe(Mensalidade mensalidade) {
		this.entidade = mensalidade;
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);
	}

	public void salvar() {
		this.servico.salvar(this.entidade);
		this.lista = servico.listarTodos();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public void excluir(Mensalidade mensalidade) {
		servico.remover(mensalidade);
		this.lista = servico.listarTodos();
	}

	public void novo() {
		this.entidade = new Mensalidade();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);
	}

	public void telaPeaquisa() {
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public Mensalidade getEntidade() {
		return entidade;
	}

	public void setEntidade(Mensalidade entidade) {
		this.entidade = entidade;
	}

	public Mensalidade getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(Mensalidade pesquisa) {
		this.pesquisa = pesquisa;
	}

	public List<Mensalidade> getLista() {
		return lista;
	}

	public void setLista(List<Mensalidade> lista) {
		this.lista = lista;
	}

	public PaginaCentralControlador getPaginaCentralControlador() {
		return paginaCentralControlador;
	}

	public void setPaginaCentralControlador(
			PaginaCentralControlador paginaCentralControlador) {
		this.paginaCentralControlador = paginaCentralControlador;
	}

	public MensalidadeServico getServico() {
		return servico;
	}

	public void setServico(MensalidadeServico servico) {
		this.servico = servico;
	}

	public void telaPesquisa() {
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public Turno[] getTurnos() {
		return Turno.values();
	}

	public void setTurnos(Turno[] turnos) {
		this.turnos = turnos;
	}

	public AlunoServico getAlunoServico() {
		return alunoServico;
	}

	public void setAlunoServico(AlunoServico alunoServico) {
		this.alunoServico = alunoServico;
	}

	public StatusPagamento[] getStatus() {
		return StatusPagamento.values();
	}

}