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

	// @Query("select u from Rotina u where u.sistema = :sistema and u.nome like :nome")
	@Query(value = " SELECT r.* FROM portal.saa_rotina r, portal.saa_perfil_rotina b where r.id = b.rotina_id and b.perfil_id = :perfil_id ", nativeQuery = true)
	public List<Rotina> findByPerfil(@Param("perfil_id") int perfil_id);

}
