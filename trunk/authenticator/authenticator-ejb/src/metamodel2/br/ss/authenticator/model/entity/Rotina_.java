package br.ss.authenticator.model.entity;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Rotina.class)
public abstract class Rotina_ {

	public static volatile SingularAttribute<Rotina, Sistema> sistema;
	public static volatile SingularAttribute<Rotina, Long> id;
	public static volatile SingularAttribute<Rotina, Boolean> csStatus;
	public static volatile SingularAttribute<Rotina, String> txRotina;
	public static volatile SetAttribute<Rotina, PerfilRotina> perfilRotinas;

}

