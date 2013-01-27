package br.com.ss.centralaamar.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.ss.centralaamar.model.entity.Profissao;

@Component
public class ProfissaoDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void save(Profissao profissao) {
		entityManager.persist(profissao);
	}

	@Transactional
	public void merge(Profissao profissao) {
		entityManager.merge(profissao);
	}

	@Transactional
	public void remove(Profissao profissao) {

		try {
			Profissao entity = entityManager.merge(profissao);
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
	public List<Profissao> list() {
		return entityManager.createQuery(
				"select t from Profissao t order by t.descricao")
				.getResultList();
	}

}
