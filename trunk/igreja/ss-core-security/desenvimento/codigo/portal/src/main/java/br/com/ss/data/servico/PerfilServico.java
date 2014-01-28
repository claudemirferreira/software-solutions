package br.com.ss.data.servico;

import java.util.List;

import br.com.ss.data.entities.Perfil;

public interface PerfilServico {

	public List<Perfil> listarTodos();

	public Perfil salvar(Perfil Perfil);

	public void remover(Perfil Perfil);

}
