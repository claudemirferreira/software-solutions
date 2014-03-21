package br.com.ss.academico.controlador;

import java.io.FileInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.ss.academico.dominio.Boletim;
import br.com.ss.academico.dominio.Matricula;
import br.com.ss.academico.ireport.RelatorioUtil;
import br.com.ss.academico.servico.BoletimServico;
import br.com.ss.academico.servico.EmpresaServico;
import br.com.ss.academico.servico.MatriculaServico;

@ManagedBean
@SessionScoped
public class BoletimControlador implements Serializable {

	private static final long serialVersionUID = -6832271293709421841L;

	private Boletim entidade;

	private Matricula pesquisa;

	private Matricula matricula;

	private List<Boletim> lista;

	private List<Matricula> matriculas;

	private StreamedContent streamedContent;

	private final String TELA_CADASTRO = "paginas/boletim/cadastro.xhtml";
	private final String TELA_PESQUISA = "paginas/boletim/pesquisa.xhtml";

	@ManagedProperty(value = "#{boletimServicoImpl}")
	private BoletimServico servico;

	@ManagedProperty(value = "#{matriculaServicoImpl}")
	private MatriculaServico matriculaServico;

	@ManagedProperty(value = "#{empresaServicoImpl}")
	private EmpresaServico empresaServico;

	@ManagedProperty(value = "#{relatorioUtil}")
	private RelatorioUtil relatorioUtil;

	@ManagedProperty(value = "#{paginaCentralControlador}")
	private PaginaCentralControlador paginaCentralControlador;

	public void init() {
		this.matriculas = matriculaServico.listarTodos();
		this.telaPesquisa();
	}

	public BoletimControlador() {
		this.entidade = new Boletim();
		this.pesquisa = new Matricula();
	}

	public void pesquisar() {
		// this.lista = servico.findByNomeLike(this.pesquisa.getNome());
	}

	public void pesquisarBoletim(Matricula matricula) {

		this.matricula = matricula;

		this.lista = servico.pesquisarBoletim(matricula);
		this.telaCadastro();

	}

	public void detalhe(Boletim boletim) {
		this.entidade = boletim;
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);
	}

	public void salvar() {

		this.servico.salvar(this.entidade);
		this.lista = servico.listarTodos();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public void excluir(Boletim boletim) {
		servico.remover(boletim);
		this.lista = servico.listarTodos();
	}

	public void novo() {
		this.entidade = new Boletim();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);
	}

	public void telaPeaquisa() {
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public void telaCadastro() {
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);
	}

	public void imprimir() {

		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		ServletContext context = (ServletContext) externalContext.getContext();
		// String arquivo =
		// context.getRealPath("/WEB-INF/jasper/boletim.jasper");

		String arquivo = "c:\\relatorio\\boletim.jasper";

		// JRDataSource jrRS = new JRBeanCollectionDataSource(this.lista);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("DATA_INICIO", null);

		FileInputStream fis = relatorioUtil.gerarRelatorio(this.lista, params,
				arquivo);

		this.streamedContent = new DefaultStreamedContent(fis,
				"application/pdf");
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "What we do in life", "Echoes in eternity.");  
        
        RequestContext.getCurrentInstance().showMessageInDialog(message); 

	}

	public Boletim getEntidade() {
		return entidade;
	}

	public void setEntidade(Boletim entidade) {
		this.entidade = entidade;
	}

	public Matricula getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(Matricula pesquisa) {
		this.pesquisa = pesquisa;
	}

	public List<Boletim> getLista() {
		return lista;
	}

	public void setLista(List<Boletim> lista) {
		this.lista = lista;
	}

	public List<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public PaginaCentralControlador getPaginaCentralControlador() {
		return paginaCentralControlador;
	}

	public void setPaginaCentralControlador(
			PaginaCentralControlador paginaCentralControlador) {
		this.paginaCentralControlador = paginaCentralControlador;
	}

	public BoletimServico getServico() {
		return servico;
	}

	public void setServico(BoletimServico servico) {
		this.servico = servico;
	}

	public MatriculaServico getMatriculaServico() {
		return matriculaServico;
	}

	public void setMatriculaServico(MatriculaServico matriculaServico) {
		this.matriculaServico = matriculaServico;
	}

	public void telaPesquisa() {
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	public StreamedContent getStreamedContent() {
		return streamedContent;
	}

	public void setStreamedContent(StreamedContent streamedContent) {
		this.streamedContent = streamedContent;
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