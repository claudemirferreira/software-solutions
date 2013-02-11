package br.com.ss.portal.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ss.portal.model.entity.Perfil;

@Repository
public class PerfilDAO extends AbstractDAO<Perfil> implements IPerfilDAO {

	private static final long serialVersionUID = -3469551348229335626L;

	@Override
	public Perfil getByPrimaryKey(Perfil entity) {
		return super.getByPrimaryKey(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Perfil> searchByEntity(Perfil entity) {
		StringBuilder s = new StringBuilder();
		List<String> condictions = new ArrayList<String>();

		s.append(" select p from Perfil p ");

		if (notEmpty(entity.getSistema())) {
			s.append(" join p.sistema sis ");
		}

		if (notEmpty(entity.getNome())) {
			condictions.add(" lower(p.nome) like :nome ");
		}

		if (notEmpty(entity.getSistema())) {
			condictions.add(" p.sistema = :sis ");
		}

		String orderBy = " order by p.nome ";

		Query q = this.entityManager.createQuery(generateHql(s.toString(),
				condictions) + orderBy);

		if (notEmpty(entity.getNome())) {
			q.setParameter("nome", "%" + entity.getNome().trim().toLowerCase()
					+ "%");
		}

		if (notEmpty(entity.getSistema())) {
			q.setParameter("sis", entity.getSistema());
		}

		return q.getResultList();

	}

}
