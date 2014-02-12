package br.com.ss.data.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.ss.data.entities.Entrada;
import br.com.ss.data.entities.EntradaItem;
import br.com.ss.data.entities.Membro;
import br.com.ss.data.entities.TipoEntrada;
import br.com.ss.data.entities.Usuario;
import br.com.ss.data.servico.EntradaServico;
import br.com.ss.data.servico.MembroServico;
import br.com.ss.data.servico.TipoEntradaServico;

@ManagedBean
@SessionScoped
public class EntradaControlador implements Serializable {

	private static final long serialVersionUID = -8110448221899633556L;

	private Entrada entidade;

	private Entrada pesquisa;

	private Date dataInicial;

	private Date dataFinal;
	
	private List<Entrada> entradasPesquisa;
	
	private List<Entrada> entradasInclusao;

	private List<TipoEntrada> listaTipoEntrada;
	
	@ManagedProperty(value = "#{entradaServicoImpl}")
	private EntradaServico servicoEntrada;

	@ManagedProperty(value = "#{tipoEntradaServicoImpl}")
	private TipoEntradaServico servicoTipoEntrada;

	@ManagedProperty(value = "#{membroServicoImpl}")
	private MembroServico membroServico;
	
	@ManagedProperty(value = "#{paginaCentralControlador}")
	private PaginaCentralControlador paginaCentralControlador;

	private final String TELA_CADASTRO = "paginas/entrada/cadastro.xhtml";
	private final String TELA_PESQUISA = "paginas/entrada/pesquisa.xhtml";

	private EntradaItem entradaItem;

	@PostConstruct
	public void init() {
		this.entradasPesquisa = servicoEntrada.listarTodos();
		this.listaTipoEntrada =  servicoTipoEntrada.listarTodos();
		pesquisa = createEntradaMembro();
	}

	public void pesquisar() {
		// TODO
		
	}

	private Entrada createEntradaMembro() {
		Entrada entrada = new Entrada();
		entrada.setMembro(new Membro());
		return entrada;
	}

	public void detalhe(Entrada entrada) {
		this.entidade = entrada;
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);
	}

	public void salvar() {
		
		Usuario usuario = null;	// TODO inject usuario
		
		this.entidade.setDataLog(new Date());
		this.entidade.setUsuario(usuario);
		
		servicoEntrada.salvar(this.entidade);
		this.init();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);

		// TODO show message
	}

	public void excluir(Entrada entrada) {
		servicoEntrada.remover(entrada);
		this.entradasPesquisa = servicoEntrada.listarTodos();
		
		// TODO show message
	}


	public void cancelar() {
		init();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_PESQUISA);
	}

	
	/**
	 * Lista os membros - para a lista do auto-complete.
	 */
	public List<Membro> listarMembros(String nome) {
		return membroServico.findByNome(nome);
	}


	public void novo() {
		entidade = createEntradaMembro(); // entidade = new Entrada();
		entidade.setDataEntrada(new Date());
		
		createEntradaItem();
		
		entradasInclusao = new ArrayList<Entrada>();
		this.paginaCentralControlador.setPaginaCentral(this.TELA_CADASTRO);
	}

	
	private void createEntradaItem() {
		entradaItem = new EntradaItem();
		entradaItem.setEntrada(entidade);
	}
	

	/**
	 * Inclui a entrada no grid de inclusao.
	 */
	public void incluirEntrada() {
		entidade.getEntradaItems().add(entradaItem);
		createEntradaItem();
	}

	
	// ----------- GETs / SETs --------------
	public Entrada getEntidade() {
		return entidade;
	}

	public void setEntidade(Entrada entidade) {
		this.entidade = entidade;
	}

	public Entrada getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(Entrada pesquisa) {
		this.pesquisa = pesquisa;
	}

	public PaginaCentralControlador getPaginaCentralControlador() {
		return paginaCentralControlador;
	}

	public void setPaginaCentralControlador(
			PaginaCentralControlador paginaCentralControlador) {
		this.paginaCentralControlador = paginaCentralControlador;
	}

	public List<TipoEntrada> getListaTipoEntrada() {
		return listaTipoEntrada;
	}

	public void setListaTipoEntrada(List<TipoEntrada> listaTipoEntrada) {
		this.listaTipoEntrada = listaTipoEntrada;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public EntradaServico getServicoEntrada() {
		return servicoEntrada;
	}

	public void setServicoEntrada(EntradaServico servicoEntrada) {
		this.servicoEntrada = servicoEntrada;
	}

	public TipoEntradaServico getServicoTipoEntrada() {
		return servicoTipoEntrada;
	}

	public void setServicoTipoEntrada(TipoEntradaServico servicoTipoEntrada) {
		this.servicoTipoEntrada = servicoTipoEntrada;
	}

	public List<Entrada> getEntradasPesquisa() {
		return entradasPesquisa;
	}

	public List<Entrada> getEntradasInclusao() {
		return entradasInclusao;
	}

	public EntradaItem getEntradaItem() {
		return entradaItem;
	}

	public MembroServico getMembroServico() {
		return membroServico;
	}

	public void setMembroServico(MembroServico membroServico) {
		this.membroServico = membroServico;
	}
}