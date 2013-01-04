package br.com.ss.centralaamar.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
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

import br.com.ss.centralaamar.component.Secao;
import br.com.ss.centralaamar.exception.ResponsavelValidator;
import br.com.ss.centralaamar.exception.ValidationException;
import br.com.ss.centralaamar.model.dao.EleitorDao;
import br.com.ss.centralaamar.model.dao.LocalDao;
import br.com.ss.centralaamar.model.dao.ResponsavelDao;
import br.com.ss.centralaamar.model.entity.Eleitor;
import br.com.ss.centralaamar.model.entity.Local;
import br.com.ss.centralaamar.model.entity.Responsavel;

@Component("eleitorController")
@Scope("session")
public class EleitorController {

	private static final Logger logger = LoggerFactory
			.getLogger(EleitorController.class);

	private Eleitor eleitor = new Eleitor();
	private Responsavel responsavel;
	private Local local;
	private Eleitor selected;
	private List<Eleitor> eleitores;
	private List<Responsavel> responsaveis;
	private List<Local> locais;
	private List<Secao> secoes = new ArrayList<Secao>();
	private Secao secao;
	private Secao secaoSelecionado = new Secao();

	@Autowired
	private EleitorDao eleitorDao;
	@Autowired
	private ResponsavelDao responsavelDao;
	@Autowired
	private LocalDao localDao;

	@PostConstruct
	public void init() {
		this.responsaveis = responsavelDao.list();
		this.eleitores = eleitorDao.list();
		this.locais = localDao.list();
		this.eleitor = new Eleitor();
		this.responsavel = new Responsavel();
		this.local = new Local();
		this.secoes = Secao.findSecoes();
	}

	public String getMessage() {
		logger.debug("Returning message from responsavel home bean");
		return "Hello from Spring";
	}

	public void save() {
		try {

			ResponsavelValidator.validarCampos(this.responsavel);

			this.eleitor.setNome(this.eleitor.getNome().toUpperCase());

			this.eleitor.setResponsavel(this.responsavel);
			this.eleitor.setLocal(this.local);

			if (this.eleitor.getId() == 0)
				eleitorDao.save(this.eleitor);
			else
				eleitorDao.merge(this.eleitor);
			this.eleitor = new Eleitor();
			this.eleitores = eleitorDao.list();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso",
							"Dados salvos com sucesso !"));
		} catch (ValidationException e) {
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

	public void comboPaisChange() {
		Secao secaoAux = null;
		if (secaoSelecionado != null) {
			secaoAux = new Secao();
			secaoAux.setNumero(this.secaoSelecionado.getNumero());
		}

		// estados = businessService.listarEstadoPorPais(paisAux);
	}

	public void listAll() {
		this.eleitores = eleitorDao.list();
	}

	public String editar() {
		this.eleitor = this.selected;
		this.responsavel = this.selected.getResponsavel();
		this.local = this.selected.getLocal();
		return "edit";
	}

	public void remove() {
		this.eleitor = this.selected;
		eleitorDao.remove(this.eleitor);
		this.eleitores = eleitorDao.list();
	}

	public List<Secao> getSecoes() {
		return secoes;
	}

	public void setSecoes(List<Secao> secoes) {
		this.secoes = secoes;
	}

	public Secao getSecao() {
		return secao;
	}

	public void setSecao(Secao secao) {
		this.secao = secao;
	}

	public Secao getSecaoSelecionado() {
		return secaoSelecionado;
	}

	public void setSecaoSelecionado(Secao secaoSelecionado) {
		this.secaoSelecionado = secaoSelecionado;
	}

	public List<Eleitor> getEleitores() {
		if (this.eleitores == null)
			this.eleitores = eleitorDao.list();
		return this.eleitores;
	}

	public List<Responsavel> getResponsaveis() {
		if (this.responsaveis == null)
			this.responsaveis = responsavelDao.list();
		return responsaveis;
	}

	public void setEleitores(List<Eleitor> eleitores) {
		this.eleitores = eleitores;
	}

	public String clean() {
		this.eleitor = new Eleitor();
		return "edit";
	}

	public Eleitor getSelected() {
		return selected;
	}

	public void setSelected(Eleitor selected) {
		this.selected = selected;
	}

	public void setEleitor(Eleitor eleitor) {
		this.eleitor = eleitor;
	}

	public void setResponsaveis(List<Responsavel> responsaveis) {
		this.responsaveis = responsaveis;
	}

	public Eleitor getEleitor() {
		return eleitor;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void print() {
		ByteArrayOutputStream byteOutPutStream = new ByteArrayOutputStream();

		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();

			HttpServletResponse response = (HttpServletResponse) facesContext
					.getExternalContext().getResponse();

			ServletContext servletContext = (ServletContext) facesContext
					.getExternalContext().getContext();

			String pathJasper = servletContext
					.getRealPath("/WEB-INF/relatorio/listagemEleitor.jasper");

			JasperPrint preencher = JasperFillManager.fillReport(pathJasper,
					null, new JRBeanCollectionDataSource(this.eleitores));
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

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public List<Local> getLocais() {
		return locais;
	}

	public void setLocais(List<Local> locais) {
		this.locais = locais;
	}

}