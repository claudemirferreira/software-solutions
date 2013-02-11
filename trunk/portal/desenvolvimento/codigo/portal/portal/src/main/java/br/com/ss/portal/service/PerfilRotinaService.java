package br.com.ss.portal.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.portal.model.dao.IAbstractDAO;
import br.com.ss.portal.model.dao.PerfilRotinaDAO;
import br.com.ss.portal.model.entity.PerfilRotina;

@Service
public class PerfilRotinaService extends BaseService<PerfilRotina> implements
		Serializable, IPerfilRotinaService {

	private static final long serialVersionUID = -7847845212726167939L;

	@Autowired
	private PerfilRotinaDAO dao;

	@Override
	protected IAbstractDAO<PerfilRotina> getDao() {
		return dao;
	}

}
