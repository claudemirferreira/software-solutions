package br.com.ss.centralaamar.model.servico;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.ss.centralaamar.model.dominio.Usuario;

public interface UsuarioServico {

	public List<Usuario> listarTodos();

	public Usuario salvar(Usuario usuario);

	public void remover(Usuario usuario);

	public Usuario findByLogin(String login);

	public Usuario findByLoginAndSenha(String login, String senha)
			throws NoResultException;

	public List<Usuario> findByNomeLike(String nome) throws NoResultException;
}
