package br.com.ss.academico.repositorio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ss.academico.dominio.Mensalidade;
import br.com.ss.academico.enumerated.TipoPesquisaData;

@Repository
public class MensalidadeRepositorioHqlImpl extends RepositorioGenerico implements MensalidadeRepositorioHql{
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Mensalidade> pesquisar(Mensalidade entity, Date dataInicio, 
									Date dataFim, TipoPesquisaData tipoPesquisaData) {
		StringBuilder sb = new StringBuilder();
		List<String> condictions = new ArrayList<String>();
		
		sb.append(" select men form Mensalidade men ");
		
		if ( notEmpty(entity.getMatricula().getAluno()) ) {
			condictions.add(" men.matricula.aluno = :aluno ");
		}
		if ( notEmpty(tipoPesquisaData)) {
			if ( tipoPesquisaData == TipoPesquisaData.PAGAMENTO ) {
				condictions.add(" men.dataPagamento >= :dataPagamento ");
			} else {
				condictions.add(" men.dataVencimento >= :dataVencimento ");
			}
		}
		String orderBy = " order by men.dataVencimento asc, men.matricula.aluno asc ";
		
		Query query = entityManager.createQuery(generateHql(sb.toString(), condictions) + orderBy);
		if ( notEmpty(entity.getMatricula().getAluno()) ) {
			query.setParameter("aluno", entity.getMatricula().getAluno());
		}
		if ( notEmpty(tipoPesquisaData)) {
			if ( tipoPesquisaData == TipoPesquisaData.PAGAMENTO ) {
				query.setParameter("dataPagamento", entity.getDataPagamento());
			} else {
				query.setParameter("dataVencimento", entity.getDataVencimento());
			}
		}
		return query.getResultList();
	}

}