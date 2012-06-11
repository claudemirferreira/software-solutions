package br.ss.core.validator;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.Set;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import br.ss.core.database.contraint.Unique;
import br.ss.core.exception.CoreException;
import br.ss.core.message.CoreMessage;
import br.ss.core.message.ErrorMessage;

@Dependent
public class BeanValidator<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private CoreMessage messages;
	
	public void check( T value, Class<?> ... groups ) throws CoreException {
		//Para testes unitarios, ate usarmos arquillian, nao remover.
		if ( messages == null ) {
			messages = new CoreMessage();
			messages.init();
		}
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<T>> constraintViolations = validator.validate( value );

		Iterator<ConstraintViolation<T>> it = constraintViolations.iterator();
		while ( it.hasNext() ) {
			ConstraintViolation<T> field = it.next();
			Annotation annotation = field.getConstraintDescriptor().getAnnotation();
			
			if(annotation instanceof Unique){
				messages.add( new ErrorMessage(((Unique) annotation).message(), ((Unique) annotation).field()));
			} else {
				//Podemos pegar a anotacao do atributo e identificar a anotacao @Id ?
				if ( "id".equalsIgnoreCase( field.getPropertyPath().toString() ) ) {
					continue;
				}
				messages.add( new ErrorMessage(field.getMessage(), field.getPropertyPath().toString() ) );
			}
			
		}
		
		for ( Class<?> g : groups ) {
			Set<ConstraintViolation<T>> groupViolations = validator.validate( value, g );
			Iterator<ConstraintViolation<T>> groupIT = groupViolations.iterator();
			while ( groupIT.hasNext() ) {
				ConstraintViolation<T> field = groupIT.next();
				//Podemos pegar a anotacao do atributo e identificar a anotacao @Id ?
				if ( "id".equalsIgnoreCase( field.getPropertyPath().toString() ) ) {
					continue;
				}
				messages.add( new ErrorMessage( field.getMessage(), field.getPropertyPath().toString() ) );
			}
		}
		
		if ( messages.getMessages().size() > 0 )
			throw new CoreException();
	}
	
}
