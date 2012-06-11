package br.ss.core.dao;

public interface PaginatedQuery<T> {

	Query<T> fromPage( int value );
	Query<T> withMaxResults( int value );
	T getResultList();
	T getResultList( Paginated paginatedQuery );
	long count();
	T getSingleResult();
}
