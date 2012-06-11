package br.ss.core.exception;

import java.util.List;

import lombok.Getter;

/**
 * Excecoes de negocio.
 */
public class CoreException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    /**
     * Encapsula varias mensagens.
     */
    @Deprecated
    @Getter
    private List<ExceptionMessage> mensagens;
    
    @Getter
    private ExceptionMessage businessMessage;
    
    public CoreException() {
    	
    }
    
    public CoreException( ExceptionMessage msg ) {
    	this.businessMessage = msg;
    }
    
    /**
     * Ap�s a finalizacao da padroniza��o de mensagens e mappers este construtor ser� removido.
     * @param messages
     */
    @Deprecated
    public CoreException( List<ExceptionMessage> m ) {
        mensagens = m;
    } 
    
}
