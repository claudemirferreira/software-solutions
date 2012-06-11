package br.ss.core.exception;

public interface ExceptionMessage {

	Type getType();
	String getTarget();
	String getDescription();
	
	 /**
     * Tipos de mensagens tratadas.
     */
    public enum Type {
        /**
         * Aviso.
         */
        WARN,
        
        /**
         * Falha.
         */
        ERROR,
        
        /**
         * Sucesso e noticias em geral.
         */
        INFO
    }
	
}
