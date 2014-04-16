package br.com.ss.academico.controlador;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.ss.academico.componentes.Util;
import br.com.ss.academico.dominio.Aluno;
import br.com.ss.academico.dominio.Curso;
import br.com.ss.academico.dominio.Turma;
import br.com.ss.academico.enumerated.Turno;
import br.com.ss.academico.servico.AlunoServico;
import br.com.ss.academico.servico.CursoServico;
import br.com.ss.academico.servico.TurmaServico;

@ManagedBean
@SessionScoped
public class TurmaControlador implements Serializable {

	private static final long serialVersionUID = -6832271293709421841L;

	private Turma entidade;

	private Turma pesquisa;

	private List<Turma> lista;
	
	private List<Aluno> listaAluno;
	
	private List<Curso> cursos;
	
	private Turno[] turnos;
	
	private List<Integer> anos;

	private final String TELA_CADASTRO = "paginas/turma/cadastro.xhtml";
	private final String TELA_PESQUISA = "paginas/turma/pesquisa.xhtml";

	@ManagedProperty(value = "#{turmaServicoImpl}")
	private TurmaServico servico;
	
	@ManagedProperty(value = "#{alunoServicoImpl}")
	private AlunoServico alunoServico;
	
	@ManagedProperty(value = "#{cursoServicoImpl}")
	private CursoServico cursoServico;

	@ManagedProperty(value = "#{paginaCentralControlador}")
	private PaginaCentralControlador paginaCentralControlador;

	public void init() {
		this.lista = servico.listarTodos();
		telaPesquisa();
	}

	public TurmaControlador() {
		this.entidade = new Turma();
		this.pesquisa = new Turma();
		this.anos = Util.pegarAnos();
	}

	public void pesquisar() {
		 this.lista = servico.findByAno(this.pesquisa.getAno());
	}

	public void detalhe(Turma turma) {
		this.entidade = turma;
		this.cursos = cursoServico.listarTodos();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);
	}

	public void salvar() {
		this.servico.salvar(this.entidade);
		this.lista = servico.listarTodos();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public void excluir(Turma turma) {
		servico.remover(turma);
		this.lista = servico.listarTodos();
	}

	public void novo() {
		this.entidade = new Turma();
		this.cursos = cursoServico.listarTodos();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);
	}
	
	public void listarAlunos(Turma turma){
		this.listaAluno = alunoServico.findByTurma(turma);
		
	}

	public void telaPeaquisa() {
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public Turma getEntidade() {
		return entidade;
	}

	public void setEntidade(Turma entidade) {
		this.entidade = entidade;
	}

	public Turma getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(Turma pesquisa) {
		this.pesquisa = pesquisa;
	}

	public List<Turma> getLista() {
		return lista;
	}

	public void setLista(List<Turma> lista) {
		this.lista = lista;
	}

	public PaginaCentralControlador getPaginaCentralControlador() {
		return paginaCentralControlador;
	}

	public void setPaginaCentralControlador(
			PaginaCentralControlador paginaCentralControlador) {
		this.paginaCentralControlador = paginaCentralControlador;
	}

	public TurmaServico getServico() {
		return servico;
	}

	public void setServico(TurmaServico servico) {
		this.servico = servico;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public CursoServico getCursoServico() {
		return cursoServico;
	}

	public void setCursoServico(CursoServico cursoServico) {
		this.cursoServico = cursoServico;
	}

	public void telaPesquisa() {
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public Turno[] getTurnos() {
		return Turno.values();
	}

	public List<Integer> getAnos() {
		return anos;
	}

	public void setAnos(List<Integer> anos) {
		this.anos = anos;
	}

	public List<Aluno> getListaAluno() {
		return listaAluno;
	}

	public void setListaAluno(List<Aluno> listaAluno) {
		this.listaAluno = listaAluno;
	}

	public AlunoServico getAlunoServico() {
		return alunoServico;
	}

	public void setAlunoServico(AlunoServico alunoServico) {
		this.alunoServico = alunoServico;
	}

}