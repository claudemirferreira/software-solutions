package br.com.ss.centralaamar.model.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaQuery;

import lombok.Getter;

import org.springframework.stereotype.Repository;

import br.com.ss.centralaamar.model.entity.AbstractEntity;

@SuppressWarnings("unchecked")
@Repository
public abstract class AbstractDAO<T extends AbstractEntity> implements
		Serializable, IAbstractDAO<T> {

	private static final long serialVersionUID = -6314171287987529278L;

	@Getter
	protected Class<T> persistentClass;

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	@RequestScoped
	@Getter
	protected EntityManager entityManager;

	public AbstractDAO() {
		initEntityClass();
	}

	public void initEntityClass() {
		if (getClass().getGenericSuperclass() instanceof ParameterizedType) {
			ParameterizedType paramType = (ParameterizedType) getClass()
					.getGenericSuperclass();
			persistentClass = (Class<T>) paramType.getActualTypeArguments()[0];
		} else if (getClass().getSuperclass().getGenericSuperclass() instanceof ParameterizedType) {
			ParameterizedType paramType = (ParameterizedType) getClass()
					.getSuperclass().getGenericSuperclass();
			persistentClass = (Class<T>) paramType.getActualTypeArguments()[0];
		}
	}

	@Override
	public T getByPrimaryKey(AbstractEntity entity) {
		return entityManager.find(persistentClass, entity.getId());
	}

	@Override
	public T getByPrimaryKey(Long id) {
		return entityManager.find(persistentClass, id);
	}

	@Override
	public T merge(T entity) {
		return merge(entity, true);
	}

	@Override
	public T merge(T entity, boolean flush) {
		T t = entityManager.merge(entity);
		flush(flush);
		return t;
	}

	@Override
	public void remove(T entity) {
		entityManager.remove(entity);
		flush();
	}

	@Override
	public void remove(Long id) {
		getEntityManager().remove(getEntityManager().find(persistentClass, id));
		flush();
	}

	@Override
	public void persist(T entity) {
		persist(entity, true);
	}

	@Override
	public void persist(T entity, boolean flush) {
		entityManager.persist(entity);
		flush(flush);
	}

	@Override
	public void saveOrUpdate(T entity) {
		saveOrUpdate(entity, true);
	}

	@Override
	public void saveOrUpdate(T entity, boolean flush) {
		if (!entity.isPersistent()) {
			persist(entity);
		} else {
			merge(entity);
		}
		flush(flush);
	}

	@Override
	public void flush() {
		entityManager.flush();
	}

	public void flush(boolean flush) {
		if (flush) {
			flush();
		}
	}

	@Override
	public List<T> listAll() {
		CriteriaQuery<T> criteria = entityManager.getCriteriaBuilder()
				.createQuery(persistentClass);
		return (List<T>) this.entityManager.createQuery(
				criteria.select(criteria.from(persistentClass)))
				.getResultList();
	}

	/**
	 * Gera o código hql com as condições informadas.
	 * 
	 * <pre>
	 * Ex:
	 * * Select:
	 *  <code>select ec from EntityClass </code>
	 * * Condições:
	 *  <code>where ec.cond1 = true and ec.cond2 = 1</code>
	 * </pre>
	 * 
	 * @param select
	 *            O select da pesquisa
	 * @param stmt
	 *            Lista com as condiçoes de filtro da pesquisa (sem where e and)
	 * @return String Hql gerado
	 */
	protected String generateHql(String select, List<String> stmt) {
		StringBuilder hql = new StringBuilder(select);
		boolean addedWhere = false;
		String where = " where ", and = " and ";
		for (String s : stmt) {
			if (addedWhere) {
				hql.append(and + s);
			} else {
				hql.append(where + s);
				addedWhere = true;
			}
		}
		return hql.toString();
	}

	public boolean notEmpty(Object obj) {
		return obj != null;
	}

	public boolean notEmpty(String str) {
		return str != null && str.length() > 0;
	}

	protected String generateHql(String select, List<String> cond,
			String orderby) {
		StringBuilder hql = new StringBuilder();
		hql.append(select);
		boolean addWhere = false;
		for (String str : cond) {
			if (!addWhere) {
				addWhere = true;
				hql.append(" where " + str);
			} else {
				hql.append(" and " + str);
			}
		}
		if (notEmpty(orderby)) {
			hql.append(orderby);
		}
		return hql.toString();
	}

	// public static void main(String[] args) {
	// StringBuilder hql = new StringBuilder();
	// List<String> cond = new ArrayList<String>();
	//
	// hql.append( "select m from Membro m ");
	// hql.append(" join m.pequenoGrupo pg ");
	// hql.append(" join m.profissao prof ");
	// cond.add(" upper(m.nome) like :nome ");
	// cond.add(" m.pequenoGrupo = :pg ");
	// cond.add(" m.profissao = :prof ");
	// String orderby = " order by m.nome asc ";
	//
	// MembroDAO dao = new MembroDAO();
	// System.out.println( dao.generateHql(hql.toString(), cond, orderby) );
	//
	// }

}
