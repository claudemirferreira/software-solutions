package br.com.ss.academico.repositorio;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ss.academico.dominio.Disciplina;

@Repository
public class DisciplinaRepositorioSqlImpl extends RepositorioGenerico implements DisciplinaRepositorioSql {

	@SuppressWarnings("unchecked")
	@Override
	public List<Disciplina> listaDisciplinaPorCurso(Long idCurso) {
		
		String sql = "SELECT d.* FROM acad_disciplina d, acad_curso_disciplina b "
				+ " where d.id_disciplina = b.id_disciplina and b.id_curso = " + idCurso;
		
		System.out.println(sql);
		
		return entityManager.createNativeQuery(sql, Disciplina.class)
				.getResultList();
		
	}

}