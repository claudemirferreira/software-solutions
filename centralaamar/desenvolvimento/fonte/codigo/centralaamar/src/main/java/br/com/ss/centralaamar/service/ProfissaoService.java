package br.com.ss.centralaamar.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.centralaamar.model.dao.IAbstractDAO;
import br.com.ss.centralaamar.model.dao.ProfissaoDAO;
import br.com.ss.centralaamar.model.entity.Profissao;

@Service
public class ProfissaoService  extends BaseService<Profissao> implements Serializable, IProfissaoService  {

	@Autowired
	private ProfissaoDAO dao;
	
	
	@Override
	protected IAbstractDAO<Profissao> getDao() {
		return dao;
	}

}
