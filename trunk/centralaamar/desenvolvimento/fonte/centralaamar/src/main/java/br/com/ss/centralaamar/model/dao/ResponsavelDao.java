package br.com.ss.centralaamar.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.ss.centralaamar.model.entity.Responsavel;

@Component
public class ResponsavelDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void save(Responsavel responsavel) {
		entityManager.persist(responsavel);
	}

	@Transactional
	public void merge(Responsavel responsavel) {
		entityManager.merge(responsavel);
	}

	@Transactional
	public void remove(Responsavel responsavel) {
		Responsavel entity = entityManager.merge(responsavel);
		entityManager.remove(entity);
	}
	
	public Responsavel find(int id) {
		return entityManager.find(Responsavel.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Responsavel> list() {
		
		Query q = entityManager
				.createQuery("select r from Responsavel r order by r.nome");
		return q.getResultList();
	}

}
