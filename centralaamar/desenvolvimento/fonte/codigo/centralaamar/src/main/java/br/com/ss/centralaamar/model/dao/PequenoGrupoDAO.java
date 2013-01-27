package br.com.ss.centralaamar.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ss.centralaamar.model.entity.PequenoGrupo;

@Repository
public class PequenoGrupoDAO  extends AbstractDAO<PequenoGrupo> implements IPequenoGrupoDAO  {


	@Override
	public List<PequenoGrupo> searchByEntity(PequenoGrupo entity) {
		StringBuilder s = new StringBuilder();
		List<String> condictions = new ArrayList<String>();
		
		s.append(" select pg from PequenoGrupo pg ");
		if ( notEmpty(entity.getDescricao()) ) {
			condictions.add(" lower(p.descricao) like :desc ");
		}
		
		String orderBy = " order by p.descricao ";
		
		Query q = this.entityManager.createQuery( generateHql(s.toString(), condictions) + orderBy );
		
		if ( notEmpty(entity.getDescricao() ) ) {
			q.setParameter("nome", "%" + entity.getDescricao().trim().toLowerCase() + "%" );
		}
		
		return q.getResultList();
	}


}
