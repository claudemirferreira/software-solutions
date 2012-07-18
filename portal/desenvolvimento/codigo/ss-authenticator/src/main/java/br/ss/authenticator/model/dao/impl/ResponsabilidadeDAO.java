package br.ss.authenticator.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.Query;

import br.ss.authenticator.model.dao.IResponsabilidadeDAO;
import br.ss.authenticator.model.entity.Responsabilidade;
import br.ss.core.annotation.stereotype.DAO;
import br.ss.core.model.dao.impl.AbstractDAO;

@SuppressWarnings({"serial", "unchecked"})
@Dependent
@Stateless
@DAO
public class ResponsabilidadeDAO extends AbstractDAO<Responsabilidade> implements IResponsabilidadeDAO {
	
	
	public List<Responsabilidade> searchByEntity( Responsabilidade responsabilidade ) {
		
		StringBuilder s = new StringBuilder();
		List<String> condictions = new ArrayList<String>();
		
		s.append(" select r from Responsabilidade r ");
		if ( notEmpty(responsabilidade.getTxResponsabilidade() ) ) {
			condictions.add(" lower(r.txResponsabilidade) like :txResponsabilidade ");
		}
		if ( notEmpty(responsabilidade.getTxDescricao() ) ) {
			condictions.add(" lower(r.txDescricao) like :txDescricao ");
		}
		if ( notEmpty(responsabilidade.getAtivo() ) ) {
			condictions.add(" r.ativo = :ativo ");
		}

		String orderBy = " order by r.txResponsabilidade ";
		Query q = this.entityManager.createQuery( generateHql(s.toString(), condictions) + orderBy );
		
		if ( notEmpty(responsabilidade.getTxResponsabilidade() ) ) {
			q.setParameter("txResponsabilidade", "%" + responsabilidade.getTxResponsabilidade().trim().toLowerCase() + "%" );
		}
		if ( notEmpty(responsabilidade.getTxDescricao() ) ) {
			q.setParameter("Descricao", "%" + responsabilidade.getTxDescricao().trim().toLowerCase() + "%" );
		}
		
		return q.getResultList();
	}
	
}
