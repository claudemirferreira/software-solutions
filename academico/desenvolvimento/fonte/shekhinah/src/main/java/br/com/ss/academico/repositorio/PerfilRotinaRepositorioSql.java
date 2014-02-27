package br.com.ss.academico.repositorio;

import java.util.List;

import br.com.ss.academico.dominio.Rotina;

public interface PerfilRotinaRepositorioSql {

	public List<Rotina> listaRotinaNotInPerfil(Long idPerfil);

}