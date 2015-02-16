package br.com.ss.academico.controlador;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.ss.academico.dominio.AvaliacaoEducacaoInfantil;
import br.com.ss.academico.dominio.Boletim;
import br.com.ss.academico.dominio.Configuracao;
import br.com.ss.academico.dominio.DetalheBoletim;
import br.com.ss.academico.dominio.Empresa;
import br.com.ss.academico.dominio.Matricula;
import br.com.ss.academico.dominio.MediaTurma;
import br.com.ss.academico.dominio.Turma;
import br.com.ss.academico.dominio.TurmaDisciplina;
import br.com.ss.academico.enumerated.Bimestre;
import br.com.ss.academico.enumerated.ConceitoAvaliacao;
import br.com.ss.academico.enumerated.StatusBoletim;
import br.com.ss.academico.servico.BoletimServico;
import br.com.ss.academico.servico.DetalheBoletimServico;
import br.com.ss.academico.servico.TurmaServico;
import br.com.ss.core.seguranca.servico.IService;
import br.com.ss.core.web.controlador.ControladorGenerico;
import br.com.ss.core.web.enumerated.Constants;
import br.com.ss.core.web.ireport.Grafico;
import br.com.ss.core.web.utils.FacesUtils;
import br.com.ss.core.web.utils.Util;

import com.lowagie.text.DocumentException;

@ManagedBean
@SessionScoped
public class BoletimControlador extends ControladorGenerico<Boletim> {

	private static final long serialVersionUID = -6832271293709421841L;

	@ManagedProperty(value = "#{boletimServicoImpl}")
	private BoletimServico service;

	@ManagedProperty(value = "#{turmaServicoImpl}")
	private TurmaServico turmaServico;

	@ManagedProperty(value = "#{detalheBoletimServicoImpl}")
	private DetalheBoletimServico detalheBoletimServico;

	private String nomeRelatorio = "boletim.jasper";

	private Turma turma;

	private List<Turma> turmas;

	private List<Matricula> filteredTurmas;

	private Configuracao configuracao;

	private StreamedContent imagemGrafico;

	private List<MediaTurma> medias;

	private JFreeChart chart;

	private Grafico grafico = new Grafico();

	private List<SelectItem> bimestreList;

	private Bimestre bimestre;

	private List<SelectItem> statusList;

	private String MEDIA_BIMESTRE = "MEDIA_BIMESTRE.PNG";

	private String MEDIA_SOMA_BIMESTRE = "MEDIA_SOMA_BIMESTRE.PNG";

	private StreamedContent chart1;

	private StreamedContent chart2;

	private List<SelectItem> conceitoAvaliacaoList;

	@PostConstruct
	@Override
	public void setup() {
		super.setup();

		this.turmas = turmaServico.listarTurmas();

		configuracao = (Configuracao) FacesUtils
				.getApplicationParam("configuracao");

		statusList = new ArrayList<SelectItem>();
		for (StatusBoletim status : StatusBoletim.values()) {
			statusList.add(new SelectItem(status, status.getDescricao()));
		}

		bimestreList = new ArrayList<SelectItem>();
		for (Bimestre c : Bimestre.values()) {
			bimestreList.add(new SelectItem(c, c.getDescricao()));
		}

		conceitoAvaliacaoList = new ArrayList<SelectItem>();
		for (ConceitoAvaliacao ca : ConceitoAvaliacao.values()) {
			conceitoAvaliacaoList.add(new SelectItem(ca, ca.getDescricao()));
		}

		this.bimestre = Bimestre.PRIMEIRO;

	}

	protected String getNomeRelatorioJasper() {
		return this.nomeRelatorio;
	}

	@Override
	public String getTituloRelatorio() {
		return "RELATÓRIO DE BOLETIM";
	}

	@Override
	public void pesquisar() {
		if (this.turma != null) {
			this.listaPesquisa = service.listBoletimPorTurma(this.turma);

			// fetch de disciplina
			for (Boletim bol : listaPesquisa) {
				for (TurmaDisciplina td : bol.getMatricula().getTurma()
						.getTurmaDisciplina()) {
					td.getDisciplina();
				}
			}
		}

	}

	public String pesquisarBoletim(Boletim boletim) {
		entidade = boletim;
		return "boletim?faces-redirect=true";
	}

	public String onEdit(RowEditEvent event) {
		salvarBoletim();
		return redirect("boletim");
	}

	public void onCancel(RowEditEvent event) {
		showMessage("Ediçao de nota cancelada!", FacesMessage.SEVERITY_WARN);
	}

