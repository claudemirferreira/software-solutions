package br.com.ss.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ss.data.entities.Pastor;

public interface PastorRepositorio extends JpaRepository<Pastor, Integer> {

}
