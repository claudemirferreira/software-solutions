package br.com.ss.centralaamar.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import lombok.Getter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ss.centralaamar.exception.ValidationException;
import br.com.ss.centralaamar.model.dao.IPastorDAO;
import br.com.ss.centralaamar.model.dao.IPequenoGrupoDAO;
import br.com.ss.centralaamar.model.dao.IProfissaoDAO;
import br.com.ss.centralaamar.model.entity.Membro;
import br.com.ss.centralaamar.model.entity.Pastor;
import br.com.ss.centralaamar.model.entity.PequenoGrupo;
import br.com.ss.centralaamar.model.entity.Profissao;
import br.com.ss.centralaamar.service.IMembroService;
import br.com.ss.centralaamar.service.IService;

@Controller
@Named
@Scope("session")
public class MembroController extends GenericBean<Membro> {

	@Autowired
	private IMembroService service;

	@Autowired
	private IPequenoGrupoDAO pequenoGrupoDAO;

	@Autowired
	private IProfissaoDAO profissaoDAO;

	@Autowired
	private IPastorDAO pastorDAO;
	
	@Getter
	private List<PequenoGrupo> pequenoGrupoList;

	@Getter
	private List<Profissao> profissaoList;

	@Getter
	private List<Pastor> pastorList;
	
	
	@Override
	public void init() {
		super.init();
		
		this.pequenoGrupoList = pequenoGrupoDAO.listAll();
		this.profissaoList = profissaoDAO.listAll();
		this.pastorList = pastorDAO.listAll();
	}
	

	@Override
	protected IService<Membro> getService() {
		return service;
	}

	public String save() {
		try {
			this.entity.setNome(this.entity.getNome().toUpperCase());
			
			// TODO usar script para Uppercase nos inputs
			
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