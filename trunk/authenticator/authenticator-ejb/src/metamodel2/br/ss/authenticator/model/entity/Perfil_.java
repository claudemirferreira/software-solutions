package br.ss.authenticator.model.entity;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Perfil.class)
public abstract class Perfil_ {

	public static volatile SingularAttribute<Perfil, Sistema> sistema;
	public static volatile SingularAttribute<Perfil, Long> id;
	public static volatile SetAttribute<Perfil, UsuarioPerfil> usuarioPerfils;
	public static volatile SingularAttribute<Perfil, String> txPerfil;
	public static volatile SetAttribute<Perfil, PerfilRotina> perfilRotinas;

}

