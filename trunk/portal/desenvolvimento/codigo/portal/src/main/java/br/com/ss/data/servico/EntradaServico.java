package br.com.ss.data.servico;

import java.util.List;

import br.com.ss.data.entities.Entrada;

public interface EntradaServico {

	public List<Entrada> listarTodos();

	public Entrada salvar(Entrada entrada);

	public void remover(Entrada entrada);

}
