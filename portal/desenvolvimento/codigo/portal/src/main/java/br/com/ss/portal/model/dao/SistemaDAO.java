package br.com.ss.portal.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ss.portal.model.entity.Sistema;

@Repository
public class SistemaDAO extends AbstractDAO<Sistema> implements ISistemaDAO {

	private static final long serialVersionUID = -3469551348229335626L;

	@Override
	public Sistema getByPrimaryKey(Sistema entity) {
		return super.getByPrimaryKey(entity);
	}

	@Override
	public List<Sistema> searchByEntity(Sistema entity) {
		StringBuilder s = new StringBuilder();
		List<String> condictions = new ArrayList<String>();

		s.append(" select p from Sistema p ");
		if (notEmpty(entity.getNome())) {
			condictions.add(" lower(p.nome) like :nome ");
		}

		String orderBy = " order by p.nome ";

		Query q = this.entityManager.createQuery(generateHql(s.toString(),
				condictions) + orderBy);

		if (notEmpty(entity.getNome())) {
			q.setParameter("nome", "%" + entity.getNome().trim().toLowerCase()
					+ "%");
		}

		return q.getResultList();

	}

}
