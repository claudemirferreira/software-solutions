package br.com.ss.academico.controlador;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import br.com.ss.academico.dominio.Aluno;
import br.com.ss.academico.dominio.Configuracao;
import br.com.ss.academico.dominio.Matricula;
import br.com.ss.academico.dominio.Mensalidade;
import br.com.ss.academico.dominio.Observacao;
import br.com.ss.academico.dominio.Turma;
import br.com.ss.academico.enumerated.Constants;
import br.com.ss.academico.enumerated.Meses;
import br.com.ss.academico.enumerated.NaoSim;
import br.com.ss.academico.enumerated.StatusMatricula;
import br.com.ss.academico.enumerated.StatusPagamento;
import br.com.ss.academico.ireport.RelatorioUtil;
import br.com.ss.academico.servico.BoletimServico;
import br.com.ss.academico.servico.ConfiguracaoServico;
import br.com.ss.academico.servico.IService;
import br.com.ss.academico.servico.MatriculaServico;
import br.com.ss.academico.servico.MensalidadeServico;
import br.com.ss.academico.servico.TurmaServico;
import br.com.ss.academico.utils.DateUtil;

@ManagedBean
@SessionScoped
public class AlunoMatriculaControlador extends ControladorGenerico<Matricula> {

	private static final long serialVersionUID = 2272887087315382335L;

	@ManagedProperty(value = "#{mensalidadeServicoImpl}")
	private MensalidadeServico servicoMensalidade;

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

	private List<Turma> turmas;

	private List<SelectItem> naoSimList;
	
	private List<SelectItem> statusMatriculaList;

	private List<SelectItem> mesesList;

	private Meses mesSelecionado;

	private Observacao observacaoMatricula;

	private Configuracao configuracao;

	@ManagedProperty(value = "#{configuracaoServicoImpl}")
	private ConfiguracaoServico servicoConfiguracao;
	
	private boolean novo;
	
	/* --------- Overrides ------------------ */

	public void init() {
		alunoMatricula = new Aluno();
		naoSimList = createNaoSimList();
		statusMatriculaList = createStatusMatriculaList();
		mesesList = createMesesList();
		carregarDiaVencimento();
	}

	private void carregarDiaVencimento() {
		configuracao = servicoConfiguracao.listarTodos().get(0);
	}

	@Override
	protected String getNomeRelatorio() {
		// FIXME #Peninha, ver relatorio
		return null;
	}

	@Override
	protected IService<Matricula, Long> getService() {
		return servicoMatricula;
	}
	
	@Override
	public void pesquisar() {
		// não faz pesquisa neste bean.. apenas no MatriculaControlador.
	}

	@Override
	public String novo() {
		novo = true;
		String page = super.novo();
		carregarMesSelecionado();
		entidade.setStatus(StatusMatricula.ATIVA);
		return page;
	}
	
	
	@Override
	public String detalhe(Matricula entidade) {
		novo = false;
		// carrega as regras da tela de cadastro
		String page = super.detalhe(entidade);
		showModalCadastroMatricula(entidade);
		return page;
	}
	
	public void imprimirContrato(Matricula matricula) {
		// FIXME #Peninha criar o relatorio..
		List<Matricula> lista = new ArrayList<Matricula>();
		lista.add(matricula);
		Map<String, Object> parametros = new HashMap<String, Object>();

		relatorioUtil.gerarRelatorioComDownload(lista, parametros, "contrato.jasper");
	}
	
