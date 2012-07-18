package br.ss.authenticator.service.impl;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ss.authenticator.model.dao.IResponsabilidadeDAO;
import br.ss.authenticator.model.entity.Responsabilidade;
import br.ss.authenticator.service.BaseService;
import br.ss.core.model.dao.IAbstractDAO;

@ConversationScoped
@Named
public class ResponsabilidadeService extends BaseService<Responsabilidade> implements Serializable {

	@Inject
	private IResponsabilidadeDAO dao;
	
	@Override
	protected IAbstractDAO<Responsabilidade> getDao() {
		return dao;
	}
	

}
