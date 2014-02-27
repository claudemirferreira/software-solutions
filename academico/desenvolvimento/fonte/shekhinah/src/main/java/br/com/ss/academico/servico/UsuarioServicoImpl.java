package br.com.ss.academico.servico;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.academico.dominio.Usuario;
import br.com.ss.academico.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServicoImpl implements UsuarioServico {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Override
	public Usuario findByLoginAndSenha(String login, String senha)
			throws NoResultException {
		return this.usuarioRepositorio.findByLoginAndSenha(login, senha);
	}

	@Override
	public Usuario findByLogin(String login) {
		return this.usuarioRepositorio.findByLogin(login);
	}

	@Override
	public List<Usuario> listarTodos() {
		return this.usuarioRepositorio.findAll();
	}

	@Override
	public Usuario salvar(Usuario usuario) {
		return this.usuarioRepositorio.save(usuario);
	}

	@Override
	public void remover(Usuario usuario) {
		this.usuarioRepositorio.delete(usuario);
	}

	@Override
	public List<Usuario> findByNomeLike(String nome) throws NoResultException {
		return this.usuarioRepositorio.findByNomeLike(nome);
	}

}