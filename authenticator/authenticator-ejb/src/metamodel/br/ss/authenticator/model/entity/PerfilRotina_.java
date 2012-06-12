package br.ss.authenticator.model.entity;

import java.util.Calendar;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(PerfilRotina.class)
public abstract class PerfilRotina_ extends br.ss.core.entity.AbstractEntity_ {

	public static volatile SingularAttribute<PerfilRotina, Long> id;
	public static volatile SingularAttribute<PerfilRotina, Calendar> dtCadastro;
	public static volatile SingularAttribute<PerfilRotina, Rotina> rotina;
	public static volatile SingularAttribute<PerfilRotina, Perfil> perfil;

}

