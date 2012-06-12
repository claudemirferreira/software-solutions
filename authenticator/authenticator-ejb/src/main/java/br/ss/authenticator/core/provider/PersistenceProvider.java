package br.ss.authenticator.core.provider;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.hibernate.FlushMode;
import org.hibernate.Session;

import br.ss.core.database.Database;

@ApplicationScoped
public class PersistenceProvider implements Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceUnit( unitName = "authenticator_PU" )
	private EntityManagerFactory emf;

	/**
	 * Nao deve ser colocado como conversationScoped
	 * Utilizado por
	 * 	- AbstractDAO
	 *  - EntityTransactionInterceptor
	 * @return
	 */
	@Produces @RequestScoped @Database 
	public EntityManager getEntityManagerConversation() {
		EntityManager entityManager = emf.createEntityManager();
		configureEntityManager( entityManager );
		return entityManager;
	}
	
	@Produces @RequestScoped 
	public EntityManagerFactory getEmf() {
		return emf;
	}
	
	private void configureEntityManager( EntityManager entityManager ) {
		Session session = ( Session ) entityManager.getDelegate();
		session.setFlushMode( FlushMode.COMMIT );	
	}
	
	 public void doNothing(@Disposes @Database EntityManager em) {
		 	em.clear();
	        em.close();
    }
	
}
