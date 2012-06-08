package br.ss.authenticator.model.dao;

import java.io.Serializable;
import br.ss.core.dao.AbstractDAO;
import br.ss.authenticator.model.entity.UsuarioPerfil;
import br.ss.authenticator.model.entity.UsuarioPerfil_;
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

public class UsuarioPerfilDAO_ extends AbstractDAO<UsuarioPerfilDAO_, UsuarioPerfil>  implements Serializable {

	/**
	* Serial.
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioPerfilDAO_() {
	}

 	public UsuarioPerfilDAO_( AbstractDAO<?, ?> delegate, SingularAttribute attr ) {
		super( delegate, attr );
	}

	public UsuarioPerfilDAO_( AbstractDAO<?, ?> delegate, ListAttribute attr ) {
		super( delegate, attr );
	}

	public UsuarioPerfilDAO_( AbstractDAO<?, ?> delegate, SetAttribute attr ) {
		super( delegate, attr );
	}

	public DateRestriction<UsuarioPerfilDAO_, UsuarioPerfil, Calendar> dtCriacao() {
		return new DateRestriction<UsuarioPerfilDAO_, UsuarioPerfil, Calendar> ( (From<?,UsuarioPerfil>) getRootOrJoin(), this, UsuarioPerfil_.dtCriacao );

	}

	public NumericRestriction<UsuarioPerfilDAO_, UsuarioPerfil, Long> id() {
		return new NumericRestriction<UsuarioPerfilDAO_, UsuarioPerfil, Long> ( (From<?,UsuarioPerfil>) getRootOrJoin(), this, UsuarioPerfil_.id );

	}

	protected br.ss.authenticator.model.dao.PerfilDAO_ perfilDAO_;
	public br.ss.authenticator.model.dao.PerfilDAO_ perfil() {
 		if ( perfilDAO_ == null ) {
			perfilDAO_ = new br.ss.authenticator.model.dao.PerfilDAO_( this, UsuarioPerfil_.perfil );
 		}
 		return perfilDAO_;
 	}

	protected br.ss.authenticator.model.dao.UsuarioDAO_ usuarioDAO_;
	public br.ss.authenticator.model.dao.UsuarioDAO_ usuario() {
 		if ( usuarioDAO_ == null ) {
			usuarioDAO_ = new br.ss.authenticator.model.dao.UsuarioDAO_( this, UsuarioPerfil_.usuario );
 		}
 		return usuarioDAO_;
 	}

}