package br.com.ss.centralaamar.service;

import java.util.List;

import br.com.ss.centralaamar.model.entity.Membro;

public interface IMembroService extends IService<Membro> {

	public List<Membro> listAniversariantes(String dataInicial,
			String dataFinal, String ano);

}