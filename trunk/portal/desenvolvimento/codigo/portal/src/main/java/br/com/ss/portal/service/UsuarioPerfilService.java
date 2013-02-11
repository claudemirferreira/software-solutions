package br.com.ss.portal.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.portal.model.dao.IAbstractDAO;
import br.com.ss.portal.model.dao.UsuarioPerfilDAO;
import br.com.ss.portal.model.entity.UsuarioPerfil;

@Service
public class UsuarioPerfilService extends BaseService<UsuarioPerfil> implements
		Serializable, IUsuarioPerfilService {

	private static final long serialVersionUID = -1426596076748086566L;
	
	@Autowired
	private UsuarioPerfilDAO dao;

	@Override
	protected IAbstractDAO<UsuarioPerfil> getDao() {
		return dao;
	}

}
