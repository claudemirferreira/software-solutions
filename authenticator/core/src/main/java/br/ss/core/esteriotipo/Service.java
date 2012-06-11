package br.ss.core.esteriotipo;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.inject.Stereotype;

import br.ss.core.message.interceptor.BusinessMessageInterceptor;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Stereotype
@BusinessMessageInterceptor
@Target({ TYPE })
@Retention(RUNTIME)
@Documented
public @interface Service {

}
