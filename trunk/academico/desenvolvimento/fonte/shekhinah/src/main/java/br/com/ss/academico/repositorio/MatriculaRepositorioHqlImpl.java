package br.com.ss.academico.repositorio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ss.academico.dominio.Matricula;

@Repository
public class MatriculaRepositorioHqlImpl extends RepositorioGenerico implements MatriculaRepositorioHql{
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Matricula> pesquisar(Matricula entity) {
		StringBuilder sb = new StringBuilder();
		List<String> condictions = new ArrayList<String>();
		
		sb.append(" select mat from Matricula mat ");
		
		if ( notEmpty(entity.getAluno()) ) {
			condictions.add(" lower( mat.aluno.nome ) like :nomeAluno ");
		}
		if ( notEmpty(entity.getStatus() ) ) {
			condictions.add(" mat.status = :status ");
		}
		if ( notEmpty(entity.getTurma().getAno() ) || notEmpty(entity.getTurma().getTurno() ) ) {
			sb.append(" join mat.turma turma ");
			if ( notEmpty(entity.getTurma().getAno() ) ) {
				condictions.add(" turma.ano = :ano ");
			}
			if ( notEmpty(entity.getTurma().getTurno() ) ) {
				condictions.add(" turma.turno = :turno ");
			}
		}
		String orderBy = " order by mat.data desc, mat.aluno.nome asc ";
		
		Query query = entityManager.createQuery(generateHql(sb.toString(), condictions) + orderBy);
		if ( notEmpty(entity.getAluno()) ) {
			query.setParameter("nomeAluno", "%" + entity.getAluno().getNome().trim().toLowerCase() + "%");
		}
		if ( notEmpty(entity.getStatus() ) ) {
			query.setParameter("status", entity.getStatus());
		}
		if ( notEmpty(entity.getTurma().getAno() ) ) {
			query.setParameter("ano", entity.getTurma().getAno());
		}
		if ( notEmpty(entity.getTurma().getTurno() ) ) {
			query.setParameter("ano", entity.getTurma().getTurno());
		}
		return query.getResultList();
	}

}