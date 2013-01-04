package br.com.ss.centralaamar.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.ss.centralaamar.model.entity.Pergunta;

@Component
public class PerguntaDAO extends GenericDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void save(Pergunta pergunta) {
		entityManager.persist(pergunta);
	}

	@Transactional
	public void merge(Pergunta pergunta) {
		entityManager.merge(pergunta);
	}

	@Transactional
	public void remove(Pergunta pergunta) {
		Pergunta entity = entityManager.merge(pergunta);
		entityManager.remove(entity);
	}

	@SuppressWarnings("unchecked")
	public List<Pergunta> list() {
		return entityManager.createQuery(
				"select t from Pergunta t order by t.descricao").getResultList();
	}

}
