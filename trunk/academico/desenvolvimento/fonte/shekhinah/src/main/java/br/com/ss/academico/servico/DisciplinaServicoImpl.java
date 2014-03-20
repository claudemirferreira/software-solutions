package br.com.ss.academico.servico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.academico.dominio.Disciplina;
import br.com.ss.academico.repositorio.DisciplinaRepositorio;
import br.com.ss.academico.repositorio.DisciplinaRepositorioSql;

@Service
public class DisciplinaServicoImpl implements DisciplinaServico, Serializable {

	private static final long serialVersionUID = -4305564891244729963L;

	@Autowired
	private DisciplinaRepositorio repositorio;

	@Autowired
	private DisciplinaRepositorioSql disciplinaRepositorioSql;

	@Override
	public List<Disciplina> listarTodos() {
		return this.repositorio.findAll();
	}

	@Override
	public Disciplina salvar(Disciplina disciplina) {
		return this.repositorio.save(disciplina);
	}

	@Override
	public void remover(Disciplina disciplina) {
		this.repositorio.delete(disciplina);
	}

	@Override
	public List<Disciplina> findByNomeLike(String nome) {
		return this.repositorio.findByNomeLike(nome);
	}

	@Override
	public List<Disciplina> listaDisciplinaPorCurso(Long idCurso) {
		return this.disciplinaRepositorioSql.listaDisciplinaPorCurso(idCurso);
	}
}