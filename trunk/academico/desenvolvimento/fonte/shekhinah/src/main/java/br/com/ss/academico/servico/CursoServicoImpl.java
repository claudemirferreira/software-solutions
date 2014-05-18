package br.com.ss.academico.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.ss.academico.dominio.Curso;
import br.com.ss.academico.repositorio.CursoRepositorio;

@Service
public class CursoServicoImpl  extends ServicoImpl<Curso, Long> implements CursoServico {

	private static final long serialVersionUID = -4305564891244729963L;

	@Autowired
	private CursoRepositorio repositorio;

	@Override
	public List<Curso> findByNomeLike(String nome) {
		return this.repositorio.findByNomeLike(nome);
	}

	@Override
	public List<Curso> pesquisar(Curso entity) {
		// FIXME implementar metodo
		return listarTodos();
	}

	@Override
	protected JpaRepository<Curso, Long> getRepository() {
		return repositorio;
	}
}