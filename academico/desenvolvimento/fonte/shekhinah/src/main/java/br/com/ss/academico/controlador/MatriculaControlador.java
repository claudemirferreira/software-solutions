package br.com.ss.academico.controlador;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.ss.academico.dominio.Aluno;
import br.com.ss.academico.dominio.Matricula;
import br.com.ss.academico.ireport.RelatorioUtil;
import br.com.ss.academico.servico.BoletimServico;
import br.com.ss.academico.servico.EmpresaServico;
import br.com.ss.academico.servico.MatriculaServico;
import br.com.ss.academico.servico.TurmaServico;

@ManagedBean
@SessionScoped
public class MatriculaControlador implements Serializable {

	private static final long serialVersionUID = -6832271293709421841L;

	private Matricula entidade;

	private Matricula pesquisa;

	private List<Matricula> lista;

	private List<Aluno> alunos;

	private final String TELA_CADASTRO = "paginas/matricula/cadastro.xhtml";
	private final String TELA_PESQUISA = "paginas/matricula/pesquisa.xhtml";

	@ManagedProperty(value = "#{matriculaServicoImpl}")
	private MatriculaServico servico;
	
	@ManagedProperty(value = "#{turmaServicoImpl}")
	private TurmaServico turmaServico;

	@ManagedProperty(value = "#{boletimServicoImpl}")
	private BoletimServico boletimServico;

	@ManagedProperty(value = "#{relatorioUtil}")
	private RelatorioUtil relatorioUtil;

	@ManagedProperty(value = "#{empresaServicoImpl}")
	private EmpresaServico empresaServico;

	@ManagedProperty(value = "#{paginaCentralControlador}")
	private PaginaCentralControlador paginaCentralControlador;

	public void init() {
		this.lista = servico.listarTodos();
		this.telaPesquisa();
	}

	public MatriculaControlador() {
		this.entidade = new Matricula();
		this.pesquisa = new Matricula();
	}

	public void pesquisar() {
		// this.lista = servico.findByNomeLike(this.pesquisa.getNome());
	}

