package br.ss.core.message.interceptor;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import br.ss.core.exception.CoreException;
import br.ss.core.message.CoreMessage;

@BusinessMessageInterceptor
@Interceptor 
public class BusinessMessageInterceptorImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private CoreMessage coreMessage;
	
	public BusinessMessageInterceptorImpl() {
	}

	@AroundInvoke
	public Object manage(InvocationContext ic) throws Exception {
		Object response = null;
		try {
			 response = ic.proceed();
		} catch ( CoreException be ) {
			coreMessage.add( ( ( CoreException ) be ).getBusinessMessage() );
			throw be;
		}
		
		return response;
	}

}
