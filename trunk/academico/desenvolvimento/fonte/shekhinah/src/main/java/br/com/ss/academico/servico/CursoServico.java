package br.com.ss.academico.servico;

import java.util.List;

import br.com.ss.academico.dominio.Curso;

public interface CursoServico extends IService<Curso, Long>  {

	public List<Curso> findByNomeLike(String nome);

}