package br.com.ss.academico.servico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.academico.dominio.Curso;
import br.com.ss.academico.repositorio.CursoRepositorio;

@Service
public class CursoServicoImpl implements CursoServico, Serializable {

	private static final long serialVersionUID = -4305564891244729963L;

	@Autowired
	private CursoRepositorio repositorio;

	@Override
	public List<Curso> listarTodos() {
		return this.repositorio.findAll();
	}

	@Override
	public Curso salvar(Curso curso) {
		return this.repositorio.save(curso);
	}

	@Override
	public void remover(Curso curso) {
		this.repositorio.delete(curso);
	}

	@Override
	public List<Curso> findByNomeLike(String nome) {
		return this.repositorio.findByNomeLike(nome);
	}
}