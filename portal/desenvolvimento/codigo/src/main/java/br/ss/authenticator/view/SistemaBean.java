package br.ss.authenticator.view;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ss.authenticator.model.dao.ISistemaDAO;
import br.ss.authenticator.model.entity.Sistema;
import br.ss.core.model.dao.IAbstractDAO;
import br.ss.core.web.managedbean.GenericBean;

@Named
@ConversationScoped
public class SistemaBean extends GenericBean<Sistema> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ISistemaDAO dao;

	@Override
	public void init() {
		this.search();
	}

	@Override
	protected void initEntity() {
		this.entity = new Sistema();
		this.search = new Sistema();
	}

	@Override
	protected IAbstractDAO<Sistema> getDAO() {
		return dao;
	}
	
	@Override
	public void search() {
		this.resultList = dao.searchByEntity(search);
	}

}
