package br.com.ss.centralaamar.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.centralaamar.model.dao.GrupoDAO;
import br.com.ss.centralaamar.model.dao.IAbstractDAO;
import br.com.ss.centralaamar.model.entity.Grupo;

@Service
public class GrupoService extends BaseService<Grupo> implements Serializable,
		IGrupoService {

	@Autowired
	private GrupoDAO dao;

	@Override
	protected IAbstractDAO<Grupo> getDao() {
		return dao;
	}

}
