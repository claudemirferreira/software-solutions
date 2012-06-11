package br.ss.core.dao;

import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;
import javax.persistence.metamodel.SingularAttribute;

import lombok.Getter;

public class Restriction <D extends AbstractDAO<D, T>, T, P> {

	protected D dao; 
	protected SingularAttribute<T, P> attribute;
	protected From<?,T> from;
	protected boolean asc = true;
	protected Predicate condition;
	protected boolean lower;
	
	@Getter
	protected boolean nullValue;
	
	public Restriction( From<?,T> f, D dao, SingularAttribute<T,P> a ) {
		this.dao = dao;
		this.attribute = a;
		this.from = f;
	}
	
	public Restriction<?,?,?> by( P value ) {
		condition = dao.getBuilder().equal( from.get( attribute ), value );
		nullValue = isValueNullOrEmpty( value );
		return this;
	}
	
	public Restriction<?,?,?> notEqual( P value ) {
		condition = dao.getBuilder().notEqual( from.get( attribute ), value );
		nullValue = isValueNullOrEmpty( value );
		return this;
	}
	
	public Restriction<?, ?, ?> in(P... value) {
		nullValue = isValueNullOrEmpty(value);
		if (!nullValue) {
			condition = from.get(attribute).in(value);
		}
		return this;
	}

	public Restriction<?,?,?> isNotNull() {
		condition = from.get( attribute ).isNotNull();
		nullValue = false;
		return this;
	}
	
	public Restriction<?,?,?> isNull() {
		condition = from.get( attribute ).isNull();
		nullValue = false;
		return this;
	}
	
	public Restriction<?,?,?>  orderByAsc() {
		asc = true;
		return this; 
	}
	
	public Restriction<?,?,?> orderByDesc() {
		asc = false;
		return this; 
	}
	
	protected boolean isValueNullOrEmpty(Object[] value) {
		if (value == null || value.length == 0) {
			return true;
		}
		return false;
	}
	
	protected boolean isValueNullOrEmpty( Object value ) {
		if ( value == null ) {
			return true;
		}
		return false;
	}
	
	public Restriction<?,?,?> asLowerCase() {
		lower = true;
		return this;
	}
	
}
