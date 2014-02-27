package br.com.ss.academico.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ss.academico.dominio.Responsavel;

@Repository
public interface ResponsavelRepositorio extends
		JpaRepository<Responsavel, Integer> {

	@Query("select u from Responsavel u where u.nome like :nome")
	public List<Responsavel> findByNomeLike(@Param("nome") String nome);

}