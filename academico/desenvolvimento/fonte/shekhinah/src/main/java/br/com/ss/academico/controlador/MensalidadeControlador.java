package br.com.ss.academico.controlador;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.ss.academico.dominio.Aluno;
import br.com.ss.academico.dominio.Empresa;
import br.com.ss.academico.dominio.Matricula;
import br.com.ss.academico.dominio.Mensalidade;
import br.com.ss.academico.dominio.Usuario;
import br.com.ss.academico.dto.ParametroRelatorioDTO;
import br.com.ss.academico.enumerated.StatusPagamento;
import br.com.ss.academico.ireport.RelatorioUtil;
import br.com.ss.academico.servico.AlunoServico;
import br.com.ss.academico.servico.EmpresaServico;
import br.com.ss.academico.servico.MensalidadeServico;

@ManagedBean
@SessionScoped
public class MensalidadeControlador implements Serializable {

	private static final long serialVersionUID = -6832271293709421841L;

	private Mensalidade entidade;

	private Mensalidade pesquisa;

	private List<Mensalidade> lista;

	private List<Aluno> alunos;

	private StatusPagamento[] statusPagamentos;

	private ParametroRelatorioDTO parametroRelatorioDTO;

	private final Long ID_EMPRESA = 1L;

	private final String TELA_CADASTRO = "paginas/mensalidade/cadastro.xhtml";
	private final String TELA_PESQUISA = "paginas/mensalidade/pesquisa.xhtml";

	@ManagedProperty(value = "#{mensalidadeServicoImpl}")
	private MensalidadeServico servico;

	@ManagedProperty(value = "#{alunoServicoImpl}")
	private AlunoServico alunoServico;

	@ManagedProperty(value = "#{empresaServicoImpl}")
	private EmpresaServico empresaServico;

	@ManagedProperty(value = "#{paginaCentralControlador}")
	private PaginaCentralControlador paginaCentralControlador;

	@ManagedProperty(value = "#{relatorioUtil}")
	private RelatorioUtil relatorioUtil;

	public void init() {
		this.lista = servico.listarTodos();
		this.alunos = alunoServico.listarTodos();

		this.parametroRelatorioDTO = new ParametroRelatorioDTO();
		this.parametroRelatorioDTO.pegarMesCorrente();
		this.telaPesquisa();
	}

	public MensalidadeControlador() {
		this.entidade = new Mensalidade();
		this.pesquisa = new Mensalidade();
	}

	public void pesquisar() {
		System.out.println(this.pesquisa.getStatusPagamento());
		this.lista = servico.findByStatusPagamento(
				this.pesquisa.getStatusPagamento(),
				this.parametroRelatorioDTO.getDataInicio(),
				this.parametroRelatorioDTO.getDataFim());
	}

	public void detalhe(Mensalidade mensalidade) {
		this.entidade = mensalidade;
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);
	}

	public void salvar() {

		// if (this.entidade.getStatusPagamento() == null)
		this.entidade.setStatusPagamento(StatusPagamento.PENDENTE);

		this.entidade.setUsuario(new Usuario(((Usuario) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal()).getId()));

//		this.entidade.setMatricula(new Matricula(this.entidade.getMatricula()
//				.getIdMatricula()));

		this.servico.salvar(this.entidade);
		this.lista = servico.listarTodos();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public void excluir(Mensalidade mensalidade) {
		servico.remover(mensalidade);
		this.lista = servico.listarTodos();
	}

	public void novo() {
		this.entidade = new Mensalidade();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);
	}

	public void telaPeaquisa() {
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public void imprimir() throws FileNotFoundException {

		Empresa empresa = empresaServico.findOne(this.ID_EMPRESA);

		Map parametros = new HashMap();

		parametros.put("empresa", empresa);

		relatorioUtil.gerarRelatorioWeb(this.lista, parametros,
				"mensalidade.jasper");
	}

	public Mensalidade getEntidade() {
		return entidade;
	}

	public void setEntidade(Mensalidade entidade) {
		this.entidade = entidade;
	}

	public Mensalidade getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(Mensalidade pesquisa) {
		this.pesquisa = pesquisa;
	}

	public List<Mensalidade> getLista() {
		return lista;
	}

	public void setLista(List<Mensalidade> lista) {
		this.lista = lista;
	}

	public PaginaCentralControlador getPaginaCentralControlador() {
		return paginaCentralControlador;
	}

	public void setPaginaCentralControlador(
			PaginaCentralControlador paginaCentralControlador) {
		this.paginaCentralControlador = paginaCentralControlador;
	}

	public MensalidadeServico getServico() {
		return servico;
	}

	public void setServico(MensalidadeServico servico) {
		this.servico = servico;
	}

	public void telaPesquisa() {
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
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

	public StatusPagamento[] getStatusPagamentos() {
		return StatusPagamento.values();
	}

	public ParametroRelatorioDTO getParametroRelatorioDTO() {
		return parametroRelatorioDTO;
	}

	public void setParametroRelatorioDTO(
			ParametroRelatorioDTO parametroRelatorioDTO) {
		this.parametroRelatorioDTO = parametroRelatorioDTO;
	}

	public RelatorioUtil getRelatorioUtil() {
		return relatorioUtil;
	}

	public void setRelatorioUtil(RelatorioUtil relatorioUtil) {
		this.relatorioUtil = relatorioUtil;
	}

	public EmpresaServico getEmpresaServico() {
		return empresaServico;
	}

	public void setEmpresaServico(EmpresaServico empresaServico) {
		this.empresaServico = empresaServico;
	}

}