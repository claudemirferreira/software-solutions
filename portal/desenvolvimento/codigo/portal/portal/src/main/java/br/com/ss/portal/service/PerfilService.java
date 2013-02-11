package br.com.ss.portal.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.portal.model.dao.IAbstractDAO;
import br.com.ss.portal.model.dao.PerfilDAO;
import br.com.ss.portal.model.entity.Perfil;

@Service
public class PerfilService extends BaseService<Perfil> implements Serializable,
		IPerfilService {

	private static final long serialVersionUID = -7847845212726167939L;

	@Autowired
	private PerfilDAO dao;

	@Override
	protected IAbstractDAO<Perfil> getDao() {
		return dao;
	}

}
