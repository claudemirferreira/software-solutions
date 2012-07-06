package br.ss.authenticator.web.exception;

public class AuthenticatorException extends GenericException {
	
	private static final long serialVersionUID = 5562137511823929849L;

	public AuthenticatorException( String msg ) {
		this.message = msg;
	}
	
	@Override
	protected String definirMensagem() {
		return message;
	}
	
}
