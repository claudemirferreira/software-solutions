package br.ss.authenticator.model.dao.impl;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;

import br.ss.authenticator.model.dao.ISistemaDAO;
import br.ss.authenticator.model.entity.Sistema;
import br.ss.core.annotation.stereotype.DAO;
import br.ss.core.model.dao.impl.AbstractDAO;

@Dependent
@Stateless
@DAO
public class SistemaDAO extends AbstractDAO<Sistema> implements ISistemaDAO {

}
