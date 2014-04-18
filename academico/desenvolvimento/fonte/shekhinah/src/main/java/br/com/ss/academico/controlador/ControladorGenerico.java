package br.com.ss.academico.controlador;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import br.com.ss.academico.dominio.AbstractEntity;
import br.com.ss.academico.enumerated.Constants;
import br.com.ss.academico.enumerated.Sexo;
import br.com.ss.academico.ireport.RelatorioUtil;
import br.com.ss.academico.servico.IService;

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
	
	@ManagedProperty(value = "#{paginaCentralControlador}")
	protected PaginaCentralControlador paginaCentralControlador;

	@ManagedProperty(value = "#{relatorioUtil}")
	protected RelatorioUtil relatorioUtil;

	// FIXME deve ficar no contexto de app - criar classe
	protected List<SelectItem> sexoList;
	
	/* ---------- Metodos ----------------------- */

	@PostConstruct
	protected void setup() {
		initEntity();
		init();
		initCommons();
		pesquisar();
	}

	private void initCommons() {
		
		sexoList = new ArrayList<SelectItem>();
		for (Sexo c : Sexo.values()) {
			sexoList.add(new SelectItem(c.getId(), c.getDescricao()));
		}
		
	}

	protected abstract void init();

	protected abstract void initEntity();

	/**  Alias para redirecionar para a tela de pesquisa. */
	protected abstract String getPaginaPesquisa();
	
	/**  Alias para redirecionar para a tela de cadastro. */
	protected abstract String getPaginaCadastro();

	/** Nome do relatorio utilizado na impressao. */
	protected abstract String getNomeRelatorio();
	
	/** Retornar o serviço da entidade. */
	protected abstract IService<T> getService();
	

	public void pesquisar() {
		this.listaPesquisa = 
//				getService().pesquisar(pesquisa);	// FIXME implementar
				getService().listarTodos();
	}

	public void salvar() {
		try {
			getService().salvar(entidade);
			setup();
			setPaginaCentral(getPaginaPesquisa());
			showMessage(Constants.MSG_SUCESSO, FacesMessage.SEVERITY_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			showMessage(Constants.MSG_ERRO, FacesMessage.SEVERITY_ERROR);
		}
	}

	protected void showMessage(String msg, Severity severityInfo) {
		FacesMessage facesMessage = new FacesMessage();
		facesMessage.setSeverity(severityInfo);
		facesMessage.setSummary(msg);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
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
			showMessage(Constants.MSG_SUCESSO, FacesMessage.SEVERITY_INFO);
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
	public void novo() {
		this.initEntity();
		setPaginaCentral(getPaginaCadastro());
	}

	/**
	 * Metodo utilizado para editar uma entidade. Sobrescrever este metodo caso
	 * necessário realizar outras operaçoes.
	 * @return string.
	 */
	public void detalhe(T entidade) {
		this.entidade = entidade;
		setPaginaCentral(getPaginaCadastro());
	}

	/**
	 * Metodo utilizado para cancelar uma edicao e retornar para a pg de inicial.
	 * @return string.
	 */
	public void cancelar() {
		init();
		setPaginaCentral(getPaginaPesquisa());
	}

	
	protected void setPaginaCentral(String pagina) {
		this.paginaCentralControlador.setPaginaCentral(pagina);
	}
	

	public void imprimir() throws FileNotFoundException {
		relatorioUtil.gerarRelatorioWeb(this.listaPesquisa, null, getNomeRelatorio());
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
	
}
