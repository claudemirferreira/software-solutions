package br.ss.core.dao;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.From;
import javax.persistence.metamodel.SingularAttribute;

import br.ss.core.util.StringUtils;

public class StringRestriction<D extends AbstractDAO<D, T>, T, P> extends Restriction<D, T, String> {

	public StringRestriction( From<?,T> f, D dao, SingularAttribute<T, String> a ) {
		super( f, dao, a);
	}
	
	public Restriction<?,?,?> like( String value ) {
		nullValue = isValueNullOrEmpty( value );
		
		if ( nullValue ) {
			value = "";
		}
		Expression<String> funcao = dao.getBuilder().function("to_ascii", String.class, dao.getBuilder().lower(from.get( attribute )));
		condition = dao.getBuilder().like(funcao, "%" + StringUtils.removerAcento(value.toLowerCase()) + "%" );
		
		return this;
	}
	
	@Override
	public Restriction<?,?,?> by( String value ) {
		nullValue = isValueNullOrEmpty( value );
		
		if ( nullValue ) {
			value = "";
		}
		
		Expression<String> funcao = dao.getBuilder().lower(  dao.getBuilder().function("to_ascii", String.class, from.get( attribute )) );
		condition = dao.getBuilder().equal(funcao, StringUtils.removerAcento( value.toLowerCase() ));
		
		return this;
	}
	
}
