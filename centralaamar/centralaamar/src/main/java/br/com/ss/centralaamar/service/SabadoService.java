package br.com.ss.centralaamar.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.centralaamar.model.dao.IAbstractDAO;
import br.com.ss.centralaamar.model.dao.SabadoDAO;
import br.com.ss.centralaamar.model.entity.Sabado;

@Service
public class SabadoService extends BaseService<Sabado> implements Serializable,
		ISabadoService {

	@Autowired
	private SabadoDAO dao;

	@Override
	protected IAbstractDAO<Sabado> getDao() {
		return dao;
	}

}
