package br.com.ss.academico.controlador;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import br.com.ss.academico.dominio.Responsavel;
import br.com.ss.academico.dominio.Turma;
import br.com.ss.academico.dominio.Usuario;
import br.com.ss.academico.enumerated.Constants;
import br.com.ss.academico.enumerated.Meses;
import br.com.ss.academico.enumerated.NaoSim;
import br.com.ss.academico.enumerated.StatusMatricula;
import br.com.ss.academico.enumerated.StatusPagamento;
import br.com.ss.academico.ireport.RelatorioUtil;
import br.com.ss.academico.servico.AlunoServico;
import br.com.ss.academico.servico.BoletimServico;
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

	@ManagedProperty(value = "#{boletimServicoImpl}")
	private BoletimServico boletimServico;

	@ManagedProperty(value = "#{paginaCentralControlador}")
	private PaginaCentralControlador paginaCentralControlador;

	@ManagedProperty(value = "#{relatorioUtil}")
	private RelatorioUtil relatorioUtil;

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

	public void init() {
		lista = servico.listarTodos();
		naoSimList = createNaoSimList();
		;
		statusMatriculaList = createStatusMatriculaList();
		mesesList = createMesesList();
		telaPesquisa();
		carregarDiaVencimento();

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

	private void carregarDiaVencimento() {

		// TODO carregar configuracao do contexto
		configuracao = new Configuracao();
		configuracao.setDiaVencimento(10); // TODO remover
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

	public void imprimirContratoold(Matricula matricula)
			throws FileNotFoundException {
		// TODO criar o relatorio..
		List<Matricula> listMat = new ArrayList<Matricula>();
		listMat.add(matricula);
		relatorioUtil.gerarRelatorioWeb(listMat, null, "contrato.jasper");
	}
	
	public void imprimirContrato(Matricula matricula) {
		List<Matricula> lista = new ArrayList<Matricula>();
		lista.add(matricula);
		Map<String, Object> parametros = new HashMap<String, Object>();

		relatorioUtil.gerarRelatorioComDownload(lista, parametros,
				"contrato.jasper");

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

		List<Matricula> matriculas = servicoMatricula
				.findByAluno(alunoMatricula);
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
		Usuario usuario = (Usuario) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		observacaoMatricula.setUsuario(usuario);
		matricula.getObservacoes().add(observacaoMatricula);

		// cancelar as mensalidades
		for (Mensalidade mens : matricula.getMensalidades()) {
			mens.setStatusPagamento(StatusPagamento.CANCELADO);
		}
	}

	private void gerarMensalidadesMatricula() {
		int mesInicial = mesSelecionado.getId();
		int mesFinal = Constants.DOZE;
		for (int i = mesInicial; i <= mesFinal; i++) {
			matricula.getMensalidades().add(createMensalidade(i));
		}
	}

	private Mensalidade createMensalidade(int mes) {
		Mensalidade mens = new Mensalidade();
		mens.setDataVencimento(criarDataVencimento(mes));
		mens.setMatricula(matricula);
		mens.setSequencial(mes);
		mens.setStatusPagamento(StatusPagamento.PENDENTE);
		return mens;
	}

	private Date criarDataVencimento(int mes) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		cal.set(cal.get(Calendar.YEAR), mes - 1,
				configuracao.getDiaVencimento());
		return cal.getTime();
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