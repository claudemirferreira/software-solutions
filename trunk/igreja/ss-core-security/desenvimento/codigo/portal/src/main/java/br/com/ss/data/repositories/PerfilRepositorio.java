package br.com.ss.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ss.data.entities.Perfil;

public interface PerfilRepositorio extends JpaRepository<Perfil, Integer> {

}
