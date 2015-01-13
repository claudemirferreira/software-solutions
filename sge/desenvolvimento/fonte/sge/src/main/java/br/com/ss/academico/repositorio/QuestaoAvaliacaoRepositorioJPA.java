package br.com.ss.academico.repositorio;

import java.util.List;

import br.com.ss.academico.dominio.QuestaoAvaliacao;

public interface QuestaoAvaliacaoRepositorioJPA {

	List<QuestaoAvaliacao> listarTodos();

}