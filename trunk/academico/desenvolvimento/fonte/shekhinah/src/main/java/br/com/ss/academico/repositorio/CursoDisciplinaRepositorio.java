package br.com.ss.academico.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ss.academico.dominio.Curso;
import br.com.ss.academico.dominio.CursoDisciplina;
import br.com.ss.academico.dominio.Disciplina;

@Repository
public interface CursoDisciplinaRepositorio extends
		JpaRepository<CursoDisciplina, Long> {

	@Query("select up from CursoDisciplina up "
			+ " where up.cursoDisciplinaPk.curso = :curso "
			+ " and up.cursoDisciplinaPk.disciplina = :disciplina ")
	public CursoDisciplina findByCursoAndDisciplina(
			@Param("curso") Curso curso,
			@Param("disciplina") Disciplina disciplina);

	@Query("select up from CursoDisciplina up "
			+ " where up.cursoDisciplinaPk.curso = :curso ")
	public List<CursoDisciplina> findByCurso(@Param("curso") Curso curso);

}