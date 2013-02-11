package br.com.ss.centralaamar.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.centralaamar.model.dao.ChamadaDAO;
import br.com.ss.centralaamar.model.dao.IAbstractDAO;
import br.com.ss.centralaamar.model.entity.Chamada;

@Service
public class ChamadaService extends BaseService<Chamada> implements Serializable,
		IChamadaService {

	@Autowired
	private ChamadaDAO dao;

	@Override
	protected IAbstractDAO<Chamada> getDao() {
		return dao;
	}

}
