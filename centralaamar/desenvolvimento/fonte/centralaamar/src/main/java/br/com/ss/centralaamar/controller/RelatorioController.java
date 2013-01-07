package br.com.ss.centralaamar.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import br.com.ss.centralaamar.component.ConnectionFactory;
import br.com.ss.centralaamar.component.Relatorio;
import br.com.ss.centralaamar.component.Sexo;
import br.com.ss.centralaamar.model.dao.EleitorDao;
import br.com.ss.centralaamar.model.dao.MembroDAO;
import br.com.ss.centralaamar.model.dao.ResponsavelDao;
import br.com.ss.centralaamar.model.entity.Eleitor;
import br.com.ss.centralaamar.model.entity.Membro;
import br.com.ss.centralaamar.model.entity.Responsavel;

@Component("relatorioController")
@Scope("session")
public class RelatorioController {

	private Responsavel responsavel = new Responsavel();
	private List<Eleitor> eleitores;
	private List<Responsavel> responsaveis;
	private Eleitor eleitor = new Eleitor();
	private List<Membro> membros = new ArrayList<Membro>();
	private Relatorio relatorio = new Relatorio();
	
	private Sexo sexo = new Sexo("M", "MASCULINO");
	private List<Sexo> sexos = new ArrayList<Sexo>();
	

	public Eleitor getEleitor() {
		return eleitor;
	}

	public void setEleitor(Eleitor eleitor) {
		this.eleitor = eleitor;
	}

	@Autowired
	private EleitorDao eleitorDao;

	@Autowired
	private MembroDAO membroDAO;

	@Autowired
	private ResponsavelDao responsavelDao;

	private static final Logger logger = LoggerFactory
			.getLogger(RelatorioController.class);

	public void printGrafico() {
		ByteArrayOutputStream byteOutPutStream = new ByteArrayOutputStream();

		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();

			HttpServletResponse response = (HttpServletResponse) facesContext
					.getExternalContext().getResponse();

			ServletContext servletContext = (ServletContext) facesContext
					.getExternalContext().getContext();

			// String pathJasper = servletContext
			// .getRealPath("/WEB-INF/relatorio/graficoResponsavel.jasper");

			String pathJasper = "C:\\jasper\\relatorioMebroPorGrupo.jasper";

			JasperPrint printReport = JasperFillManager.fillReport(pathJasper,
					null, ConnectionFactory.getConnection());

			JasperExportManager.exportReportToPdfStream(printReport,
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

	public void printPais() {
		ByteArrayOutputStream byteOutPutStream = new ByteArrayOutputStream();
		Map parametros = new HashMap();
		

		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();

			HttpServletResponse response = (HttpServletResponse) facesContext
					.getExternalContext().getResponse();

			ServletContext servletContext = (ServletContext) facesContext
					.getExternalContext().getContext();

			String pathJasper = "C:\\jasper\\relatorioAniversariante.jasper";
			
			this.membros = membroDAO.listPais(this.sexo.getCodigo());
			
			String TITULO = "RELATÓRIO DE MEMBROS ANIVERSARIANTES";
			if (sexo.getCodigo() == "M")
				parametros.put("titulo", "RELATÓRIO DE MÃES"); 
			else
				parametros.put("titulo", "RELATÓRIO DE PAIS"); 

			JasperPrint preencher = JasperFillManager.fillReport(pathJasper,
					parametros, new JRBeanCollectionDataSource(this.membros));
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
	
	
	public void printAniversariante() {
		ByteArrayOutputStream byteOutPutStream = new ByteArrayOutputStream();
		Map parametros = new HashMap();
		String TITULO = "RELATÓRIO DE MEMBROS ANIVERSARIANTES";

		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();

			HttpServletResponse response = (HttpServletResponse) facesContext
					.getExternalContext().getResponse();

			ServletContext servletContext = (ServletContext) facesContext
					.getExternalContext().getContext();

//			String pathJasper = servletContext
//					.getRealPath("/WEB-INF/relatorio/graficoResponsavel.jasper");
			
			String pathJasper = "C:\\jasper\\relatorioAniversariante.jasper";
			
			parametros.put("dataInicial", "2012-11-1"); 
			parametros.put("dataFinal", "2012-12-31"); 
			parametros.put("ano", "'2012'"); 
			parametros.put("titulo", TITULO); 
			this.membros = membroDAO.listAniversariantes(
					relatorio.converterDataToString(this.relatorio.getDataInicial()),
							relatorio.converterDataToString(this.relatorio.getDataFinal()),
									relatorio.converterAnoToString(this.relatorio.getDataInicial()));

			JasperPrint preencher = JasperFillManager.fillReport(pathJasper,
					null, new JRBeanCollectionDataSource(this.membros));
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

	public void print() {
		ByteArrayOutputStream byteOutPutStream = new ByteArrayOutputStream();
		Map parametros = new HashMap();

		try {

			this.responsavel = responsavelDao.find(this.responsavel.getId());

			this.eleitores = eleitorDao.listPorResposavel(this.responsavel
					.getId());

			parametros.put("responsavel", this.responsavel);
			FacesContext facesContext = FacesContext.getCurrentInstance();

			HttpServletResponse response = (HttpServletResponse) facesContext
					.getExternalContext().getResponse();

			ServletContext servletContext = (ServletContext) facesContext
					.getExternalContext().getContext();

			String pathJasper = servletContext
					.getRealPath("/WEB-INF/relatorio/relatorioResponsavelEleitores.jasper");

			JasperPrint printReport = JasperFillManager.fillReport(pathJasper,
					parametros, new JRBeanCollectionDataSource(this.eleitores));

			JasperExportManager.exportReportToPdfStream(printReport,
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

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public List<Eleitor> getEleitores() {
		return eleitores;
	}

	public void setEleitores(List<Eleitor> eleitores) {
		this.eleitores = eleitores;
	}

	public List<Responsavel> getResponsaveis() {
		if (responsaveis == null)
			responsaveis = responsavelDao.list();
		return responsaveis;
	}

	public void setResponsaveis(List<Responsavel> responsaveis) {
		this.responsaveis = responsaveis;
	}

	public Relatorio getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(Relatorio relatorio) {
		this.relatorio = relatorio;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public List<Sexo> getSexos() {
		return sexos;
	}

	public void setSexos(List<Sexo> sexos) {
		this.sexos = Sexo.list();
	}

}