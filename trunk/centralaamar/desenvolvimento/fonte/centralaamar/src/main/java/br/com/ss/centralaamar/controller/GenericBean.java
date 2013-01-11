package br.com.ss.centralaamar.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;

import org.springframework.stereotype.Component;

import br.com.ss.centralaamar.model.dao.IAbstractDAO;
import br.com.ss.centralaamar.model.entity.AbstractEntity;
import br.com.ss.centralaamar.service.IService;

@Component
public abstract class GenericBean<T extends AbstractEntity> implements Serializable {

	private static final long serialVersionUID = -1229239475130268144L;
	
	/**
	 * Alias para redirecionar para a tela de cadastro.
	 */
	public static final String CADASTRO = "create.jsf";

	/**
	 * Alias para redirecionar para a tela de pesquisa. */
	public static final String PESQUISA = "search.jsf";
	/* ---------- Atributos ----------------------- */

//	@Inject
//	@Autowired
//	protected Conversation conversation;	// TODO conversation

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
			return PESQUISA;
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
		return CADASTRO;
	}

	/**
	 * Metodo utilizado para editar uma entidade. Sobrescrever este metodo caso
	 * necessário realizar outras operaçoes.
	 * @return string.
	 */
	public String editar(T entity) {
		this.entity = entity;
		System.out.println(">> editar: " + entity );
		
		return "http://localhost:8080/centralaamar/pages/pastor/" + CADASTRO;
	}

	/**
	 * Metodo utilizado para editar uma entidade.
	 * @return string.
	 */
	public String cancel() {
//		endConversation();
		init();
		return PESQUISA;
	}

	
	/* ---------- Others ------------- */


	
}
