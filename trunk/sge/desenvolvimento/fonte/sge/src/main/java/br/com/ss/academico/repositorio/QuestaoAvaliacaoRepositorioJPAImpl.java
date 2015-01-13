package br.com.ss.academico.repositorio;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ss.academico.dominio.QuestaoAvaliacao;
import br.com.ss.core.seguranca.repositorio.RepositorioGenerico;

@Repository
public class QuestaoAvaliacaoRepositorioJPAImpl extends RepositorioGenerico implements QuestaoAvaliacaoRepositorioJPA {

	@SuppressWarnings("unchecked")
	@Override
	public List<QuestaoAvaliacao> listarTodos() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select qa from QuestaoAvaliacao qa ");
		
		Query query = entityManager.createQuery(sb.toString());
		
		return query.getResultList();
	}

}