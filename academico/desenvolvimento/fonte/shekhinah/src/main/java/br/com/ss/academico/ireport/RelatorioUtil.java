package br.com.ss.academico.ireport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
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
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import br.com.ss.academico.dominio.Empresa;
import br.com.ss.academico.servico.EmpresaServico;

@ManagedBean
@SessionScoped
public class RelatorioUtil {

	@ManagedProperty(value = "#{dataSource}")
	private DriverManagerDataSource dataSource;

	@ManagedProperty(value = "#{empresaServicoImpl}")
	private EmpresaServico empresaServico;

	@PostConstruct
	public void init() {
	}

	public void gerarRelatorioWebDatasource(JRDataSource jrRS, Map parametros,
			String arquivo) {
		ServletOutputStream servletOutputStream = null;
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();

		try {
			servletOutputStream = response.getOutputStream();
			JasperRunManager.runReportToPdfStream(new FileInputStream(new File(
					arquivo)), response.getOutputStream(), parametros,
					this.dataSource.getConnection());

			response.setContentType("application/pdf");
			servletOutputStream.flush();
			servletOutputStream.close();
			context.renderResponse();
			context.responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public FileInputStream gerarRelatorio(List lista, Map parametros,
			String arquivo) {

		FileInputStream fis = null;
		JRDataSource jrRS = new JRBeanCollectionDataSource(lista);
		Empresa empresa = empresaServico.findOne(1l);
		parametros.put("empresa", empresa);

		try {

			JasperPrint print = JasperFillManager.fillReport(arquivo,
					parametros, jrRS);

			File arquivoGerado = File.createTempFile("relatorio.", ".pdf");

			JasperExportManager.exportReportToPdfStream(print,
					new FileOutputStream(arquivoGerado));

			fis = new FileInputStream(arquivoGerado);

			// Verificar
			arquivoGerado.delete();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return fis;
	}

	public void gerarRelatorioWeb(List lista, Map parametros, String nome)
			throws FileNotFoundException {

		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		ServletContext servletContext = (ServletContext) externalContext
				.getContext();
		String arquivo = servletContext.getRealPath("WEB-INF/jasper/" + nome);

		// CARREGA O FILE DA UNIDADE C
		// String arquivo = "c:/relatorio/" + nome;

		JRDataSource jrRS = new JRBeanCollectionDataSource(lista);

		ServletOutputStream servletOutputStream = null;
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();

		try {
			servletOutputStream = response.getOutputStream();

			JasperRunManager.runReportToPdfStream(new FileInputStream(new File(
					arquivo)), response.getOutputStream(), parametros, jrRS);

			response.setContentType("application/pdf");
			servletOutputStream.flush();
			servletOutputStream.close();
			context.renderResponse();
			context.responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DriverManagerDataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DriverManagerDataSource dataSource) {
		this.dataSource = dataSource;
	}

	public EmpresaServico getEmpresaServico() {
		return empresaServico;
	}

	public void setEmpresaServico(EmpresaServico empresaServico) {
		this.empresaServico = empresaServico;
	}
}