package br.ss.authenticator.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.ss.authenticator.model.entity.Sistema;

/**
 * Backing bean for Sistema entities.
 * <p>
 * This class provides CRUD functionality for all Sistema entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD
 * framework or custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class SistemaBean_Old implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * Support creating and retrieving Sistema entities
	 */

	private Integer id;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private Sistema sistema;

	public Sistema getSistema() {
		return this.sistema;
	}

	@Inject
	private Conversation conversation;

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	public String create() {

		this.conversation.begin();
		return "create?faces-redirect=true";
	}

	public void retrieve() {

		if (FacesContext.getCurrentInstance().isPostback()) {
			return;
		}

		if (this.conversation.isTransient()) {
			this.conversation.begin();
		}

		if (this.id == null) {
			this.sistema = this.search;
		} else {
			this.sistema = this.entityManager.find(Sistema.class, getId());
		}
	}

	/*
	 * Support updating and deleting Sistema entities
	 */

	public String update() {
		this.conversation.end();

		try {
			if (this.id == null) {
				this.entityManager.persist(this.sistema);
				return "search?faces-redirect=true";
			} else {
				this.entityManager.merge(this.sistema);
				return "view?faces-redirect=true&id="
						+ this.sistema.getIdSistema();
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
			return null;
		}
	}

	public String delete() {
		this.conversation.end();

		try {
			this.entityManager.remove(this.entityManager.find(Sistema.class,
					getId()));
			this.entityManager.flush();
			return "search?faces-redirect=true";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
			return null;
		}
	}

	/*
	 * Support searching Sistema entities with pagination
	 */

	private int page;
	private long count;
	private List<Sistema> pageItems;

	private Sistema search = new Sistema();

	public int getPage() {
		return this.page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return 10;
	}

	public Sistema getSearch() {
		return this.search;
	}

	public void setSearch(Sistema search) {
		this.search = search;
	}

	public void search() {
		this.page = 0;
	}
	
	

	public void paginate() {

		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();

		// Populate this.count

		CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
		Root<Sistema> root = countCriteria.from(Sistema.class);
		countCriteria = countCriteria.select(builder.count(root)).where(
				getSearchPredicates(root));
		this.count = this.entityManager.createQuery(countCriteria)
				.getSingleResult();

		// Populate this.pageItems

		CriteriaQuery<Sistema> criteria = builder.createQuery(Sistema.class);
		root = criteria.from(Sistema.class);
		TypedQuery<Sistema> query = this.entityManager.createQuery(criteria
				.select(root).where(getSearchPredicates(root)));
		query.setFirstResult(this.page * getPageSize()).setMaxResults(
				getPageSize());
		this.pageItems = query.getResultList();
	}
	
	
	

	private Predicate[] getSearchPredicates(Root<Sistema> root) {

		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		List<Predicate> predicatesList = new ArrayList<Predicate>();

		Long idSistema = this.search.getIdSistema();
		if (idSistema != 0) {
			predicatesList.add(builder.equal(root.get("idSistema"), idSistema));
		}
		String txSistema = this.search.getTxSistema();
		if (txSistema != null && !"".equals(txSistema)) {
			predicatesList.add(builder.like(root.<String> get("txSistema"),
					'%' + txSistema + '%'));
		}
		String txDescricao = this.search.getTxDescricao();
		if (txDescricao != null && !"".equals(txDescricao)) {
			predicatesList.add(builder.like(root.<String> get("txDescricao"),
					'%' + txDescricao + '%'));
		}
		String txSigla = this.search.getTxSigla();
		if (txSigla != null && !"".equals(txSigla)) {
			predicatesList.add(builder.like(root.<String> get("txSigla"),
					'%' + txSigla + '%'));
		}

		return predicatesList.toArray(new Predicate[predicatesList.size()]);
	}

	public List<Sistema> getPageItems() {
		return this.pageItems;
	}

	public long getCount() {
		return this.count;
	}

	/*
	 * Support listing and POSTing back Sistema entities (e.g. from inside an
	 * HtmlSelectOneMenu)
	 */

	public List<Sistema> getAll() {

		CriteriaQuery<Sistema> criteria = this.entityManager
				.getCriteriaBuilder().createQuery(Sistema.class);
		return this.entityManager.createQuery(
				criteria.select(criteria.from(Sistema.class))).getResultList();
	}

	public Converter getConverter() {

		return new Converter() {

			@Override
			public Object getAsObject(FacesContext context,
					UIComponent component, String value) {

				return SistemaBean_Old.this.entityManager.find(Sistema.class,
						new Integer (value));
			}

			@Override
			public String getAsString(FacesContext context,
					UIComponent component, Object value) {

				if (value == null) {
					return "";
				}

				return String.valueOf(((Sistema) value).getIdSistema());
			}
		};
	}

	/*
	 * Support adding children to bidirectional, one-to-many tables
	 */

	private Sistema add = new Sistema();

	public Sistema getAdd() {
		return this.add;
	}

	public Sistema getAdded() {
		Sistema added = this.add;
		this.add = new Sistema();
		return added;
	}
}