package br.ss.core.validator;

import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class SessionAwareConstraintValidator<T> {
	private SessionFactory sessionFactory;
	boolean openedNewTransaction;
	private Session tmpSession;

	public SessionAwareConstraintValidator() {
	}

	public boolean isValid(T value, ConstraintValidatorContext context) {
		try {
			openTmpSession();
			boolean result = isValidInSession(value, context);
			closeTmpSession();
			return result;
		} catch ( Exception e ) {
			System.out.println("Falha na conexao com o banco, na classe SessionAwareConstraintValidator<T> ");
			return true;
		}
	}

	public abstract boolean isValidInSession(T value,
			ConstraintValidatorContext context);

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getTmpSession() {
		return tmpSession;
	}

	private void openTmpSession() {
		try {
			tmpSession = getSessionFactory().openSession();
		} catch (HibernateException e) {
			throw new ValidationException("Unable to open temporary session", e);
		}
	}

	private void closeTmpSession() {
		if (openedNewTransaction) {
			try {
				getSessionFactory().getCurrentSession().getTransaction()
						.commit();
			} catch (HibernateException e) {
				throw new ValidationException(
						"Unable to commit transaction for temporary session", e);
			}
		}
		try {
			tmpSession.close();
		} catch (HibernateException e) {
			throw new ValidationException(
					"Unable to close temporary Hibernate session", e);
		}
	}
}
