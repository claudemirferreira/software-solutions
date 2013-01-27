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
import br.com.ss.centralaamar.model.dao.SabadoDAO;
import br.com.ss.centralaamar.model.entity.Sabado;

@Component("sabadoController")
@Scope("session")
public class SabadoController {

	private static final Logger logger = LoggerFactory
			.getLogger(SabadoController.class);

	private Sabado sabado = new Sabado();
	private List<Sabado> sabados;
	private Sabado selected;

	@Autowired
	private SabadoDAO sabadoDAO;

	public String getMessage() {
		logger.debug("Returning message from pequenoGrupo home bean");
		return "Hello from Spring";
	}

	public Sabado getSabado() {
		return sabado;
	}

	public void save() {
		try {

			if (this.sabado.getId() == null)
				sabadoDAO.save(this.sabado);
			else
				sabadoDAO.merge(this.sabado);
			this.sabado = new Sabado();
			this.sabados = sabadoDAO.list();
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
		this.sabados = sabadoDAO.list();
		return "listaAllSabado";
	}

	public String editar() {
		this.sabado = this.selected;
		this.selected = new Sabado();
		return "/pages/sabado/sabado.xhtml";
	}

	public void remove() {
		this.sabado = this.selected;
		try {
			sabadoDAO.remove(this.sabado);
			this.sabados = sabadoDAO.list();
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

	public List<Sabado> getSabados() {
		if (this.sabados == null)
			this.sabados = sabadoDAO.list();
		return this.sabados;
	}

	public void setSabados(List<Sabado> sabados) {
		this.sabados = sabados;
	}

	public String clean() {
		this.sabado = new Sabado();
		return "/pages/sabado/sabado.xhtml";
	}

	public Sabado getSelected() {
		return selected;
	}

	public void setSelected(Sabado selected) {
		this.selected = selected;
	}

	public void setSabado(Sabado sabado) {
		this.sabado = sabado;
	}

	public void print() {
		ByteArrayOutputStream byteOutPutStream = new ByteArrayOutputStream();

		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();

			HttpServletResponse response = (HttpServletResponse) facesContext
					.getExternalContext().getResponse();

			String pathJasper = "C:\\jasper\\pequenoGrup.jasper";

			JasperPrint preencher = JasperFillManager.fillReport(pathJasper,
					null, new JRBeanCollectionDataSource(this.sabados));

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