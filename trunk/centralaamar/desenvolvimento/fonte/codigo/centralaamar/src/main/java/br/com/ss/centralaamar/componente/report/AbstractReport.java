package br.com.ss.centralaamar.componente.report;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import br.com.ss.centralaamar.model.entity.AbstractEntity;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public abstract class AbstractReport<T extends AbstractEntity> implements IReport<T> {
	
	protected abstract String getReportPath();
	
	public abstract List<AbstractEntity> getList();
	
	@Override
	public void print() {
		ByteArrayOutputStream byteOutPutStream = new ByteArrayOutputStream();

		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();

			HttpServletResponse response = (HttpServletResponse) facesContext
					.getExternalContext().getResponse();

			JasperPrint preencher = JasperFillManager.fillReport(
							getReportPath(), 
							null,
							new JRBeanCollectionDataSource(getList()));

			JasperExportManager.exportReportToPdfStream(preencher,
					byteOutPutStream);

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
