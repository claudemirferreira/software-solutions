package br.com.ss.data.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-01-26T23:48:21.060-0200")
@StaticMetamodel(Perfil.class)
public class Perfil_ {
	public static volatile SingularAttribute<Perfil, Integer> id;
	public static volatile SingularAttribute<Perfil, String> nome;
	public static volatile SingularAttribute<Perfil, Sistema> sistema;
	public static volatile SetAttribute<Perfil, Usuario> usuarios;
	public static volatile SetAttribute<Perfil, Rotina> rotinas;
}
