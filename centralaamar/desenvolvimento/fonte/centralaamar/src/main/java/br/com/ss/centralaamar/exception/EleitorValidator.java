package br.com.ss.centralaamar.exception;

import br.com.ss.centralaamar.model.entity.Eleitor;

public class EleitorValidator {

	public static void validarCampos(Eleitor eleitor) {

		String erros = "";

		// if (agenda.getPK().getDTFECHAMENTO() == null )
		// erros = erros + "Data deve ser informada !" + "<br/>";
		//
		// if (agenda.getPK().getMES() == null)
		// erros = erros + "M�s deve ser informado !" + "<br/>";
		//
		// if (agenda.getPK().getANO() == null)
		// erros = erros + "Ano deve ser informado !" + "<br/>";
		//
		// if (agenda.getPK().getMES() != null) {
		// if ((agenda.getPK().getMES() < 1) || (agenda.getPK().getMES() > 12))
		// erros = erros + "M�s deve estar entre 1 e 12 !" + "<br/>";
		// }
		//
		// if (agenda.getPK().getANO() != null) {
		// if (agenda.getPK().getANO().toString().length() < 4)
		// erros = erros + "Ano deve conter 4 digitos" + "<br/>";
		// if (agenda.getPK().getANO() < 0)
		// erros = erros + "Ano deve ser maio que 0" + "<br/>";
		// }

		if (eleitor.getNome().trim().length() < 1
				|| eleitor.getNome().equals(null)) {
			erros = erros + "Favor informar o nome" + "<br/>";
			System.out.println("nome esta nulo");
		}

		if (erros != "")
			throw new ValidationException(erros);

	}

}
