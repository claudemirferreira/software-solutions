package br.ss.core.database.contraint;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.metadata.ClassMetadata;

import br.ss.core.dao.AbstractDAO;
import br.ss.core.util.StringUtils;
import br.ss.core.validator.SessionAwareConstraintValidator;

public class UniqueConstraintImpl extends
		SessionAwareConstraintValidator<Object> implements
		ConstraintValidator<Unique, Object> {

	private AbstractDAO<?, ?> dao;
	private String column;
	private String colmnName;

	@Override
	public void initialize(Unique annotation) {
		try {
			InitialContext ctx = new InitialContext();
			BeanManager bm = (BeanManager) ctx.lookup("java:comp/BeanManager");

			Bean<?> userDataBean = bm.resolve(bm.getBeans(annotation.dao()));

			dao = (AbstractDAO<?, ?>) bm.getReference(userDataBean, annotation.dao(), bm.createCreationalContext(userDataBean));

			column = annotation.field();
			
			colmnName = annotation.columnName();

			setSessionFactory(((Session) dao.getEntityManager().getDelegate()).getSessionFactory());

		} catch (NamingException e) {
		}

	}

	@Override
	public boolean isValidInSession(Object value,
			ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		return countRows(value) == 0;
	}

	@SuppressWarnings("rawtypes")
	private int countRows(Object value) {
		ClassMetadata meta = getSessionFactory().getClassMetadata(value.getClass());
		String idName = meta.getIdentifierPropertyName();
		Serializable id = meta.getIdentifier(value, (SessionImplementor) getTmpSession());
		
		DetachedCriteria criteria = DetachedCriteria.forClass(value.getClass());
		String propertyValue = (String) meta.getPropertyValue(value, column);
		criteria.add(Restrictions.ne(idName, id)).setProjection(Projections.rowCount());
		criteria.add(Restrictions.sqlRestriction("lower(TRANSLATE({alias}."+colmnName+", '����������������','AAaaEEeeIiOoOoUu')) LIKE '" + StringUtils.removerAcento(propertyValue).toLowerCase() + "%'"));
		List results = criteria.getExecutableCriteria(getTmpSession()).list();
		Number count = (Number) results.iterator().next();
		return count.intValue();
	}

}
