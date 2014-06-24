package br.com.ss.academico.controlador;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import br.com.ss.academico.dominio.Aluno;
import br.com.ss.academico.dominio.Matricula;
import br.com.ss.academico.dominio.Mensalidade;
import br.com.ss.academico.enumerated.StatusPagamento;
import br.com.ss.academico.enumerated.TipoPesquisaData;
import br.com.ss.academico.servico.AlunoServico;
import br.com.ss.academico.servico.EmpresaServico;
import br.com.ss.academico.servico.MensalidadeServico;
import br.com.ss.core.seguranca.servico.IService;
import br.com.ss.core.web.controlador.ControladorGenerico;

@ManagedBean
@SessionScoped
public class MensalidadeControlador extends ControladorGenerico<Mensalidade> {

	private static final long serialVersionUID = -6832271293709421841L;

	private List<Aluno> alunos;

	@ManagedProperty(value = "#{mensalidadeServicoImpl}")
	private MensalidadeServico servico;

	@ManagedProperty(value = "#{alunoServicoImpl}")
	private AlunoServico alunoServico;

	@ManagedProperty(value = "#{empresaServicoImpl}")
	private EmpresaServico empresaServico;

	private List<SelectItem> statusList;

	private Date dataInicio;

	private Date dataFim;

	private TipoPesquisaData tipoPesquisaData;

	private List<SelectItem> tipoPesquisaDataList;

	private String nomeRelatorio = "mensalidade.jasper";

	@Override
	public void init() {
		pesquisa.setMatricula(new Matricula());
		this.alunos = alunoServico.listarTodos();
		statusList = new ArrayList<SelectItem>();
		for (StatusPagamento status : StatusPagamento.values()) {
			statusList.add(new SelectItem(status, status.getDescricao()));
		}
		tipoPesquisaDataList = new ArrayList<SelectItem>();
		for (TipoPesquisaData tipo : TipoPesquisaData.values()) {
			tipoPesquisaDataList.add(new SelectItem(tipo, tipo.getDescricao()));
		}
		tipoPesquisaData = TipoPesquisaData.VECIMENTO;
		carregarDatas();
	}

	public void carregarDatas() {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(new Date());
		int dia = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int mes = (cal.get(Calendar.MONDAY) + 1);
		int ano = cal.get(Calendar.YEAR);
		try {
			this.dataInicio = (new SimpleDateFormat("dd/MM/yyyy")).parse("01/"
					+ mes + "/" + ano);
			this.dataFim = (new SimpleDateFormat("dd/MM/yyyy")).parse(dia + "/"
					+ mes + "/" + ano);
		} catch (ParseException e) {
		}
	}

	@Override
	public String getNomeRelatorio() {
		return this.nomeRelatorio;
	}

	public void setNomeRelatorio(String nomeRelatorio) {
		this.nomeRelatorio = nomeRelatorio;
	}

	@Override
	protected IService<Mensalidade, Long> getService() {
		return servico;
	}

	/*
	 * FIXME #Peninha, verificar relatorio
	 * 
	 * private final Long ID_EMPRESA = 1L; // FIXME #Peninha, recuperar registro
	 * da tabela de configuração
	 * 
	 * public void imprimir() throws FileNotFoundException { Empresa empresa =
	 * empresaServico.findOne(this.ID_EMPRESA); Map parametros = new HashMap();
	 * parametros.put("empresa", empresa);
	 * relatorioUtil.gerarRelatorioWeb(this.lista, parametros,
	 * "mensalidade.jasper"); }
	 */

	public void pesquisar() {
		this.listaPesquisa = servico.pesquisar(pesquisa, dataInicio, dataFim,
				tipoPesquisaData);
	}

	@Override
	public String detalhe() {
		String page = super.detalhe();
		if (StatusPagamento.PENDENTE.equals(entidade.getStatusPagamento())) {
			entidade.setDataPagamento(new Date());
		}
		return page;
	}

	/**
	 * Lista os Alunos - para a lista do auto-complete da tela de pesquisa.
	 */
	public List<Aluno> listarAlunos(String nome) {
		Aluno aluno = new Aluno();
		aluno.setNome(nome);
		return alunoServico.pesquisar(aluno);
	}

	public String cancelarMensalidade() {
		this.entidade.setStatusPagamento(StatusPagamento.CANCELADO);
		return this.salvar();
	}

	public String baixarMensalidade() {
		this.entidade.setStatusPagamento(StatusPagamento.PAGO);
		return this.salvar();
	}

	public String salvar() {
		this.entidade.setUsuario(getUsuarioLogado());
		return super.salvar();
	}

	/* ------------- Gets/Sets ----------------------- */

	public MensalidadeServico getServico() {
		return servico;
	}

	public void setServico(MensalidadeServico servico) {
		this.servico = servico;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public AlunoServico getAlunoServico() {
		return alunoServico;
	}

	public void setAlunoServico(AlunoServico alunoServico) {
		this.alunoServico = alunoServico;
	}

	public EmpresaServico getEmpresaServico() {
		return empresaServico;
	}

	public void setEmpresaServico(EmpresaServico empresaServico) {
		this.empresaServico = empresaServico;
	}

	public List<SelectItem> getStatusList() {
		return statusList;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public TipoPesquisaData getTipoPesquisaData() {
		return tipoPesquisaData;
	}

	public void setTipoPesquisaData(TipoPesquisaData tipoPesquisaData) {
		this.tipoPesquisaData = tipoPesquisaData;
	}

	public List<SelectItem> getTipoPesquisaDataList() {
		return tipoPesquisaDataList;
	}

	public void setTipoPesquisaDataList(List<SelectItem> tipoPesquisaDataList) {
		this.tipoPesquisaDataList = tipoPesquisaDataList;
	}

	public void imprimir() {
		Map<String, Object> parametros = new HashMap<String, Object>();
		super.imprimir(this.listaPesquisa, parametros, "mensalidade.jasper");
		
//		return getInputStream();
	}

}