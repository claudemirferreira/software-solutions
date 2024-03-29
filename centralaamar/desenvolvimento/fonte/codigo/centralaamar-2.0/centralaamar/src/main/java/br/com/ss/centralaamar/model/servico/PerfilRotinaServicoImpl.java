package br.com.ss.centralaamar.model.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.centralaamar.model.dominio.PerfilRotina;
import br.com.ss.centralaamar.model.dominio.Rotina;
import br.com.ss.centralaamar.model.repositorio.PerfilRotinaRepositorio;
import br.com.ss.centralaamar.model.repositorio.PerfilRotinaRepositorioSql;

@Service
public class PerfilRotinaServicoImpl implements PerfilRotinaServico {

	@Autowired
	private PerfilRotinaRepositorio repositorio;

	@Autowired
	private PerfilRotinaRepositorioSql repositorioSql;

	@Override
	public List<PerfilRotina> listarTodos() {
		return repositorio.findAll();
	}

	@Override
	public PerfilRotina salvar(PerfilRotina perfilRotina) {
		return repositorio.save(perfilRotina);
	}

	@Override
	public void remover(PerfilRotina perfilRotina) {
		this.repositorio.delete(perfilRotina);
	}

	public List<Rotina> listaRotinaNotInPerfil(Long idPerfil) {
		return repositorioSql.listaRotinaNotInPerfil(idPerfil);
	}

}
