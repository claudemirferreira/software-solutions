package br.com.ss.academico.servico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.academico.dominio.Rotina;
import br.com.ss.academico.repositorio.RotinaRepositorio;
import br.com.ss.academico.repositorio.RotinaRepositorioSql;

@Service
public class RotinaServicoImpl implements RotinaServico, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4128328556228022891L;

	@Autowired
	private RotinaRepositorio rotinaRepositorio;

	@Autowired
	private RotinaRepositorioSql rotinaRepositorioSql;

	@Override
	public List<Rotina> listarTodos() {
		return this.rotinaRepositorio.findAll();
	}

	@Override
	public Rotina salvar(Rotina rotina) {
		return this.rotinaRepositorio.save(rotina);
	}

	@Override
	public void remover(Rotina rotina) {
		this.rotinaRepositorio.delete(rotina);

	}

	@Override
	public List<Rotina> listaRotinasPorPerfil(Long id) {
		return rotinaRepositorioSql.listaRotinasPorPerfil(id);
	}

	@Override
	public List<Rotina> findByNomeLike(String nome) {
		return this.rotinaRepositorio.findByNomeLike(nome);
	}
}