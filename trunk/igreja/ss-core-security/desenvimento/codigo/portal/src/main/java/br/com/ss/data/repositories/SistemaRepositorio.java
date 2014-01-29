package br.com.ss.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ss.data.entities.Sistema;

public interface SistemaRepositorio extends JpaRepository<Sistema, Integer> {

}
