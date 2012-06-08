package br.ss.authenticator.model.dao;

import java.util.List;

import br.ss.authenticator.model.entity.Sistema;
import br.ss.core.dao.PaginatedQuery;
import br.ss.core.dao.Query;
import br.ss.core.esteriotipo.DAO;

@DAO
public class SistemaDAO extends SistemaDAO_ {

	public PaginatedQuery<List<Sistema>> obterSistemas( String txSistema ) {
		
		Query<List<Sistema>> query = new Query<List<Sistema>>( this );
		
		select( txSistema(), txDescricao(), txSigla(), ativo() )
				.toCustomClass( Sistema.class )
		
		.where( txSistema().like( txSistema ) )
		
		.orderBy( txSistema().orderByAsc().asLowerCase() );
		
		return query;
	}


	public List<Sistema> obterAllSistemas() {
		Query<List<Sistema>> query = new Query<List<Sistema>>( this );
		
		select( txSistema(), txDescricao(), txSigla(), ativo() ).toCustomClass( Sistema.class )
		
		.where(
			ativo().isTrue()
		)

		.orderBy( txSistema().orderByAsc() );
		
		return query.getResultList();
	}
	
}