package br.com.ss.centralaamar.component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

	public static String removeMask(String valor) {
		valor = valor.trim();
		valor = valor.replace(" ", "");
		valor = valor.replace("-", "");
		valor = valor.replace("(", "");
		valor = valor.replace(")", "");
		return valor;
	}

	public static boolean validar(String email) {
		boolean isEmailIdValid = false;
		if (email != null && email.length() > 0) {
			String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
			Pattern pattern = Pattern.compile(expression,
					Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(email);
			if (matcher.matches()) {
				isEmailIdValid = true;
			}
		}
		return isEmailIdValid;
	}

}
