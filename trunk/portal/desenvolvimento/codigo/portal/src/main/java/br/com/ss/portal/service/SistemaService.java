package br.com.ss.portal.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.portal.model.dao.IAbstractDAO;
import br.com.ss.portal.model.dao.SistemaDAO;
import br.com.ss.portal.model.entity.Sistema;

@Service
public class SistemaService extends BaseService<Sistema> implements
		Serializable, ISistemaService {

	private static final long serialVersionUID = -7847845212726167939L;

	@Autowired
	private SistemaDAO dao;

	@Override
	protected IAbstractDAO<Sistema> getDao() {
		return dao;
	}

}
