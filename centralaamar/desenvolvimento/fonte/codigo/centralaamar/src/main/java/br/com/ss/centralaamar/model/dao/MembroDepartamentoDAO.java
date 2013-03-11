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
	@Override
	public List<MembroDepartamento> searchByEntity(MembroDepartamento entity) {
		StringBuilder s = new StringBuilder();
		List<String> condictions = new ArrayList<String>();

		s.append(" select p from MembroDepartamento p ");
		// if ( notEmpty(entity.getNome()) ) {
		// condictions.add(" lower(p.nome) like :nome ");
		// }

		String orderBy = " order by p.membro.nome ";

		Query q = this.entityManager.createQuery(generateHql(s.toString(),
				condictions) + orderBy);

		// if ( notEmpty(entity.getNome() ) ) {
		// q.setParameter("nome", "%" + entity.getNome().trim().toLowerCase() +
		// "%" );
		// }

		return q.getResultList();
	}

}
