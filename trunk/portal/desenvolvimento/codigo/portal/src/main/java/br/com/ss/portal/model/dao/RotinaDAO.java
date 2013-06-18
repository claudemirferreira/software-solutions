package br.com.ss.portal.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ss.portal.model.entity.Perfil;
import br.com.ss.portal.model.entity.Rotina;

@Repository
public class RotinaDAO extends AbstractDAO<Rotina> implements IRotinaDAO {

	private static final long serialVersionUID = -3469551348229335626L;

	@Override
	public Rotina getByPrimaryKey(Rotina entity) {
		return super.getByPrimaryKey(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rotina> searchByEntity(Rotina entity) {
		StringBuilder s = new StringBuilder();
		List<String> condictions = new ArrayList<String>();

		s.append(" select p from Rotina p ");
		if (notEmpty(entity.getNome()))
			condictions.add(" lower(p.nome) like :nome ");

		String orderBy = " order by p.nome ";

		Query q = this.entityManager.createQuery(generateHql(s.toString(),
				condictions) + orderBy);

		if (notEmpty(entity.getNome()))
			q.setParameter("nome", "%" + entity.getNome().trim().toLowerCase()
					+ "%");

		return q.getResultList();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rotina> searchRotinasDisponivel(Perfil entity) {
		String sql = " select r from Rotina r "
				+ "where r != ( "
				+ " 	SELECT r2 FROM PerfilRotina pr "
				+ " 	join pr.rotina r2 " 
				+ "		where pr = " + entity.getIdPerfil() + "  )";

		System.out.println("sql === " + sql);
		
		List<Rotina> list = entityManager.createQuery(sql).getResultList();

		return list;

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Rotina> listarRotinaPorPerfil(Perfil perfil) {
		
		String sql = " select a.* from portal.saa_rotina a, " +
				" portal.saa_perfil_rotina b " +
				" where a.rotina_id = b.rotina_id " +
				" and b.perfil_id = " + perfil.getId() +
				" order by rot_nome ";

		System.out.println("sql === " + sql);
		
		List<Rotina> list = (List<Rotina>) entityManager.createNativeQuery(sql, Rotina.class).getResultList();
		return list;
	}


}
