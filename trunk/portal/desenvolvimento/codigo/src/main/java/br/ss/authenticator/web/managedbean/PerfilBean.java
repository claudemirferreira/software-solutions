package br.ss.authenticator.web.managedbean;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ss.authenticator.model.dao.IPerfilDAO;
import br.ss.authenticator.model.entity.Perfil;
import br.ss.authenticator.service.IService;
import br.ss.core.model.dao.IAbstractDAO;
import br.ss.core.web.managedbean.GenericBean;

@Named
@ConversationScoped
public class PerfilBean extends GenericBean<Perfil> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private IPerfilDAO dao;
	
	@Inject
	private IService<Perfil> service;
	
	@Override
	public void init() {
		this.search();
	}

	@Override
	protected void initEntity() {
		this.entity = new Perfil();
		this.search = new Perfil();
	}

	@Override
	protected IAbstractDAO<Perfil> getDAO() {
		return dao;
	}

	@Override
	protected IService<Perfil> getService() {
		return service;
	}
	
}
