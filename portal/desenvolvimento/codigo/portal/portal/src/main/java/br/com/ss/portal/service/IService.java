package br.com.ss.portal.service;

import java.util.List;

import br.com.ss.portal.model.entity.AbstractEntity;

public interface IService<T extends AbstractEntity> {

	public abstract void save(T entity);

	public List<T> search(T entity);

	public void remove(T entity);

}