	public void detalhe(Matricula matricula) {
		this.entidade = matricula;
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);
	}

	public void salvar() {

		// gerar boletim
		long gerarBoletim = this.entidade.getIdMatricula();

		this.servico.salvar(this.entidade);

		if (gerarBoletim < 1)
			this.boletimServico.gerarBoletim(this.entidade);

		this.lista = servico.listarTodos();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public void imprimir(Matricula matricula) {
		List<Matricula> lista = new ArrayList<Matricula>();
		lista.add(matricula);
		Map<String, Object> parametros = new HashMap<String, Object>();

		relatorioUtil.gerarRelatorioComDownload(lista, parametros,
				"contrato-parte1.jasper");

	}

	public void excluir(Matricula matricula) {
		servico.remover(matricula);
		this.lista = servico.listarTodos();
	}

	public void imprimircontrato(Matricula matricula)
			throws FileNotFoundException {
		// TODO criar o relatorio..
		List<Matricula> listMat = new ArrayList<Matricula>();
		listMat.add(matricula);
		relatorioUtil
				.gerarRelatorioWeb(listMat, null, "contrato-parte1.jasper");
	}

	public void gerar(Matricula matricula) throws MalformedURLException {

		Map<String, Object> parametros = new HashMap<String, Object>();
		List<Matricula> lista = new ArrayList<Matricula>();
		String nome = "contrato-parte1.jasper";

		gerarRelatorio(null, parametros, lista, nome);

	}

	public StreamedContent gerarRelatorio(String localRelatorio,
			Map<String, Object> parametros, List<Matricula> lista2,
			String nomeArquivoSaida) throws MalformedURLException {

		InputStream relatorio = null;
		ByteArrayOutputStream relat = new ByteArrayOutputStream();
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		ServletContext contextS = (ServletContext) externalContext.getContext();
		// String arquivo = contextS.getRealPath("/relatorios/" +
		// localRelatorio);

		String arquivo = "c:/relatorio/contrato-parte1.jasper";

		try {

			JasperPrint print = JasperFillManager.fillReport(
					new FileInputStream(new File(arquivo)), parametros,
					new JRBeanCollectionDataSource(lista2));
			JRExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, relat);
			exporter.exportReport();
			relatorio = new ByteArrayInputStream(relat.toByteArray());

		} catch (JRException e) {
			// LOG.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (IOException e) {
			// LOG.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			// LOG.error(e.getMessage(), e);
		}

		return new DefaultStreamedContent(relatorio, "application/pdf",
				nomeArquivoSaida + ".pdf");

	}

	public void teste(Matricula matricula) throws IOException, JRException {

		Map parametros = new HashMap();
		parametros.put("empresa", empresaServico.findOne(1l));

		List<Matricula> lista = new ArrayList<Matricula>();
		lista.add(matricula);

		FacesContext context = FacesContext.getCurrentInstance();

		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();

		ServletOutputStream responseStream = response.getOutputStream();

		String caminho = "c:/relatorio/contrato-parte1.jasper";

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition",
				"attachment; filename=\"relatorio.pdf\"");

		// JasperPrint preencher = JasperFillManager.fillReport(caminho,
		// null,new JRBeanCollectionDataSource(lista));

		JasperRunManager.runReportToPdfStream(new FileInputStream(new File(
				caminho)), response.getOutputStream(), parametros,
				new JRBeanCollectionDataSource(lista));

		// JasperExportManager.exportReportToPdfStream(preencher,responseStream);

		responseStream.flush();
		responseStream.close();
		context.renderResponse();
		context.responseComplete();

	}

	public void imprimirRelatorio(Matricula matricula) throws FileNotFoundException {
		
//		matricula.setTurma(	turmaServico.findByMatricula(matricula));

		ExternalContext econtext = FacesContext.getCurrentInstance()
				.getExternalContext();
		InputStream stream1 = new FileInputStream(
				"C:\\relatorio\\contrato-parte1.jasper");
		InputStream stream2 = new FileInputStream(
				"C:\\relatorio\\contrato-parte2.jasper");

		List j = new ArrayList();
		
		List<Matricula> list = new ArrayList<Matricula>();
		list.add(matricula);
		
		JRDataSource jrRS = new JRBeanCollectionDataSource(list);

		try {
			Map params = new HashMap(); // preenche os parâmetros do relatório 1
			
			params.put("empresa", empresaServico.findOne(1l));
			
			// stream1 =
			// econtext.getResourceAsStream("/WEB-INF/reports/rel1.jasper");
			j.add(JasperFillManager.fillReport(stream1, params, new JRBeanCollectionDataSource(list)));

			params = new HashMap(); // preenche os parâmetros do relatório 2
			params.put("empresa", empresaServico.findOne(1l));
			// stream =
			// econtext.getResourceAsStream("/WEB-INF/reports/rel2.jasper");
			j.add(JasperFillManager.fillReport(stream2, params,new JRBeanCollectionDataSource(list) ));

			JRPdfExporter exporter = new JRPdfExporter();
			HttpServletResponse response = (HttpServletResponse) econtext
					.getResponse();
			FacesContext fcontext = FacesContext.getCurrentInstance();
			ByteArrayOutputStream retorno = new ByteArrayOutputStream();

			exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, j);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, retorno);
			exporter.setParameter(
					JRPdfExporterParameter.IS_CREATING_BATCH_MODE_BOOKMARKS,
					Boolean.TRUE);
			exporter.exportReport();

			byte[] retornoArray = retorno.toByteArray();

			response.setContentType("application/pdf");
			response.setContentLength(retornoArray.length);

			OutputStream output = response.getOutputStream();
			output.write(retornoArray);
			output.flush();
			output.close();
			fcontext.responseComplete();

		} catch (RuntimeException e) {
			// logar e tratar exception
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				stream1.close();
				stream2.close();
			} catch (IOException e) {
				// logar e tratar exception
			}
		}

	}

	public void novo() {
		this.entidade = new Matricula();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);
	}

	public void telaPeaquisa() {
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public Matricula getEntidade() {
		return entidade;
	}

	public void setEntidade(Matricula entidade) {
		this.entidade = entidade;
	}

	public Matricula getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(Matricula pesquisa) {
		this.pesquisa = pesquisa;
	}

	public List<Matricula> getLista() {
		return lista;
	}

	public void setLista(List<Matricula> lista) {
		this.lista = lista;
	}

	public PaginaCentralControlador getPaginaCentralControlador() {
		return paginaCentralControlador;
	}

	public void setPaginaCentralControlador(
			PaginaCentralControlador paginaCentralControlador) {
		this.paginaCentralControlador = paginaCentralControlador;
	}

	public MatriculaServico getServico() {
		return servico;
	}

	public void setServico(MatriculaServico servico) {
		this.servico = servico;
	}

	public BoletimServico getBoletimServico() {
		return boletimServico;
	}

	public void setBoletimServico(BoletimServico boletimServico) {
		this.boletimServico = boletimServico;
	}

	public RelatorioUtil getRelatorioUtil() {
		return relatorioUtil;
	}

	public void setRelatorioUtil(RelatorioUtil relatorioUtil) {
		this.relatorioUtil = relatorioUtil;
	}

	public void telaPesquisa() {
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public EmpresaServico getEmpresaServico() {
		return empresaServico;
	}

	public void setEmpresaServico(EmpresaServico empresaServico) {
		this.empresaServico = empresaServico;
	}

	public TurmaServico getTurmaServico() {
		return turmaServico;
	}

	public void setTurmaServico(TurmaServico turmaServico) {
		this.turmaServico = turmaServico;
	}

}