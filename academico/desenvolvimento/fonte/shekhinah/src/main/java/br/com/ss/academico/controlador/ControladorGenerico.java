package br.com.ss.academico.controlador;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import br.com.ss.academico.dominio.AbstractEntity;
import br.com.ss.academico.enumerated.Constants;
import br.com.ss.academico.enumerated.Sexo;
import br.com.ss.academico.ireport.RelatorioUtil;
import br.com.ss.academico.servico.IService;
import br.com.ss.academico.utils.DateUtil;

@Named
public abstract class ControladorGenerico<T extends AbstractEntity> implements Serializable {

	private static final long serialVersionUID = -1229239475130268144L;
	
	/* ---------- Atributos ----------------------- */

	/** Entity usado no cadastro. */
	protected T entidade;

	/** Entity usado na pesquisa. */
	protected T pesquisa;

	/** Entity selecionada para exclusão. */
	protected T itemToRemove;

	/** Lista com o resultado da pesquisa. */
	protected List<T> listaPesquisa;

	@ManagedProperty(value = "#{relatorioUtil}")
	protected RelatorioUtil relatorioUtil;

	// FIXME deve ficar no contexto de app - criar classe
	protected List<SelectItem> sexoList;
	
	/**
	 * Alias para redirecionar para a tela de cadastro.
	 */
	public static final String CADASTRO = "cadastro";

	/**
	 * Alias para redirecionar para a tela de pesquisa. */
	public static final String PESQUISA = "pesquisa";
	
	
	/* ---------- Metodos ----------------------- */

	@PostConstruct
	protected void setup() {
		initEntity();
		init();
		initCommons();
		pesquisar();
	}

	protected void initCommons() {
		
		sexoList = new ArrayList<SelectItem>();
		for (Sexo c : Sexo.values()) {
			sexoList.add(new SelectItem(c, c.getDescricao()));
		}
		
	}

	protected abstract void init();

	protected abstract void initEntity();		// FIXME refatorar usar reflexao

	/** Nome do relatorio utilizado na impressao. */
	protected abstract String getNomeRelatorio();
	
	/** Retornar o serviço da entidade. */
	protected abstract IService<T, Long> getService();
	

	public void pesquisar() {
		this.listaPesquisa = getService().pesquisar(pesquisa);
	}

	public String salvar() {
		try {
			getService().salvar(entidade);
			setup();
			showMessage(Constants.MSG_SUCESSO, FacesMessage.SEVERITY_INFO);
			return PESQUISA;
		} catch (Exception e) {
			e.printStackTrace();
			showMessage(Constants.MSG_ERRO, FacesMessage.SEVERITY_ERROR);
			return null;
		}
	}
	

	public void excluir(T itemRemove) {
		itemToRemove = itemRemove;
		excluir();
	}
	
	public void excluir() {
		try {
			executarExcluir(itemToRemove);
			pesquisar();
			setItemToRemove(null);
			showMessage(Constants.MSG_SUCESSO, FacesMessage.SEVERITY_INFO);	// FIXME validar: nao exibe msg
		} catch (Exception e) {
			e.printStackTrace();
			showMessage(Constants.MSG_ERRO, FacesMessage.SEVERITY_ERROR);
		}
	}

	
	public void executarExcluir(T itemRemove) {
		getService().remover(itemRemove);
	}

	/**
	 * Metodo utilizado para ir para a tela de cadastra da entidade.
	 * @return string.
	 */
	public String novo() {
		this.initEntity();
		return CADASTRO;
	}
	
	/**
	 * Metodo utilizado para editar uma entidade. Sobrescrever este metodo caso
	 * necessário realizar outras operaçoes.
	 * @return string.
	 */
	public String detalhe(T entidade) {
		this.entidade = entidade;
		return CADASTRO;
	}

	/**
	 * Metodo utilizado para cancelar uma edicao e retornar para a pg de inicial.
	 * @return string.
	 */
	public String cancelar() {
		init();
		return PESQUISA;
	}

	public void imprimir() throws FileNotFoundException {
		relatorioUtil.gerarRelatorioWeb(this.listaPesquisa, null, getNomeRelatorio());
	}

	
	/* -------- Metodos utilitarios -------------- */

	protected void showMessage(String msg, Severity severityInfo) {
		showMessage(msg, null, severityInfo);		
	}

	protected void showMessage(String msg, String detail, Severity severityInfo) {
		FacesMessage facesMessage = new FacesMessage();
		facesMessage.setSeverity(severityInfo);
		facesMessage.setSummary(msg);
		facesMessage.setDetail(detail);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}


	/**
	 * Retorna a instancia de HttpServletRequest.
	 */
	protected HttpServletRequest getRequest() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		return request;
	}

	
	protected boolean isDataFuturo(Date date) {
		return DateUtil.isDataFuturo(date);
	}

	/* ---------- Others ------------- */



	/* ---------- Gets/Sets ------------- */

	public T getItemToRemove() {
		return itemToRemove;
	}

	public void setItemToRemove(T itemToRemove) {
		this.itemToRemove = itemToRemove;
	}

	public List<T> getResultList() {
		return listaPesquisa;
	}

	public T getEntidade() {
		return entidade;
	}

	public T getPesquisa() {
		return pesquisa;
	}

	public List<T> getListaPesquisa() {
		return listaPesquisa;
	}

	public List<SelectItem> getSexoList() {
		return sexoList;
	}

	public RelatorioUtil getRelatorioUtil() {
		return relatorioUtil;
	}

	public void setRelatorioUtil(RelatorioUtil relatorioUtil) {
		this.relatorioUtil = relatorioUtil;
	}

	
}
