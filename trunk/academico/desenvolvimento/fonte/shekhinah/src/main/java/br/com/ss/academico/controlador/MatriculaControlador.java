package br.com.ss.academico.controlador;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import br.com.ss.academico.dominio.Aluno;
import br.com.ss.academico.dominio.Curso;
import br.com.ss.academico.dominio.Matricula;
import br.com.ss.academico.dominio.Turma;
import br.com.ss.academico.enumerated.StatusMatricula;
import br.com.ss.academico.enumerated.Turno;
import br.com.ss.academico.servico.AlunoServico;
import br.com.ss.academico.servico.CursoServico;
import br.com.ss.academico.servico.EmpresaServico;
import br.com.ss.academico.servico.MatriculaServico;
import br.com.ss.core.seguranca.servico.IService;
import br.com.ss.core.web.controlador.ControladorGenerico;
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

	@ManagedProperty(value = "#{cursoServicoImpl}")
	private CursoServico servicoCurso;

	@ManagedProperty(value = "#{empresaServicoImpl}")
	private EmpresaServico empresaServico;

	private List<SelectItem> statusList;

	private List<SelectItem> turnoList;

	private static final String PATH_REPORT = "resources" + File.separator + "jasper" + File.separator;

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


	public void imprimirContrato(Matricula matricula)
			throws FileNotFoundException {

		ExternalContext econtext = FacesContext.getCurrentInstance()
				.getExternalContext();
		FacesContext context = FacesContext.getCurrentInstance();

		String webPath = context.getExternalContext().getRealPath("/");
		String reportPath1 = webPath + PATH_REPORT + "contrato-parte1.jasper";
		String reportPath2 = webPath + PATH_REPORT + "contrato-parte2.jasper";

		InputStream stream1 = new FileInputStream(reportPath1);
		InputStream stream2 = new FileInputStream(reportPath2);

		List<JasperPrint> lista = new ArrayList<JasperPrint>();

		List<Matricula> list = new ArrayList<Matricula>();
		list.add(matricula);

		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("empresa", empresaServico.findOne(1l));
			lista.add(JasperFillManager.fillReport(stream1, params,
					new JRBeanCollectionDataSource(list)));

			lista.add(JasperFillManager.fillReport(stream2, params,
					new JRBeanCollectionDataSource(list)));

			JRPdfExporter exporter = new JRPdfExporter();
			HttpServletResponse response = (HttpServletResponse) econtext
					.getResponse();
			FacesContext fcontext = FacesContext.getCurrentInstance();
			ByteArrayOutputStream retorno = new ByteArrayOutputStream();

			exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, lista);
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

	public CursoServico getServicoCurso() {
		return servicoCurso;
	}

	public void setServicoCurso(CursoServico servicoCurso) {
		this.servicoCurso = servicoCurso;
	}

}