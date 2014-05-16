package br.com.ss.academico.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.ss.academico.dominio.Perfil;
import br.com.ss.academico.repositorio.PerfilRepositorio;
import br.com.ss.academico.repositorio.PerfilRepositorioSqlHql;

@Service
public class PerfilServicoImpl extends ServicoImpl<Perfil, Long> implements PerfilServico {

	private static final long serialVersionUID = 6281856438650886521L;

	@Autowired
	private PerfilRepositorio perfilRepositorio;

	@Autowired
	private PerfilRepositorioSqlHql perfilRepositorioSql;

	@Override
	protected JpaRepository<Perfil, Long> getRepository() {
		return perfilRepositorio;
	}

	@Override
	public List<Perfil> pesquisar(Perfil entity) {
		return perfilRepositorioSql.listaPerfil(entity);
	}
	
	@Override
	public List<Perfil> findByNomeLike(String nome) {
		return this.perfilRepositorio.findByNomeLike(nome);
	}

	@Override
	public List<Perfil> listaPerfilPorSistemaPorUsuario(int sistemaId, Long usuarioId) {
		return this.perfilRepositorioSql.listaPerfilPorSistemaPorUsuario( sistemaId, usuarioId);
	}

}