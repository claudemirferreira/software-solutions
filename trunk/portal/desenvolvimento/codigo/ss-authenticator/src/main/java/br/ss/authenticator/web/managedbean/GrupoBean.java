package br.ss.authenticator.web.managedbean;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ss.authenticator.model.dao.IGrupoDAO;
import br.ss.authenticator.model.entity.Grupo;
import br.ss.authenticator.service.IService;
import br.ss.core.model.dao.IAbstractDAO;
import br.ss.core.web.managedbean.GenericBean;

@Named
@ConversationScoped
public class GrupoBean extends GenericBean<Grupo> implements Serializable {

	@Inject
	private IGrupoDAO dao;
	
	@Inject
	private IService<Grupo> service;
	
	@Override
	protected void init() {
		this.search();
		
	}

	@Override
	protected void initEntity() {
		this.entity = new Grupo();
		this.search = new Grupo();
		
	}

	@Override
	protected IAbstractDAO<Grupo> getDAO() {
		return dao;
	}

	@Override
	protected IService<Grupo> getService() {
		return service;
	}
	

}
