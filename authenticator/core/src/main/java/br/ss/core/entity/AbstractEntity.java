package br.ss.core.entity;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable, NaturalIdentifier {
	private static final long serialVersionUID = 1L;
 
	@Getter @Setter
	@Transient 
	private boolean checked;
	
	@Getter @Setter
	@Transient 
	private Integer naturalIdentifier;
	
	@Transient
	public abstract Long getId();
	
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
    
    /** Equals Natural. */
    public boolean sameNaturalIdentifier( NaturalIdentifier obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }

        if ( ( this.getNaturalIdentifier() == null ) || ( obj.getNaturalIdentifier() == null ) ) {
            return false;
        }

        return this.getNaturalIdentifier().intValue() == obj.getNaturalIdentifier().intValue();
    }
}
