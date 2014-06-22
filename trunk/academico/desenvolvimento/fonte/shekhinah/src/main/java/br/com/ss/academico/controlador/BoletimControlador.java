package br.com.ss.academico.controlador;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import br.com.ss.academico.dominio.Boletim;
import br.com.ss.academico.dominio.Disciplina;
import br.com.ss.academico.dominio.Matricula;
import br.com.ss.academico.dominio.Turma;
import br.com.ss.academico.dominio.TurmaDisciplina;
import br.com.ss.academico.servico.BoletimServico;
import br.com.ss.academico.servico.MatriculaServico;
import br.com.ss.academico.servico.TurmaServico;
import br.com.ss.core.seguranca.servico.IService;
import br.com.ss.core.web.controlador.ControladorGenerico;
import br.com.ss.core.web.utils.Util;

@ManagedBean
@SessionScoped
public class BoletimControlador extends ControladorGenerico<Boletim> {

	private static final long serialVersionUID = -6832271293709421841L;

	@ManagedProperty(value = "#{boletimServicoImpl}")
	private BoletimServico servico;

	@ManagedProperty(value = "#{turmaServicoImpl}")
	private TurmaServico turmaServico;

	@ManagedProperty(value = "#{matriculaServicoImpl}")
	private MatriculaServico matriculaServico;

	private String nomeRelatorio = "boletim.jasper";

	private Turma turma;

	private List<Turma> turmas;

	private List<Matricula> matriculas;

	private List<Matricula> filteredTurmas;
	
	private List<Double> notas;

	@PostConstruct
	@Override
	public void setup() {
		super.setup();
		this.turmas = turmaServico.listarTodos();
	}

	@Override
	protected String getNomeRelatorio() {
		// FIXME #Peninha ver relatorio
		return this.nomeRelatorio;
	}

	@Override
	public void pesquisar() {
		if (this.turma != null) {
			this.matriculas = matriculaServico.listaMatriculasPorTurma(this.turma);
		
			// fetch de disciplina 
			for ( Matricula mat : matriculas ) {
				for ( TurmaDisciplina td : mat.getTurma().getTurmaDisciplina() ) {
					td.getDisciplina();
				}
			}
		}
		
	}

	@Override
	protected IService<Boletim, Long> getService() {
		return servico;
	}

	public BoletimServico getServico() {
		return servico;
	}

	public void setServico(BoletimServico servico) {
		this.servico = servico;
	}

	public void setNomeRelatorio(String nomeRelatorio) {
		this.nomeRelatorio = nomeRelatorio;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public TurmaServico getTurmaServico() {
		return turmaServico;
	}

	public void setTurmaServico(TurmaServico turmaServico) {
		this.turmaServico = turmaServico;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public List<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public MatriculaServico getMatriculaServico() {
		return matriculaServico;
	}

	public void setMatriculaServico(MatriculaServico matriculaServico) {
		this.matriculaServico = matriculaServico;
	}

	public List<Matricula> getFilteredTurmas() {
		return filteredTurmas;
	}

	public void setFilteredTurmas(List<Matricula> filteredTurmas) {
		this.filteredTurmas = filteredTurmas;
	}

	public String pesquisarBoletim(Matricula matricula) {

		this.listaPesquisa = this.servico.pesquisarBoletim(matricula);

//		if (this.listaPesquisa.size() == 0) {
			
			// FIXME... nao deve criar boletim neste momento - deve criar na matricula do aluno

//			this.servico.gerarBoletim(matricula);
//			this.listaPesquisa = this.servico.pesquisarBoletim(matricula);
			

//		}
		
		for (Boletim bol : listaPesquisa) {
			bol.getDisciplina();
		}

		return "boletim";
	}

	public void onEdit(RowEditEvent event) {
		this.entidade = (Boletim) event.getObject();
		
		this.servico.salvar(this.entidade);
	}

	public void onCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Item Cancelled");
		FacesContext.getCurrentInstance().addMessage(null, msg);
//		orderList.remove((Boletim) event.getObject());
	}

	public List<Double> getNotas() {
		return Util.pegarNotas();
	}

}