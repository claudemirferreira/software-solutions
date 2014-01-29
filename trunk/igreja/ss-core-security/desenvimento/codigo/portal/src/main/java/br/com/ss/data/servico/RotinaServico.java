package br.com.ss.data.servico;

import java.util.List;

import br.com.ss.data.entities.Rotina;

public interface RotinaServico {

	public List<Rotina> listarTodos();

	public Rotina salvar(Rotina rotina);

	public void remover(Rotina rotina);

}
