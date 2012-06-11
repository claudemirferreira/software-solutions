package br.ss.core.dao;

import javax.persistence.criteria.From;
import javax.persistence.metamodel.SingularAttribute;

public class OrderBy  <D extends AbstractDAO<D, T>, T, P> {

	protected D dao; 
	protected SingularAttribute<T, P> attribute;
	protected From<?,T> from;
	
	

}
