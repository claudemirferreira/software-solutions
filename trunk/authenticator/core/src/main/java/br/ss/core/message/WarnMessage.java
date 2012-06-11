package br.ss.core.message;

import lombok.Getter;
import lombok.Setter;
import br.ss.core.exception.ExceptionMessage;

public class WarnMessage implements ExceptionMessage {
    
	
    /**
     * Detalhamento da mensagem.
     */
    @Getter @Setter
    private String description;
    
    private String target;
    
    public WarnMessage( String desc ) {
    	description = desc;
    }
    
    public WarnMessage( String desc, String targ ) {
    	description = desc;
    	target = targ;
    }
	
    @Override
	public Type getType() {
		return Type.WARN;
	}

	@Override
	public String getTarget() {
		return target;
	}
    
}
