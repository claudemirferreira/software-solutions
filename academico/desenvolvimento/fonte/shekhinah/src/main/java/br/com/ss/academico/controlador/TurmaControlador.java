package br.com.ss.academico.controlador;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import br.com.ss.academico.dominio.Aluno;
import br.com.ss.academico.dominio.Curso;
import br.com.ss.academico.dominio.Turma;
import br.com.ss.academico.enumerated.Turno;
import br.com.ss.academico.servico.AlunoServico;
import br.com.ss.academico.servico.CursoServico;
import br.com.ss.academico.servico.TurmaServico;
import br.com.ss.core.seguranca.servico.IService;
import br.com.ss.core.web.controlador.ControladorGenerico;
import br.com.ss.core.web.utils.Util;

@ManagedBean
@SessionScoped
public class TurmaControlador extends ControladorGenerico<Turma> {

	private static final long serialVersionUID = -6832271293709421841L;

	private List<Aluno> listaAluno;
	
	private List<Curso> cursos;
	
	private List<SelectItem> turnos;
	
	private List<SelectItem> anos;

	@ManagedProperty(value = "#{turmaServicoImpl}")
	private TurmaServico servico;
	
	@ManagedProperty(value = "#{alunoServicoImpl}")
	private AlunoServico alunoServico;
	
	@ManagedProperty(value = "#{cursoServicoImpl}")
	private CursoServico cursoServico;

	private Turma turmaSelecaoModal;


	@Override
	protected void init() {
		this.anos = new ArrayList<SelectItem>();
		for (Integer ano : Util.pegarAnos() ) {
			anos.add(new SelectItem(ano, ano.toString()));
		}
		turnos = new ArrayList<SelectItem>();
		for (Turno t : Turno.values()) {
			turnos.add(new SelectItem(t, t.getDescricao()));
		}
		this.cursos = cursoServico.listarTodos();
	}
	
	@Override
	protected String getNomeRelatorio() {
		// FIXME #Peninha relatorio
		return null;
	}

	@Override
	protected IService<Turma, Long> getService() {
		return servico;
	}

	
	/**
	 * @param turma
	 */
	public void listarAlunos(Turma turma){
		this.turmaSelecaoModal = turma;
		this.listaAluno = alunoServico.findByTurma(turma);
	}

	
	public void closeModal() {
		this.turmaSelecaoModal = null;
	}
	
	
	/* ---------- Gets/Sets ------------- */

	public Turma getTurmaSelecaoModal() {
		return turmaSelecaoModal;
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

	public CursoServico getCursoServico() {
		return cursoServico;
	}

	public void setCursoServico(CursoServico cursoServico) {
		this.cursoServico = cursoServico;
	}

	public List<SelectItem> getAnos() {
		return anos;
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

	public List<SelectItem> getTurnos() {
		return turnos;
	}
}