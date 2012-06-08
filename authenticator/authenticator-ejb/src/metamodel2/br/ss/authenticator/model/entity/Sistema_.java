package br.ss.authenticator.model.entity;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Sistema.class)
public abstract class Sistema_ {

	public static volatile SingularAttribute<Sistema, Long> id;
	public static volatile SetAttribute<Sistema, Perfil> perfils;
	public static volatile SingularAttribute<Sistema, Boolean> ativo;
	public static volatile SingularAttribute<Sistema, String> txSistema;
	public static volatile SingularAttribute<Sistema, String> txDescricao;
	public static volatile SetAttribute<Sistema, Rotina> rotinas;
	public static volatile SingularAttribute<Sistema, String> txSigla;

}

