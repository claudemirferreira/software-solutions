package br.com.ss.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ss.data.entities.Membro;

public interface MembroRepositorio extends JpaRepository<Membro, Integer> {

}
