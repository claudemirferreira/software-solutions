package br.com.ss.academico.controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import net.sf.jasperreports.engine.JRException;
import br.com.ss.academico.dominio.Aluno;
import br.com.ss.academico.dominio.Responsavel;
import br.com.ss.academico.enumerated.GrauParentesco;
import br.com.ss.academico.servico.AlunoServico;
import br.com.ss.academico.servico.ResponsavelServico;
import br.com.ss.core.seguranca.servico.IService;
import br.com.ss.core.web.controlador.ControladorGenerico;
import br.com.ss.core.web.enumerated.Constants;
import br.com.ss.core.web.enumerated.UF;
import br.com.ss.core.web.utils.StringUtil;

@ManagedBean
@SessionScoped
public class AlunoControlador extends ControladorGenerico<Aluno> {

	private static final long serialVersionUID = -6832271293709421841L;

	private List<Responsavel> responsaveis;

	@ManagedProperty(value = "#{alunoServicoImpl}")
	private AlunoServico servico;

	@ManagedProperty(value = "#{responsavelServicoImpl}")
	private ResponsavelServico responsavelServico;

	private List<SelectItem> grauParentescoList;

	private List<SelectItem> ufList;

	private List<Responsavel> responsavelList;

	/* --------- Overrides ------------------ */

	@Override
	public void init() {
		grauParentescoList = createGrauParentescoList();
		this.responsaveis = new ArrayList<Responsavel>();
		this.ufList = createUFList();
	}

	private List<SelectItem> createUFList() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		for (UF c : UF.values()) {
			list.add(new SelectItem(c.getId(), c.getDescricao()));
		}
		return list;
	}

	@Override
	protected IService<Aluno, Long> getService() {
		return servico;
	}

	@Override
	public String getNomeRelatorioJasper() {
		return "aluno.jasper";
	}

	@Override
	public String getTituloRelatorio() {
		return "RELATÓRIO DE ALUNO";
	}

	/**
	 * Lista os Responsaveis - para a lista do auto-complete da tela de
	 * pesquisa.
	 */
	public List<Responsavel> listarResponsavel(String nome) {
		responsavelList = responsavelServico.findByNomeLike(nome);
		return responsavelList;
	}

	public String novo() {
		this.responsaveis = responsavelServico.listarTodos();
		return super.novo();
	}

	public String detalhe(Aluno aluno) {
		this.responsaveis = responsavelServico.listarTodos();
		return super.detalhe(aluno);
	}

	public String salvar() {

		if (isDataFuturo(entidade.getDataNascimento())) {
			showMessage(Constants.MSG_WARN_VALIDACAO,
					"Data de Nascimento é maior que a data atual.",
					FacesMessage.SEVERITY_WARN);
			return null;
		}

		if (this.entidade.getDataCadastro() == null) {
			this.entidade.setDataCadastro(new Date());
		}
		if (!StringUtil.notEmpty(entidade.getCpf())) {
			entidade.setCpf(null);
		}
		if (!StringUtil.notEmpty(entidade.getEmail())) {
			entidade.setEmail(null);
		}
		return super.salvar();
	}

	public void imprimirFicha() throws JRException {
		this.imprimir(this.entidade, "ficha.jasper");
	}

	private List<SelectItem> createGrauParentescoList() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		for (GrauParentesco mes : GrauParentesco.values()) {
			list.add(new SelectItem(mes, mes.getDescricao()));
		}
		return list;
	}

	/* --------------- Gets/Sets ---------------------- */

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

	public AlunoServico getServico() {
		return servico;
	}

	public void setServico(AlunoServico servico) {
		this.servico = servico;
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

	public List<SelectItem> getUfList() {
		return ufList;
	}

	public void setAlunosTurma(List<Aluno> alunos) {
		this.listaPesquisa = alunos;
	}

}