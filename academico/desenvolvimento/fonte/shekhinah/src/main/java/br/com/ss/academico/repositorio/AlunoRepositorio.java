package br.com.ss.academico.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ss.academico.dominio.Aluno;
import br.com.ss.academico.dominio.Turma;

@Repository
public interface AlunoRepositorio extends JpaRepository<Aluno, Long> {

	@Query("select u from Aluno u where u.nome like :nome")
	public List<Aluno> findByNomeLike(@Param("nome") String nome);

	@Query(" select a from Aluno a join a.matriculas m join m.turma t"
			+ " where t = :turma ")
	public List<Aluno> findByTurma(@Param("turma") Turma turma);

}