	public void salvarBoletim() {

		entidade.atualizarBoletim(configuracao.getMediaEscolar());
		getService().salvar(entidade);
		showMessage(Constants.MSG_SUCESSO, FacesMessage.SEVERITY_INFO);

	}

	@Override
	public String salvar() {
		entidade.atualizarBoletim(configuracao.getMediaEscolar());
		return super.salvar();
	}

	public void gerarImagemGrafico() throws IOException {

		this.MEDIA_BIMESTRE = pegarGraficoBimestre(bimestre,
				"Gráfico de Média do " + bimestre.getDescricao());

		chart1 = new DefaultStreamedContent(new FileInputStream(
				this.MEDIA_BIMESTRE), "image/png");

		if (bimestre.getDescricao() != Bimestre.PRIMEIRO.getDescricao()) {
			this.MEDIA_SOMA_BIMESTRE = pegarGraficoMediaSomaBimestre(bimestre,
					"Gráfico de Média dos Bimestres ");
			chart2 = new DefaultStreamedContent(new FileInputStream(
					this.MEDIA_SOMA_BIMESTRE), "image/png");
		}
	}

	public void showModalGrafico() throws IOException {
		gerarImagemGrafico();
		RequestContext rc = RequestContext.getCurrentInstance();
		rc.execute("PF('dlgGrafico').show()");
	}

	public void telaGraficoPoup() throws IOException {
		gerarImagemGrafico();
	}

	public void imprimirBoletim() throws IOException, DocumentException, JRException {
		if (this.entidade.isEducacaoFundamental()) {
			imprimirBoletimEducFundamental();
		} else {
			imprimirBoletimInfantil();
		}
	}

