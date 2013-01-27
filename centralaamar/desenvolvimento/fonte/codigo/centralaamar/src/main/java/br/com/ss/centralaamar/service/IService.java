package br.com.ss.centralaamar.service;

import java.util.List;

import br.com.ss.centralaamar.model.entity.AbstractEntity;

public interface IService<T extends AbstractEntity> {

	public abstract void save( T entity );

	public List<T> search( T entity );
	
	public void remove( T entity );
	
	
}