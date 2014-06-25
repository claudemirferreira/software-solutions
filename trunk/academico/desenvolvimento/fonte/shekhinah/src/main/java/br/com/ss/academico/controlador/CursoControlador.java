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
import br.com.ss.academico.servico.CursoServico;
import br.com.ss.core.seguranca.dominio.Usuario;
import br.com.ss.core.seguranca.servico.IService;
import br.com.ss.core.web.controlador.ControladorGenerico;
import br.com.ss.core.web.utils.Util;

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

	
}