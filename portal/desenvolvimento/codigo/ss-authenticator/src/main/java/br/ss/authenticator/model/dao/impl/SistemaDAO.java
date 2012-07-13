package br.ss.authenticator.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.Query;

import br.ss.authenticator.model.dao.ISistemaDAO;
import br.ss.authenticator.model.entity.Sistema;
import br.ss.core.annotation.stereotype.DAO;
import br.ss.core.model.dao.impl.AbstractDAO;

@Dependent
@Stateless
@DAO
public class SistemaDAO extends AbstractDAO<Sistema> implements ISistemaDAO {
	
	
	@SuppressWarnings("unchecked")
	public List<Sistema> searchByEntity( Sistema sistema ) {
		
		StringBuilder s = new StringBuilder();
		List<String> condictions = new ArrayList<String>();
		
		s.append(" select s from Sistema s ");
		if ( notEmpty(sistema.getTxSistema() ) ) {
			condictions.add(" lower(s.txSistema) like :txSistema ");
		}
		if ( notEmpty(sistema.getTxDescricao() ) ) {
			condictions.add(" lower(s.txDescricao) like :txDescricao ");	// TODO tentar usar to_ascii
		}
		if ( notEmpty(sistema.getTxSigla() ) ) {
			condictions.add(" lower(s.txSigla) like :txSigla ");
		}
		if ( notEmpty(sistema.getAtivo() ) ) {
			condictions.add(" s.ativo = :ativo ");
		}
		
		String orderBy = " order by s.txSistema ";
		
		Query q = this.entityManager.createQuery( generateHql(s.toString(), condictions) + orderBy );
		
		if ( notEmpty(sistema.getTxSistema() ) ) {
//			q.setParameter("txSistema", "%" + StringUtil.removerAcento( sistema.getTxSistema().trim().toLowerCase() ) + "%" );
			q.setParameter("txSistema", "%" + sistema.getTxSistema().trim().toLowerCase() + "%" );
		}
		if ( notEmpty(sistema.getTxDescricao() ) ) {
//			q.setParameter("txDescricao", "%" + StringUtil.removerAcento( sistema.getTxDescricao().trim().toLowerCase() ) + "%" );
			q.setParameter("txDescricao", "%" + sistema.getTxDescricao().trim().toLowerCase() + "%" );
		}
		if ( notEmpty(sistema.getTxSigla() ) ) {
			q.setParameter("txSigla", "%" + sistema.getTxSigla().trim().toLowerCase() + "%" );
		}
		if ( notEmpty(sistema.getAtivo() ) ) {
			q.setParameter("ativo", ( Boolean ) sistema.getAtivo());
		}
		
		return q.getResultList();
	}

}
