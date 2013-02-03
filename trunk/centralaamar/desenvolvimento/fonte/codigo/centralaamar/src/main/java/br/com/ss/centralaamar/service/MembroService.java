package br.com.ss.centralaamar.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.centralaamar.model.dao.IAbstractDAO;
import br.com.ss.centralaamar.model.dao.MembroDAO;
import br.com.ss.centralaamar.model.entity.Membro;

@Service
public class MembroService  extends BaseService<Membro> implements Serializable, IMembroService  {

	@Autowired
	private MembroDAO dao;
	
	
	@Override
	protected IAbstractDAO<Membro> getDao() {
		return dao;
	}
	
	public List<Membro> listAniversariantes(String dataInicial, String dataFinal, String ano){
		return dao.listAniversariantes(dataInicial, dataFinal, ano);
	}

}
