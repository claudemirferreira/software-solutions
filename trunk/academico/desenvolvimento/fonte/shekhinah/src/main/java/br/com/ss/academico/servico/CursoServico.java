package br.com.ss.academico.servico;

import java.util.List;

import br.com.ss.academico.dominio.Curso;

public interface CursoServico {

	public List<Curso> listarTodos();

	public Curso salvar(Curso curso);

	public void remover(Curso curso);

	public List<Curso> findByNomeLike(String nome);

}