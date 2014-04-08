package br.com.ss.academico.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.ss.academico.dominio.Disciplina;

@Repository
public class CursoDisciplinaRepositorioSqlImpl implements
		CursoDisciplinaRepositorioSql {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Disciplina> listaDisciplinaNotInCurso(Long idCurso) {
		return entityManager.createNativeQuery(
				"select p.* from acad_disciplina p "
						+ "where p.id_disciplina not in ( "
						+ "select id_disciplina from acad_curso_disciplina up "
						+ "where up.id_curso = " + idCurso + ")",
				Disciplina.class).getResultList();

	}
}