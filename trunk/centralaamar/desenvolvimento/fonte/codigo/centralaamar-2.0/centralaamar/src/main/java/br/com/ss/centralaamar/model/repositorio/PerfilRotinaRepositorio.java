package br.com.ss.centralaamar.model.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ss.centralaamar.model.dominio.PerfilRotina;

@Repository
public interface PerfilRotinaRepositorio extends
		JpaRepository<PerfilRotina, Long> {

}