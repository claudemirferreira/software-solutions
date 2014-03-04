package br.com.ss.academico.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ss.academico.dominio.Aluno;
import br.com.ss.academico.dominio.Matricula;
import br.com.ss.academico.dominio.Turma;
import br.com.ss.academico.enumerated.StatusMatricula;

@Repository
public interface MatriculaRepositorio extends JpaRepository<Matricula, Long> {

	@Query("select mat from Matricula mat where mat.aluno = :aluno")
	public List<Matricula> findByAluno( @Param("aluno") Aluno aluno );
	
	/*
	 SELECT count(mat.id_matricula)
FROM acad_matricula mat
join acad_turma tur on tur.id_turma = mat.id_turma
where mat.id_turma = 1  -- TURMA
and mat.status = 0      -- ATIVO
and tur.ano = 2014      -- ANO
;
	*/
	@Query( " select count(mat.idMatricula) "
			+ "from Matricula mat "
			+ "join mat.turma tur "
			+ "where mat.turma = :turma "
			+ "and mat.status = :status "
			+ "and tur.ano = :ano " )
	public Integer countMatriculas( @Param("turma") Turma turma, 
									@Param("status") StatusMatricula status,
									@Param("ano") Integer ano );
	
	
}