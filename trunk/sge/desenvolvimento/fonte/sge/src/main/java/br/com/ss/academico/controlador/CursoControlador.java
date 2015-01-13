package br.com.ss.academico.controlador;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import br.com.ss.academico.dominio.Curso;
import br.com.ss.academico.enumerated.StatusBoletim;
import br.com.ss.academico.enumerated.TipoCurso;
import br.com.ss.academico.servico.CursoServico;
import br.com.ss.core.seguranca.servico.IService;
import br.com.ss.core.web.controlador.ControladorGenerico;

@ManagedBean
@SessionScoped
public class CursoControlador extends ControladorGenerico<Curso> {

	private static final long serialVersionUID = -6832271293709421841L;

	@ManagedProperty(value = "#{cursoServicoImpl}")
	private CursoServico servico;

	private List<SelectItem> tipoCursoList;
	

	private String nomeRelatorio = "curso.jasper";
	

	@PostConstruct
	@Override
	public void setup() {
		super.setup();
		
		tipoCursoList = new ArrayList<SelectItem>();
		for (TipoCurso tc : TipoCurso.values()) {
			tipoCursoList.add(new SelectItem(tc, tc.getDescricao()));
		}
	}
	

	@Override
	protected String getNomeRelatorioJasper() {
		return this.nomeRelatorio;
	}

	@Override
	public String getTituloRelatorio() {
		return "RELATÓRIO DE CURSO";
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

	public void setNomeRelatorio(String nomeRelatorio) {
		this.nomeRelatorio = nomeRelatorio;
	}

	public List<SelectItem> getTipoCursoList() {
		return tipoCursoList;
	}

}