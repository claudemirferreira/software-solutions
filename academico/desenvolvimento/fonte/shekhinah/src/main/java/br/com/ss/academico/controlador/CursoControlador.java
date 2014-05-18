package br.com.ss.academico.controlador;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.ss.academico.dominio.Curso;
import br.com.ss.academico.servico.CursoServico;
import br.com.ss.academico.servico.IService;

@ManagedBean
@SessionScoped
public class CursoControlador extends ControladorGenerico<Curso> {

	private static final long serialVersionUID = -6832271293709421841L;

	@ManagedProperty(value = "#{cursoServicoImpl}")
	private CursoServico servico;

	@Override
	public void init() { }

	@Override
	protected String getNomeRelatorio() {
		// TODO #Peninha ver relatorio
		return null;
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
}