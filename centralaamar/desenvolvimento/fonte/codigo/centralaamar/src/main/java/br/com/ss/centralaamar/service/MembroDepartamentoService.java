package br.com.ss.centralaamar.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.centralaamar.model.dao.IAbstractDAO;
import br.com.ss.centralaamar.model.dao.MembroDepartamentoDAO;
import br.com.ss.centralaamar.model.entity.MembroDepartamento;

@Service
public class MembroDepartamentoService extends BaseService<MembroDepartamento>
		implements Serializable, IMembroDepartamentoService {

	private static final long serialVersionUID = 2951353191123654260L;

	@Autowired
	private MembroDepartamentoDAO dao;

	@Override
	protected IAbstractDAO<MembroDepartamento> getDao() {
		return dao;
	}

}
