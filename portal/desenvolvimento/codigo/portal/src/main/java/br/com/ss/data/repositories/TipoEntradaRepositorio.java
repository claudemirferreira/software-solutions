package br.com.ss.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ss.data.entities.TipoEntrada;

public interface TipoEntradaRepositorio extends
		JpaRepository<TipoEntrada, Integer> {

}
