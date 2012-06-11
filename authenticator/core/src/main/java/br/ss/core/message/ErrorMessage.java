package br.ss.core.message;

import lombok.Getter;
import lombok.Setter;
import br.ss.core.exception.ExceptionMessage;

/**
 * Encapsula uma mensagem de excecao.
 * @author diego.coronel
 */
public class ErrorMessage implements ExceptionMessage {

    /**
     * Endere�ada para um elemento.
     */
    @Getter @Setter
    private String target;
    
    /**
     * Detalhamento da mensagem.
     */
    @Getter @Setter
    private String description;
    
    public ErrorMessage() {
        
    }
    
    public ErrorMessage( String d, String tar ) {
        description = d;
        target = tar;
    }
    
    public ErrorMessage( String d ) {
        description = d;
    }
	@Override
	public Type getType() {
		return Type.ERROR;
	}
    
}
