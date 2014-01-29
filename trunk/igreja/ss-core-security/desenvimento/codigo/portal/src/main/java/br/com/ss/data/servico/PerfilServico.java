package br.com.ss.data.servico;

import java.util.List;

import br.com.ss.data.entities.Perfil;
import br.com.ss.data.entities.Sistema;

public interface PerfilServico {

	public List<Perfil> listarTodos();

	public Perfil salvar(Perfil Perfil);

	public void remover(Perfil Perfil);

	public List<Perfil> findBySistema(Sistema sistema);

	public List<Perfil> findBySistemaByNomeLike(Sistema sistema, String nome);

}
