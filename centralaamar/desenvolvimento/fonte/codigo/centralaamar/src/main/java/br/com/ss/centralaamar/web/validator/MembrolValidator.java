package br.com.ss.centralaamar.web.validator;

import br.com.ss.centralaamar.component.Util;
import br.com.ss.centralaamar.exception.ValidationException;
import br.com.ss.centralaamar.model.entity.Membro;

public class MembrolValidator {

	public static void validarCampos(Membro membro) {

		String erros = "";
		membro.setCelular(Util.removeMask(membro.getCelular()));
		membro.setFoneComercial(Util.removeMask(membro.getFoneComercial()));
		membro.setFoneResidencial(Util.removeMask(membro.getFoneResidencial()));

		if (erros != "")
			throw new ValidationException(erros);

	}

}
