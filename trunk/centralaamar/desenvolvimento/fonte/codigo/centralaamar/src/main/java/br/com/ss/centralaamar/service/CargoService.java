package br.com.ss.centralaamar.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.centralaamar.model.dao.CargoDAO;
import br.com.ss.centralaamar.model.dao.IAbstractDAO;
import br.com.ss.centralaamar.model.entity.Cargo;

@Service
public class CargoService extends BaseService<Cargo> implements Serializable,
		ICargoService {

	private static final long serialVersionUID = 4815004831238733682L;
	
	@Autowired
	private CargoDAO dao;

	@Override
	protected IAbstractDAO<Cargo> getDao() {
		return dao;
	}

}
