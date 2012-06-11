package br.ss.core.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Selection;

public class Query<T> implements PaginatedQuery<T> {

	private AbstractDAO<?, ?> dao;
	private Integer maxResults;
	private Integer from;

	public Query( AbstractDAO<?, ?> dao ) {
		this.dao = dao;
	}

	public Query<T> fromPage( int value ) {
        from = value;
        return this;
    }
	
	public Query<T> withMaxResults( int value ) {
	    maxResults = value;
	    return this;
	}
	
	public T getResultList( Paginated paginatedQuery ) {
		from = paginatedQuery.getPage();
		maxResults = paginatedQuery.getLimit();
		return getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public T getResultList() {
		if ( dao.toCustomSelection != null ) {
			dao.createCustomSelection();
		}
		
		dao.getDelegate().criteria.where( dao.getDelegate().qb.and( dao.getDelegate().restriction.toArray( new Predicate[0]) ) );
		dao.getDelegate().criteria.orderBy( dao.getDelegate().orders );
		createGroupBy();
		
		javax.persistence.Query query = dao.getDelegate().entityManager.createQuery( dao.getDelegate().criteria );
		
		configurarPaginacao( query );
		
		List<?> result = ( List<?> ) query.getResultList();
		
		dao.getDelegate().clear();
		return (T) result;
	}
	
	@SuppressWarnings("unchecked")
	public T getSingleResult() {
		dao.getDelegate().criteria.where( dao.getDelegate().qb.and( dao.getDelegate().restriction.toArray( new Predicate[0]) ) );
		try {
			
			dao.getDelegate().criteria.orderBy( dao.getDelegate().orders );
			createGroupBy();
			
			javax.persistence.Query query = dao.getDelegate().entityManager.createQuery( dao.getDelegate().criteria );
			
			configurarPaginacao(query);
			
			T retorno = (T) query.getSingleResult();
			dao.getDelegate().clear();
			return  retorno;
		} catch ( NoResultException ne ) {
			dao.getDelegate().clear();
			throw ne;
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public long count() {
		if ( dao.criteria.isDistinct() ) {
			dao.getCriteriaBuilder().select( (Selection) dao.getDelegate() .qb.countDistinct( dao.getRootOrJoin().get( "id" ) ) );	
		} else {
			dao.getCriteriaBuilder().select( (Selection) dao.getDelegate().qb.count( dao.getRootOrJoin().get( "id" ) ) );	
		}
		
		dao.getDelegate().criteria.where( dao.getDelegate().qb.and( dao.getDelegate().restriction.toArray( new Predicate[0]) ) );
		
		createGroupBy();
		
		javax.persistence.Query query = dao.getDelegate().entityManager.createQuery( dao.getDelegate().criteria );
		Number result = ( Number ) query.getSingleResult();
		
		return result.longValue(); 
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public long countDistinct( Restriction res ) {
		dao.getCriteriaBuilder().select( (Selection) dao.getDelegate().qb.countDistinct( dao.getRootOrJoin().get( res.attribute ) ) );
		
		dao.getDelegate().criteria.where( dao.getDelegate().qb.and( dao.getDelegate().restriction.toArray( new Predicate[0]) ) );
		
		createGroupBy();
		
		javax.persistence.Query query = dao.getDelegate().entityManager.createQuery( dao.getDelegate().criteria );
		Number result = ( Number ) query.getSingleResult();
		
		return result.longValue(); 
	}

	private void createGroupBy() {
		if ( dao.getDelegate().groups.size() > 0 ) {
			dao.getDelegate().criteria.groupBy( dao.getDelegate().groups.toArray( new Path<?>[0] ) );	
		}
	}
	
	private void configurarPaginacao( javax.persistence.Query query ) {
		if ( from != null ) {
		    query.setFirstResult( from * maxResults );
		}
		
		if ( maxResults != null ) {
		    query.setMaxResults( maxResults );
		}
	}
	
}
