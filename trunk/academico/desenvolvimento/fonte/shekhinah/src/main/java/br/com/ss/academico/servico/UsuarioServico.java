package br.com.ss.academico.servico;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.ss.academico.dominio.Usuario;

public interface UsuarioServico  extends IService<Usuario, Long> {

	public Usuario findByLogin(String login);

	public Usuario findByLoginAndSenha(String login, String senha) throws NoResultException;

	public List<Usuario> findByNomeLike(String nome) throws NoResultException;
}
