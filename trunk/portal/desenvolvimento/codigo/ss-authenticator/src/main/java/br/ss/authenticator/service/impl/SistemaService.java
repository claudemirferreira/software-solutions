package br.ss.authenticator.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import br.ss.authenticator.model.dao.ISistemaDAO;
import br.ss.authenticator.model.entity.Sistema;
import br.ss.authenticator.service.BaseService;
import br.ss.core.model.dao.IAbstractDAO;

@ConversationScoped
@Named
public class SistemaService extends BaseService<Sistema> implements Serializable {

	@Inject
	private ISistemaDAO sistemaDao;
	
	@Override
	protected IAbstractDAO<Sistema> getDao() {
		return sistemaDao;
	}
	
	@Produces @Named 
	public List<Sistema> sistemasAtivos() {
		Sistema sis = new Sistema();
		sis.setAtivo(true);	// somente ativos
		return sistemaDao.searchByEntity( sis );
	}


}
