package br.com.ss.centralaamar.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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

import br.com.ss.centralaamar.component.Batizado;
import br.com.ss.centralaamar.component.MembroIgreja;
import br.com.ss.centralaamar.component.ModoConversao;
import br.com.ss.centralaamar.component.Sexo;
import br.com.ss.centralaamar.component.TemFilho;
import br.com.ss.centralaamar.exception.MembrolValidator;
import br.com.ss.centralaamar.exception.ValidationException;
import br.com.ss.centralaamar.model.dao.MembroDAO;
import br.com.ss.centralaamar.model.dao.PastorDAO;
import br.com.ss.centralaamar.model.dao.PequenoGrupoDAO;
import br.com.ss.centralaamar.model.dao.ProfissaoDAO;
import br.com.ss.centralaamar.model.entity.Membro;
import br.com.ss.centralaamar.model.entity.Pastor;
import br.com.ss.centralaamar.model.entity.PequenoGrupo;
import br.com.ss.centralaamar.model.entity.Profissao;

@Component("membroController")
@Scope("session")
public class MembroController {

	private static final Logger logger = LoggerFactory
			.getLogger(MembroController.class);

	private Membro membro = new Membro();
	private PequenoGrupo pequenoGrupo;
	private Profissao profissao;
	private Pastor pastor;
	private Membro selected;
	private List<Membro> membros;
	private List<PequenoGrupo> pequenoGrupos;
	private List<Profissao> profissoes;
	private List<Pastor> pastores = new ArrayList<Pastor>();
	private List<Sexo> sexos = new ArrayList<Sexo>();
	private List<Batizado> batizados = new ArrayList<Batizado>();
	private List<MembroIgreja> membroIgrejas = new ArrayList<MembroIgreja>();
	private List<TemFilho> temFilhos = new ArrayList<TemFilho>();
	private List<ModoConversao> modoConversoes = new ArrayList<ModoConversao>();

	@Autowired
	private MembroDAO membroDAO;
	@Autowired
	private PequenoGrupoDAO pequenoGrupoDAO;
	@Autowired
	private ProfissaoDAO profissaoDAO;
	@Autowired
	private PastorDAO pastorDAO;

	@PostConstruct
	public void init() {
		this.pequenoGrupos = pequenoGrupoDAO.list();
		this.membros = membroDAO.list();
		this.profissoes = profissaoDAO.list();
		this.pastores = pastorDAO.list();
		this.membro = new Membro();
		this.pequenoGrupo = new PequenoGrupo();
		this.profissao = new Profissao();
		this.pastor = new Pastor();
		this.setSexos(getSexos());
		this.setTemFilhos(getTemFilhos());
		this.setModoConversoes(getModoConversoes());
	}

	public String getMessage() {
		logger.debug("Returning message from pequenoGrupo home bean");
		return "Hello from Spring";
	}

	public void save() {
		try {

			MembrolValidator.validarCampos(this.membro);

			this.membro.setNome(this.membro.getNome().toUpperCase());
			if (this.pequenoGrupo.getId() > 0)
				this.membro.setPequenoGrupo(this.pequenoGrupo);

			if (this.profissao.getId() > 0)
				this.membro.setProfissao(this.profissao);

			if (this.pastor.getId() > 0)
				this.membro.setPastor(this.pastor);

			if (this.membro.getId() == null)
				membroDAO.save(this.membro);
			else
				membroDAO.merge(this.membro);
			this.membro = new Membro();
			this.membros = membroDAO.list();
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

	public void findP() {
		try {

			this.membro.setNome(this.membro.getNome().toUpperCase());
			
			if (this.pequenoGrupo.getId() > 0)
				this.membro.setPequenoGrupo(this.pequenoGrupo);
			
			this.membros = membroDAO.listPesquisa(this.membro);
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

	public void listAll() {
		this.membros = membroDAO.list();
	}

	public String editar() {
		this.membro = this.selected;
		if (this.selected.getProfissao() == null)
			this.pequenoGrupo = new PequenoGrupo();
		else
			this.pequenoGrupo = this.selected.getPequenoGrupo();

		if (this.selected.getProfissao() == null)
			this.profissao = new Profissao();
		else
			this.profissao = this.selected.getProfissao();

		if (this.selected.getPastor() == null)
			this.pastor = new Pastor();
		else
			this.pastor = this.selected.getPastor();

		return "/pages/membro/membro.xhtml";
	}

	public void remove() {
		this.membro = this.selected;
		membroDAO.remove(this.membro);
		this.membros = membroDAO.list();
	}

	public List<Membro> getMembros() {
		if (this.membros == null)
			this.membros = membroDAO.list();
		return this.membros;
	}

	public List<PequenoGrupo> getPequenoGrupos() {
		if (this.pequenoGrupos == null)
			this.pequenoGrupos = pequenoGrupoDAO.list();
		return pequenoGrupos;
	}

	public void setMembros(List<Membro> membros) {
		this.membros = membros;
	}

	public String clean() {
		this.membro = new Membro();
		return "/pages/membro/membro.xhtml";
	}
	
	public String back() {
		this.membro = new Membro();
		return "/pages/membro/listMembro.xhtml";
	}

	public Membro getSelected() {
		return selected;
	}

	public void setSelected(Membro selected) {
		this.selected = selected;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}

	public void setPequenoGrupos(List<PequenoGrupo> pequenoGrupos) {
		this.pequenoGrupos = pequenoGrupos;
	}

	public Membro getMembro() {
		return membro;
	}

	public void setPequenoGrupo(PequenoGrupo pequenoGrupo) {
		this.pequenoGrupo = pequenoGrupo;
	}

	public PequenoGrupo getPequenoGrupo() {
		return pequenoGrupo;
	}

	public void print() {
		ByteArrayOutputStream byteOutPutStream = new ByteArrayOutputStream();

		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();

			HttpServletResponse response = (HttpServletResponse) facesContext
					.getExternalContext().getResponse();

			String pathJasper = "C:\\jasper\\membro.jasper";

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

	public Profissao getProfissao() {
		return profissao;
	}

	public void setProfissao(Profissao profissao) {
		this.profissao = profissao;
	}

	public List<Profissao> getProfissoes() {
		return profissoes;
	}

	public void setProfissoes(List<Profissao> profissoes) {
		this.profissoes = profissoes;
	}

	public List<Sexo> getSexos() {
		return Sexo.list();
	}

	public void setSexos(List<Sexo> sexos) {
		this.sexos = sexos;
	}

	public List<Batizado> getBatizados() {
		return Batizado.list();
	}

	public void setBatizados(List<Batizado> batizados) {
		this.batizados = batizados;
	}

	public List<MembroIgreja> getMembroIgrejas() {
		return MembroIgreja.list();
	}

	public void setMembroIgrejas(List<MembroIgreja> membroIgrejas) {
		this.membroIgrejas = membroIgrejas;
	}

	public List<TemFilho> getTemFilhos() {
		return TemFilho.list();
	}

	public void setTemFilhos(List<TemFilho> temFilhos) {
		this.temFilhos = temFilhos;
	}

	public List<ModoConversao> getModoConversoes() {
		return ModoConversao.list();
	}

	public void setModoConversoes(List<ModoConversao> modoConversoes) {
		this.modoConversoes = modoConversoes;
	}

	public Pastor getPastor() {
		return pastor;
	}

	public void setPastor(Pastor pastor) {
		this.pastor = pastor;
	}

	public List<Pastor> getPastores() {
		return pastorDAO.list();
	}

	public void setPastores(List<Pastor> pastores) {
		this.pastores = pastores;
	}

}