package br.ss.core.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(AbstractEntity.class)
public abstract class AbstractEntity_ {

	public static volatile SingularAttribute<AbstractEntity, Integer> naturalIdentifier;
	public static volatile SingularAttribute<AbstractEntity, Integer> hashCode;
	public static volatile SingularAttribute<AbstractEntity, Boolean> checked;

}

