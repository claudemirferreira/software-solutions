package br.ss.authenticator.model.entity;

import java.util.Calendar;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Usuario.class)
public abstract class Usuario_ extends br.ss.core.entity.AbstractEntity_ {

	public static volatile SingularAttribute<Usuario, Long> id;
	public static volatile SetAttribute<Usuario, UsuarioPerfil> usuarioPerfils;
	public static volatile SingularAttribute<Usuario, String> txLogin;
	public static volatile SingularAttribute<Usuario, Calendar> dtCadastro;
	public static volatile SingularAttribute<Usuario, Short> csStatus;
	public static volatile SingularAttribute<Usuario, String> txSenha;
	public static volatile SingularAttribute<Usuario, String> txEmail;

}

