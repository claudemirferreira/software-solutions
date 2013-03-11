package br.com.ss.centralaamar.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.centralaamar.model.dao.DepartamentoDAO;
import br.com.ss.centralaamar.model.dao.IAbstractDAO;
import br.com.ss.centralaamar.model.entity.Departamento;

@Service
public class DepartamentoService extends BaseService<Departamento> implements
		Serializable, IDepartamentoService {

	private static final long serialVersionUID = -2648654212543476419L;
	
	@Autowired
	private DepartamentoDAO dao;

	@Override
	protected IAbstractDAO<Departamento> getDao() {
		return dao;
	}

}
