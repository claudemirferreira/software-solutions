package br.com.ss.academico.controlador;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import br.com.ss.academico.dominio.Aluno;
import br.com.ss.academico.dominio.Matricula;
import br.com.ss.academico.dominio.Responsavel;
import br.com.ss.academico.enumerated.GrauParentesco;
import br.com.ss.academico.ireport.RelatorioUtil;
import br.com.ss.academico.servico.AlunoServico;
import br.com.ss.academico.servico.IService;
import br.com.ss.academico.servico.ResponsavelServico;

@ManagedBean
@SessionScoped
public class AlunoControlador extends ControladorGenerico<Aluno> {

	private static final long serialVersionUID = -6832271293709421841L;

	private List<Responsavel> responsaveis;

	private final String TELA_CADASTRO = "paginas/aluno/cadastro.xhtml";
	private final String TELA_PESQUISA = "paginas/aluno/pesquisa.xhtml";

	@ManagedProperty(value = "#{alunoServicoImpl}")
	private AlunoServico servico;

	@ManagedProperty(value = "#{responsavelServicoImpl}")
	private ResponsavelServico responsavelServico;

	private List<SelectItem> grauParentescoList;

	private List<Responsavel> responsavelList;
	
	
	/* --------- Overrides ------------------ */

	@Override
	public void init() {
		grauParentescoList = createGrauParentescoList();
		setPaginaCentral(TELA_PESQUISA);
	}

	@Override
	protected void initEntity() {
		this.entidade = new Aluno();
		this.pesquisa = new Aluno();
		this.responsaveis = new ArrayList<Responsavel>();
	}

	@Override
	protected String getPaginaPesquisa() {
		return TELA_PESQUISA;
	}

	@Override
	protected String getPaginaCadastro() {
		return TELA_CADASTRO;
	}

	@Override
	protected IService<Aluno> getService() {
		return servico;
	}

	@Override
	public String getNomeRelatorio() {
		return "aluno.jasper";
	}

	/**
	 * Lista os Responsaveis - para a lista do auto-complete da tela de pesquisa.
	 */
	public List<Responsavel> listarResponsavel(String nome) {
		responsavelList = responsavelServico.findByNomeLike(nome);
		return responsavelList;
	}


	public void novo() {
		super.novo();
		this.responsaveis = responsavelServico.listarTodos();
	}

	public void detalhe(Aluno aluno) {
		super.detalhe(aluno);
		this.responsaveis = responsavelServico.listarTodos();
	}


	public void salvar() {
		if (this.entidade.getDataCadastro() == null) {
			this.entidade.setDataCadastro(new Date());
		}
		super.salvar();
	}


	public void imprimirContratoold(Matricula matricula) throws FileNotFoundException {
		// FIXME #Peninha validar/remover se nao precisar mais desse metodo
		List<Matricula> listMat = new ArrayList<Matricula>();
		listMat.add(matricula);
		relatorioUtil.gerarRelatorioWeb(listMat, null, "contrato.jasper");
	}
	

	private List<SelectItem> createGrauParentescoList() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		for (GrauParentesco mes : GrauParentesco.values()) {
			list.add(new SelectItem(mes, mes.getDescricao()));
		}
		return list;
	}

	
	/* --------------- Gets/Sets ----------------------*/
	
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

	public PaginaCentralControlador getPaginaCentralControlador() {
		return paginaCentralControlador;
	}

	public void setPaginaCentralControlador( PaginaCentralControlador paginaCentralControlador) {
		this.paginaCentralControlador = paginaCentralControlador;
	}

	public AlunoServico getServico() {
		return servico;
	}

	public void setServico(AlunoServico servico) {
		this.servico = servico;
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

	public List<SelectItem> getGrauParentescoList() {
		return grauParentescoList;
	}

	public List<Responsavel> getResponsavelList() {
		return responsavelList;
	}

}