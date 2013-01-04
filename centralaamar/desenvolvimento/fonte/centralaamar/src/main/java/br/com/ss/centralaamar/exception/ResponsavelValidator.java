package br.com.ss.centralaamar.exception;

import br.com.ss.centralaamar.model.entity.Responsavel;

public class ResponsavelValidator {

	public static void validarCampos(Responsavel responsavel) {

		String erros = "";
		if (erros != "")
			throw new ValidationException(erros);

	}

}
