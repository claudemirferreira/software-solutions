package br.ss.authenticator.web.exception;


public abstract class GenericException extends Exception {

	private static final long serialVersionUID = 6877189313901756064L;

	/** Mensagem descritiva do erro. */
	protected String message = definirMensagem();

	
	/** Define a mensagem apropriada à exceção. */
	protected abstract String definirMensagem();

	
	@Override
	public String getMessage() {
		return this.message;
	}

}
