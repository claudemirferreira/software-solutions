package br.com.ss.centralaamar.model.repositorio;

import java.util.List;

import br.com.ss.centralaamar.model.dominio.Perfil;

public interface UsuarioPerfilRepositorioSql {

	public List<Perfil> listaPerfilNotInUsuario(Long idUsuario);

}