package br.com.ss.centralaamar.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
import br.com.ss.centralaamar.model.dao.ChamadaDAO;
import br.com.ss.centralaamar.model.dao.MembroDAO;
import br.com.ss.centralaamar.model.dao.PequenoGrupoDAO;
import br.com.ss.centralaamar.model.dao.SabadoDAO;
import br.com.ss.centralaamar.model.entity.Chamada;
import br.com.ss.centralaamar.model.entity.Membro;
import br.com.ss.centralaamar.model.entity.PequenoGrupo;
import br.com.ss.centralaamar.model.entity.Sabado;

@Component("chamadaController")
@Scope("session")
public class ChamadaController {

	private static final Logger logger = LoggerFactory
			.getLogger(ChamadaController.class);

	private Chamada chamada = new Chamada();

	private Chamada selected;
	private Sabado sabado = new Sabado();
	private PequenoGrupo pequenoGrupo = new PequenoGrupo();
	private List<Sabado> sabados;
	private List<Chamada> chamadas = new ArrayList<Chamada>();
	private List<PequenoGrupo> pequenoGrupos;
	private List<Membro> membros;

	@Autowired
	private ChamadaDAO chamadaDAO;

	@Autowired
	private SabadoDAO sabadoDAO;

	@Autowired
	private MembroDAO membroDAO;

	@Autowired
	private PequenoGrupoDAO pequenoGrupoDAO;

	public String getMessage() {
		logger.debug("Returning message from pequenoGrupo home bean");
		return "Hello from Spring";
	}

	public Chamada getChamada() {
		return chamada;
	}

	public void save() {
		try {

			if (this.chamada.getId() == null)
				chamadaDAO.save(this.chamada);
			else
				chamadaDAO.merge(this.chamada);
			this.chamada = new Chamada();
			this.chamadas = chamadaDAO.list();
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
		this.chamadas = chamadaDAO.list();
		return "listaAllChamada";
	}

	public String editar() {
		this.chamada = this.selected;
		this.selected = new Chamada();
		return "/pages/chamada/chamada.xhtml";
	}

	public String find1() {
		this.membros = (List<Membro>) membroDAO.listPorGrupo(this.pequenoGrupo,
				sabado);

		return "/pages/chamada/chamada.xhtml";
	}

	public String find() {
		Chamada chamada = new Chamada();
		sabado = new Sabado();
		sabado.setId(1);
		this.membros = (List<Membro>) membroDAO.listPorGrupo(this.pequenoGrupo,
				sabado);
		for (Iterator<Membro> iterator = membros.iterator(); iterator.hasNext();) {
			Membro membro = (Membro) iterator.next();
			chamada = new Chamada();
			chamada.setMembro(membro);
			chamada.setPresente(true);
			chamada.setSabado(sabado);
			chamada.setPequenoGrupo(this.pequenoGrupo);

			chamadaDAO.save(chamada);
			System.out.println("inseriu um registro ");

		}

		this.chamadas = chamadaDAO.listPorGrupo(chamada);
		return "/pages/chamada/chamada.xhtml";
	}

	public List<Membro> getMembros() {
		return membros;
	}

	public void setMembros(List<Membro> membros) {
		this.membros = membros;
	}

	public void remove() {
		this.chamada = this.selected;
		try {
			chamadaDAO.remove(this.chamada);
			this.chamadas = chamadaDAO.list();
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

	public void update() {
		this.chamada = this.selected;
		try {
			chamadaDAO.merge(this.chamada);
			// this.chamadas = chamadaDAO.list();
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

	public List<Chamada> getChamadas() {
		// if (this.chamadas == null)
		// this.chamadas = chamadaDAO.list();
		return this.chamadas;
	}

	public void setChamadas(List<Chamada> chamadas) {
		this.chamadas = chamadas;
	}

	public String clean() {
		this.chamada = new Chamada();
		return "/pages/chamada/chamada.xhtml";
	}

	public Chamada getSelected() {
		return selected;
	}

	public void setSelected(Chamada selected) {
		this.selected = selected;
	}

	public void setChamada(Chamada chamada) {
		this.chamada = chamada;
	}

	public void print() {
		ByteArrayOutputStream byteOutPutStream = new ByteArrayOutputStream();

		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();

			HttpServletResponse response = (HttpServletResponse) facesContext
					.getExternalContext().getResponse();

			String pathJasper = "C:\\jasper\\pequenoGrup.jasper";

			JasperPrint preencher = JasperFillManager.fillReport(pathJasper,
					null, new JRBeanCollectionDataSource(this.chamadas));

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

	public Sabado getSabado() {
		return sabado;
	}

	public void setSabado(Sabado sabado) {
		this.sabado = sabado;
	}

	public List<Sabado> getSabados() {
		return sabadoDAO.list();
	}

	public void setSabados(List<Sabado> sabados) {
		this.sabados = sabados;
	}

	public PequenoGrupo getPequenoGrupo() {
		return pequenoGrupo;
	}

	public void setPequenoGrupo(PequenoGrupo pequenoGrupo) {
		this.pequenoGrupo = pequenoGrupo;
	}

	public List<PequenoGrupo> getPequenoGrupos() {
		this.pequenoGrupos = pequenoGrupoDAO.list();
		return this.pequenoGrupos;
	}

	public void setPequenoGrupos(List<PequenoGrupo> pequenoGrupos) {
		this.pequenoGrupos = pequenoGrupos;
	}

}