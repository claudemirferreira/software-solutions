 package br.ss.core.dao;

import javax.persistence.criteria.From;
import javax.persistence.metamodel.SingularAttribute;

public class NumericRestriction <D extends AbstractDAO<D, T>, T, P extends Number> extends Restriction<D, T, P> {

	public NumericRestriction( From<?,T> f, D dao, SingularAttribute<T, P> a ) {
		super( f, dao, a);
	}

	public Restriction<?,?,?> ge( P value ) {
		condition = dao.getBuilder().ge( from.get( attribute ), value );
		nullValue = isValueNullOrEmpty( value );
		return this;
	}
	
	public Restriction<?,?,?> gt( P value ) {
		condition = dao.getBuilder().gt( from.get( attribute ), value );
		nullValue = isValueNullOrEmpty( value );
		return this;
	}
	
	public Restriction<?,?,?> le( P value ) {
		condition = dao.getBuilder().le( from.get( attribute ), value );
		nullValue = isValueNullOrEmpty( value );
		return this;
	}
	
	public Restriction<?,?,?> lt( P value ) {
		condition = dao.getBuilder().lt( from.get( attribute ), value );
		nullValue = isValueNullOrEmpty( value );
		return this;
	}
	
}
