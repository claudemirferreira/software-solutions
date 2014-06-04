package br.com.ss.academico.controlador;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.ss.academico.dominio.Aluno;
import br.com.ss.academico.dominio.Curso;
import br.com.ss.academico.dominio.Matricula;
import br.com.ss.academico.dominio.Turma;
import br.com.ss.academico.enumerated.StatusMatricula;
import br.com.ss.academico.enumerated.Turno;
import br.com.ss.academico.servico.AlunoServico;
import br.com.ss.academico.servico.BoletimServico;
import br.com.ss.academico.servico.CursoServico;
import br.com.ss.academico.servico.EmpresaServico;
import br.com.ss.academico.servico.MatriculaServico;
import br.com.ss.academico.servico.TurmaServico;
import br.com.ss.core.seguranca.servico.IService;
import br.com.ss.core.web.controlador.ControladorGenerico;
import br.com.ss.core.web.enumerated.Constants;
import br.com.ss.core.web.ireport.RelatorioUtil;
import br.com.ss.core.web.utils.ReflectionsUtil;

@ManagedBean
@SessionScoped
public class MatriculaControlador extends ControladorGenerico<Matricula> {

	private static final long serialVersionUID = -6832271293709421841L;

	@ManagedProperty(value = "#{matriculaServicoImpl}")
	private MatriculaServico servico;

	@ManagedProperty(value = "#{alunoServicoImpl}")
	private AlunoServico servicoAluno;

	@ManagedProperty(value = "#{boletimServicoImpl}")
	private BoletimServico boletimServico;

	@ManagedProperty(value = "#{cursoServicoImpl}")
	private CursoServico servicoCurso;
	
	@ManagedProperty(value = "#{empresaServicoImpl}")
	private EmpresaServico empresaServico;
	
	private List<SelectItem> statusList;

	private List<SelectItem> turnoList;
	
	@Override
	protected void init() {
		statusList = new ArrayList<SelectItem>();
		for (StatusMatricula sm : StatusMatricula.values()) {
			statusList.add(new SelectItem(sm, sm.getDescricao()));
		}
		turnoList = new ArrayList<SelectItem>();
		for (Turno t : Turno.values()) {
			turnoList.add(new SelectItem(t, t.getDescricao()));
		}
		pesquisa.setTurma(new Turma());
		pesquisa.setAluno(new Aluno());
	}

	@Override
	protected String getNomeRelatorio() {
		// FIXME #Peninha
		return null;
	}

	@Override
	protected IService<Matricula, Long> getService() {
		return servico;
	}

	
	@Override
	public String novo() {
		// inicializa o bean alunoMatriculaControlador
		ReflectionsUtil.invokeBean("alunoMatriculaControlador", "novo");
		return super.novo();
	}
	
	/**
	 * Lista os Alunos - para a lista do auto-complete da tela de pesquisa.
	 */
	public List<Aluno> listarAlunos(String nome) {
		pesquisa.setAluno(new Aluno());
		pesquisa.getAluno().setNome(nome);
		return servicoAluno.pesquisar(pesquisa.getAluno());
	}

	/**
	 * Lista os Alunos - para a lista do auto-complete da tela de pesquisa.
	 */
	public List<Curso> listarCursos(String nome) {
		return servicoCurso.findByNomeLike(nome);
	}


	public String salvar() {

		// gerar boletim
		if (this.entidade.getIdMatricula() == null) {
			this.boletimServico.gerarBoletim(this.entidade);
		}
		
		return super.salvar();

	}

	public void imprimir(Matricula matricula) {
		List<Matricula> lista = new ArrayList<Matricula>();
		lista.add(matricula);
		Map<String, Object> parametros = new HashMap<String, Object>();

		relatorioUtil.gerarRelatorioComDownload(lista, parametros, "contrato-parte1.jasper");
		
		// FIXME #Peninha padronizar a impressao

	}

	public void imprimircontrato(Matricula matricula) throws FileNotFoundException {
		// TODO criar o relatorio..
		List<Matricula> listMat = new ArrayList<Matricula>();
		listMat.add(matricula);
		relatorioUtil.gerarRelatorioWeb(listMat, null, "contrato-parte1.jasper");
		
		// FIXME #Peninha padronizar a impressao
		
	}

	// FIXME #Peninha, validar se está utilizando este metodo, se nao, remover
	private void gerar(Matricula matricula) throws MalformedURLException {
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<Matricula> lista = new ArrayList<Matricula>();
		String nome = "contrato-parte1.jasper";

		gerarRelatorio(null, parametros, lista, nome);

	}

	private StreamedContent gerarRelatorio(String localRelatorio, Map<String, Object> parametros, 
								List<Matricula> lista2, String nomeArquivoSaida) throws MalformedURLException {

		InputStream isRelatorio = null;
		try {
			ByteArrayOutputStream relat = new ByteArrayOutputStream();
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			ServletContext contextS = (ServletContext) externalContext.getContext();
			// String arquivo = contextS.getRealPath("/relatorios/" +
			// localRelatorio);
			
			String arquivo = "c:/relatorio/contrato-parte1.jasper";

			JasperPrint print = JasperFillManager.fillReport(
					new FileInputStream(new File(arquivo)), parametros,
					new JRBeanCollectionDataSource(lista2));
			JRExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, relat);
			exporter.exportReport();
			isRelatorio = new ByteArrayInputStream(relat.toByteArray());

		} catch (Exception e) {
			e.printStackTrace();
			showMessage(Constants.MSG_ERRO, FacesMessage.SEVERITY_ERROR);
			return null;
		}

		return new DefaultStreamedContent(isRelatorio, "application/pdf", nomeArquivoSaida + ".pdf");

	}
	
	/* FIXME remover metodos de teste..
	
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
	*/
	
	 
	@ManagedProperty(value = "#{turmaServicoImpl}")
	private TurmaServico turmaServico;
	
	// FIXME #Peninha: refatorar esse metodo
	
	public void imprimirRelatorio(Matricula matricula) throws FileNotFoundException {
		
//		matricula.setTurma(	turmaServico.findByMatricula(matricula));

		ExternalContext econtext = FacesContext.getCurrentInstance()
				.getExternalContext();
		InputStream stream1 = new FileInputStream( "C:\\relatorio\\contrato-parte1.jasper");
		InputStream stream2 = new FileInputStream( "C:\\relatorio\\contrato-parte2.jasper");

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

	/* -------------- Gets/Sets --------------------- */
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

	public AlunoServico getServicoAluno() {
		return servicoAluno;
	}

	public void setServicoAluno(AlunoServico servicoAluno) {
		this.servicoAluno = servicoAluno;
	}

	public List<SelectItem> getStatusList() {
		return statusList;
	}

	public List<SelectItem> getTurnoList() {
		return turnoList;
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

	public CursoServico getServicoCurso() {
		return servicoCurso;
	}

	public void setServicoCurso(CursoServico servicoCurso) {
		this.servicoCurso = servicoCurso;
	}

}