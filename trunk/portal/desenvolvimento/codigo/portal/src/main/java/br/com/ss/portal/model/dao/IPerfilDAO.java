package br.com.ss.portal.model.dao;

import java.util.List;

import br.com.ss.portal.model.entity.Perfil;
import br.com.ss.portal.model.entity.Sistema;
import br.com.ss.portal.model.entity.Usuario;

public interface IPerfilDAO extends IAbstractDAO<Perfil> {

	public abstract List<Perfil> searchByEntity(Sistema sistema);
	
	public List<Perfil> listarPerfilPorUsuarioSistema(Sistema sistema,
			Usuario usuario);

}