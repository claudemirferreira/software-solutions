package br.com.ss.portal.service;

import java.util.List;

import br.com.ss.portal.model.entity.Perfil;
import br.com.ss.portal.model.entity.Sistema;
import br.com.ss.portal.model.entity.Usuario;

public interface IPerfilService extends IService<Perfil> {
	
	public abstract List<Perfil> searchByEntity(Sistema entity);
	
	public abstract List<Perfil> listarPerfilPorUsuarioSistema(Sistema sistema,
			Usuario usuario);
	
}