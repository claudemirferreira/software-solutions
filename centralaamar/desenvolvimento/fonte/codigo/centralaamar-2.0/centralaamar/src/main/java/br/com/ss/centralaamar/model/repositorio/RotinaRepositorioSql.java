package br.com.ss.centralaamar.model.repositorio;

import java.util.List;

import br.com.ss.centralaamar.model.dominio.Rotina;

public interface RotinaRepositorioSql {

	public List<Rotina> listaRotinasPorPerfil(Long id);

}
