package br.com.ss.data.servico;

import java.util.List;

import br.com.ss.data.entities.TipoSaida;

public interface TipoSaidaServico {

	public List<TipoSaida> listarTodos();

	public TipoSaida salvar(TipoSaida tipoSaida);

	public void remover(TipoSaida tipoSaida);

}