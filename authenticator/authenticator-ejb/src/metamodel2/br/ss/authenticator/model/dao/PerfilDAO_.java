package br.ss.authenticator.model.dao;

import java.io.Serializable;
import br.ss.core.dao.AbstractDAO;
import br.ss.authenticator.model.entity.Perfil;
import br.ss.authenticator.model.entity.Perfil_;
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

public class PerfilDAO_ extends AbstractDAO<PerfilDAO_, Perfil>  implements Serializable {

	/**
	* Serial.
	 */
	private static final long serialVersionUID = 1L;

	public PerfilDAO_() {
	}

 	public PerfilDAO_( AbstractDAO<?, ?> delegate, SingularAttribute attr ) {
		super( delegate, attr );
	}

	public PerfilDAO_( AbstractDAO<?, ?> delegate, ListAttribute attr ) {
		super( delegate, attr );
	}

	public PerfilDAO_( AbstractDAO<?, ?> delegate, SetAttribute attr ) {
		super( delegate, attr );
	}

	public NumericRestriction<PerfilDAO_, Perfil, Long> id() {
		return new NumericRestriction<PerfilDAO_, Perfil, Long> ( (From<?,Perfil>) getRootOrJoin(), this, Perfil_.id );

	}

//serialVersionUID de tipo: long
	protected br.ss.authenticator.model.dao.SistemaDAO_ sistemaDAO_;
	public br.ss.authenticator.model.dao.SistemaDAO_ sistema() {
 		if ( sistemaDAO_ == null ) {
			sistemaDAO_ = new br.ss.authenticator.model.dao.SistemaDAO_( this, Perfil_.sistema );
 		}
 		return sistemaDAO_;
 	}

	public StringRestriction<PerfilDAO_, Perfil, String> txPerfil() {
		return new StringRestriction<PerfilDAO_, Perfil, String> ( (From<?,Perfil>) getRootOrJoin(), this, Perfil_.txPerfil );

	}

}