package br.com.ss.academico.servico;

import java.util.List;

import br.com.ss.academico.dominio.Professor;

public interface ProfessorServico {

	public List<Professor> listarTodos();

	public Professor salvar(Professor professor);

	public void remover(Professor professor);

	public List<Professor> findByNomeLike(String nome);

}