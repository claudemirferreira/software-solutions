package br.com.ss.academico.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.ss.academico.utils.StringUtil;
import br.com.ss.core.web.annotation.Cep;

/**
 * Validator para CEP.
 * @author Robson
 */
public class CepValidator implements ConstraintValidator<Cep, String> {

	private Pattern pattern = Pattern.compile("[0-9]{5}-[0-9]{3}");

	@Override
	public void initialize(Cep constraintAnnotation) {

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if ( !StringUtil.notEmpty(value) ) {
			// ignora validacao quando o valor nao é informado
			return true;
		}
		
		Matcher m = pattern.matcher(value);
		return m.matches();
	}
}
