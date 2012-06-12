package br.ss.authenticator.model.dao;

import java.io.Serializable;
import br.ss.core.dao.AbstractDAO;
import br.ss.authenticator.model.entity.PerfilRotina;
import br.ss.authenticator.model.entity.PerfilRotina_;
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

public class PerfilRotinaDAO_ extends AbstractDAO<PerfilRotinaDAO_, PerfilRotina>  implements Serializable {

	/**
	* Serial.
	 */
	private static final long serialVersionUID = 1L;

	public PerfilRotinaDAO_() {
	}

 	public PerfilRotinaDAO_( AbstractDAO<?, ?> delegate, SingularAttribute attr ) {
		super( delegate, attr );
	}

	public PerfilRotinaDAO_( AbstractDAO<?, ?> delegate, ListAttribute attr ) {
		super( delegate, attr );
	}

	public PerfilRotinaDAO_( AbstractDAO<?, ?> delegate, SetAttribute attr ) {
		super( delegate, attr );
	}

	public DateRestriction<PerfilRotinaDAO_, PerfilRotina, Calendar> dtCadastro() {
		return new DateRestriction<PerfilRotinaDAO_, PerfilRotina, Calendar> ( (From<?,PerfilRotina>) getRootOrJoin(), this, PerfilRotina_.dtCadastro );

	}

	public NumericRestriction<PerfilRotinaDAO_, PerfilRotina, Long> id() {
		return new NumericRestriction<PerfilRotinaDAO_, PerfilRotina, Long> ( (From<?,PerfilRotina>) getRootOrJoin(), this, PerfilRotina_.id );

	}

	protected br.ss.authenticator.model.dao.PerfilDAO_ perfilDAO_;
	public br.ss.authenticator.model.dao.PerfilDAO_ perfil() {
 		if ( perfilDAO_ == null ) {
			perfilDAO_ = new br.ss.authenticator.model.dao.PerfilDAO_( this, PerfilRotina_.perfil );
 		}
 		return perfilDAO_;
 	}

	protected br.ss.authenticator.model.dao.RotinaDAO_ rotinaDAO_;
	public br.ss.authenticator.model.dao.RotinaDAO_ rotina() {
 		if ( rotinaDAO_ == null ) {
			rotinaDAO_ = new br.ss.authenticator.model.dao.RotinaDAO_( this, PerfilRotina_.rotina );
 		}
 		return rotinaDAO_;
 	}

}