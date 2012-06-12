package br.ss.authenticator.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ss.authenticator.business.service.SistemaService;
import br.ss.authenticator.model.dao.SistemaDAO;
import br.ss.authenticator.model.entity.Sistema;
import br.ss.core.web.managedBean.GenericBean;

@ConversationScoped
@Named
public class SistemaBean extends GenericBean<Sistema> implements Serializable {

	@Inject
	private SistemaService dsl;
	
	@Inject
	private SistemaDAO dao;

	@Inject
	private SistemaService service;
	
	public SistemaBean() {
		 resultList = dao.obterAllSistemas();
	}

	@Override
	protected void initEntity() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Sistema> search() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String remove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save() {
		dsl.salvar();
		return PESQUISA;
	}

}
