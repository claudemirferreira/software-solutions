package br.ss.authenticator.model.dao;

import javax.ejb.Local;

import br.ss.authenticator.model.entity.Sistema;
import br.ss.core.model.dao.IAbstractDAO;

@Local
public interface ISistemaDAO extends IAbstractDAO<Sistema> {

}
