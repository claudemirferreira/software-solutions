package br.ss.authenticator.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.Query;

import br.ss.authenticator.model.dao.IServicoDAO;
import br.ss.authenticator.model.entity.Servico;
import br.ss.core.annotation.stereotype.DAO;
import br.ss.core.model.dao.impl.AbstractDAO;

@Dependent
@Stateless
@DAO
public class ServicoDAO extends AbstractDAO<Servico> implements IServicoDAO {

	@Override
	public Servico getByPrimaryKey(Servico servico) {
		return null;
	}

		
	@SuppressWarnings("unchecked")
	public List<Servico> searchByEntity( Servico servico ) {
		
		StringBuilder s = new StringBuilder();
		List<String> condictions = new ArrayList<String>();
		
		s.append(" select s from Servico s ");
		if ( notEmpty(servico.getTxServico() ) ) {
			condictions.add(" lower(s.txServico) like :txServico ");
		}
		if ( notEmpty(servico.getTxPacoteCodigo() ) ) {
			condictions.add(" lower(s.txPacoteCodigo) like :txPacoteCodigo ");
		}
		if ( notEmpty(servico.getTxServicoCodigo() ) ) {
			condictions.add(" lower(s.txServicoCodigo) like :txServicoCodigo ");
		}
		if ( notEmpty(servico.getAtivo() ) ) {
			condictions.add(" s.ativo = :ativo ");
		}
		
		String orderBy = " order by s.txServico ";
		
		Query q = this.entityManager.createQuery( generateHql(s.toString(), condictions) + orderBy );
		
		if ( notEmpty(servico.getTxServico() ) ) {
			q.setParameter("txServico", "%" + servico.getTxServico().trim().toLowerCase() + "%" );
		}
		if ( notEmpty(servico.getTxPacoteCodigo() ) ) {
			q.setParameter("txPacoteCodigo", "%" + servico.getTxPacoteCodigo().trim().toLowerCase() + "%" );
		}
		if ( notEmpty(servico.getTxServicoCodigo() ) ) {
			q.setParameter("txServicoCodigo", "%" + servico.getTxServicoCodigo().trim().toLowerCase() + "%" );
		}
		if ( notEmpty(servico.getAtivo() ) ) {
			q.setParameter("ativo", ( Boolean ) servico.getAtivo());
		}
		
		return q.getResultList();
	}
	
	
}
