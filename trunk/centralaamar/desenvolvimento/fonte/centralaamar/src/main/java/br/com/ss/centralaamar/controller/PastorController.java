package br.com.ss.centralaamar.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.ss.centralaamar.exception.ValidationException;
import br.com.ss.centralaamar.model.dao.PastorDAO;
import br.com.ss.centralaamar.model.entity.Pastor;

@Component("pastorController")
@Scope("session")
public class PastorController {

	private static final Logger logger = LoggerFactory
			.getLogger(PastorController.class);

	private Pastor pastor = new Pastor();
	private List<Pastor> pastores;
	private Pastor selected;

	@Autowired
	private PastorDAO pastorDAO;

	public String getMessage() {
		logger.debug("Returning message from pequenoGrupo home bean");
		return "Hello from Spring";
	}

	public Pastor getPastor() {
		return pastor;
	}

	public void save() {
		try {
			this.pastor.setNome(this.pastor.getNome().toUpperCase());

			if (this.pastor.getId() == null)
				pastorDAO.save(this.pastor);
			else
				pastorDAO.merge(this.pastor);
			this.pastor = new Pastor();
			this.pastores = pastorDAO.list();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso",
							"Dados salvos com sucesso !"));
		} catch (ValidationException e) {
			System.out.println(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Warnning", e
							.getMessage()));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e
							.getMessage()));
		}

	}

	public String listAll() {
		this.pastores = pastorDAO.list();
		return "listaAllPastor";
	}

	public String editar() {
		this.pastor = this.selected;
		this.selected = new Pastor();
		return "/pages/pastor/pastor.xhtml";
	}

	public void remove() {

		this.pastor = this.selected;
		try {
			pastorDAO.remove(this.pastor);
			this.pastores = pastorDAO.list();
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

	public List<Pastor> getPastores() {
		if (this.pastores == null)
			this.pastores = pastorDAO.list();
		return this.pastores;
	}

	public void setPastores(List<Pastor> pastores) {
		this.pastores = pastores;
	}

	public String clean() {
		this.pastor = new Pastor();
		return "/pages/pastor/pastor.xhtml";
	}

	public Pastor getSelected() {
		return selected;
	}

	public void setSelected(Pastor selected) {
		this.selected = selected;
	}

	public void setPastor(Pastor pastor) {
		this.pastor = pastor;
	}

	public void print() {
		ByteArrayOutputStream byteOutPutStream = new ByteArrayOutputStream();

		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();

			HttpServletResponse response = (HttpServletResponse) facesContext
					.getExternalContext().getResponse();

			ServletContext servletContext = (ServletContext) facesContext
					.getExternalContext().getContext();

			// String pathJasper = servletContext
			// .getRealPath("/WEB-INF/jasper/pequenoGrup.jasper");

			String pathJasper = "C:\\jasper\\pequenoGrup.jasper";

			JasperPrint preencher = JasperFillManager.fillReport(pathJasper,
					null, new JRBeanCollectionDataSource(this.pastores));

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

}