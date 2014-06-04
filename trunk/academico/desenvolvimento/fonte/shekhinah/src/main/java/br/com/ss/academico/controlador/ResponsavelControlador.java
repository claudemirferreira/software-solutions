package br.com.ss.academico.controlador;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.ss.academico.dominio.Aluno;
import br.com.ss.academico.dominio.Responsavel;
import br.com.ss.academico.servico.ResponsavelServico;
import br.com.ss.core.seguranca.servico.IService;
import br.com.ss.core.web.controlador.ControladorGenerico;

@ManagedBean
@SessionScoped
public class ResponsavelControlador extends ControladorGenerico<Responsavel> {

	private static final long serialVersionUID = -6832271293709421841L;

	private Aluno aluno;

	@ManagedProperty(value = "#{responsavelServicoImpl}")
	private ResponsavelServico servico;


	@Override
	protected void init() {
		aluno = new Aluno();
	}
	
	@Override
	protected String getNomeRelatorio() {
		// FIXME #Peninha verificar o relatorio..
		return null;
	}

	@Override
	public void pesquisar() {
		this.listaPesquisa = ( ( ResponsavelServico ) getService() ).pesquisar(pesquisa, aluno.getNome());
	}
	
	@Override
	protected IService<Responsavel, Long> getService() {
		return servico;
	}

	public ResponsavelServico getServico() {
		return servico;
	}

	public void setServico(ResponsavelServico servico) {
		this.servico = servico;
	}

	public Aluno getAluno() {
		return aluno;
	}

}