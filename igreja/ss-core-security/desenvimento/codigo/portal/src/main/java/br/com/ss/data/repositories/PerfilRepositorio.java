package br.com.ss.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ss.data.entities.Perfil;
import br.com.ss.data.entities.Sistema;

public interface PerfilRepositorio extends JpaRepository<Perfil, Integer> {

	@Query("select u from Perfil u where u.sistema = :sistema ")
	public List<Perfil> findBySistema(@Param("sistema") Sistema sistema);

	@Query("select u from Perfil u where u.sistema = :sistema and u.nome like :nome")
	public List<Perfil> findBySistemaByNomeLike(
			@Param("sistema") Sistema sistema, @Param("nome") String nome);

}