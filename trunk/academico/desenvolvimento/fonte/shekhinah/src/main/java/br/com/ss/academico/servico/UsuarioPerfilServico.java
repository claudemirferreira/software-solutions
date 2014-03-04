package br.com.ss.academico.servico;

import java.util.List;

import br.com.ss.academico.dominio.Perfil;
import br.com.ss.academico.dominio.Usuario;
import br.com.ss.academico.dominio.UsuarioPerfil;

public interface UsuarioPerfilServico {

	public List<UsuarioPerfil> listarTodos();

	public UsuarioPerfil salvar(UsuarioPerfil usuarioPerfil);

	public void remover(UsuarioPerfil usuarioPerfil);

	public List<Perfil> listaPerfilNotInUsuario(Long idUsuario);
	
	public List<UsuarioPerfil> findByUsuario(Usuario usuario);
}
