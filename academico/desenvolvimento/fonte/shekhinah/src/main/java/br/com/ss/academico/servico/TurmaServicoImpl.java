package br.com.ss.academico.servico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.academico.dominio.Turma;
import br.com.ss.academico.repositorio.TurmaRepositorio;

@Service
public class TurmaServicoImpl implements TurmaServico, Serializable {

	private static final long serialVersionUID = -4305564891244729963L;

	@Autowired
	private TurmaRepositorio repositorio;

	@Override
	public List<Turma> listarTodos() {
		return this.repositorio.findAll();
	}

	@Override
	public Turma salvar(Turma turma) {
		return this.repositorio.save(turma);
	}

	@Override
	public void remover(Turma turma) {
		this.repositorio.delete(turma);
	}

	@Override
	public List<Turma> findByAno(Integer ano) {
		return this.repositorio.findByAno(ano);
	}

}