package br.com.ss.portal.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.portal.model.dao.IAbstractDAO;
import br.com.ss.portal.model.dao.LicenciadoDAO;
import br.com.ss.portal.model.entity.Licenciado;

@Service
public class LicenciadoService extends BaseService<Licenciado> implements
		Serializable, ILicenciadoService {

	private static final long serialVersionUID = -891354654298756024L;
	
	@Autowired
	private LicenciadoDAO dao;

	@Override
	protected IAbstractDAO<Licenciado> getDao() {
		return dao;
	}

}
