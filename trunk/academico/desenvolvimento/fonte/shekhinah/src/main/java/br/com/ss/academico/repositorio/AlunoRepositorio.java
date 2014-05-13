package br.com.ss.academico.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ss.academico.dominio.Aluno;
import br.com.ss.academico.dominio.Turma;

@Repository
public interface AlunoRepositorio extends IRepository<Aluno, Long> {

	@Query("select a from Aluno a where a.nome like :nome")
	public List<Aluno> findByNomeLike(@Param("nome") String nome);

	@Query(" select a from Aluno a join a.matriculas m join m.turma t"
			+ " where t = :turma ")
	public List<Aluno> findByTurma(@Param("turma") Turma turma);

//	@Query(	"   select a from Aluno a "
//			+ " where lower( a.nome ) like :lower(aluno.nome) "
//			)
//	public List<Aluno> findByAluno(@Param("aluno") Aluno aluno);
	
//	FIXME: criar consulta com os parametros de aluno..
	
}