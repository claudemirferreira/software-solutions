package br.com.ss.academico.servico;

import java.util.List;

import br.com.ss.academico.dominio.Matricula;

public interface MatriculaServico {

	public List<Matricula> listarTodos();

	public Matricula salvar(Matricula matricula);

	public void remover(Matricula matricula);

}