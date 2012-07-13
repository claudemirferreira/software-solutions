package br.ss.authenticator.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import br.ss.authenticator.model.dao.IServicoDAO;
import br.ss.authenticator.model.entity.Servico;
import br.ss.authenticator.service.BaseService;
import br.ss.core.model.dao.IAbstractDAO;

@ConversationScoped
@Named
public class ServicoService extends BaseService<Servico> implements Serializable{

	@Inject
	private IServicoDAO servicoDao;
	
	@Override
	protected IAbstractDAO<Servico> getDao() {
		return servicoDao;
	}
	
	@Produces @Named 
	public List<Servico> servicosAtivos() {
		Servico ser = new Servico();
		ser.setAtivo(true);	// somente ativos
		return servicoDao.searchByEntity(ser);
	}

	
}
