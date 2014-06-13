package br.com.ss.academico.controlador;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.ss.academico.dominio.Boletim;
import br.com.ss.academico.dominio.Turma;
import br.com.ss.academico.servico.BoletimServico;
import br.com.ss.academico.servico.TurmaServico;
import br.com.ss.core.seguranca.servico.IService;
import br.com.ss.core.web.controlador.ControladorGenerico;

@ManagedBean
@SessionScoped
public class BoletimControlador extends ControladorGenerico<Boletim> {

	private static final long serialVersionUID = -6832271293709421841L;

	@ManagedProperty(value = "#{boletimServicoImpl}")
	private BoletimServico servico;

	@ManagedProperty(value = "#{turmaServicoImpl}")
	private TurmaServico turmaServico;

	private String nomeRelatorio = "boletim.jasper";

	private Turma turma;
	
	private List<Turma> turmas;
	
	@PostConstruct
	@Override
	public void setup() {
		super.setup();
		this.turmas = turmaServico.listarTodos();
	}

	@Override
	protected String getNomeRelatorio() {
		// FIXME #Peninha ver relatorio
		return this.nomeRelatorio;
	}

	@Override
	protected IService<Boletim, Long> getService() {
		return servico;
	}

	public BoletimServico getServico() {
		return servico;
	}

	public void setServico(BoletimServico servico) {
		this.servico = servico;
	}

	public void setNomeRelatorio(String nomeRelatorio) {
		this.nomeRelatorio = nomeRelatorio;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public TurmaServico getTurmaServico() {
		return turmaServico;
	}

	public void setTurmaServico(TurmaServico turmaServico) {
		this.turmaServico = turmaServico;
	}
	
	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

}