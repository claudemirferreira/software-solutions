package br.com.ss.academico.controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import br.com.ss.academico.dominio.Curso;
import br.com.ss.academico.dominio.CursoDisciplina;
import br.com.ss.academico.dominio.Disciplina;
import br.com.ss.academico.servico.CursoDisciplinaServico;

@ManagedBean
@SessionScoped
public class CursoDisciplinaControlador {

	private static final String MSG_ADICIONAR = "Disciplina adicionado com sucesso.";
	private static final String MSG_REMOVER = "Disciplina removido com sucesso.";

	private static final String MSG_ERRO = "Ocorreu um erro ao executar a operação!";

	@ManagedProperty(value = "#{cursoDisciplinaServicoImpl}")
	private CursoDisciplinaServico cursoDisciplinaServico;

	private DualListModel<CursoDisciplina> dualListDisciplina;

	private List<CursoDisciplina> listaCursoDisciplinaNotInCurso;

	@PostConstruct
	public void init() {
		dualListDisciplina = new DualListModel<CursoDisciplina>();
	}

	public void showModalDisciplina(Curso curso) {
		listaCursoDisciplinaNotInCurso = new ArrayList<CursoDisciplina>();
		List<Disciplina> listaDisciplinaNotInCurso = cursoDisciplinaServico
				.listaDisciplinaNotInCurso(curso.getId());

		for (Disciplina disciplina : listaDisciplinaNotInCurso) {
			CursoDisciplina cursoDisciplina = createCursoDisciplina(disciplina,
					curso);
			listaCursoDisciplinaNotInCurso.add(cursoDisciplina);
		}
		// faz o fetch de CursoDisciplina
		List<CursoDisciplina> usuPerfis = cursoDisciplinaServico
				.findByCurso(curso);
		curso.setCursoDisciplina(usuPerfis);
		dualListDisciplina = new DualListModel<CursoDisciplina>(
				listaCursoDisciplinaNotInCurso, curso.getCursoDisciplina());
	}

	private CursoDisciplina createCursoDisciplina(Disciplina disciplina,
			Curso curso) {
		CursoDisciplina cursoDisciplina = new CursoDisciplina();
		cursoDisciplina.setData(new Date());
		cursoDisciplina.setDisciplina(disciplina);
		cursoDisciplina.setCurso(curso); // TODO validar relacionamento qdo o
											// item for excluido/adicionado..
		return cursoDisciplina;
	}

	public void onTransfer(TransferEvent event) {

		CursoDisciplina cursoDisciplina = (CursoDisciplina) event.getItems()
				.get(0);

		salvarCurso(cursoDisciplina, event.isAdd());

	}

	private void salvarCurso(CursoDisciplina cursoDisciplina, boolean add) {

		try {
			String msg;

			if (add) {
				cursoDisciplinaServico.salvar(cursoDisciplina);
				msg = MSG_ADICIONAR;
			} else {
				cursoDisciplinaServico.remover(cursoDisciplina);
				msg = MSG_REMOVER;
			}

			showMessage(msg, FacesMessage.SEVERITY_INFO);

		} catch (Exception e) {
			e.printStackTrace();
			showMessage(MSG_ERRO, FacesMessage.SEVERITY_ERROR);
		}

	}

	private void showMessage(String msg, Severity severityInfo) {
		FacesMessage facesMessage = new FacesMessage();
		facesMessage.setSeverity(severityInfo);
		facesMessage.setSummary(msg);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}

	/* ---------- Gets/Sets --------------- */

	public CursoDisciplinaServico getCursoDisciplinaServico() {
		return cursoDisciplinaServico;
	}

	public void setCursoDisciplinaServico(
			CursoDisciplinaServico cursoDisciplinaServico) {
		this.cursoDisciplinaServico = cursoDisciplinaServico;
	}

	public DualListModel<CursoDisciplina> getDualListDisciplina() {
		return dualListDisciplina;
	}

	public void setDualListDisciplina(
			DualListModel<CursoDisciplina> dualListDisciplina) {
		this.dualListDisciplina = dualListDisciplina;
	}
}
