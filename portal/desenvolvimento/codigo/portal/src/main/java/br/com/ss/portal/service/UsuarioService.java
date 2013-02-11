package br.com.ss.portal.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.portal.model.dao.IAbstractDAO;
import br.com.ss.portal.model.dao.UsuarioDAO;
import br.com.ss.portal.model.entity.Usuario;

@Service
public class UsuarioService extends BaseService<Usuario> implements
		Serializable, IUsuarioService {

	private static final long serialVersionUID = -6176591009061829492L;

	@Autowired
	private UsuarioDAO dao;

	@Override
	protected IAbstractDAO<Usuario> getDao() {
		return dao;
	}

	@Override
	public Usuario logar(Usuario usuario) {
		return dao.logar(usuario);
	}

}
