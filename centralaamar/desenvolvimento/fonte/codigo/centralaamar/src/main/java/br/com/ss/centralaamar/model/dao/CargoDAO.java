package br.com.ss.centralaamar.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ss.centralaamar.model.entity.Cargo;

@Repository
public class CargoDAO extends AbstractDAO<Cargo> implements ICargoDAO {

	private static final long serialVersionUID = -407492377035891150L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Cargo> searchByEntity(Cargo entity) {
		StringBuilder s = new StringBuilder();
		List<String> condictions = new ArrayList<String>();

		s.append(" select p from Cargo p ");
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
