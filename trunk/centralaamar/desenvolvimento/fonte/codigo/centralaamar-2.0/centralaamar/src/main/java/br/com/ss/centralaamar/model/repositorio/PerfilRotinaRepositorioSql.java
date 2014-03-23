package br.com.ss.centralaamar.model.repositorio;

import java.util.List;

import br.com.ss.centralaamar.model.dominio.Rotina;

public interface PerfilRotinaRepositorioSql {

	public List<Rotina> listaRotinaNotInPerfil(Long idPerfil);

}