package br.com.ss.centralaamar.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.ss.centralaamar.model.entity.Questionario;

@Component
public class QuestionarioDAO extends AbstractDAO<Questionario> implements
		IQuestionarioDAO {

	private static final long serialVersionUID = 265999445460930706L;

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
