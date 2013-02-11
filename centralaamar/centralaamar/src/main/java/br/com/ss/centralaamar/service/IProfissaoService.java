package br.com.ss.centralaamar.service;

import br.com.ss.centralaamar.model.entity.Profissao;

public interface IProfissaoService extends IService<Profissao> {
	
	public Profissao getByPrimaryKey(Profissao entity);

}