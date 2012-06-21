package br.ss.core.model.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaQuery;

import lombok.Getter;
import br.ss.authenticator.model.entity.Sistema;
import br.ss.core.annotation.qualifier.Database;
import br.ss.core.model.dao.IAbstractDAO;
import br.ss.core.model.entity.AbstractEntity;

@SuppressWarnings( { "serial", "unchecked" } )
public abstract class AbstractDAO<T extends AbstractEntity> implements Serializable, IAbstractDAO<T> {

	@Getter
	protected Class<T> persistentClass;
	
//	@Inject 
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	@RequestScoped 
	@Database
	@Getter
	protected EntityManager entityManager;

//	public AbstractDAO( Class<T> clazz) {
//		this.persistentClass = clazz;
//	}
	
	
	public AbstractDAO() {
		initEntityClass();
	}

	public void initEntityClass() {
		if ( getClass().getGenericSuperclass() instanceof ParameterizedType ) {
			ParameterizedType paramType = (ParameterizedType)  getClass().getGenericSuperclass();
			persistentClass = (Class<T>) paramType.getActualTypeArguments()[0];
		} else if ( getClass().getSuperclass().getGenericSuperclass() instanceof ParameterizedType ) {
			ParameterizedType paramType = (ParameterizedType)  getClass().getSuperclass().getGenericSuperclass();
			persistentClass = (Class<T>) paramType.getActualTypeArguments()[0];
		}
	}
	
	
	@Override
	public T getByPrimaryKey( AbstractEntity entity ) {
		return entityManager.find(persistentClass, entity.getId() );
	}
	
	@Override
	public T getByPrimaryKey( Long id ) {
		return entityManager.find(persistentClass, id );
	}
	
	@Override
	public T merge( T entity ) {
		return entityManager.merge( entity );
	}
	
	@Override
	public void remove( T entity ) {
        entityManager.remove( entity );
    }
	
	@Override
	public void remove( Long id ) {
		getEntityManager().remove( getEntityManager().find( persistentClass, id ) );
	}
	
	@Override
	public void persist( T entity ) {
        entityManager.persist( entity );
    }
	
	
	@Override
	public void saveOrUpdate(T entity) {
		if ( !entity.isPersistent() ) {
			 persist(entity);
		} else {
			merge(entity);
		}
	}

	@Override
	public List<T> listAll() {
		CriteriaQuery<Sistema> criteria = entityManager.getCriteriaBuilder().createQuery(Sistema.class);
		return (List<T>) this.entityManager.createQuery(
				criteria.select(criteria.from(Sistema.class))).getResultList();
	}

	
}
