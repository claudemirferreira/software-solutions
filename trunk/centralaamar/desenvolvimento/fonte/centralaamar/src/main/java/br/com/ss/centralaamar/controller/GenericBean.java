package br.com.ss.centralaamar.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import lombok.Getter;
import lombok.Setter;

import org.springframework.stereotype.Component;

import br.com.ss.centralaamar.model.dao.IAbstractDAO;
import br.com.ss.centralaamar.model.entity.AbstractEntity;
import br.com.ss.centralaamar.service.IService;

@Component
public abstract class GenericBean<T extends AbstractEntity> implements Serializable {

	/* ---------- Atributos ----------------------- */

	private static final long serialVersionUID = -1229239475130268144L;

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
	
//	@Inject
//	@Autowired
//	protected Conversation conversation;	// TODO conversation

	/* ---------- Metodos ----------------------- */

	@PostConstruct
	protected void setup() {
//		initConversation();
		initEntity();
		init();

	}

	protected abstract void init();

	protected abstract void initEntity();

	protected abstract IAbstractDAO<T> getDAO();

	protected abstract IService<T> getService();
	

//	private void initConversation() {
//		if (conversation.isTransient()) {
//			conversation.begin();
//		}
//	}

//	protected void endConversation() {
//		conversation.end();
//	}

	public void search() {
		this.resultList = getService().search(search);
	}

	public String save() {
		try {
			getService().save(entity);
			init();
			// TODO enviar msg de sucesso
			return resolveNavigation(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void remove() {
		remove(itemToRemove);
		search();
		setItemToRemove(null);
	}

	
	public void remove(T itemRemove) {
		getService().remove(itemRemove);
	}

	/**
	 * Metodo utilizado para ir para a tela de cadastra da entidade.
	 * @return string.
	 */
	public String cadastrar() {
		this.initEntity();
		return resolveNavigation(true);
	}

	/**
	 * Metodo utilizado para editar uma entidade. Sobrescrever este metodo caso
	 * necessário realizar outras operaçoes.
	 * @return string.
	 */
	public String editar(T entity) {
		this.entity = entity;
		return resolveNavigation(true);
	}

	/**
	 * Metodo utilizado para editar uma entidade.
	 * @return string.
	 */
	public String cancel() {
//		endConversation();
		init();
		return resolveNavigation(false);
	}

	
	/* ---------- Others ------------- */
	protected String resolveNavigation(boolean crud ) {
		String url = "/pages/" + entity.getClass().getSimpleName().toLowerCase() + "/";
		url += crud ? "create.jsf" : "search.jsf";
		return url;
	}
	
	
}
