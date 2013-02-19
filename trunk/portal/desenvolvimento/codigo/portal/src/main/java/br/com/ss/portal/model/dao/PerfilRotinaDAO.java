package br.com.ss.portal.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ss.portal.model.entity.Perfil;
import br.com.ss.portal.model.entity.PerfilRotina;

@Repository
public class PerfilRotinaDAO extends AbstractDAO<PerfilRotina> implements
		IPerfilRotinaDAO {

	private static final long serialVersionUID = -3469551348229335626L;

	@Override
	public PerfilRotina getByPrimaryKey(PerfilRotina entity) {
		return super.getByPrimaryKey(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PerfilRotina> searchByEntity(PerfilRotina entity) {
		StringBuilder s = new StringBuilder();
		List<String> condictions = new ArrayList<String>();

		s.append(" select p from PerfilRotina p ");
		
		if (notEmpty(entity)) {
			s.append(" join p.perfil perf ");
		}

		if (notEmpty(entity)) {
			condictions.add(" p.perfil  = :perf ");
		}

		String orderBy = " order by p.rotina.nome ";

		Query q = this.entityManager.createQuery(generateHql(s.toString(),
				condictions) + orderBy);

		if (notEmpty(entity)) {
			q.setParameter("perf", entity.getPerfil());
		}

		return q.getResultList();

	}
	
	@SuppressWarnings("unchecked")
	public List<PerfilRotina> searchByEntity(Perfil entity) {
		StringBuilder s = new StringBuilder();
		List<String> condictions = new ArrayList<String>();

		s.append(" select p from PerfilRotina p ");
		
		if (notEmpty(entity)) {
			s.append(" join p.perfil perf ");
		}

		if (notEmpty(entity)) {
			condictions.add(" p.perfil  = :perf ");
		}

		String orderBy = " order by p.rotina.nome ";

		Query q = this.entityManager.createQuery(generateHql(s.toString(),
				condictions) + orderBy);

		if (notEmpty(entity)) {
			q.setParameter("perf", entity);
		}

		return q.getResultList();

	}

}