	public void renderObservacao() {
		if (entidade.getStatus() == StatusMatricula.ATIVA
				&& observacaoMatricula == null) {
			observacaoMatricula = new Observacao();
			observacaoMatricula.setMatricula(entidade);
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
		entidade = null;
		mesSelecionado = null;
		observacaoMatricula = null;
	}

	public void showModalCadastroMatricula(Matricula matricula) {
		// faz o load das mensalidades (fetch)
		this.entidade = servicoMatricula.loadMatriculaMensalidades(matricula);
		modalCadastro = true;
		int mes = DateUtil.getMes(entidade.getData());
		mesSelecionado = Meses.getEnum(mes + 1);
		turmas = servicoTurma.listarTodos();
		selectTurma(!matricula.isPersistent());
	}

	/**
	 * Nova matricula.
	 */
	public void showModalCadastroMatricula() {
		modalCadastro = true;
		entidade = createMatricula();
		turmas = servicoTurma.listarTodos();
		carregarMesSelecionado();
	}

	private void carregarMesSelecionado() {
		int mes = DateUtil.getMes(new Date());
		mesSelecionado = Meses.getEnum(mes);
	}

	private Matricula createMatricula() {
		Matricula matricula = new Matricula();
		matricula.setData(new Date());
		matricula.setStatus(StatusMatricula.ATIVA);
		return matricula;
	}

	/**
	 * ChangeListener do combo de turma.
	 * @param event
	 */
	public void turmaChanged(ValueChangeEvent e) {
		// assign new value to country
		Turma turma = (Turma) e.getNewValue();
		entidade.setTurma(turma);
		selectTurma(true);
	}

	public void selectTurma(boolean atualizarValor) {
		
		if (atualizarValor) {
			// atualiza o valor da matricula pelo valor do curso selecionado
			entidade.setValor(entidade.getTurma().getCurso().getValor());
		}
		entidade.setIntegral(false);

		Long vagasDisponiveis = servicoMatricula.countVagasDisponiveis(entidade.getTurma());
		entidade.getTurma().setVagasDisponiveis(vagasDisponiveis.intValue());

		if (!(vagasDisponiveis > 0)) {
			showMessage("Não há vaga disponível para a turma selecionada.",
					FacesMessage.SEVERITY_WARN);
		}
	}

	
	public void salvarMatricula() {
		salvar();
	}
	
	public String salvar() {

		try {
			if (entidade.getStatus() != StatusMatricula.ATIVA
					&& observacaoMatricula != null) {
				
				// salva a observacao da matricula
				cancelarMatricula();
				
			} else if (entidade.getStatus() == StatusMatricula.ATIVA ) {
				
				// cria ou atualiza as mensalidades
				gerarMensalidadesMatricula(entidade.isPersistent());
				
				// FIXME #Peninha: verificar se vai gerar boletim neste momento..
//				 this.boletimServico.gerarBoletim(matricula);				
			}
			
			entidade.setAluno(alunoMatricula);
			entidade = servicoMatricula.salvar(entidade);

			showModalPesquisaMatricula();
			showMessage(Constants.MSG_SUCESSO, FacesMessage.SEVERITY_INFO);
			observacaoMatricula = null;

			// gera o boletim para o aluno matriculado
			this.boletimServico.gerarBoletim(entidade);
			
			showMessage(Constants.MSG_SUCESSO, FacesMessage.SEVERITY_INFO);
			
			setup();
			return PESQUISA;
		} catch (Exception e) {
			e.printStackTrace();
			showMessage(Constants.MSG_ERRO, FacesMessage.SEVERITY_ERROR);
			return null;
		}
	}

	private void cancelarMatricula() {
		observacaoMatricula.setUsuario(getUsuarioLogado());
		entidade.getObservacoes().add(observacaoMatricula);

		// cancelar as mensalidades
		for (Mensalidade mens : entidade.getMensalidades()) {
			mens.setStatusPagamento(StatusPagamento.CANCELADO);
		}
	}

	private void gerarMensalidadesMatricula( boolean atualizarMatricula ) {
		int mesInicial = mesSelecionado.getId();
		int mesFinal = Constants.DOZE;
		
		int qtMensalidades = mesFinal - (mesInicial -1);
		
		// calcula o valor da mensalidade dividindo o valor do curso pela quantidade de meses
		double valorMens = entidade.getValor() / qtMensalidades;
		
		// FIXME #Peninha: validar a regra de geracao das mensalidades 
		/*
		 * Regra:
		 * Mensalidade = Valor_Curso / Qtde_Meses
		 * */
		if (atualizarMatricula) {
			// se for atualizar limpa a lista e recria novas.
			entidade.getMensalidades().clear();
		}
		
		for (int i = mesInicial; i <= mesFinal; i++) {
			entidade.getMensalidades().add(createMensalidade(i, valorMens));
		}
	}

	private Mensalidade createMensalidade(int mes, double valorVencimento) {
		Mensalidade mens = new Mensalidade();
		mens.setDataVencimento(criarDataVencimento(mes));
		mens.setMatricula(entidade);
		mens.setSequencial(mes);
		mens.setStatusPagamento(StatusPagamento.PENDENTE);
		mens.setValorVencimento(valorVencimento);
		mens.setUsuario(getUsuarioLogado());
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

	public MensalidadeServico getServicoMensalidade() {
		return servicoMensalidade;
	}

	public void setServicoMensalidade(MensalidadeServico servicoMensalidade) {
		this.servicoMensalidade = servicoMensalidade;
	}

	public boolean isNovo() {
		return novo;
	}

	public void setNovo(boolean novo) {
		this.novo = novo;
	}

	public ConfiguracaoServico getServicoConfiguracao() {
		return servicoConfiguracao;
	}

	public void setServicoConfiguracao(ConfiguracaoServico servicoConfiguracao) {
		this.servicoConfiguracao = servicoConfiguracao;
	}

}