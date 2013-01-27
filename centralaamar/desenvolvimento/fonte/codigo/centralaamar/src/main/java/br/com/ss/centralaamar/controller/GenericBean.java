package br.com.ss.centralaamar.controller;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
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
	
	/** Entity usado na pesquisa. */
	@Getter
	protected T search;

	private Class<T> entityClass;
	
	@Getter
	@Setter
	protected T itemToRemove;


	@Getter 
	protected List<T> resultList;
	
//	@Inject
//	@Autowired
//	protected Conversation conversation;	// TODO conversation

	/* ---------- Metodos ----------------------- */

	@PostConstruct
	protected void setup() throws InstantiationException, IllegalAccessException {
//		initConversation();
		instanciateEntityClass();
		initEntity();
		init();

	}

	public void init() {
		this.search();
	}

	protected void initEntity() throws InstantiationException, IllegalAccessException {
		this.entity = entityClass.newInstance();
		this.search = entityClass.newInstance();
		
	}
	
//	protected abstract IAbstractDAO<T> getDAO();

	protected abstract IService<T> getService();
	

//	private void initConversation() {
//		if (conversation.isTransient()) {
//			conversation.begin();
//		}
//	}

//	protected void endConversation() {
//		conversation.end();
//	}

	
	
	private void instanciateEntityClass() {
		if ( getClass().getGenericSuperclass() instanceof ParameterizedType ) {
			ParameterizedType paramType = (ParameterizedType)  getClass().getGenericSuperclass();
			entityClass = (Class<T>) paramType.getActualTypeArguments()[0];
		}
	}
	
	
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
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public String cadastrar() throws InstantiationException, IllegalAccessException {
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
