package br.ss.authenticator.view;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ss.authenticator.model.dao.ISistemaDAO;
import br.ss.authenticator.model.entity.Sistema;
import br.ss.core.model.dao.IAbstractDAO;
import br.ss.core.web.managedbean.GenericBean;

@Named( value="sistemaBean" )
@ConversationScoped
public class SistemaBean extends GenericBean<Sistema> {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ISistemaDAO dao;
	
	@PostConstruct
	public void init() {
		this.search();
	}

	@Override
	protected void initEntity() {
		this.entity = new Sistema();
		this.search = new Sistema();
		this.search.setAtivo(true);
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
