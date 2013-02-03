package br.com.ss.centralaamar.componente.report;

import br.com.ss.centralaamar.model.entity.AbstractEntity;

public interface IReport<T extends AbstractEntity> {
	
	void print();

}
