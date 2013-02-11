package br.com.ss.centralaamar.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ss.centralaamar.model.entity.PequenoGrupo;

@Repository
public class PequenoGrupoDAO extends AbstractDAO<PequenoGrupo> implements
		IPequenoGrupoDAO {

	private static final long serialVersionUID = -6752718354883115458L;

	@SuppressWarnings("unchecked")
	@Override
	public List<PequenoGrupo> searchByEntity(PequenoGrupo entity) {
		StringBuilder s = new StringBuilder();
		List<String> condictions = new ArrayList<String>();

		s.append(" select pg from PequenoGrupo pg ");
		if (notEmpty(entity.getNome())) {
			condictions.add(" lower(pg.nome) like :nome ");
		}

		String orderBy = " order by pg.nome ";

		Query q = this.entityManager.createQuery(generateHql(s.toString(),
				condictions) + orderBy);

		if (notEmpty(entity.getNome())) {
			q.setParameter("nome", "%" + entity.getNome().trim().toLowerCase()
					+ "%");
		}

		return q.getResultList();
	}

}
