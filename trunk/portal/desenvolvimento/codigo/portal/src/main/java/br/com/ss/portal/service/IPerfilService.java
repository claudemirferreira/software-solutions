package br.com.ss.portal.service;

import java.util.List;

import br.com.ss.portal.model.entity.Perfil;
import br.com.ss.portal.model.entity.Sistema;

public interface IPerfilService extends IService<Perfil> {
	
	public abstract List<Perfil> searchByEntity(Sistema entity);

}