package br.ss.authenticator.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import br.ss.authenticator.model.dao.IPerfilDAO;
import br.ss.authenticator.model.dao.ISistemaDAO;
import br.ss.authenticator.model.entity.Perfil;
import br.ss.authenticator.model.entity.Sistema;
import br.ss.authenticator.service.BaseService;
import br.ss.core.model.dao.IAbstractDAO;

@ConversationScoped
@Named
public class PerfilService extends BaseService<Perfil> implements Serializable {

	@Inject
	private IPerfilDAO dao;
	
	@Override
	protected IAbstractDAO<Perfil> getDao() {
		return dao;
	}
	

}
