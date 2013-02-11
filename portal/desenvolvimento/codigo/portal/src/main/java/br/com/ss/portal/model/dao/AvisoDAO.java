package br.com.ss.portal.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ss.portal.model.entity.Aviso;

@Repository
public class AvisoDAO extends AbstractDAO<Aviso> implements IAvisoDAO {

	private static final long serialVersionUID = -3469551348229335626L;

	@Override
	public Aviso getByPrimaryKey(Aviso entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Aviso> searchByEntity(Aviso entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
