package br.com.ss.data.servico;

import java.util.List;

import br.com.ss.data.entities.TipoEntrada;

public interface TipoEntradaServico {

	public List<TipoEntrada> listarTodos();

	public TipoEntrada salvar(TipoEntrada tipoEntrada);

	public void remover(TipoEntrada tipoEntrada);

}
