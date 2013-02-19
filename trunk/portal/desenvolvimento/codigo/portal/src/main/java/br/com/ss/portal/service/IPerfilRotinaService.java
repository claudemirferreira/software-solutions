package br.com.ss.portal.service;

import java.util.List;

import br.com.ss.portal.model.entity.Perfil;
import br.com.ss.portal.model.entity.PerfilRotina;

public interface IPerfilRotinaService extends IService<PerfilRotina> {
	
	public List<PerfilRotina> searchByEntity(PerfilRotina entity);
	
	public List<PerfilRotina> searchByEntity(Perfil entity);

}