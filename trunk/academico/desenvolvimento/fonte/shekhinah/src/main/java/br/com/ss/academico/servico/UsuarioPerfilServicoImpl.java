package br.com.ss.academico.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.academico.dominio.Perfil;
import br.com.ss.academico.dominio.Usuario;
import br.com.ss.academico.dominio.UsuarioPerfil;
import br.com.ss.academico.repositorio.UsuarioPerfilRepositorio;
import br.com.ss.academico.repositorio.UsuarioPerfilRepositorioSql;

@Service
public class UsuarioPerfilServicoImpl implements UsuarioPerfilServico {

	@Autowired
	private UsuarioPerfilRepositorio repositorio;

	@Autowired
	private UsuarioPerfilRepositorioSql repositorioSql;

	@Override
	public List<UsuarioPerfil> listarTodos() {
		return this.repositorio.findAll();
	}

	@Override
	public UsuarioPerfil salvar(UsuarioPerfil usuarioPerfil) {
		return this.repositorio.save(usuarioPerfil);
	}

	@Override
	public void remover(UsuarioPerfil usuarioPerfil) {
		this.repositorio.delete(usuarioPerfil);

	}

	@Override
	public List<Perfil> listaPerfilNotInUsuario(Long idUsuario) {
		return repositorioSql.listaPerfilNotInUsuario(idUsuario);
	}

	@Override
	public List<UsuarioPerfil> findByUsuario(Usuario usuario) {
		return repositorio.findByUsuario(usuario);
	}
}
