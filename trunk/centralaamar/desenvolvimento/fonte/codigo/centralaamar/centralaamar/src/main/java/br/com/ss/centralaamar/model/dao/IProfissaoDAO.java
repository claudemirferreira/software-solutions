package br.com.ss.centralaamar.model.dao;

import br.com.ss.centralaamar.model.entity.Profissao;

public interface IProfissaoDAO extends IAbstractDAO<Profissao> {

	public abstract Profissao getByPrimaryKey(Profissao entity);

}