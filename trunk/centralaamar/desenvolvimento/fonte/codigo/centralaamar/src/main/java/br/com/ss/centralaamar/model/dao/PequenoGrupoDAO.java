package br.com.ss.centralaamar.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.ss.centralaamar.model.entity.PequenoGrupo;

@Component
public class PequenoGrupoDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void save(PequenoGrupo pequenoGrupo) {
		entityManager.persist(pequenoGrupo);
	}

	@Transactional
	public void merge(PequenoGrupo pequenoGrupo) {
		entityManager.merge(pequenoGrupo);
	}

	@Transactional
	public void remove(PequenoGrupo pequenoGrupo) {
		PequenoGrupo entity = entityManager.merge(pequenoGrupo);
		entityManager.remove(entity);
	}

	@SuppressWarnings("unchecked")
	public List<PequenoGrupo> list() {
		Query q = entityManager
				.createQuery("select r from PequenoGrupo r order by r.descricao");
		return q.getResultList();
	}

}
