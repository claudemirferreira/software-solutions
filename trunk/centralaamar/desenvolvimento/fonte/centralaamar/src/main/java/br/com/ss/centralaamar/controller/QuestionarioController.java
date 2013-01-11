package br.com.ss.centralaamar.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.ss.centralaamar.exception.ValidationException;
import br.com.ss.centralaamar.model.dao.MembroDAO;
import br.com.ss.centralaamar.model.dao.PequenoGrupoDAO;
import br.com.ss.centralaamar.model.dao.QuestionarioDAO;
import br.com.ss.centralaamar.model.dao.SabadoDAO;
import br.com.ss.centralaamar.model.entity.Questionario;
import br.com.ss.centralaamar.model.entity.Membro;
import br.com.ss.centralaamar.model.entity.PequenoGrupo;
import br.com.ss.centralaamar.model.entity.Sabado;

@Component("questionarioController")
@Scope("session")
public class QuestionarioController {

	private static final Logger logger = LoggerFactory
			.getLogger(QuestionarioController.class);

	private Questionario questionario = new Questionario();

	private Questionario selected;
	private Sabado sabado;
	private PequenoGrupo pequenoGrupo = new PequenoGrupo();
	private List<Sabado> sabados;
	private List<Questionario> questionarios = new ArrayList<Questionario>();
	private List<PequenoGrupo> pequenoGrupos;
	private List<Membro> membros;

	@Autowired
	private QuestionarioDAO questionarioDAO;

	@Autowired
	private SabadoDAO sabadoDAO;

	@Autowired
	private MembroDAO membroDAO;

	@Autowired
	private PequenoGrupoDAO pequenoGrupoDAO;

	public String getMessage() {
		logger.debug("Returning message from pequenoGrupo home bean");
		return "Hello from Spring";
	}

	public Questionario getQuestionario() {
		return questionario;
	}

//	public void save() {
//		try {
//
//			if (this.questionario.getId() == null)
////				questionarioDAO.save(this.questionario);
//			else
//				questionarioDAO.merge(this.questionario);
//			this.questionario = new Questionario();
////			this.questionarios = questionarioDAO.list();
//			FacesContext.getCurrentInstance().addMessage(
//					null,
//					new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso",
//							"Dados salvos com sucesso !"));
//		} catch (ValidationException e) {
//			System.out.println(e.getMessage());
//			FacesContext.getCurrentInstance().addMessage(
//					null,
//					new FacesMessage(FacesMessage.SEVERITY_WARN, "Warnning", e
//							.getMessage()));
//		} catch (Exception e) {
//			FacesContext.getCurrentInstance().addMessage(
//					null,
//					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e
//							.getMessage()));
//		}
//
//	}

	public String listAll() {
//		this.questionarios = questionarioDAO.list();
		return "listaAllQuestionario";
	}

	public String editar() {
		this.questionario = this.selected;
		this.selected = new Questionario();
		return "/pages/questionario/questionario.xhtml";
	}

	public String find1() {
		this.membros = (List<Membro>) membroDAO.listPorGrupo(this.pequenoGrupo, sabado);

		return "/pages/questionario/questionario.xhtml";
	}

	public String find() {
		Questionario questionario = new Questionario();
		sabado = new Sabado();
		sabado.setId(1);
		this.membros = (List<Membro>) membroDAO.listPorGrupo(this.pequenoGrupo, sabado);
		for (Iterator<Membro> iterator = membros.iterator(); iterator.hasNext();) {
			Membro membro = (Membro) iterator.next();
			questionario = new Questionario();
			questionario.setMembro(membro);
			questionario.setSabado(sabado);
			questionario.setPequenoGrupo(this.pequenoGrupo);

//			questionarioDAO.save(questionario);

		}
		
		this.questionarios=questionarioDAO.listPorGrupo(questionario);

		return "/pages/questionario/questionario.xhtml";
	}
	
	public void t(){
		System.out.println("teste");
	}

	public List<Membro> getMembros() {
		return membros;
	}

	public void setMembros(List<Membro> membros) {
		this.membros = membros;
	}

	public void remove() {
		this.questionario = this.selected;
		try {
			questionarioDAO.remove(this.questionario);
//			this.questionarios = questionarioDAO.list();
		} catch (ConstraintViolationException e) {
			e.printStackTrace();

			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Erro",
									"O registro não pode ser excluido, Favor entre em contato com o suporte"));
		}

	}
	
	public void update() {
		this.questionario = this.selected;
		try {
			questionarioDAO.merge(this.questionario);
//			this.questionarios = questionarioDAO.list();
		} catch (ConstraintViolationException e) {
			e.printStackTrace();

			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Erro",
									"O registro não pode ser excluido, Favor entre em contato com o suporte"));
		}

	}

	public List<Questionario> getQuestionarios() {
		//if (this.questionarios == null)
			//this.questionarios = questionarioDAO.list();
		return this.questionarios;
	}

	public void setQuestionarios(List<Questionario> questionarios) {
		this.questionarios = questionarios;
	}

	public String clean() {
		this.questionario = new Questionario();
		return "/pages/questionario/questionario.xhtml";
	}

	public Questionario getSelected() {
		return selected;
	}

	public void setSelected(Questionario selected) {
		this.selected = selected;
	}

	public void setQuestionario(Questionario questionario) {
		this.questionario = questionario;
	}

	public void print() {
		ByteArrayOutputStream byteOutPutStream = new ByteArrayOutputStream();

		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();

			HttpServletResponse response = (HttpServletResponse) facesContext
					.getExternalContext().getResponse();

			String pathJasper = "C:\\jasper\\pequenoGrup.jasper";

			JasperPrint preencher = JasperFillManager.fillReport(pathJasper,
					null, new JRBeanCollectionDataSource(this.questionarios));

			JasperExportManager.exportReportToPdfStream(preencher,
					byteOutPutStream);

			System.out.println("Size of OutPut: " + byteOutPutStream.size());
			response.setContentLength(byteOutPutStream.size());
			response.setContentType("application/pdf");

			ServletOutputStream servletOutPutStream = response
					.getOutputStream();
			servletOutPutStream.write(byteOutPutStream.toByteArray(), 0,
					byteOutPutStream.size());

			servletOutPutStream.flush();
			servletOutPutStream.close();

			FacesContext.getCurrentInstance().responseComplete();

		} catch (JRException jrex) {
			jrex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Sabado getSabado() {
		return sabado;
	}

	public void setSabado(Sabado sabado) {
		this.sabado = sabado;
	}

	public List<Sabado> getSabados() {
		return sabados;
	}

	public void setSabados(List<Sabado> sabados) {
		this.sabados = sabados;
	}

	public PequenoGrupo getPequenoGrupo() {
		return pequenoGrupo;
	}

	public void setPequenoGrupo(PequenoGrupo pequenoGrupo) {
		this.pequenoGrupo = pequenoGrupo;
	}

	public List<PequenoGrupo> getPequenoGrupos() {
		this.pequenoGrupos =  pequenoGrupoDAO.list();
		return this.pequenoGrupos;
	}

	public void setPequenoGrupos(List<PequenoGrupo> pequenoGrupos) {
		this.pequenoGrupos = pequenoGrupos;
	}
	
	public void onRowSelect(SelectEvent event) {  
        
        this.questionario = (Questionario) event.getObject();
        System.out.println("id === " + this.questionario.getMembro().getId());
  
    }

}