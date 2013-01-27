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
import br.com.ss.centralaamar.model.dao.ProfissaoDAO;
import br.com.ss.centralaamar.model.entity.Profissao;

@Component("profissaoController")
@Scope("session")
public class ProfissaoController {

	private static final Logger logger = LoggerFactory
			.getLogger(ProfissaoController.class);

	private Profissao profissao = new Profissao();
	private List<Profissao> profissoes;
	private Profissao selected;

	@Autowired
	private ProfissaoDAO profissaoDAO;

	public String getMessage() {
		logger.debug("Returning message from pequenoGrupo home bean");
		return "Hello from Spring";
	}

	public Profissao getProfissao() {
		return profissao;
	}

	public void save() {
		try {
			// ProfissaoValidator.validarCampos(pequenoGrupo);

			this.profissao.setDescricao(this.profissao.getDescricao());

			if (this.profissao.getId() == null)
				profissaoDAO.save(this.profissao);
			else
				profissaoDAO.merge(this.profissao);
			this.profissao = new Profissao();
			this.profissoes = profissaoDAO.list();
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
		this.profissoes = profissaoDAO.list();
		return "listaAllProfissao";
	}

	public String editar() {
		this.profissao = this.selected;
		this.selected = new Profissao();
		return "/pages/profissao/profissao.xhtml";
	}

	public void remove() {

		this.profissao = this.selected;
		try {
			profissaoDAO.remove(this.profissao);
			this.profissoes = profissaoDAO.list();
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

	public List<Profissao> getProfissaos() {
		if (this.profissoes == null)
			this.profissoes = profissaoDAO.list();
		return this.profissoes;
	}

	public void setProfissaos(List<Profissao> profissoes) {
		this.profissoes = profissoes;
	}

	public String clean() {
		this.profissao = new Profissao();
		return "/pages/profissao/profissao.xhtml";
	}

	public Profissao getSelected() {
		return selected;
	}

	public void setSelected(Profissao selected) {
		this.selected = selected;
	}

	public void setProfissao(Profissao profissao) {
		this.profissao = profissao;
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
					null, new JRBeanCollectionDataSource(this.profissoes));

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