package br.com.ss.portal.model.dao;

import java.util.List;

import br.com.ss.portal.model.entity.Perfil;
import br.com.ss.portal.model.entity.PerfilRotina;

public interface IPerfilRotinaDAO extends IAbstractDAO<PerfilRotina> {
	
	public List<PerfilRotina> searchByEntity(Perfil entity);

}