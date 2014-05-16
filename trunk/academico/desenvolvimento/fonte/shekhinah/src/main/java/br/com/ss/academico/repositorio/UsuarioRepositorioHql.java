package br.com.ss.academico.repositorio;

import java.util.List;

import br.com.ss.academico.dominio.Usuario;

public interface UsuarioRepositorioHql {

	List<Usuario> pesquisar(Usuario entity);

}
