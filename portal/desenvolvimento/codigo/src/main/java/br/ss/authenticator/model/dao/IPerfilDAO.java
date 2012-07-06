package br.ss.authenticator.model.dao;

import java.util.List;

import javax.ejb.Local;

import br.ss.authenticator.model.entity.Perfil;
import br.ss.authenticator.model.entity.Sistema;
import br.ss.core.model.dao.IAbstractDAO;

@Local
public interface IPerfilDAO extends IAbstractDAO<Perfil> {

	public List<Perfil> searchBySistema( Sistema sistema );
	
}
