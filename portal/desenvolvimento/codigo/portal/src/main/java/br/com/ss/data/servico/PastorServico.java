package br.com.ss.data.servico;

import java.util.List;

import br.com.ss.data.entities.Pastor;

public interface PastorServico {

	public List<Pastor> listarTodos();

	public Pastor salvar(Pastor pastor);

	public void remover(Pastor pastor);

}
