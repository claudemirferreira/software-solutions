package br.com.ss.centralaamar.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.ss.centralaamar.model.entity.Eleitor;

@Component
public class EleitorDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void save(Eleitor eleitor) {
		entityManager.persist(eleitor);
	}

	@Transactional
	public void merge(Eleitor eleitor) {
		entityManager.merge(eleitor);
	}

	@Transactional
	public void remove(Eleitor eleitor) {
		Eleitor entity = entityManager.merge(eleitor);
		entityManager.remove(entity);
	}

	@SuppressWarnings("unchecked")
	public List<Eleitor> list() {
		Query q = entityManager
				.createQuery("select r from Eleitor r order by r.nome");
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Eleitor> listPorResposavel(int id) {
		Query q = entityManager
				.createQuery("select r from Eleitor r WHERE r.responsavel.id = :id order by r.nome");
		q.setParameter("id", id);
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Eleitor> listPorSecao(String secao) {
		Query q = entityManager
				.createQuery("select r from Eleitor r WHERE r.secao = :secao order by r.nome");
		q.setParameter("secao", secao);
		return q.getResultList();
	}

	public Eleitor find(int id) {
		return entityManager.find(Eleitor.class, id);
	}

}
