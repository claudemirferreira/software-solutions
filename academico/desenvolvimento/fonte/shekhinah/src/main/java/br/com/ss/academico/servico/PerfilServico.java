package br.com.ss.academico.servico;

import java.util.List;

import br.com.ss.academico.dominio.Perfil;

public interface PerfilServico {

	public List<Perfil> listarTodos();

	public Perfil salvar(Perfil perfil);

	public void remover(Perfil perfil);

	public List<Perfil> findByNomeLike(String nome);
	
	public List<Perfil> listaPerfilPorSistemaPorUsuario(int sistemaId,
			Long usuarioId);

}