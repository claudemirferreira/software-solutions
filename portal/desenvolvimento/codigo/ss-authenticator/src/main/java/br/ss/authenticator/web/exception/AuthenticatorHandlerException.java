package br.ss.authenticator.web.exception;

import javax.ejb.EJBException;
import javax.enterprise.context.NonexistentConversationException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;

import org.hibernate.exception.ConstraintViolationException;
import org.jboss.solder.exception.control.CaughtException;
import org.jboss.solder.exception.control.Handles;
import org.jboss.solder.exception.control.HandlesExceptions;
import org.jboss.solder.exception.control.TraversalMode;
import org.jboss.solder.logging.Logger;

//import org.jboss.weld.context.http.HttpConversationContext;

@HandlesExceptions
public class AuthenticatorHandlerException {

	@Inject
	private Logger log;

	public void onAuthenticatorException( @Handles(during = TraversalMode.DEPTH_FIRST) CaughtException<AuthenticatorException> ce) {

		ce.getException().printStackTrace();
		ce.rethrow(); // relança a exceçao

		log.debug("\t# Ocorreu o seguinte erro: " + ce.getException().getMessage());

	}


	public void onConstraintViolationException( @Handles(during = TraversalMode.DEPTH_FIRST) CaughtException<ConstraintViolationException> ce) {

		ce.getException().printStackTrace();
		ce.rethrow(); // relança a exceçao

		log.debug("\t# Ocorreu o seguinte erro: " + ce.getException().getMessage());

	}

	public void onEntityExistsException( @Handles(during = TraversalMode.DEPTH_FIRST) CaughtException<EntityExistsException> ce) {

		ce.getException().printStackTrace();
		ce.rethrow(); // relança a exceçao

		log.debug("\t# Ocorreu o seguinte erro: " + ce.getException().getMessage());

	}

	public void onEJBException( @Handles(during = TraversalMode.DEPTH_FIRST) CaughtException<EJBException> ce) {

		ce.getException().printStackTrace();
		ce.rethrow(); // relança a exceçao

		log.debug("\t# Ocorreu o seguinte erro: " + ce.getException().getMessage());

	}
	
	
	public void onNonexistentConversation( @Handles CaughtException<NonexistentConversationException> evt ) {
		final NonexistentConversationException exception = evt.getException();
		log.error( "NonexistentConversationException!\n" + exception.getMessage(), exception);
		evt.handled();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No conversation!", exception.getMessage());
		final FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, msg);
		facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "error");
		
//		conversationContext.activate(null); // Workaround WELD-855 - Create a new transient conversation.	// TODO mudou o import..
	}

}