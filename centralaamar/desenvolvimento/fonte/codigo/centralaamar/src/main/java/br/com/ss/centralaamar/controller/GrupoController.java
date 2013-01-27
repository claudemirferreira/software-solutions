package br.com.ss.centralaamar.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.ss.centralaamar.exception.ValidationException;
import br.com.ss.centralaamar.model.dao.GrupoDAO;
import br.com.ss.centralaamar.model.entity.Grupo;

@Component("grupoController")
@Scope("session")
public class GrupoController {

	private static final Logger logger = LoggerFactory
			.getLogger(GrupoController.class);

	private Grupo grupo = new Grupo();
	private List<Grupo> grupos;
	private Grupo selected;

	@Autowired
	private GrupoDAO grupoDAO;

	public String getMessage() {
		logger.debug("Returning message from pequenoGrupo home bean");
		return "Hello from Spring";
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void save() {
		try {
			this.grupo.setNome(this.grupo.getNome().toUpperCase());

			if (this.grupo.getId() == null)
				grupoDAO.save(this.grupo);
			else
				grupoDAO.merge(this.grupo);
			this.grupo = new Grupo();
			this.grupos = grupoDAO.list();
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
		this.grupos = grupoDAO.list();
		return "listaAllGrupo";
	}

	public String editar() {
		this.grupo = this.selected;
		this.selected = new Grupo();
		return "/pages/grupo/grupo.xhtml";
	}

	public void remove() {

		this.grupo = this.selected;
		try {
			grupoDAO.remove(this.grupo);
			this.grupos = grupoDAO.list();
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

	public List<Grupo> getGrupos() {
		if (this.grupos == null)
			this.grupos = grupoDAO.list();
		return this.grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public String clean() {
		this.grupo = new Grupo();
		return "/pages/grupo/grupo.xhtml";
	}

	public Grupo getSelected() {
		return selected;
	}

	public void setSelected(Grupo selected) {
		this.selected = selected;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public void print() {
		ByteArrayOutputStream byteOutPutStream = new ByteArrayOutputStream();

		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();

			HttpServletResponse response = (HttpServletResponse) facesContext
					.getExternalContext().getResponse();

			String pathJasper = "C:\\jasper\\pequenoGrup.jasper";

			JasperPrint preencher = JasperFillManager.fillReport(pathJasper,
					null, new JRBeanCollectionDataSource(this.grupos));

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