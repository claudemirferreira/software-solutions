package br.ss.authenticator.core.transaction;

import java.io.Serializable;
import java.util.Iterator;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import br.ss.core.database.Database;
import br.ss.core.transaction.qualifier.Transactional;

@Transactional @Interceptor
public class EntityTransactionInterceptor implements Serializable {
	
	/**
	 * .
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	protected UserTransaction userTransaction;

	@Inject @RequestScoped @Database
    protected EntityManager entityManager;
	 
	@AroundInvoke
	public Object manageTransaction(InvocationContext context) throws Exception {
		Object result = null;
		
    	try {
    		userTransaction.begin();
    		entityManager.joinTransaction();
    		
    		result = context.proceed();
    		
    		entityManager.flush();
    		userTransaction.commit();
    	} catch( ConstraintViolationException e ) {
    		Iterator<ConstraintViolation<?>> it = e.getConstraintViolations().iterator();
    		while ( it.hasNext() ) {
    			ConstraintViolation<?> violation = it.next();
    			System.out.println( violation.getPropertyPath() + ": " + violation.getMessage() );
    		}
    		rollback( e );
    	} catch(Exception e) {
    		rollback( e );
    	}
    	return result;
	}
	
	private void rollback( Exception e ) throws Exception {
		try {
			userTransaction.rollback();
		}
		catch (Exception f) {
		}
		throw e;
	}
	
}
