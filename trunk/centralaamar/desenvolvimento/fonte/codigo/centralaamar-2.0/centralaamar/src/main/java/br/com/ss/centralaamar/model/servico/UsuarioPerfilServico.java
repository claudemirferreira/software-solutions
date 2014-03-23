package br.com.ss.centralaamar.model.servico;

import java.util.List;

import br.com.ss.centralaamar.model.dominio.Perfil;
import br.com.ss.centralaamar.model.dominio.Usuario;
import br.com.ss.centralaamar.model.dominio.UsuarioPerfil;

public interface UsuarioPerfilServico {

	public List<UsuarioPerfil> listarTodos();

	public UsuarioPerfil salvar(UsuarioPerfil usuarioPerfil);

	public void remover(UsuarioPerfil usuarioPerfil);

	public List<Perfil> listaPerfilNotInUsuario(Long idUsuario);

	public List<UsuarioPerfil> findByUsuario(Usuario usuario);
}
