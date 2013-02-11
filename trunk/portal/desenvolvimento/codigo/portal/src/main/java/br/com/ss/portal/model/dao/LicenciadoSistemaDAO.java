package br.com.ss.portal.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ss.portal.model.entity.LicenciadoSistema;

@Repository
public class LicenciadoSistemaDAO extends AbstractDAO<LicenciadoSistema>
		implements ILicenciadoSistemaDAO {

	private static final long serialVersionUID = -3469551348229335626L;

	@Override
	public LicenciadoSistema getByPrimaryKey(LicenciadoSistema entity) {
		return super.getByPrimaryKey(entity);
	}

	@Override
	public List<LicenciadoSistema> searchByEntity(LicenciadoSistema entity) {
		StringBuilder s = new StringBuilder();
		List<String> condictions = new ArrayList<String>();

		s.append(" select p from Licenciado p ");
//		if (notEmpty(entity.getNome())) {
//			condictions.add(" lower(p.nome) like :nome ");
//		}

		String orderBy = " order by p.nome ";

		Query q = this.entityManager.createQuery(generateHql(s.toString(),
				condictions) + orderBy);

//		if (notEmpty(entity.getNome())) {
//			q.setParameter("nome", "%" + entity.getNome().trim().toLowerCase()
//					+ "%");
//		}

		return q.getResultList();

	}

}
