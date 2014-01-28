package br.com.ss.data.servico;

import java.util.List;

import br.com.ss.data.entities.Sistema;

public interface SistemaServico {

	public List<Sistema> listarTodos();

	public Sistema salvar(Sistema sistema);

	public void remover(Sistema sistema);

}
