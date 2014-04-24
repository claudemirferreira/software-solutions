package br.com.ss.academico.controlador;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.ss.academico.dominio.Aluno;
import br.com.ss.academico.dominio.Configuracao;
import br.com.ss.academico.dominio.Matricula;
import br.com.ss.academico.dominio.Mensalidade;
import br.com.ss.academico.dominio.Observacao;
import br.com.ss.academico.dominio.Turma;
import br.com.ss.academico.dominio.Usuario;
import br.com.ss.academico.enumerated.Constants;
import br.com.ss.academico.enumerated.Meses;
import br.com.ss.academico.enumerated.NaoSim;
import br.com.ss.academico.enumerated.StatusMatricula;
import br.com.ss.academico.enumerated.StatusPagamento;
import br.com.ss.academico.ireport.RelatorioUtil;
import br.com.ss.academico.servico.BoletimServico;
import br.com.ss.academico.servico.MatriculaServico;
import br.com.ss.academico.servico.TurmaServico;

@ManagedBean
@SessionScoped
public class AlunoMatriculaControlador {

	@ManagedProperty(value = "#{matriculaServicoImpl}")
	private MatriculaServico servicoMatricula;

	@ManagedProperty(value = "#{turmaServicoImpl}")
	private TurmaServico servicoTurma;
	
	@ManagedProperty(value = "#{boletimServicoImpl}")
	private BoletimServico boletimServico;
	
	@ManagedProperty(value = "#{relatorioUtil}")
	protected RelatorioUtil relatorioUtil;

	private Aluno alunoMatricula;

	private boolean modalCadastro;

	private Matricula matricula;

	private List<Turma> turmas;

	private List<SelectItem> naoSimList;
	
	private List<SelectItem> statusMatriculaList;

	private List<SelectItem> mesesList;

	private Meses mesSelecionado;

	private Observacao observacaoMatricula;

	private Configuracao configuracao;

	private Usuario usuarioLogado;

	
	/* --------- Overrides ------------------ */

	@PostConstruct
	public void init() {
		alunoMatricula = new Aluno();
		naoSimList = createNaoSimList();
		statusMatriculaList = createStatusMatriculaList();
		mesesList = createMesesList();
		carregarDiaVencimento();

		usuarioLogado = (Usuario) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
	}

	private void carregarDiaVencimento() {
		// FIXME carregar configuracao do contexto
		configuracao = new Configuracao();
		configuracao.setDiaVencimento(10); // FIXME recuperar a data da configuracao
	}

	
	public void imprimirContrato(Matricula matricula) {
		// FIXME #Peninha criar o relatorio..
		List<Matricula> lista = new ArrayList<Matricula>();
		lista.add(matricula);
		Map<String, Object> parametros = new HashMap<String, Object>();

		relatorioUtil.gerarRelatorioComDownload(lista, parametros, "contrato.jasper");

	}

	public void renderObservacao() {
		if (matricula.getStatus() != StatusMatricula.ATIVA
				&& observacaoMatricula == null) {
			observacaoMatricula = new Observacao();
			observacaoMatricula.setMatricula(matricula);
		}
	}

	public void showModalMatricula(Aluno aluno) {
		alunoMatricula = aluno;
		showModalPesquisaMatricula();
	}

