package br.com.ss.data.servico;

import java.util.List;

import br.com.ss.data.entities.Saida;

public interface SaidaServico {

	public List<Saida> listarTodos();

	public Saida salvar(Saida saida);

	public void remover(Saida saida);

}
