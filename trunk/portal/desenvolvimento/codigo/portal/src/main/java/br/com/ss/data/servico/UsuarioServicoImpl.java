package br.com.ss.data.servico;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.data.entities.Usuario;
import br.com.ss.data.repositories.UsuarioRepositorio;

@Service
public class UsuarioServicoImpl implements UsuarioServico, Serializable {

	private static final long serialVersionUID = 460008173632552219L;
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Override
	public Usuario findByCpfAndSenha(String cpf, String senha)
			throws NoResultException {
		return this.usuarioRepositorio.findByCpfAndSenha(cpf, senha);
	}

	@Override
	public Usuario findByCpf(String cpf) {
		return this.usuarioRepositorio.findByCpf(cpf);
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
}
