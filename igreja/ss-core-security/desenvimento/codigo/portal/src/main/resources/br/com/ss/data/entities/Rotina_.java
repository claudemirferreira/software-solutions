package br.com.ss.data.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-01-26T23:48:21.076-0200")
@StaticMetamodel(Rotina.class)
public class Rotina_ {
	public static volatile SingularAttribute<Rotina, Integer> id;
	public static volatile SingularAttribute<Rotina, String> nome;
	public static volatile SingularAttribute<Rotina, Sistema> sistema;
	public static volatile SingularAttribute<Rotina, Boolean> status;
	public static volatile SingularAttribute<Rotina, String> path;
	public static volatile SetAttribute<Rotina, Perfil> perfis;
}
