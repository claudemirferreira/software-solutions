package br.ss.authenticator.web.managedbean;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ss.authenticator.model.dao.IServicoDAO;
import br.ss.authenticator.model.entity.Servico;
import br.ss.authenticator.service.IService;
import br.ss.core.model.dao.IAbstractDAO;
import br.ss.core.web.managedbean.GenericBean;

@Named
@ConversationScoped
public class ServicoBean extends GenericBean<Servico> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private IServicoDAO dao;
	
	@Inject
	private IService<Servico> service;
	
	
	@Override
	protected void init() {
		this.search();
	}

	@Override
	protected void initEntity() {
		this.entity = new Servico();
		this.search = new Servico();
	}

	@Override
	protected IAbstractDAO<Servico> getDAO() {
		return dao;
	}

	@Override
	protected IService<Servico> getService() {
		return service;
	}
	
}
