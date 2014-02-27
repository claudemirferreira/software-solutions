package br.com.ss.academico.repositorio;

import java.util.List;

import br.com.ss.academico.dominio.Perfil;

public interface PerfilRepositorioSql {

	public List<Perfil> listaPerfilPorSistemaPorUsuario(int sistemaId,
			Long usuarioId);

}