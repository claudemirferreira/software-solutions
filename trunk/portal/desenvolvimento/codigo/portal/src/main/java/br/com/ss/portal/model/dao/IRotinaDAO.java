package br.com.ss.portal.model.dao;

import java.util.List;

import br.com.ss.portal.model.entity.Perfil;
import br.com.ss.portal.model.entity.Rotina;

public interface IRotinaDAO extends IAbstractDAO<Rotina> {

	public List<Rotina> searchRotinasDisponivel(Perfil entity);
	
	public List<Rotina> listarRotinaPorPerfil(Perfil perfil);

}