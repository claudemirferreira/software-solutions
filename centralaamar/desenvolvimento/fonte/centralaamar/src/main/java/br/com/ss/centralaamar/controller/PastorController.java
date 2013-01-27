package br.com.ss.centralaamar.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
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
import org.springframework.stereotype.Controller;

import br.com.ss.centralaamar.exception.ValidationException;
import br.com.ss.centralaamar.model.dao.IAbstractDAO;
import br.com.ss.centralaamar.model.dao.IPastorDAO;
import br.com.ss.centralaamar.model.entity.Pastor;
import br.com.ss.centralaamar.service.IService;

@Controller
@Named
@Scope("session")
public class PastorController extends GenericBean<Pastor> {

	private static final Logger logger = LoggerFactory.getLogger(PastorController.class);

	@Autowired
	private IPastorDAO dao;

	@Autowired
	private IService<Pastor> service;

	@Override
	public void init() {
		this.search();
	}

	@Override
	protected void initEntity() {
		this.entity = new Pastor();
		this.search = new Pastor();
	}

	@Override
	protected IAbstractDAO<Pastor> getDAO() {
		return dao;
	}

	@Override
	protected IService<Pastor> getService() {
		return service;
	}

	public String getMessage() {
		logger.debug("Returning message from pequenoGrupo home bean");
		return "Hello from Spring";
	}


	public String save() {
		try {
			this.entity.setNome(this.entity.getNome().toUpperCase());
			return super.save();
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
		
		return null;

	}

	public void print() {
		ByteArrayOutputStream byteOutPutStream = new ByteArrayOutputStream();

		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();

			HttpServletResponse response = (HttpServletResponse) facesContext
					.getExternalContext().getResponse();

			ServletContext servletContext = (ServletContext) facesContext
					.getExternalContext().getContext();

			String pathJasper = "C:\\jasper\\pequenoGrup.jasper";

			JasperPrint preencher = JasperFillManager.fillReport(pathJasper,
					null, new JRBeanCollectionDataSource(this.resultList));

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