package br.ss.core.model.entity;

import java.io.Serializable;

import javax.persistence.Transient;

public abstract class GenericEntity implements Serializable {
	
	private static final long serialVersionUID = 9214400590247487525L;

	public GenericEntity() {
		
	}
	
	public  boolean isPersistent() {
		return getId() != null;
	}
	
	/** M�todo fake, apenas para possibilitar validar se a entidade � persistente pelo hibernate.
	 * Deve ser anotado nas subclasses com @Transiente. */
	@Transient
	public abstract Integer getId();
	

}
