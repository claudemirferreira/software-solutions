package br.com.ss.portal.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.portal.model.dao.AvisoDAO;
import br.com.ss.portal.model.dao.IAbstractDAO;
import br.com.ss.portal.model.entity.Aviso;

@Service
public class AvisoService extends BaseService<Aviso> implements Serializable,
		IAvisoService {

	private static final long serialVersionUID = -6305330440962568351L;

	@Autowired
	private AvisoDAO dao;

	@Override
	protected IAbstractDAO<Aviso> getDao() {
		return dao;
	}

}
