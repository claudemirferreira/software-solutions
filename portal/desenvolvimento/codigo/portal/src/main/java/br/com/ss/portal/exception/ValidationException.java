package br.com.ss.portal.exception;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = -3693584614530495622L;

	public ValidationException(String erro) {
		super(erro);
	}

}
