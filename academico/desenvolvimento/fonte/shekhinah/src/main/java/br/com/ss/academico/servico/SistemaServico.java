package br.com.ss.academico.servico;

import java.util.List;

import br.com.ss.academico.dominio.Sistema;

public interface SistemaServico {

	public List<Sistema> listarTodos();

	public Sistema salvar(Sistema sistema);

	public void remover(Sistema sistema);

	public List<Sistema> findByNomeLike(String nome);

	public Sistema findByCodigo(String codigo);

}