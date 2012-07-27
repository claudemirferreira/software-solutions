package br.ss.authenticator.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import br.ss.authenticator.model.dao.IGrupoDAO;
import br.ss.authenticator.model.entity.Grupo;
import br.ss.authenticator.service.BaseService;
import br.ss.core.model.dao.IAbstractDAO;

@Named
@ConversationScoped
public class GrupoService extends BaseService<Grupo> implements Serializable{

	@Inject
	private IGrupoDAO grupoDAO;
	
	@Override
	protected IAbstractDAO<Grupo> getDao() {
		return grupoDAO;
	}
	
	@Produces @Named 
	public List<Grupo> gruposAtivos() {
		Grupo grupo = new Grupo();
		grupo.setAtivo(true);	// somente ativos
		return grupoDAO.searchByEntity(grupo);
	}

}
