package br.com.ss.data.servico;

import java.util.List;

import br.com.ss.data.entities.Rotina;
import br.com.ss.data.entities.Sistema;

public interface RotinaServico {

	public List<Rotina> listarTodos();

	public Rotina salvar(Rotina rotina);

	public void remover(Rotina rotina);

	public List<Rotina> findBySistema(Sistema sistema);

	public List<Rotina> findBySistemaByNomeLike(Sistema sistema, String nome);

}
