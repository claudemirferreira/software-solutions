package br.com.ss.academico.servico;

import java.util.List;

import br.com.ss.academico.dominio.Disciplina;

public interface DisciplinaServico {

	public List<Disciplina> listarTodos();

	public Disciplina salvar(Disciplina disciplina);

	public void remover(Disciplina disciplina);

	public List<Disciplina> findByNomeLike(String nome);
	
	public List<Disciplina> listaDisciplinaPorCurso(Long idCurso);

}