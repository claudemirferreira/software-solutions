package br.com.ss.academico.controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
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

import br.com.ss.academico.dominio.Curso;
import br.com.ss.academico.dominio.Usuario;
import br.com.ss.academico.servico.CursoServico;
import br.com.ss.academico.servico.IService;
import br.com.ss.academico.utils.Util;

import com.lowagie.text.DocumentException;

@ManagedBean
@SessionScoped
public class CursoControlador extends ControladorGenerico<Curso> {

	private static final long serialVersionUID = -6832271293709421841L;

	@ManagedProperty(value = "#{cursoServicoImpl}")
	private CursoServico servico;
	
	private String nomeRelatorio = "curso.jasper";
	
	private boolean visualizar = false;
	
	@Override
	protected String getNomeRelatorio() {
		// FIXME #Peninha ver relatorio
		return this.nomeRelatorio;
	}

	@Override
	protected IService<Curso, Long> getService() {
		return servico;
	}

	public CursoServico getServico() {
		return servico;
	}

	public void setServico(CursoServico servico) {
		this.servico = servico;
	}

	public boolean isVisualizar() {
		return visualizar;
	}

	public void setVisualizar(boolean visualizar) {
		this.visualizar = visualizar;
	}

	public void setNomeRelatorio(String nomeRelatorio) {
		this.nomeRelatorio = nomeRelatorio;
	}
	
	public void visualiarRelatorio() {
		this.visualizar = true;
	}

	public void imprimirPdf() throws IOException, DocumentException {

		System.out.println("imprimirPdf");
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext externalContext = fc.getExternalContext();
		ServletContext context = (ServletContext) externalContext.getContext();
		
		String arquivo = context.getRealPath("WEB-INF/jasper/" +getNomeRelatorio());

		// BLOCO USADO PARA TESTES
		List<Usuario> usuarios = new ArrayList<Usuario>();
		Usuario u = new Usuario();
		u.setLogin("login");
		usuarios.add(u);
		// BLOCO USADO PARA TESTES

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		Map<String, Object> params = new HashMap<String, Object>();

		externalContext.setResponseContentType("application/pdf");

		try {
			
			byte[] relatorio = relatorioUtil.gerarRelatorioWebBytes(listaPesquisa, params, arquivo);
			
			if (relatorio == null || relatorio.length < 1000) {
				arquivo = context.getRealPath("/resources/relatorioVazio.pdf");
				FileInputStream file = new FileInputStream(new File(arquivo));
				relatorio = Util.getBytes(file);
			}

			externalContext.getResponseOutputStream().write(relatorio);

		} catch (FileNotFoundException e) {

			arquivo = context.getRealPath("/resources/relatorioNotFound.pdf");
			FileInputStream file = new FileInputStream(new File(arquivo));

			externalContext.getResponseOutputStream()
					.write(Util.getBytes(file));

		} finally {
			setVisualizar(true);
			
			fc.responseComplete();
			
		}

	}

}