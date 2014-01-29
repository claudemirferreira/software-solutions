package br.com.ss.data.servico;

import java.util.List;

import br.com.ss.data.entities.Membro;

public interface MembroServico {

	public List<Membro> listarTodos();

	public Membro salvar(Membro membro);

	public void remover(Membro membro);

}
