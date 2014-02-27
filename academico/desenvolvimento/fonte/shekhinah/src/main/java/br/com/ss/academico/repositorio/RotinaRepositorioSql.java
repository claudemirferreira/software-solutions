package br.com.ss.academico.repositorio;

import java.util.List;

import br.com.ss.academico.dominio.Rotina;

public interface RotinaRepositorioSql {

	public List<Rotina> listaRotinasPorPerfil(Long id);

}
