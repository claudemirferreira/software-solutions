package br.com.ss.academico.servico;

import java.util.List;

import br.com.ss.academico.dominio.Perfil;

public interface PerfilServico extends IService<Perfil, Long> {

	public List<Perfil> findByNomeLike(String nome);
	
	public List<Perfil> listaPerfilPorSistemaPorUsuario(int sistemaId, Long usuarioId);

}