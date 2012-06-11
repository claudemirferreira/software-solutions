package br.ss.core.database.contraint;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.ss.core.dao.AbstractDAO;

@Constraint( validatedBy = UniqueConstraintImpl.class )
@Target( { TYPE, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface Unique {
	  String message() default "Valor já cadastrado";
	  Class<?>[] groups() default {};
	  Class<? extends Payload>[] payload() default {};
	  
	  Class<? extends AbstractDAO<?,?>> dao();
  	  String field();
  	  String columnName();

}
