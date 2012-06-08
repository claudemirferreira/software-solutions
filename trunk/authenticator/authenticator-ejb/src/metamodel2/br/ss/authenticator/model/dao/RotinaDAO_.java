package br.ss.authenticator.model.dao;

import java.io.Serializable;
import br.ss.core.dao.AbstractDAO;
import br.ss.authenticator.model.entity.Rotina;
import br.ss.authenticator.model.entity.Rotina_;
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

public class RotinaDAO_ extends AbstractDAO<RotinaDAO_, Rotina>  implements Serializable {

	/**
	* Serial.
	 */
	private static final long serialVersionUID = 1L;

	public RotinaDAO_() {
	}

 	public RotinaDAO_( AbstractDAO<?, ?> delegate, SingularAttribute attr ) {
		super( delegate, attr );
	}

	public RotinaDAO_( AbstractDAO<?, ?> delegate, ListAttribute attr ) {
		super( delegate, attr );
	}

	public RotinaDAO_( AbstractDAO<?, ?> delegate, SetAttribute attr ) {
		super( delegate, attr );
	}

	public BooleanRestriction<RotinaDAO_, Rotina, Boolean> csStatus() {
		return new BooleanRestriction<RotinaDAO_, Rotina, Boolean> ( (From<?,Rotina>) getRootOrJoin(), this, Rotina_.csStatus );

	}

	public NumericRestriction<RotinaDAO_, Rotina, Long> id() {
		return new NumericRestriction<RotinaDAO_, Rotina, Long> ( (From<?,Rotina>) getRootOrJoin(), this, Rotina_.id );

	}

	protected br.ss.authenticator.model.dao.SistemaDAO_ sistemaDAO_;
	public br.ss.authenticator.model.dao.SistemaDAO_ sistema() {
 		if ( sistemaDAO_ == null ) {
			sistemaDAO_ = new br.ss.authenticator.model.dao.SistemaDAO_( this, Rotina_.sistema );
 		}
 		return sistemaDAO_;
 	}

	public StringRestriction<RotinaDAO_, Rotina, String> txRotina() {
		return new StringRestriction<RotinaDAO_, Rotina, String> ( (From<?,Rotina>) getRootOrJoin(), this, Rotina_.txRotina );

	}

}