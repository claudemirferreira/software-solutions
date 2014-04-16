package br.com.ss.academico.servico;

import java.util.List;

import br.com.ss.academico.dominio.Aluno;
import br.com.ss.academico.dominio.Turma;

public interface AlunoServico extends IService<Aluno> {

	public List<Aluno> findByNomeLike(String nome);

	public List<Aluno> findByTurma(Turma turma);

}