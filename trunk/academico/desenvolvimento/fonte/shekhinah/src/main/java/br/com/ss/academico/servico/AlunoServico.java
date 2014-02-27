package br.com.ss.academico.servico;

import java.util.List;

import br.com.ss.academico.dominio.Aluno;

public interface AlunoServico {

	public List<Aluno> listarTodos();

	public Aluno salvar(Aluno aluno);

	public void remover(Aluno aluno);

	public List<Aluno> findByNomeLike(String nome);

}