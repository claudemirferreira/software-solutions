package br.com.ss.centralaamar.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import lombok.Getter;
import lombok.Setter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ss.centralaamar.component.Batizado;
import br.com.ss.centralaamar.component.ConnectionFactory;
import br.com.ss.centralaamar.component.MembroIgreja;
import br.com.ss.centralaamar.component.ModoConversao;
import br.com.ss.centralaamar.component.Relatorio;
import br.com.ss.centralaamar.component.Sexo;
import br.com.ss.centralaamar.component.TemFilho;
import br.com.ss.centralaamar.model.entity.Membro;
import br.com.ss.centralaamar.model.entity.Pastor;
import br.com.ss.centralaamar.model.entity.PequenoGrupo;
import br.com.ss.centralaamar.model.entity.Profissao;
import br.com.ss.centralaamar.service.IMembroService;
import br.com.ss.centralaamar.service.IPastorService;
import br.com.ss.centralaamar.service.IPequenoGrupoService;
import br.com.ss.centralaamar.service.IProfissaoService;
import br.com.ss.centralaamar.service.IService;

@Controller
@Named
@Scope("session")
public class MembroController extends GenericBean<Membro> {

	private static final long serialVersionUID = 4169632240996553603L;

	@Autowired
	private IMembroService service;
	@Autowired
	private IProfissaoService profissaoService;
	@Autowired
	private IPastorService pastorService;
	@Autowired
	private IPequenoGrupoService pequenoGrupoService;

	@Getter
	@Setter
	private List<Batizado> batizados = Batizado.list();

	@Getter
	@Setter
	private List<MembroIgreja> membroIgrejas = MembroIgreja.list();

	@Getter
	@Setter
	private List<ModoConversao> modoConvecaos = ModoConversao.list();

	@Getter
	@Setter
	private List<Sexo> sexos = Sexo.list();

	@Getter
	@Setter
	private List<TemFilho> temFilhos = TemFilho.list();

	@Getter
	@Setter
	private Pastor pastor = new Pastor();

	@Getter
	@Setter
	private Profissao profissao = new Profissao();

	@Getter
	@Setter
	private PequenoGrupo pequenoGrupo = new PequenoGrupo();

	@Getter
	@Setter
	private List<Pastor> pastors = new ArrayList<Pastor>();

	@Getter
	@Setter
	private List<Profissao> profissaos = new ArrayList<Profissao>();

	@Getter
	@Setter
	private List<PequenoGrupo> pequenoGrupos = new ArrayList<PequenoGrupo>();

	@Override
	protected IService<Membro> getService() {
		return service;
	}

	public String save() throws SQLException {

		this.entity.setNome(this.entity.getNome().toUpperCase());
		if (this.pastor.getIdPastor() != 0)
			this.entity.setPastor(this.pastor);
		if (this.profissao.getIdProfissao() != 0)
			this.entity.setProfissao(this.profissao);
		if (this.pequenoGrupo.getIdPequenoGrupo() != 0)
			this.entity.setPequenoGrupo(this.pequenoGrupo);

		return super.save();

	}

	@PostConstruct
	protected void setup() throws InstantiationException,
			IllegalAccessException {

		pastors = pastorService.search(new Pastor());
		profissaos = profissaoService.search(new Profissao());
		pequenoGrupos = pequenoGrupoService.search(new PequenoGrupo());

		profissao = new Profissao();
		pastor = new Pastor();
		pequenoGrupo = new PequenoGrupo();

		super.setup();

	}

	public String cadastrar() throws InstantiationException,
			IllegalAccessException {

		this.pastors = pastorService.search(new Pastor());
		this.profissaos = profissaoService.search(new Profissao());
		this.pequenoGrupos = pequenoGrupoService.search(new PequenoGrupo());

		this.profissao = new Profissao();
		this.pastor = new Pastor();
		this.pequenoGrupo = new PequenoGrupo();

		return super.cadastrar();
	}

	public String editar(Membro entity) {

		this.setPastor(new Pastor());
		this.setProfissao(new Profissao());
		this.setPequenoGrupo(new PequenoGrupo());

		if (null != entity.getPastor())
			this.pastor = entity.getPastor();
		if (entity.getProfissao() != null)
			this.profissao = entity.getProfissao();
		if (entity.getPequenoGrupo() != null)
			this.pequenoGrupo = entity.getPequenoGrupo();

		return super.editar(entity);

	}

	@SuppressWarnings("unchecked")
	public void printAniversariante() {

		List<Membro> list = service
				.listAniversariantes(
						Relatorio.converterDataToString(this.relatorio
								.getDataInicial()), Relatorio
								.converterDataToString(this.relatorio
										.getDataFinal()), Relatorio
								.converterAnoToString(this.relatorio
										.getDataInicial()));
		relatorio.setPath("D:\\jasper\\relatorioAniversariante.jasper");
		relatorio.setResultList(list);

		super.print();

	}

	@SuppressWarnings("unchecked")
	public void printAniversarianteResumido() {

		List<Membro> list = service
				.listAniversariantes(
						Relatorio.converterDataToString(this.relatorio
								.getDataInicial()), Relatorio
								.converterDataToString(this.relatorio
										.getDataFinal()), Relatorio
								.converterAnoToString(this.relatorio
										.getDataInicial()));
		relatorio.setPath("D:\\jasper\\relatorioAniversarianteResumido.jasper");
		relatorio.setResultList(list);

		super.print();

	}

	@SuppressWarnings("unchecked")
	public void print() {
		Map param = new HashMap();
		param.put("titulo", "RELATORIO DE MEMBROS ");

		this.relatorio.setParametros(param);
		this.relatorio.setPath("D:\\jasper\\membro.jasper");
		this.relatorio.setResultList(this.resultList);
		super.print();
	}

	@SuppressWarnings("unchecked")
	public void listMembrosPorProfissao() {

		Map param = new HashMap();
//		this.setProfissao(profissaoService.getByPrimaryKey(this.getProfissao()));

//		param.put("titulo", "RELATORIO DE MEMBROS POR PROFISSAO [ "
//				+ this.getProfissao().getNome() + " ]");
		this.getProfissao().setNome("");
		param.put("titulo", "RELATORIO DE MEMBROS POR PROFISSAO [ "
				+ this.getProfissao().getNome() + " ]");
		
		this.relatorio.setParametros(param);
		this.relatorio.setPath("D:\\jasper\\membro.jasper");
		this.relatorio.setResultList(service
				.listMembrosPorProfissao(this.profissao));

		super.print();
	}

	@SuppressWarnings("deprecation")
	public void printRelatorioMaster() throws IOException, JRException {
		ByteArrayOutputStream byteOutPutStream = new ByteArrayOutputStream();

		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();

			Connection connection = ConnectionFactory.getConnection();

			HttpServletResponse response = (HttpServletResponse) facesContext
					.getExternalContext().getResponse();

			String pathJasper = "D:\\jasper\\relatorioMasterMembroPorPequenoGrupo.jasper";

			JasperPrint preencher = JasperFillManager.fillReport(pathJasper,
					null, connection);

			System.out.println("Writing to InPut stream...");
			JasperExportManager.exportReportToPdfStream(preencher,
					byteOutPutStream);

			System.out.println("Size of OutPut: " + byteOutPutStream.size());
			response.setContentLength(byteOutPutStream.size());
			response.setContentType("application/pdf");

			System.out.println("Writing to OutPut stream...");
			ServletOutputStream servletOutPutStream = response
					.getOutputStream();
			servletOutPutStream.write(byteOutPutStream.toByteArray(), 0,
					byteOutPutStream.size());

			System.out.println("Closing stream...");
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