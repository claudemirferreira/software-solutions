package br.com.ss.academico.ireport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

@ManagedBean
@SessionScoped
public class RelatorioUtil {

	@ManagedProperty(value = "#{dataSource}")
	private DriverManagerDataSource dataSource;

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

	public void gerarRelatorioWeb(List lista, Map parametros,
			String nome) throws FileNotFoundException {

		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		// ServletContext servletContext = (ServletContext)
		// externalContext.getContext();
		// String arquivo = context.getRealPath("/relatorio/"+nome);

		String arquivo = "c:/relatorio/" + nome;
		
		InputStream stream = new FileInputStream(arquivo);

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
}