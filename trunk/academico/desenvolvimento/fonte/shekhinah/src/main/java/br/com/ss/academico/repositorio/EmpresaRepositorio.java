package br.com.ss.academico.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ss.academico.dominio.Empresa;

@Repository
public interface EmpresaRepositorio extends JpaRepository<Empresa, Long> {

	@Query("select u from Empresa u where u.nome like :nome")
	public List<Empresa> findByNomeLike(@Param("nome") String nome);

}