package br.com.ss.centralaamar.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.ss.centralaamar.model.entity.Profissao;

@Component
public class ProfissaoDAO extends AbstractDAO<Profissao> implements IProfissaoDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Profissao> searchByEntity(Profissao entity) {
		StringBuilder s = new StringBuilder();
		List<String> condictions = new ArrayList<String>();
		
		s.append(" select p from Profissao p ");
		if ( notEmpty(entity.getNome()) ) {
			condictions.add(" lower(p.nome) like :nome ");
		}
		
		String orderBy = " order by p.nome ";
		
		Query q = this.entityManager.createQuery( generateHql(s.toString(), condictions) + orderBy );
		
		if ( notEmpty(entity.getNome() ) ) {
			q.setParameter("nome", "%" + entity.getNome().trim().toLowerCase() + "%" );
		}
		
		return q.getResultList();
	}

}
