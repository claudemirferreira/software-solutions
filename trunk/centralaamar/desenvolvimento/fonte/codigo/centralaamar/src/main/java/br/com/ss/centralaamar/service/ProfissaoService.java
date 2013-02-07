package br.com.ss.centralaamar.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.centralaamar.model.dao.IAbstractDAO;
import br.com.ss.centralaamar.model.dao.ProfissaoDAO;
import br.com.ss.centralaamar.model.entity.Profissao;

@Service
public class ProfissaoService  extends BaseService<Profissao> implements Serializable, IProfissaoService  {

	private static final long serialVersionUID = -4537153933201915492L;
	
	@Autowired
	private ProfissaoDAO dao;
	
	
	@Override
	protected IAbstractDAO<Profissao> getDao() {
		return dao;
	}
	
	@Override
	public Profissao getByPrimaryKey(Profissao entity) {
		return dao.getByPrimaryKey(entity);
	}

}
