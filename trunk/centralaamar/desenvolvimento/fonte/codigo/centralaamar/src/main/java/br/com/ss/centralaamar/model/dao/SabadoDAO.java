package br.com.ss.centralaamar.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.ss.centralaamar.model.entity.Sabado;

@Component
public class SabadoDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void save(Sabado sabado) {
		entityManager.persist(sabado);
	}

	@Transactional
	public void merge(Sabado sabado) {
		entityManager.merge(sabado);
	}

	@Transactional
	public void remove(Sabado sabado) {

		try {
			Sabado entity = entityManager.merge(sabado);
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
	public List<Sabado> list() {
		return entityManager.createQuery(
				"select t from Sabado t order by t.data desc").getResultList();
	}

}
