package br.ss.authenticator.model.dao;

import javax.ejb.Local;

import br.ss.authenticator.model.entity.Servico;
import br.ss.core.model.dao.IAbstractDAO;

@Local
public interface IServicoDAO extends IAbstractDAO<Servico>{

}
