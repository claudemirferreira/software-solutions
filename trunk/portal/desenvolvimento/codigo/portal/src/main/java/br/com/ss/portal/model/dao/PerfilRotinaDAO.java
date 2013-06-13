package br.com.ss.portal.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ss.portal.model.entity.Perfil;
import br.com.ss.portal.model.entity.PerfilRotina;
import br.com.ss.portal.model.entity.Sistema;
import br.com.ss.portal.model.entity.Usuario;

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
	
	@SuppressWarnings("unchecked")
	public List<Object[]> searchPerfilUsuario(Usuario usuario, Sistema sistema) {
		StringBuilder s = new StringBuilder();
		List<String> condictions = new ArrayList<String>();

		List<Object[]> result;
		
		s.append(" select up, rot ");
		s.append(" from UsuarioPerfil up ");
		s.append(" join up.perfil per ");
		s.append(" join per.perfilRotinas perRot ");
		s.append(" join perRot.rotina rot ");
		s.append(" where rot.sistema = :sistema ");
		s.append(" and up.usuario = :usuario ");
		s.append("  ");
		
		String orderBy = " order by per.nome, rot.nome ";
		
		System.out.println(generateHql(s.toString(),
				condictions) + orderBy);

		Query q = this.entityManager.createQuery(generateHql(s.toString(),
				condictions) + orderBy);

		q.setParameter("sistema", sistema);
		q.setParameter("usuario", usuario);

		return q.getResultList();

	}

}
