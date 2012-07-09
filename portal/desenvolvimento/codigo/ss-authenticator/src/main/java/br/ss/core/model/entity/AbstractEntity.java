package br.ss.core.model.entity;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;


@MappedSuperclass
public abstract class AbstractEntity implements Serializable {
	
	private static final long serialVersionUID = -2461303228447435467L;
	
	@Getter @Setter
	@Transient 
	private boolean checked;
	
	public AbstractEntity() { }

	@Transient
	public abstract Long getId();
	
	
	public boolean isPersistent() {
		return getId() != null;
	}
	
	
	/** HashCode. */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (int) (prime * result + ( ( getId() == null ) ? 0 : getId() ));
        return result;
    }

    /** Equals. */
    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        final AbstractEntity other = ( AbstractEntity ) obj;

        if ( ( this.getId() == null ) || ( other.getId() == null ) ) {
            return false;
        }

        if ( this.getId().intValue() != other.getId().intValue() ) {
            return false;
        }

        return true;
    }
	

}
