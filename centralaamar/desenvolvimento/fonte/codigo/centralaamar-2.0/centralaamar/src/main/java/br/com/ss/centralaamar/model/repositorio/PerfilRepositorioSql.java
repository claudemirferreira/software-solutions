package br.com.ss.centralaamar.model.repositorio;

import java.util.List;

import br.com.ss.centralaamar.model.dominio.Perfil;

public interface PerfilRepositorioSql {

	public List<Perfil> listaPerfilPorSistemaPorUsuario(int sistemaId,
			Long usuarioId);

}