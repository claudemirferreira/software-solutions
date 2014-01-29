package br.com.ss.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ss.data.entities.Rotina;
import br.com.ss.data.entities.Sistema;

public interface RotinaRepositorio extends JpaRepository<Rotina, Integer> {

	@Query("select u from Rotina u where u.sistema = :sistema ")
	public List<Rotina> findBySistema(@Param("sistema") Sistema sistema);

	@Query("select u from Rotina u where u.sistema = :sistema and u.nome like :nome")
	public List<Rotina> findBySistemaByNomeLike(
			@Param("sistema") Sistema sistema, @Param("nome") String nome);

}
