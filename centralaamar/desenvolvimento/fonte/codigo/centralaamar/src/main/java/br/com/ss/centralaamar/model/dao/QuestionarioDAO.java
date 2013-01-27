package br.com.ss.centralaamar.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.ss.centralaamar.model.entity.Questionario;

@Component
public class QuestionarioDAO extends AbstractDAO<Questionario> implements IQuestionarioDAO  {

//	@PersistenceContext
//	private EntityManager entityManager;
//
//	@Transactional
//	public void save(Questionario questionario) {
//		entityManager.persist(questionario);
//	}
//
//	@Transactional
//	public void merge(Questionario questionario) {
//		entityManager.merge(questionario);
//	}
//
//	@Transactional
//	public void remove(Questionario questionario) {
//
//		try {
//			Questionario entity = entityManager.merge(questionario);
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
//	public List<Questionario> list() {
//		return entityManager.createQuery("select t from Questionario t ")
//				.getResultList();
//	}

	@SuppressWarnings("unchecked")
	public List<Questionario> listPorGrupo(Questionario questionario) {
		StringBuilder hql = new StringBuilder();
		List<String> cond = new ArrayList<String>();
		List<Questionario> listQuestionario = new ArrayList<Questionario>();

		hql.append("select m from Questionario m ");

		cond.add(" m.pequenoGrupo = :pg ");
		cond.add(" m.sabado = :sb ");

		String orderby = " order by m.id asc ";
		String generatedHql = generateHql(hql.toString(), cond, orderby);

		System.out.println("sql === " + generatedHql);
		Query q = entityManager.createQuery(generatedHql);

		q.setParameter("pg", questionario.getPequenoGrupo());
		q.setParameter("sb", questionario.getSabado());

		listQuestionario = q.getResultList();
		return listQuestionario;
	}

	@Override
	public List<Questionario> searchByEntity(Questionario entity) {
		// TODO Auto-generated method stub
		return null;
	}


}
