package br.com.ss.portal.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.portal.model.dao.IAbstractDAO;
import br.com.ss.portal.model.dao.LicenciadoSistemaDAO;
import br.com.ss.portal.model.entity.LicenciadoSistema;

@Service
public class LicenciadoSistemaService extends BaseService<LicenciadoSistema>
		implements Serializable, ILicenciadoSistemaService {

	private static final long serialVersionUID = -6955081125449654891L;

	@Autowired
	private LicenciadoSistemaDAO dao;

	@Override
	protected IAbstractDAO<LicenciadoSistema> getDao() {
		return dao;
	}

}
