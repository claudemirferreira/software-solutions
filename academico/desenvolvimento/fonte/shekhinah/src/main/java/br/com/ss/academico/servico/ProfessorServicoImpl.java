package br.com.ss.academico.servico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.academico.dominio.Professor;
import br.com.ss.academico.repositorio.ProfessorRepositorio;

@Service
public class ProfessorServicoImpl implements ProfessorServico, Serializable {

	private static final long serialVersionUID = -4305564891244729963L;

	@Autowired
	private ProfessorRepositorio repositorio;

	@Override
	public List<Professor> listarTodos() {
		return this.repositorio.findAll();
	}

	@Override
	public Professor salvar(Professor professor) {
		return this.repositorio.save(professor);
	}

	@Override
	public void remover(Professor professor) {
		this.repositorio.delete(professor);
	}

	@Override
	public List<Professor> findByNomeLike(String nome) {
		return this.repositorio.findByNomeLike(nome);
	}
}