package br.ss.authenticator.web.managedbean;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ss.authenticator.model.dao.IResponsabilidadeDAO;
import br.ss.authenticator.model.entity.Responsabilidade;
import br.ss.authenticator.service.IService;
import br.ss.core.model.dao.IAbstractDAO;
import br.ss.core.web.managedbean.GenericBean;

@Named
@ConversationScoped
public class ResponsabilidadeBean extends GenericBean<Responsabilidade> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private IResponsabilidadeDAO dao;
	
	@Inject
	private IService<Responsabilidade> service;
	
	@Override
	public void init() {
		this.search();
	}

	@Override
	protected void initEntity() {
		this.entity = new Responsabilidade();
		this.search = new Responsabilidade();
	}

	@Override
	protected IAbstractDAO<Responsabilidade> getDAO() {
		return dao;
	}

	@Override
	protected IService<Responsabilidade> getService() {
		return service;
	}
	
}
