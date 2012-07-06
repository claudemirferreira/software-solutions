package br.ss.authenticator.service;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ss.authenticator.model.dao.ISistemaDAO;
import br.ss.authenticator.model.entity.Sistema;
import br.ss.core.model.dao.IAbstractDAO;

@ConversationScoped
@Named
public class SistemaService extends GenericService<Sistema> implements Serializable {

	@Inject
	private ISistemaDAO dao;

	
	@Override
	protected IAbstractDAO<Sistema> getDao() {
		return dao;
	}


}
