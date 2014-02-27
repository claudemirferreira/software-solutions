package br.com.ss.academico.servico;

import java.util.List;

import br.com.ss.academico.dominio.Rotina;

public interface RotinaServico {

	public List<Rotina> listarTodos();

	public Rotina salvar(Rotina rotina);

	public void remover(Rotina rotina);

	public List<Rotina> listaRotinasPorPerfil(Long id);

	public List<Rotina> findByNomeLike(String nome);

}