	private List<SelectItem> createNaoSimList() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		for (NaoSim c : NaoSim.values()) {
			list.add(new SelectItem(c.getValue(), c.getDescricao()));
		}
		return list;
	}

	private List<SelectItem> createStatusMatriculaList() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		for (StatusMatricula c : StatusMatricula.values()) {
			list.add(new SelectItem(c, c.getDescricao()));
		}
		return list;
	}

	private List<SelectItem> createMesesList() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		for (Meses mes : Meses.values()) {
			list.add(new SelectItem(mes, mes.getDescricao()));
		}
		return list;
	}

	public void showModalPesquisaMatricula() {
		modalCadastro = false;
		List<Matricula> matriculas = servicoMatricula.findByAluno(alunoMatricula);
		alunoMatricula.setMatriculas(matriculas);
	}

	public void showModalCadastroMatricula(Matricula matricula) {
		this.matricula = matricula;
		modalCadastro = true;
		turmas = servicoTurma.listarTodos();
		selectTurma();
	}

	/**
	 * Nova matricula.
	 */
	public void showModalCadastroMatricula() {
		modalCadastro = true;
		matricula = createMatricula();
		turmas = servicoTurma.listarTodos();
		carregarMesSelecionado();
	}

	private void carregarMesSelecionado() {
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		mesSelecionado = Meses.getEnum(cal.get(Calendar.MONTH) + 1);
	}

	private Matricula createMatricula() {
		Matricula matricula = new Matricula();
		matricula.setData(new Date());
		matricula.setStatus(StatusMatricula.ATIVA);
		return matricula;
	}

	public void selectTurma() {
		matricula.setValor(matricula.getTurma().getCurso().getValor());
		matricula.setIntegral(false);

		Long vagasDisponiveis = servicoMatricula
				.countVagasDisponiveis(matricula.getTurma());
		matricula.getTurma().setVagasDisponiveis(vagasDisponiveis.intValue());

		if (!(vagasDisponiveis > 0)) {
			showMessage("Não há vaga disponível para a turma selecionada.",
					FacesMessage.SEVERITY_WARN);
		}
	}

	protected void showMessage(String msg, Severity severityInfo) {
		FacesMessage facesMessage = new FacesMessage();
		facesMessage.setSeverity(severityInfo);
		facesMessage.setSummary(msg);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
	public void salvarMatricula() {

		try {
			if (matricula.getStatus() != StatusMatricula.ATIVA
					&& observacaoMatricula != null) {
				
				// salva a observacao da matricula
				cancelarMatricula();
				
			} else if (matricula.getStatus() == StatusMatricula.ATIVA
					&& !matricula.isPersistent()) {
				
				// cria as mensalidades
				gerarMensalidadesMatricula();
				
				// FIXME #Peninha: verificar se vai gerar boletim neste momento..
//				 this.boletimServico.gerarBoletim(matricula);				
			}
			
			matricula.setAluno(alunoMatricula);
			matricula = servicoMatricula.salvar(matricula);

			showModalPesquisaMatricula();
			showMessage(Constants.MSG_SUCESSO, FacesMessage.SEVERITY_INFO);
			observacaoMatricula = null;

			// gera o boletim para o aluno matriculado
			this.boletimServico.gerarBoletim(matricula);

		} catch (Exception e) {
			e.printStackTrace();
			showMessage(Constants.MSG_ERRO, FacesMessage.SEVERITY_ERROR);
		}
	}

	private void cancelarMatricula() {
		observacaoMatricula.setUsuario(usuarioLogado);
		matricula.getObservacoes().add(observacaoMatricula);

		// cancelar as mensalidades
		for (Mensalidade mens : matricula.getMensalidades()) {
			mens.setStatusPagamento(StatusPagamento.CANCELADO);
		}
	}

	private void gerarMensalidadesMatricula() {
		int mesInicial = mesSelecionado.getId();
		int mesFinal = Constants.DOZE;
		
		int qtMensalidades = mesFinal - (mesInicial -1);
		
		// calcula o valor da mensalidade dividindo o valor do curso pela quantidade de meses
		double valorMens = matricula.getValor() / qtMensalidades;
		
		for (int i = mesInicial; i <= mesFinal; i++) {
			matricula.getMensalidades().add(createMensalidade(i, valorMens));
		}
	}

	private Mensalidade createMensalidade(int mes, double valorVencimento) {
		Mensalidade mens = new Mensalidade();
		mens.setDataVencimento(criarDataVencimento(mes));
		mens.setMatricula(matricula);
		mens.setSequencial(mes);
		mens.setStatusPagamento(StatusPagamento.PENDENTE);
		mens.setValorVencimento(valorVencimento);
		mens.setUsuario(usuarioLogado);
		return mens;
	}

	private Date criarDataVencimento(int mes) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		cal.set(cal.get(Calendar.YEAR), mes - 1,
				configuracao.getDiaVencimento());
		return cal.getTime();
	}

	
	/* -------- Gets/Sets ------------------- */
	public RelatorioUtil getRelatorioUtil() {
		return relatorioUtil;
	}

	public void setRelatorioUtil(RelatorioUtil relatorioUtil) {
		this.relatorioUtil = relatorioUtil;
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

	public List<SelectItem> getNaoSimList() {
		return naoSimList;
	}

	public List<SelectItem> getStatusMatriculaList() {
		return statusMatriculaList;
	}

	public Observacao getObservacaoMatricula() {
		return observacaoMatricula;
	}

	public void setObservacaoMatricula(Observacao observacaoMatricula) {
		this.observacaoMatricula = observacaoMatricula;
	}

	public List<SelectItem> getMesesList() {
		return mesesList;
	}

	public Meses getMesSelecionado() {
		return mesSelecionado;
	}

	public void setMesSelecionado(Meses mesSelecionado) {
		this.mesSelecionado = mesSelecionado;
	}

	public BoletimServico getBoletimServico() {
		return boletimServico;
	}

	public void setBoletimServico(BoletimServico boletimServico) {
		this.boletimServico = boletimServico;
	}

}