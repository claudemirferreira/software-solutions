package br.com.ss.academico.controlador;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.ss.academico.dominio.Aluno;
import br.com.ss.academico.dominio.Matricula;
import br.com.ss.academico.dominio.Responsavel;
import br.com.ss.academico.dominio.Turma;
import br.com.ss.academico.enumerated.Constants;
import br.com.ss.academico.ireport.RelatorioUtil;
import br.com.ss.academico.servico.AlunoServico;
import br.com.ss.academico.servico.MatriculaServico;
import br.com.ss.academico.servico.ResponsavelServico;
import br.com.ss.academico.servico.TurmaServico;

@ManagedBean
@SessionScoped
public class AlunoControlador implements Serializable {

	private static final long serialVersionUID = -6832271293709421841L;

	private Aluno entidade;

	private Aluno pesquisa;

	private List<Responsavel> responsaveis;

	private List<Aluno> lista;

	private final String TELA_CADASTRO = "paginas/aluno/cadastro.xhtml";
	private final String TELA_PESQUISA = "paginas/aluno/pesquisa.xhtml";

	@ManagedProperty(value = "#{alunoServicoImpl}")
	private AlunoServico servico;

	@ManagedProperty(value = "#{responsavelServicoImpl}")
	private ResponsavelServico responsavelServico;

	@ManagedProperty(value = "#{matriculaServicoImpl}")
	private MatriculaServico servicoMatricula;

	@ManagedProperty(value = "#{turmaServicoImpl}")
	private TurmaServico servicoTurma;
	
	@ManagedProperty(value = "#{paginaCentralControlador}")
	private PaginaCentralControlador paginaCentralControlador;

	@ManagedProperty(value = "#{relatorioUtil}")
	private RelatorioUtil relatorioUtil;

	private Aluno alunoMatricula;
	
	private boolean modalCadastro;

	private Matricula matricula;
	
	private List<Turma> turmas;
	

	public void init() {
		this.lista = servico.listarTodos();
		telaPesquisa();
	}

	public AlunoControlador() {
		this.entidade = new Aluno();
		this.pesquisa = new Aluno();
		this.responsaveis = new ArrayList<Responsavel>();
	}

	public void pesquisar() {
		this.lista = servico.findByNomeLike(this.pesquisa.getNome());
	}

	public void detalhe(Aluno aluno) {
		this.entidade = aluno;
		this.responsaveis = responsavelServico.listarTodos();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);
	}

	public void salvar() {
		if (this.entidade.getDataCadastro() == null)
			this.entidade.setDataCadastro(new Date());

		this.servico.salvar(this.entidade);
		this.lista = servico.listarTodos();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public void excluir(Aluno aluno) {
		this.servico.remover(aluno);
		this.lista = servico.listarTodos();
	}

	public void novo() {
		this.entidade = new Aluno();
		this.responsaveis = responsavelServico.listarTodos();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);
	}

	public void telaPeaquisa() {
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public void imprimir() throws FileNotFoundException {
		relatorioUtil.gerarRelatorioWeb(this.lista, null, "aluno.jasper");
	}

	
	
	public void showModalMatricula(Aluno aluno) {
		
		alunoMatricula = aluno;
		
		List<Matricula> matriculas = servicoMatricula.findByAluno(aluno);
		alunoMatricula.setMatriculas(matriculas);
		
		showModalPesquisaMatricula();
	}
	
	
	public void showModalPesquisaMatricula() {
		modalCadastro = false;
	}
	
	public void showModalCadastroMatricula() {
		modalCadastro = true;
		matricula = new Matricula();
		
		turmas = servicoTurma.listarTodos();
	}
	
	
	public void selectTurma() {
		matricula.setValor(matricula.getTurma().getCurso().getValor());
		matricula.setIntegral(false);
		
		Long vagasDisponiveis = servicoMatricula.countVagasDisponiveis(matricula.getTurma());
		matricula.getTurma().setVagasDisponiveis(vagasDisponiveis.intValue());
		
	}
	
	public void salvarMatricula() {

		try {
			matricula.setAluno(alunoMatricula);
			servicoMatricula.salvar(matricula);
			
			showMessage(Constants.MSG_SUCESSO, FacesMessage.SEVERITY_INFO);
			
		} catch (Exception e) {
			e.printStackTrace();
			showMessage(Constants.MSG_ERRO, FacesMessage.SEVERITY_ERROR);
		}
		
	}

	private void showMessage(String msg, Severity severityInfo) {
		FacesMessage facesMessage = new FacesMessage();  
        facesMessage.setSeverity(severityInfo);  
        facesMessage.setSummary(msg);  
        FacesContext.getCurrentInstance().addMessage(null, facesMessage); 		
	}


	
	public Aluno getEntidade() {
		return entidade;
	}

	public void setEntidade(Aluno entidade) {
		this.entidade = entidade;
	}

	public Aluno getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(Aluno pesquisa) {
		this.pesquisa = pesquisa;
	}

	public List<Aluno> getLista() {
		return lista;
	}

	public void setLista(List<Aluno> lista) {
		this.lista = lista;
	}

	public PaginaCentralControlador getPaginaCentralControlador() {
		return paginaCentralControlador;
	}

	public void setPaginaCentralControlador(
			PaginaCentralControlador paginaCentralControlador) {
		this.paginaCentralControlador = paginaCentralControlador;
	}

	public AlunoServico getServico() {
		return servico;
	}

	public void setServico(AlunoServico servico) {
		this.servico = servico;
	}

	public void telaPesquisa() {
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public RelatorioUtil getRelatorioUtil() {
		return relatorioUtil;
	}

	public void setRelatorioUtil(RelatorioUtil relatorioUtil) {
		this.relatorioUtil = relatorioUtil;
	}

	public List<Responsavel> getResponsaveis() {
		return responsaveis;
	}

	public void setResponsaveis(List<Responsavel> responsaveis) {
		this.responsaveis = responsaveis;
	}

	public ResponsavelServico getResponsavelServico() {
		return responsavelServico;
	}

	public void setResponsavelServico(ResponsavelServico responsavelServico) {
		this.responsavelServico = responsavelServico;
	}

	public Aluno getAlunoMatricula() {
		return alunoMatricula;
	}

	public boolean isModalCadastro() {
		return modalCadastro;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public MatriculaServico getServicoMatricula() {
		return servicoMatricula;
	}

	public void setServicoMatricula(MatriculaServico servicoMatricula) {
		this.servicoMatricula = servicoMatricula;
	}

	public TurmaServico getServicoTurma() {
		return servicoTurma;
	}

	public void setServicoTurma(TurmaServico servicoTurma) {
		this.servicoTurma = servicoTurma;
	}

}