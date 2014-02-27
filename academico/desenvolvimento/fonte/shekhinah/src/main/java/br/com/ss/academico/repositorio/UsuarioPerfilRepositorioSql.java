package br.com.ss.academico.repositorio;

import java.util.List;

import br.com.ss.academico.dominio.Perfil;

public interface UsuarioPerfilRepositorioSql {

	public List<Perfil> listaPerfilNotInUsuario(Long idUsuario);

}