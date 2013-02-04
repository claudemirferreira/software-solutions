package br.com.ss.centralaamar.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.ss.centralaamar.model.entity.Membro;
import br.com.ss.centralaamar.model.entity.PequenoGrupo;
import br.com.ss.centralaamar.service.IMembroService;
import br.com.ss.centralaamar.service.IPequenoGrupoService;
import br.com.ss.centralaamar.service.IService;

@Component("pequenoGrupoController")
@Named
@Scope("session")
public class PequenoGrupoController extends GenericBean<PequenoGrupo> {

	@Autowired
	private IPequenoGrupoService service;

	@Autowired
	private IMembroService membroService;

	@Getter
	@Setter
	private Membro anfitriao;

	@Getter
	@Setter
	private Membro coordenador;

	@Getter
	@Setter
	private List<Membro> anfitriaos;

	@Getter
	@Setter
	private List<Membro> coordenadors;

	@Override
	protected IService<PequenoGrupo> getService() {
		return service;
	}

	@Override
	public String save() {
		entity.setNome(this.entity.getNome().toUpperCase());
		if (null != this.getAnfitriao())
			entity.setAnfritriao(this.anfitriao);
		this.anfitriao = entity.getAnfritriao();
		if (entity.getCoordenador() != null)
			this.coordenador = entity.getCoordenador();

		try {
			return super.save();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@PostConstruct
	protected void setup() throws InstantiationException,
			IllegalAccessException {
		anfitriaos = membroService.search(new Membro());
		coordenadors = anfitriaos;
		anfitriao = new Membro();
		coordenador = new Membro();

		super.setup();
	}

	public String editar(PequenoGrupo entity) {

		if (this.anfitriao != null)
			entity.setAnfritriao(this.anfitriao);
		if (this.coordenador != null)
			entity.setCoordenador(this.coordenador);

		return super.editar(entity);

	}

	public void print() {
		ByteArrayOutputStream byteOutPutStream = new ByteArrayOutputStream();

		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();

			HttpServletResponse response = (HttpServletResponse) facesContext
					.getExternalContext().getResponse();

			String pathJasper = "D:\\jasper\\pequenoGrupo.jasper";

			JasperPrint preencher = JasperFillManager.fillReport(pathJasper,
					null, new JRBeanCollectionDataSource(this.resultList));

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

}