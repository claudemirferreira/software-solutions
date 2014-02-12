package br.com.ss.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ss.data.entities.Membro;

public interface MembroRepositorio extends JpaRepository<Membro, Integer> {

	@Query("select m from Membro m where lower(m.nome) like lower(:nome) ")
	public List<Membro> findByNomeLike(@Param("nome") String nome);

	@Query("select m from Membro m where m.sexo = :sexo ")
	public List<Membro> findBySexo(@Param("sexo") String nome);
	
}
