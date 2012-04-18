package br.ss.core.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

import br.ss.core.model.entity.GenericEntity;

public interface IGenericDAO<T extends GenericEntity, ID extends Serializable> {

    T findById(ID id, boolean lock);
 
    List<T> findByExample(T exampleInstance);
 
    T makePersistent(T entity);
 
    void makeTransient(T entity);
    
    T find(Object paramObject, Serializable paramSerializable);
	T findByPrimaryKey( ID id, boolean lock );

    T findByPrimaryKey( ID id );

    List<T> findAll();

    List<T> findByExample( T exampleInstance, String... excludeProperty );

    T persist( T entity );

    T persist( T entity, boolean flush );

    T merge( T entity );
    
    T merge( T entity, boolean flush );

    void remove( T entity );

    List<T> findByCriteria( Criterion... criterion );

    long count( Criterion... criterion );

    void refresh( T entity );

    Criteria createCriteria();

    void evict( T entity );

    Criterion equalsIgnoreCaseToAscii( String atributo, String value );
    
    Criterion likeIgnoreCaseToAscii( String atributo, String value );

    Criterion iLiketoAscii( String atributo, String value );

    Criterion iLiketoAscii( String alias, String atributo, String value );

    void clear();

    void flush();

    Criterion toDate( String atributo, Date value, String condicao );

    Criterion toDate( String atributo, Date value, String condicao, Class<?> clazz );
    
    Session getSession();
	 
}