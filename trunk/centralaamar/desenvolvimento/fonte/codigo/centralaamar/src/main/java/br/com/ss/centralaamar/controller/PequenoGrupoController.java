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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.ss.centralaamar.exception.ValidationException;
import br.com.ss.centralaamar.model.dao.PequenoGrupoDAO;
import br.com.ss.centralaamar.model.entity.PequenoGrupo;

@Component("pequenoGrupoController")
@Scope("session")
public class PequenoGrupoController {

	private static final Logger logger = LoggerFactory
			.getLogger(PequenoGrupoController.class);

	private PequenoGrupo pequenoGrupo = new PequenoGrupo();
	private List<PequenoGrupo> pequenoGrupos;
	private PequenoGrupo selected;

	@Autowired
	private PequenoGrupoDAO pequenoGrupoDAO;

	public String getMessage() {
		logger.debug("Returning message from pequenoGrupo home bean");
		return "Hello from Spring";
	}

	public PequenoGrupo getPequenoGrupo() {
		return pequenoGrupo;
	}

	public void save() {
		try {
			// PequenoGrupoValidator.validarCampos(pequenoGrupo);

			this.pequenoGrupo.setDescricao(this.pequenoGrupo.getDescricao());

			if (this.pequenoGrupo.getId() == null)
				pequenoGrupoDAO.save(this.pequenoGrupo);
			else
				pequenoGrupoDAO.merge(this.pequenoGrupo);
			this.pequenoGrupo = new PequenoGrupo();
			this.pequenoGrupos = pequenoGrupoDAO.list();
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
		this.pequenoGrupos = pequenoGrupoDAO.list();
		return "listaAllPequenoGrupo";
	}

	public String editar() {
		this.pequenoGrupo = this.selected;
		this.selected = new PequenoGrupo();
		return "edit";
	}

	public void remove() {
		this.pequenoGrupo = this.selected;
		pequenoGrupoDAO.remove(this.pequenoGrupo);
		this.pequenoGrupos = pequenoGrupoDAO.list();

	}

	public List<PequenoGrupo> getPequenoGrupos() {
		if (this.pequenoGrupos == null)
			this.pequenoGrupos = pequenoGrupoDAO.list();
		return this.pequenoGrupos;
	}

	public void setPequenoGrupos(List<PequenoGrupo> pequenoGrupos) {
		this.pequenoGrupos = pequenoGrupos;
	}

	public String clean() {
		this.pequenoGrupo = new PequenoGrupo();
		return "edit";
	}

	public PequenoGrupo getSelected() {
		return selected;
	}

	public void setSelected(PequenoGrupo selected) {
		this.selected = selected;
	}

	public void setPequenoGrupo(PequenoGrupo pequenoGrupo) {
		this.pequenoGrupo = pequenoGrupo;
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
					null, new JRBeanCollectionDataSource(this.pequenoGrupos));

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