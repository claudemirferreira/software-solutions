package br.ss.authenticator.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.Query;

import br.ss.authenticator.model.dao.IGrupoDAO;
import br.ss.authenticator.model.entity.Grupo;
import br.ss.core.annotation.stereotype.DAO;
import br.ss.core.model.dao.impl.AbstractDAO;

@Dependent
@Stateless
@DAO
public class GrupoDAO extends AbstractDAO<Grupo> implements IGrupoDAO{

	@SuppressWarnings("unchecked")
	public List<Grupo> searchByEntity( Grupo grupo ) {
		
		StringBuilder s = new StringBuilder();
		List<String> condictions = new ArrayList<String>();
		
		s.append(" select s from Grupo s ");
		if ( notEmpty(grupo.getTxGrupo()) ) {
			condictions.add(" lower(s.txGrupo) like :txGrupo ");
		}
		
		if ( notEmpty(grupo.getAtivo()) ) {
			condictions.add(" s.ativo = :ativo ");
		}
		
		String orderBy = " order by s.txGrupo ";
		
		Query q = this.entityManager.createQuery( generateHql(s.toString(), condictions) + orderBy );
		
		if ( notEmpty(grupo.getTxGrupo() ) ) {
			q.setParameter("txGrupo", "%" + grupo.getTxGrupo().trim().toLowerCase() + "%" );
		}
		
		if ( notEmpty(grupo.getAtivo() ) ) {
			q.setParameter("ativo", ( Boolean ) grupo.getAtivo());
		}
		
		return q.getResultList();
	}

}