	private void imprimirBoletimEducFundamental() throws IOException {

		gerarImagemGrafico();

		try {

			Map<String, Object> param = new HashMap<String, Object>();
			Empresa empresa = (Empresa) FacesUtils
					.getApplicationParam("empresa");

			// parametros usados no relatorio
			param.put(REPORT_TITLE, getTituloRelatorio());
			param.put(EMPRESA, empresa);
			param.put("boletim", this.entidade);
			param.put(USUARIO, getUsuarioLogado());
			param.put("GRAFICO1", this.MEDIA_BIMESTRE);

			if (bimestre.getDescricao() != Bimestre.PRIMEIRO.getDescricao())
				param.put("GRAFICO2", this.MEDIA_SOMA_BIMESTRE);

			List<DetalheBoletim> lista = new ArrayList<DetalheBoletim>(
					this.entidade.getDetalheBoletims());

			gerarRelatorioWeb(lista, param, true);

		} catch (JRException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void imprimir() throws FileNotFoundException, IOException, DocumentException, JRException {
		try {

			Map<String, Object> param = new HashMap<String, Object>();
			Empresa empresa = (Empresa) FacesUtils
					.getApplicationParam("empresa");

			// parametros usados no relatorio
			param.put(REPORT_TITLE, getTituloRelatorio());
			param.put(EMPRESA, empresa);
			param.put(USUARIO, getUsuarioLogado());

			gerarRelatorioWeb(listaPesquisa, param, false);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void gerarRelatorioWeb(List<?> lista,
			Map<String, Object> parametros, boolean boletim) throws JRException {
		nomeRelatorio = "boletim.jasper";
		if (!boletim) {
			nomeRelatorio = "turma-aluno.jasper";
		}

		JRDataSource jrRS = new JRBeanCollectionDataSource(lista);

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();

		BufferedOutputStream output = null;
		BufferedInputStream input = null;

		String webPath = context.getExternalContext().getRealPath("/");
		String reportPath = webPath + PATH_REPORT + nomeRelatorio;

		try {

			input = new BufferedInputStream(new FileInputStream(reportPath),
					DEFAULT_BUFFER_SIZE);

			File fileReport = new File(reportPath);

			response.reset();
			response.setHeader("Content-Type", "application/pdf");
			response.setHeader("Content-Length",
					String.valueOf(fileReport.length()));
			response.setHeader("Content-Disposition", "inline; filename=\""
					+ fileReport.getName() + "\"");

			JasperRunManager.runReportToPdfStream(new FileInputStream(
					fileReport), response.getOutputStream(), parametros, jrRS);

			output = new BufferedOutputStream(response.getOutputStream(),
					DEFAULT_BUFFER_SIZE);

			// Write file contents to response.
			byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
			int length;
			while ((length = input.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}

			// Finalize task.
			output.flush();
		} catch (FileNotFoundException e) {
			System.out.println("Erro : Relatorio não foi encontrado: "
					+ reportPath);
			showMessage(Constants.MSG_ERRO, FacesMessage.SEVERITY_ERROR);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// Gently close streams.
			close(output);
			close(input);
		}

		context.renderResponse();
		context.responseComplete();
	}

	public void imprimirBoletim(Set<DetalheBoletim> lista,
			Map<String, Object> parametros, boolean boletim) throws JRException {

		nomeRelatorio = "turma-aluno.jasper";

		JRDataSource jrRS = new JRBeanCollectionDataSource(lista);

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();

		BufferedOutputStream output = null;
		BufferedInputStream input = null;

		String webPath = context.getExternalContext().getRealPath("/");
		String reportPath = webPath + PATH_REPORT + nomeRelatorio;

		try {

			input = new BufferedInputStream(new FileInputStream(reportPath),
					DEFAULT_BUFFER_SIZE);

			File fileReport = new File(reportPath);

			response.reset();
			response.setHeader("Content-Type", "application/pdf");
			response.setHeader("Content-Length",
					String.valueOf(fileReport.length()));
			response.setHeader("Content-Disposition", "inline; filename=\""
					+ fileReport.getName() + "\"");

			JasperRunManager.runReportToPdfStream(new FileInputStream(
					fileReport), response.getOutputStream(), parametros, jrRS);

			output = new BufferedOutputStream(response.getOutputStream(),
					DEFAULT_BUFFER_SIZE);

			// Write file contents to response.
			byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
			int length;
			while ((length = input.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}

			// Finalize task.
			output.flush();
		} catch (FileNotFoundException e) {
			System.out.println("Erro : Relatorio não foi encontrado: "
					+ reportPath);
			showMessage(Constants.MSG_ERRO, FacesMessage.SEVERITY_ERROR);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// Gently close streams.
			close(output);
			close(input);
		}

		context.renderResponse();
		context.responseComplete();
	}

	public void imprimirBoletimInfantil() throws FileNotFoundException, IOException, DocumentException, JRException {
		try {

			Map<String, Object> param = new HashMap<String, Object>();
			Empresa empresa = (Empresa) FacesUtils.getApplicationParam("empresa");

			// parametros usados no relatorio
			param.put(REPORT_TITLE, getTituloRelatorio());
			param.put(EMPRESA, empresa);
			param.put(USUARIO, getUsuarioLogado());

			imprimirBoletimInfantil(this.entidade.getAvaliacaoEducacaoInfantilAsList(), param, false);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void imprimirBoletimInfantil(List<AvaliacaoEducacaoInfantil> lista,
			Map<String, Object> parametros, boolean boletim) throws JRException {

		nomeRelatorio = "boletim-infantil.jasper";

		JRDataSource jrRS = new JRBeanCollectionDataSource(lista);

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();

		BufferedOutputStream output = null;
		BufferedInputStream input = null;

		String webPath = context.getExternalContext().getRealPath("/");
		String reportPath = webPath + PATH_REPORT + nomeRelatorio;

		try {

			input = new BufferedInputStream(new FileInputStream(reportPath),
					DEFAULT_BUFFER_SIZE);

			File fileReport = new File(reportPath);

			response.reset();
			response.setHeader("Content-Type", "application/pdf");
			response.setHeader("Content-Length",
					String.valueOf(fileReport.length()));
			response.setHeader("Content-Disposition", "inline; filename=\""
					+ fileReport.getName() + "\"");

			JasperRunManager.runReportToPdfStream(new FileInputStream(
					fileReport), response.getOutputStream(), parametros, jrRS);

			output = new BufferedOutputStream(response.getOutputStream(),
					DEFAULT_BUFFER_SIZE);

			// Write file contents to response.
			byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
			int length;
			while ((length = input.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}

			// Finalize task.
			output.flush();
		} catch (FileNotFoundException e) {
			System.out.println("Erro : Relatorio não foi encontrado: "
					+ reportPath);
			showMessage(Constants.MSG_ERRO, FacesMessage.SEVERITY_ERROR);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// Gently close streams.
			close(output);
			close(input);
		}

		context.renderResponse();
		context.responseComplete();
	}

	public List<Double> getNotas() {
		return Util.gerarNotas();
	}

	/* ----------- Gets/Sets ------------------ */

	@Override
	protected IService<Boletim, Long> getService() {
		return service;
	}

	public void setService(BoletimServico service) {
		this.service = service;
	}

	public void setNomeRelatorio(String nomeRelatorio) {
		this.nomeRelatorio = nomeRelatorio;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public TurmaServico getTurmaServico() {
		return turmaServico;
	}

	public void setTurmaServico(TurmaServico turmaServico) {
		this.turmaServico = turmaServico;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public List<Matricula> getFilteredTurmas() {
		return filteredTurmas;
	}

	public void setFilteredTurmas(List<Matricula> filteredTurmas) {
		this.filteredTurmas = filteredTurmas;
	}

	public String pegarGraficoMediaSomaBimestre(Bimestre bimestre,
			String nomeGrafico) throws IOException {
		imagemGrafico = null;

		this.chart = this.grafico.criarGraficoMediaAcumulada(medias,
				this.entidade.getDetalheBoletims(), bimestre);

		FacesContext context = FacesContext.getCurrentInstance();
		String webPath = context.getExternalContext().getRealPath("/")
				+ "MEDIA_SOMA_BIMESTRE.PNG";

		File file1 = new File(webPath);
		ChartUtilities.saveChartAsPNG(file1, this.chart, 555, 300);
		System.out.println(webPath);
		return webPath;
	}

	public String pegarGraficoBimestre(Bimestre bimestre, String nomeGrafico)
			throws IOException {
		imagemGrafico = null;

		this.medias = detalheBoletimServico.listaMediaTurma(turma);
		this.chart = this.grafico.criarGraficoMedia(this.medias,
				this.entidade.getDetalheBoletims(), bimestre);

		FacesContext context = FacesContext.getCurrentInstance();
		String webPath = context.getExternalContext().getRealPath("/")
				+ "MEDIA_BIMESTRE.PNG";

		File file1 = new File(webPath);
		ChartUtilities.saveChartAsPNG(file1, this.chart, 555, 300);
		System.out.println(webPath);
		return webPath;
	}

	public DetalheBoletimServico getDetalheBoletimServico() {
		return detalheBoletimServico;
	}

	public void setDetalheBoletimServico(
			DetalheBoletimServico detalheBoletimServico) {
		this.detalheBoletimServico = detalheBoletimServico;
	}

	public Configuracao getConfiguracao() {
		return configuracao;
	}

	public void setConfiguracao(Configuracao configuracao) {
		this.configuracao = configuracao;
	}

	public StreamedContent getImagemGrafico() {
		return imagemGrafico;
	}

	public void setImagemGrafico(StreamedContent imagemGrafico) {
		this.imagemGrafico = imagemGrafico;
	}

	public List<MediaTurma> getMedias() {
		return medias;
	}

	public void setMedias(List<MediaTurma> medias) {
		this.medias = medias;
	}

	public JFreeChart getChart() {
		return chart;
	}

	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}

	public Grafico getGrafico() {
		return grafico;
	}

	public void setGrafico(Grafico grafico) {
		this.grafico = grafico;
	}

	public List<SelectItem> getBimestreList() {
		return bimestreList;
	}

	public void setBimestreList(List<SelectItem> bimestreList) {
		this.bimestreList = bimestreList;
	}

	public Bimestre getBimestre() {
		return bimestre;
	}

	public void setBimestre(Bimestre bimestre) {
		this.bimestre = bimestre;
	}

	public List<SelectItem> getStatusList() {
		return statusList;
	}

	public String getMEDIA_BIMESTRE() {
		return MEDIA_BIMESTRE;
	}

	public void setMEDIA_BIMESTRE(String mEDIA_BIMESTRE) {
		MEDIA_BIMESTRE = mEDIA_BIMESTRE;
	}

	public String getMEDIA_SOMA_BIMESTRE() {
		return MEDIA_SOMA_BIMESTRE;
	}

	public void setMEDIA_SOMA_BIMESTRE(String mEDIA_SOMA_BIMESTRE) {
		MEDIA_SOMA_BIMESTRE = mEDIA_SOMA_BIMESTRE;
	}

	public StreamedContent getChart1() {
		return chart1;
	}

	public void setChart1(StreamedContent chart1) {
		this.chart1 = chart1;
	}

	public StreamedContent getChart2() {
		return chart2;
	}

	public void setChart2(StreamedContent chart2) {
		this.chart2 = chart2;
	}

	public List<SelectItem> getConceitoAvaliacaoList() {
		return conceitoAvaliacaoList;
	}

}