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
		
		sb.append(" select men from Mensalidade men ");
		sb.append(" join fetch men.matricula mat ");
		
		if ( notEmpty(entity.getMatricula().getAluno()) ) {
			condictions.add(" men.matricula.aluno = :aluno ");
		}
		if ( notEmpty(entity.getStatusPagamento()) ) {
			condictions.add(" men.statusPagamento = :statusPagamento ");
		}
		if ( notEmpty(tipoPesquisaData)) {
			if ( tipoPesquisaData == TipoPesquisaData.VECIMENTO ) {
				if (notEmpty(dataInicio)) {
					condictions.add(" men.dataVencimento >= :dataInicio ");
				}
				if (notEmpty(dataFim)) {
					condictions.add(" men.dataVencimento <= :dataFim ");
				}
			} else {
				if (notEmpty(dataInicio)) {
					condictions.add(" men.dataPagamento >= :dataInicio ");
				}
				if (notEmpty(dataFim)) {
					condictions.add(" men.dataPagamento <= :dataFim ");
				}
			}
		}
		String orderBy = " order by men.dataVencimento asc, men.matricula.aluno asc, men.matricula.turma.curso.nome ";
		
		Query query = entityManager.createQuery(generateHql(sb.toString(), condictions) + orderBy);
		if ( notEmpty(entity.getMatricula().getAluno()) ) {
			query.setParameter("aluno", entity.getMatricula().getAluno());
		}
		if ( notEmpty(entity.getStatusPagamento()) ) {
			query.setParameter("statusPagamento", entity.getStatusPagamento());
		}
		if ( notEmpty(tipoPesquisaData)) {
			if (notEmpty(dataInicio)) {
				query.setParameter("dataInicio", dataInicio);
			}
			if (notEmpty(dataFim)) {
				query.setParameter("dataFim", dataFim);
			}
			if (notEmpty(entity.getDataPagamento())) {
				
			}
		}
		return query.getResultList();
	}

}