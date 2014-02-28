package br.com.ss.academico.servico;

import java.util.List;

import br.com.ss.academico.dominio.Turma;

public interface TurmaServico {

	public List<Turma> listarTodos();

	public Turma salvar(Turma turma);

	public void remover(Turma turma);

	public List<Turma> findByAno(Integer ano);

}