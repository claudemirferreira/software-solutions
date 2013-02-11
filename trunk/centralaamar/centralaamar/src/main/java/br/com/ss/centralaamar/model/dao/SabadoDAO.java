package br.com.ss.centralaamar.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.ss.centralaamar.model.entity.Sabado;

@Component
public class SabadoDAO extends AbstractDAO<Sabado> implements ISabadoDAO {

	private static final long serialVersionUID = -178993207889207021L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Sabado> searchByEntity(Sabado entity) {
		StringBuilder s = new StringBuilder();
		List<String> condictions = new ArrayList<String>();

		s.append(" select p from Chamada p ");
		// if (notEmpty(entity.getPequenoGrupo().getIdPequenoGrupo())) {
		// condictions.add(" m.pequenoGrupo = :pg ");
		// }

		String orderBy = " order by p.data ";

		Query q = this.entityManager.createQuery(generateHql(s.toString(),
				condictions) + orderBy);

		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Sabado> listPorGrupo(Sabado chamada) {
		StringBuilder hql = new StringBuilder();
		List<String> cond = new ArrayList<String>();
		List<Sabado> listSabado = new ArrayList<Sabado>();

		hql.append("select m from Chamada m ");

		// cond.add(" m.pequenoGrupo = :pg ");

		// cond.add(" m.sabado = :sb ");

		String orderby = " order by m.id asc ";
		String generatedHql = generateHql(hql.toString(), cond, orderby);

		System.out.println("sql === " + generatedHql);
		Query q = entityManager.createQuery(generatedHql);

		// q.setParameter("pg", chamada.getPequenoGrupo());
		// q.setParameter("sb", chamada.getSabado());

		listSabado = q.getResultList();
		return listSabado;
	}

}