package br.com.ss.centralaamar.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.centralaamar.model.dao.IAbstractDAO;
import br.com.ss.centralaamar.model.dao.PequenoGrupoDAO;
import br.com.ss.centralaamar.model.entity.PequenoGrupo;

@Service(value="pequenoGrupoService")
public class PequenoGrupoService extends BaseService<PequenoGrupo> implements Serializable, IPequenoGrupoService  {

	@Autowired
	private PequenoGrupoDAO dao;
	
	
	@Override
	protected IAbstractDAO<PequenoGrupo> getDao() {
		return dao;
	}

}
