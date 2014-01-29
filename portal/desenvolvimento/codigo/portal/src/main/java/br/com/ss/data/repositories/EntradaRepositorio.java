package br.com.ss.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ss.data.entities.Entrada;

public interface EntradaRepositorio extends JpaRepository<Entrada, Integer> {

}
