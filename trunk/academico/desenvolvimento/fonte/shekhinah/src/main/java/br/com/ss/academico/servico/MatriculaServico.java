package br.com.ss.academico.servico;

import java.util.List;

import br.com.ss.academico.dominio.Aluno;
import br.com.ss.academico.dominio.Matricula;
import br.com.ss.academico.dominio.Turma;

public interface MatriculaServico {

	public List<Matricula> listarTodos();

	public Matricula salvar(Matricula matricula);

	public void remover(Matricula matricula);

	public List<Matricula> findByAluno(Aluno aluno);
	
	public Long countVagasDisponiveis(Turma turma);
	
}