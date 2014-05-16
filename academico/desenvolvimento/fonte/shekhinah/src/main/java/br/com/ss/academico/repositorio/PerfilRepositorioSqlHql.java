package br.com.ss.academico.repositorio;

import java.util.List;

import br.com.ss.academico.dominio.Perfil;

public interface PerfilRepositorioSqlHql {

	public List<Perfil> listaPerfilPorSistemaPorUsuario(int sistemaId, Long usuarioId);

	public List<Perfil> listaPerfil(Perfil perfil);
	
}