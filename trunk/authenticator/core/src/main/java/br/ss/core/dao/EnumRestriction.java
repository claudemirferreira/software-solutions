package br.ss.core.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.From;
import javax.persistence.metamodel.SingularAttribute;

import br.ss.core.util.StringUtils;

public class EnumRestriction <D extends AbstractDAO<D, T>, T, P> extends Restriction<D, T, P> {

	public EnumRestriction( From<?,T> f, D dao, SingularAttribute<T, P> a ) {
		super( f, dao, a);
	}
	
	@SuppressWarnings("unchecked")
	public Restriction<?,?,?> like( String value ) {
		value = value == null ? "" : value.toLowerCase();
		List<P> lista = new ArrayList<P>();
		for ( P tipo : attribute.getJavaType().getEnumConstants()) {
			if (!value.equals("") && StringUtils.removerAcento( ( ( Constant<P> ) tipo ).getDescricao()).toLowerCase().contains(value)) {
				lista.add(tipo);
			}
		}
		
		in( (P[]) lista.toArray( ) );
		
		return this;
	}
}

