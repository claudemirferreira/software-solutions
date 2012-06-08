package br.ss.authenticator.model.entity;

import java.util.Calendar;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(UsuarioPerfil.class)
public abstract class UsuarioPerfil_ {

	public static volatile SingularAttribute<UsuarioPerfil, Long> id;
	public static volatile SingularAttribute<UsuarioPerfil, Calendar> dtCriacao;
	public static volatile SingularAttribute<UsuarioPerfil, Usuario> usuario;
	public static volatile SingularAttribute<UsuarioPerfil, Perfil> perfil;

}

