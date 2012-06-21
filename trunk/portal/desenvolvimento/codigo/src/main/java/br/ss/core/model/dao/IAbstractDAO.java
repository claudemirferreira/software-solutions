package br.ss.core.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import br.ss.core.model.entity.AbstractEntity;

public interface IAbstractDAO<T extends AbstractEntity> extends Serializable {

	public abstract T getByPrimaryKey(AbstractEntity entity);

	public abstract T getByPrimaryKey(Long id);

	public abstract T merge(T entity);

	public abstract void remove(T entity);

	public abstract void remove(Long id);

	public abstract void persist(T entity);
	
	public abstract void saveOrUpdate(T entity);

	public abstract List<T> listAll();
}