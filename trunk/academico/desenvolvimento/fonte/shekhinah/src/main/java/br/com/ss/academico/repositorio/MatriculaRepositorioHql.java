package br.com.ss.academico.repositorio;

import java.util.List;

import br.com.ss.academico.dominio.Matricula;

public interface MatriculaRepositorioHql {

	public List<Matricula> pesquisar(Matricula entity);
	
}
