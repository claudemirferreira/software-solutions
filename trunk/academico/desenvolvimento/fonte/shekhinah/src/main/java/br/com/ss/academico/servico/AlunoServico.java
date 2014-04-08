package br.com.ss.academico.servico;

import java.util.List;

import br.com.ss.academico.dominio.Aluno;

public interface AlunoServico extends IService<Aluno> {

	public List<Aluno> findByNomeLike(String nome);

}