package br.com.ss.centralaamar.model.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.ss.centralaamar.model.entity.Pergunta;

@Component
public class PerguntaDAO extends AbstractDAO<Pergunta> implements IPerguntaDAO  {

//	@PersistenceContext
//	private EntityManager entityManager;
//
//	@Transactional
//	public void save(Pergunta pergunta) {
//		entityManager.persist(pergunta);
//	}
//
//	@Transactional
//	public void merge(Pergunta pergunta) {
//		entityManager.merge(pergunta);
//	}
//
//	@Transactional
//	public void remove(Pergunta pergunta) {
//		Pergunta entity = entityManager.merge(pergunta);
//		entityManager.remove(entity);
//	}
//
//	@SuppressWarnings("unchecked")
//	public List<Pergunta> list() {
//		return entityManager.createQuery(
//				"select t from Pergunta t order by t.descricao").getResultList();
//	}

	@Override
	public List<Pergunta> searchByEntity(Pergunta entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
