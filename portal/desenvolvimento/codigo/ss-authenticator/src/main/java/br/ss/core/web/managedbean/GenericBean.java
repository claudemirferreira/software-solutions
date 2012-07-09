package br.ss.core.web.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;
import br.ss.authenticator.service.IService;
import br.ss.core.model.dao.IAbstractDAO;
import br.ss.core.model.entity.AbstractEntity;

@Named
public abstract class GenericBean<T extends AbstractEntity> implements Serializable {

	private static final long serialVersionUID = -1229239475130268144L;
	
	/**
	 * Alias para redirecionar para a tela de cadastro.
	 */
	public static final String CADASTRO = "create.seam";

	/**
	 * Alias para redirecionar para a tela de pesquisa. */
	public static final String PESQUISA = "search.seam";
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
	
	
	/* ---------- Metodos ----------------------- */

	@PostConstruct
	protected void setup() {
		initConversation();
		initEntity();
		init();

	}

	protected abstract void init();

	protected abstract void initEntity();

	protected abstract IAbstractDAO<T> getDAO();

	protected abstract IService<T> getService();
	

	private void initConversation() {
		if (conversation.isTransient()) {
			conversation.begin();
		}
	}

	protected void endConversation() {
		conversation.end();
	}

	public void search() {
		this.resultList = getService().search(search);
	}

	public String save() {
		try {
			getService().save(entity);
			init();
			return PESQUISA;
		} catch (Exception e) {
//			e.printStackTrace();	// 
		}
		return null;
	}

	public void remove() {
		remove(itemToRemove);
	}

	
	public void remove(T itemRemove) {
		try {
			getService().remove(itemRemove);
		} catch (Exception e) {
			e.printStackTrace();
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

	
	/* ---------- Others ------------- */


	
}
