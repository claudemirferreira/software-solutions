package br.ss.core.web.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;
import br.ss.core.constant.AtivoInativoConstant;
import br.ss.core.model.dao.IAbstractDAO;
import br.ss.core.model.entity.AbstractEntity;

public abstract class GenericBean<T extends AbstractEntity> implements Serializable {

	private static final long serialVersionUID = -1229239475130268144L;

	/* ---------- Atributos ----------------------- */

	@Inject
	protected Conversation conversation;

	/** Entity usado no cadastro. */
	@Getter
	protected T entity;

	@Getter
	@Setter
	protected T itemToRemove;

	/** Entity usado na pesquisa. */
	@Getter
	protected T search;

	@Getter
	protected List<T> resultList;

	// @Inject @RequestScoped @Database
	// protected EntityManager entityManager;

	// /** Mensagem. */
	// protected CoreMessage message;

	@Getter
	protected List<Object> ativoInativoList;

	@PostConstruct
	protected void setup() {
		initConversation();
		initEntity();
		init();

		ativoInativoList = new ArrayList<Object>();
		for (AtivoInativoConstant c : AtivoInativoConstant.values()) {
			ativoInativoList.add(new SelectItem(c.isValue(), c.getDescricao()));
		}
	}

	/* ---------- Metodos ----------------------- */

	protected abstract void init();

	protected abstract void initEntity();

	protected abstract IAbstractDAO<T> getDAO();

	private void initConversation() {
		if (conversation.isTransient()) {
			conversation.begin();
		}
	}

	protected void endConversation() {
		conversation.end();
	}

	public void search() {
		this.resultList = getDAO().listAll();
	}

	public String save() {
		try {

			getDAO().saveOrUpdate(entity);

//			endConversation();
			sendInfoMessageToUser(MSG_SUCESSO);
			return PESQUISA;

		} catch (Exception e) {
			e.printStackTrace();
			// TODO message
		}
		return ERRO;
	}

	public void remove() {
		remove(itemToRemove);
	}

	public void remove(T itemRemove) {
		try {
			getDAO().remove(itemRemove);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO message
		}

	}

	/**
	 * Metodo utilizado para ir para a tela de cadastra da entidade.
	 * 
	 * @return string.
	 */
	public String cadastrar() {
		this.initEntity();
		return CADASTRO;
	}

	/**
	 * Metodo utilizado para editar uma entidade. Sobrescrever este metodo caso
	 * necessário realizar outras operaçoes.
	 * 
	 * @return string.
	 */
	public String editar(T entity) {
		this.entity = entity;
		return CADASTRO;
	}

	/**
	 * Metodo utilizado para editar uma entidade.
	 * 
	 * @return string.
	 */
	public String cancel() {
		endConversation();
		init();
		return PESQUISA;
	}

	/**
	 * Retorna a qtde de itens retornados na pesquisa.
	 * 
	 * @return Integer
	 */
	public Integer resultCount() {
		return resultList == null ? 0 : resultList.size();
	}

	protected void sendInfoMessageToUser(String message) {
		FacesContext context = getContext();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				message, message));
	}

	protected void sendWarnMessageToUser(String message) {
		FacesContext context = getContext();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
				message, message));
	}

	protected void sendErrorMessageToUser(String message) {
		FacesContext context = getContext();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
				message, message));
	}

	protected FacesContext getContext() {
		return FacesContext.getCurrentInstance();
	}
	
	/* ---------- Others ------------- */
	/**
	 * Alias para redirecionar para a tela de cadastro. Mapear o mesmo no
	 * page.xml.
	 */
	public static final String CADASTRO = "create.seam?faces-redirect=true";

	/**
	 * Alias para redirecionar para a tela de pesquisa. Mapear o mesmo no
	 * page.xml.
	 */
	public static final String PESQUISA = "search.seam?faces-redirect=true";

	/**
	 * Alias para redirecionamento apos uma operacao de SUCESSO. Mapear o mesmo
	 * no page.xml.
	 */
	public static final String SUCESSO = "sucesso";

	/**
	 * Alias para redirecionamento apos uma operacao com ERRO. Mapear o mesmo no
	 * page.xml.
	 */
	public static final String ERRO = "erro";

	/**
	 * Msg de sucesso.
	 */
	public static final String MSG_SUCESSO = "Operação realizada com sucesso.";
	
}
