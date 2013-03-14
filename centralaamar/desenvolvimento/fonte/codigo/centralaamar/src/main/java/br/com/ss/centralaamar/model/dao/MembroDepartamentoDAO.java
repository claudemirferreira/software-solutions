package br.com.ss.centralaamar.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ss.centralaamar.model.entity.MembroDepartamento;

@Repository
public class MembroDepartamentoDAO extends AbstractDAO<MembroDepartamento>
		implements IMembroDepartamentoDAO {

	private static final long serialVersionUID = -1206104211685281610L;

	@SuppressWarnings("unchecked")
	public List<MembroDepartamento> searchByEntity(MembroDepartamento membro) {
		StringBuilder hql = new StringBuilder();
		List<String> cond = new ArrayList<String>();

		hql.append("select m from MembroDepartamento m ");
		if (notEmpty(membro.getCargo())) {
			hql.append(" join m.cargo carg ");
		}
		if (notEmpty(membro.getDepartamento())) {
			hql.append(" join m.departamento depart ");
		}
		if (notEmpty(membro.getMembro())) {
			hql.append(" join m.membro memb ");
		}
		if (notEmpty(membro.getCargo())) {
			cond.add(" m.cargo = :carg ");
		}
		if (notEmpty(membro.getDepartamento())) {
			cond.add(" m.departamento = :depart ");
		}
		if (notEmpty(membro.getMembro())) {
			cond.add(" m.membro = :memb ");
		}

		String orderby = " order by p.membro.nome ";
		String generatedHql = generateHql(hql.toString(), cond, orderby);

		System.out.println("sql === " + generatedHql);
		Query q = entityManager.createQuery(generatedHql);

		if (notEmpty(membro.getCargo())) {
			q.setParameter("carg", membro.getCargo());
		}
		if (notEmpty(membro.getDepartamento())) {
			q.setParameter("depart", membro.getDepartamento());
		}
		if (notEmpty(membro.getMembro())) {
			q.setParameter("memb", membro.getMembro());
		}
		return q.getResultList();
	}

	// @SuppressWarnings("unchecked")
	// @Override
	// public List<MembroDepartamento> searchByEntity(MembroDepartamento entity)
	// {
	// StringBuilder s = new StringBuilder();
	// List<String> condictions = new ArrayList<String>();
	//
	// s.append(" select p from MembroDepartamento p ");
	// // if ( notEmpty(entity.getNome()) ) {
	// // condictions.add(" lower(p.nome) like :nome ");
	// // }
	//
	// String orderBy = " order by p.membro.nome ";
	//
	// Query q = this.entityManager.createQuery(generateHql(s.toString(),
	// condictions) + orderBy);
	//
	// // if ( notEmpty(entity.getNome() ) ) {
	// // q.setParameter("nome", "%" + entity.getNome().trim().toLowerCase() +
	// // "%" );
	// // }
	//
	// return q.getResultList();
	// }

}
