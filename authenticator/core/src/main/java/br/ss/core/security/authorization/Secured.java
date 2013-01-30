package br.ss.core.security.authorization;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;

@Target( { METHOD, TYPE } )
@Retention( RUNTIME )
@Documented
@InterceptorBinding
public @interface Secured {
	@Nonbinding String[] value() default {};
}