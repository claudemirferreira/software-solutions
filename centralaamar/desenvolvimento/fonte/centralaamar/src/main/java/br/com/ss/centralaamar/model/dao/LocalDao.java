package br.com.ss.centralaamar.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.ss.centralaamar.model.entity.Local;

@Component
public class LocalDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void save(Local local) {
		entityManager.persist(local);
	}

	@Transactional
	public void merge(Local local) {
		entityManager.merge(local);
	}

	@Transactional
	public void remove(Local local) {
		Local entity = entityManager.merge(local);
		entityManager.remove(entity);
	}

	@SuppressWarnings("unchecked")
	public List<Local> list() {
		Query q = entityManager
				.createQuery("select r from Local r order by r.nome");
		return q.getResultList();
	}

}
