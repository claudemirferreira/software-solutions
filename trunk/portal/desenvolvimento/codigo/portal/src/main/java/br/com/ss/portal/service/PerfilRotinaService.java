package br.com.ss.portal.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.portal.model.dao.IAbstractDAO;
import br.com.ss.portal.model.dao.PerfilRotinaDAO;
import br.com.ss.portal.model.entity.Perfil;
import br.com.ss.portal.model.entity.PerfilRotina;
import br.com.ss.portal.model.entity.Sistema;
import br.com.ss.portal.model.entity.Usuario;

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

	public List<PerfilRotina> searchByEntity(PerfilRotina entity) {
		return dao.searchByEntity(entity);
	}

	@Override
	public List<PerfilRotina> searchByEntity(Perfil entity) {
		return dao.searchByEntity(entity);
	}

	@Override
	public List<Object[]> searchPerfilUsuario(Usuario usuario, Sistema sistema) {
		List<Object[]> list = dao.searchPerfilUsuario(usuario, sistema);
		return list;
	}

}
