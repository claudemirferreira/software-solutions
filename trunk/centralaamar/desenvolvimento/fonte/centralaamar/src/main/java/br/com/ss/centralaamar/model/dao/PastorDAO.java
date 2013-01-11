package br.com.ss.centralaamar.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.ss.centralaamar.model.entity.Pastor;

@Component
public class PastorDAO extends AbstractDAO<Pastor> implements IPastorDAO {

//	@PersistenceContext
//	private EntityManager entityManager;
//
//	@Transactional
//	public void save(Pastor Pastor) {
//		entityManager.persist(Pastor);
//	}
//
//	@Transactional
//	public void merge(Pastor Pastor) {
//		entityManager.merge(Pastor);
//	}
//
//	@Transactional
//	public void remove(Pastor Pastor) {
//
//		try {
//			Pastor entity = entityManager.merge(Pastor);
//			entityManager.remove(entity);
//
//		} catch (org.hibernate.exception.ConstraintViolationException e) {
//			e.printStackTrace();
//		} catch (org.springframework.dao.DataIntegrityViolationException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	@SuppressWarnings("unchecked")
//	public List<Pastor> list() {
//		return entityManager.createQuery(
//				"select t from Pastor t order by t.nome").getResultList();
//	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Pastor> searchByEntity(Pastor entity) {
		StringBuilder s = new StringBuilder();
		List<String> condictions = new ArrayList<String>();
		
		s.append(" select p from Pastor p ");
		if ( notEmpty(entity.getNome()) ) {
			condictions.add(" lower(p.nome) like :nome ");
		}
		
		String orderBy = " order by p.nome ";
		
		Query q = this.entityManager.createQuery( generateHql(s.toString(), condictions) + orderBy );
		
		if ( notEmpty(entity.getNome() ) ) {
			q.setParameter("nome", "%" + entity.getNome().trim().toLowerCase() + "%" );
		}
		
		return q.getResultList();
	}

}
