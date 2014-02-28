package br.com.ss.academico.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ss.academico.dominio.Matricula;

@Repository
public interface MatriculaRepositorio extends JpaRepository<Matricula, Long> {

}