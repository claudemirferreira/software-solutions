package br.ss.authenticator.model.dao;

import java.io.Serializable;
import br.ss.core.dao.AbstractDAO;
import br.ss.authenticator.model.entity.Sistema;
import br.ss.authenticator.model.entity.Sistema_;
import javax.persistence.criteria.From;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.ListAttribute;
import br.ss.core.dao.DateRestriction;
import br.ss.core.dao.NumericRestriction;
import br.ss.core.dao.StringRestriction;
import br.ss.core.dao.BooleanRestriction;
import br.ss.core.dao.EnumRestriction;
import br.ss.core.dao.EnumRestriction;
import java.math.BigDecimal;
import java.util.Calendar;

public class SistemaDAO_ extends AbstractDAO<SistemaDAO_, Sistema>  implements Serializable {

	/**
	* Serial.
	 */
	private static final long serialVersionUID = 1L;

	public SistemaDAO_() {
	}

 	public SistemaDAO_( AbstractDAO<?, ?> delegate, SingularAttribute attr ) {
		super( delegate, attr );
	}

	public SistemaDAO_( AbstractDAO<?, ?> delegate, ListAttribute attr ) {
		super( delegate, attr );
	}

	public SistemaDAO_( AbstractDAO<?, ?> delegate, SetAttribute attr ) {
		super( delegate, attr );
	}

	public BooleanRestriction<SistemaDAO_, Sistema, Boolean> ativo() {
		return new BooleanRestriction<SistemaDAO_, Sistema, Boolean> ( (From<?,Sistema>) getRootOrJoin(), this, Sistema_.ativo );

	}

	public NumericRestriction<SistemaDAO_, Sistema, Long> id() {
		return new NumericRestriction<SistemaDAO_, Sistema, Long> ( (From<?,Sistema>) getRootOrJoin(), this, Sistema_.id );

	}

	public StringRestriction<SistemaDAO_, Sistema, String> txDescricao() {
		return new StringRestriction<SistemaDAO_, Sistema, String> ( (From<?,Sistema>) getRootOrJoin(), this, Sistema_.txDescricao );

	}

	public StringRestriction<SistemaDAO_, Sistema, String> txSigla() {
		return new StringRestriction<SistemaDAO_, Sistema, String> ( (From<?,Sistema>) getRootOrJoin(), this, Sistema_.txSigla );

	}

	public StringRestriction<SistemaDAO_, Sistema, String> txSistema() {
		return new StringRestriction<SistemaDAO_, Sistema, String> ( (From<?,Sistema>) getRootOrJoin(), this, Sistema_.txSistema );

	}

}