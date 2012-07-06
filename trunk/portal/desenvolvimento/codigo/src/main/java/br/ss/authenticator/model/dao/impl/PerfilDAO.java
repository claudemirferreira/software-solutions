package br.ss.authenticator.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.Query;

import br.ss.authenticator.model.dao.IPerfilDAO;
import br.ss.authenticator.model.entity.Perfil;
import br.ss.authenticator.model.entity.Sistema;
import br.ss.core.annotation.stereotype.DAO;
import br.ss.core.model.dao.impl.AbstractDAO;

@Dependent
@Stateless
@DAO
public class PerfilDAO extends AbstractDAO<Perfil> implements IPerfilDAO {
	
	
	@SuppressWarnings("unchecked")
	public List<Perfil> searchByEntity( Perfil perfil ) {
		
		StringBuilder s = new StringBuilder();
		List<String> condictions = new ArrayList<String>();
		
		s.append(" select p from Perfil p ");
		if ( notEmpty(perfil.getTxPerfil() ) ) {
			condictions.add(" lower(p.txPerfil) like :txPerfil ");
		}
		
		if ( notEmpty(perfil.getSistema() ) ) {
			condictions.add(" p.sistema = :sistema ");
		}

		String orderBy = " order by p.txPerfil ";
		Query q = this.entityManager.createQuery( generateHql(s.toString(), condictions) + orderBy );
		
		if ( notEmpty(perfil.getTxPerfil() ) ) {
			q.setParameter("txPerfil", "%" + perfil.getTxPerfil().trim().toLowerCase() + "%" );
		}
		if ( notEmpty(perfil.getSistema() ) ) {
			q.setParameter("sistema", perfil.getSistema() );
		}
		
		return q.getResultList();
	}
	
	

	@SuppressWarnings("unchecked")
	public List<Perfil> searchBySistema( Sistema sistema ) {
		
		StringBuilder s = new StringBuilder();
		List<String> condictions = new ArrayList<String>();
		
		s.append(" select p from Perfil p ");
		if ( notEmpty(sistema) ) {
			condictions.add(" p.sistema = :sistema");
		}

		String orderBy = " order by p.txPerfil ";
		Query q = this.entityManager.createQuery( generateHql(s.toString(), condictions) + orderBy );
		
		if ( notEmpty( sistema ) ) {
			q.setParameter("sistema", sistema );
		}
		
		return q.getResultList();
	}


}
