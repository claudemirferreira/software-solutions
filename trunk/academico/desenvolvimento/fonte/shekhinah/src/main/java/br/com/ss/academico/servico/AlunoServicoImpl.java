package br.com.ss.academico.servico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.academico.dominio.Aluno;
import br.com.ss.academico.dominio.Turma;
import br.com.ss.academico.repositorio.AlunoRepositorio;
import br.com.ss.academico.repositorio.AlunoRepositorioJPA;

@Service
public class AlunoServicoImpl implements AlunoServico, Serializable {

	private static final long serialVersionUID = -4305564891244729963L;

	@Autowired
	private AlunoRepositorio repositorio;

	@Autowired
	private AlunoRepositorioJPA repositorioJpa;
	
	@Override
	public List<Aluno> listarTodos() {
		return this.repositorio.findAll();
	}

	@Override
	public Aluno salvar(Aluno aluno) {
		return this.repositorio.save(aluno);
	}

	@Override
	public void remover(Aluno aluno) {
		this.repositorio.delete(aluno);
	}

	@Override
	public List<Aluno> findByNomeLike(String nome) {
		return this.repositorio.findByNomeLike(nome);
	}
	
	@Override
	public List<Aluno> findByTurma(Turma turma) {
		return this.repositorio.findByTurma(turma);
	}

	@Override
	public List<Aluno> pesquisar(Aluno entity) {
		return repositorioJpa.findByNomeLike(entity);
	}
}