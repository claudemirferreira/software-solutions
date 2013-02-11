package br.com.ss.portal.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.portal.model.dao.IAbstractDAO;
import br.com.ss.portal.model.dao.RotinaDAO;
import br.com.ss.portal.model.entity.Rotina;

@Service
public class RotinaService extends BaseService<Rotina> implements Serializable,
		IRotinaService {

	private static final long serialVersionUID = -7847845212726167939L;

	@Autowired
	private RotinaDAO dao;

	@Override
	protected IAbstractDAO<Rotina> getDao() {
		return dao;
	}

}
