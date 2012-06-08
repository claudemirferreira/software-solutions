package br.ss.authenticator.model.dao;

import java.io.Serializable;
import br.ss.core.dao.AbstractDAO;
import br.ss.authenticator.model.entity.Usuario;
import br.ss.authenticator.model.entity.Usuario_;
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

public class UsuarioDAO_ extends AbstractDAO<UsuarioDAO_, Usuario>  implements Serializable {

	/**
	* Serial.
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioDAO_() {
	}

 	public UsuarioDAO_( AbstractDAO<?, ?> delegate, SingularAttribute attr ) {
		super( delegate, attr );
	}

	public UsuarioDAO_( AbstractDAO<?, ?> delegate, ListAttribute attr ) {
		super( delegate, attr );
	}

	public UsuarioDAO_( AbstractDAO<?, ?> delegate, SetAttribute attr ) {
		super( delegate, attr );
	}

	public NumericRestriction<UsuarioDAO_, Usuario, Short> csStatus() {
		return new NumericRestriction<UsuarioDAO_, Usuario, Short> ( (From<?,Usuario>) getRootOrJoin(), this, Usuario_.csStatus );

	}

	public DateRestriction<UsuarioDAO_, Usuario, Calendar> dtCadastro() {
		return new DateRestriction<UsuarioDAO_, Usuario, Calendar> ( (From<?,Usuario>) getRootOrJoin(), this, Usuario_.dtCadastro );

	}

	public NumericRestriction<UsuarioDAO_, Usuario, Long> id() {
		return new NumericRestriction<UsuarioDAO_, Usuario, Long> ( (From<?,Usuario>) getRootOrJoin(), this, Usuario_.id );

	}

	public StringRestriction<UsuarioDAO_, Usuario, String> txEmail() {
		return new StringRestriction<UsuarioDAO_, Usuario, String> ( (From<?,Usuario>) getRootOrJoin(), this, Usuario_.txEmail );

	}

	public StringRestriction<UsuarioDAO_, Usuario, String> txLogin() {
		return new StringRestriction<UsuarioDAO_, Usuario, String> ( (From<?,Usuario>) getRootOrJoin(), this, Usuario_.txLogin );

	}

	public StringRestriction<UsuarioDAO_, Usuario, String> txSenha() {
		return new StringRestriction<UsuarioDAO_, Usuario, String> ( (From<?,Usuario>) getRootOrJoin(), this, Usuario_.txSenha );

	}

}