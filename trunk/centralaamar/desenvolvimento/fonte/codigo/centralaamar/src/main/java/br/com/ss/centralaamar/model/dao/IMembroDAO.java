package br.com.ss.centralaamar.model.dao;

import java.util.List;

import br.com.ss.centralaamar.model.entity.Membro;;

public interface IMembroDAO extends IAbstractDAO<Membro> {
	
	public List<Membro> listAniversariantes(String dataInicial, String dataFinal, String ano);

}