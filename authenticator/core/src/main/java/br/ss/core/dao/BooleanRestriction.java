package br.ss.core.dao;

import javax.persistence.criteria.From;
import javax.persistence.metamodel.SingularAttribute;

public class BooleanRestriction <D extends AbstractDAO<D, T>, T, P> extends Restriction<D, T, Boolean> {

	public BooleanRestriction( From<?,T> f, D dao, SingularAttribute<T, Boolean> a ) {
		super( f, dao, a);
	}
	
	public Restriction<?,?,?> isTrue() {
		condition = dao.getBuilder().equal( from.get( attribute ), true );
		nullValue = false;
		return this;
	}
	
	public Restriction<?,?,?> isFalse() {
		condition = dao.getBuilder().equal( from.get( attribute ), false );
		nullValue = false;
		return this;
	}
	
}
