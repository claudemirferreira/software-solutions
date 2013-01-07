package br.com.ss.centralaamar.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.ss.centralaamar.model.entity.Grupo;

@Component
public class GrupoDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void save(Grupo Grupo) {
		entityManager.persist(Grupo);
	}

	@Transactional
	public void merge(Grupo Grupo) {
		entityManager.merge(Grupo);
	}

	@Transactional
	public void remove(Grupo Grupo) {

		try {
			Grupo entity = entityManager.merge(Grupo);
			entityManager.remove(entity);

		} catch (org.hibernate.exception.ConstraintViolationException e) {
			e.printStackTrace();
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public List<Grupo> list() {
		return entityManager.createQuery(
				"select t from Grupo t order by t.nome desc").getResultList();
	}

}
