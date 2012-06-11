package br.ss.core.message;

import lombok.Getter;
import lombok.Setter;
import br.ss.core.exception.ExceptionMessage;

public class InfoMessage implements ExceptionMessage {
    
	
    /**
     * Detalhamento da mensagem.
     */
    @Getter @Setter
    private String description;
    
    private String target;
    
    public InfoMessage( String desc ) {
    	description = desc;
    }
    
    public InfoMessage( String desc, String targ ) {
    	description = desc;
    	target = targ;
    }
	
    @Override
	public Type getType() {
		return Type.INFO;
	}

	@Override
	public String getTarget() {
		return target;
	}
    
}
