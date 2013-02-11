package br.com.ss.centralaamar.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.centralaamar.model.dao.IAbstractDAO;
import br.com.ss.centralaamar.model.dao.PastorDAO;
import br.com.ss.centralaamar.model.entity.Pastor;

@Service
public class PastorService  extends BaseService<Pastor> implements Serializable, IPastorService  {

	@Autowired
	private PastorDAO dao;
	
	
	@Override
	protected IAbstractDAO<Pastor> getDao() {
		return dao;
	}

}
