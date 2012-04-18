package br.ss.core.web.managedBean;

import java.util.List;

public abstract class GenericBean<T> {

	protected T entity;

	protected List<T> resultList;

	protected abstract void initEntity();
	
	public abstract String persist();

	public abstract String merge();

	public abstract String remove();

	public abstract List<T> search();

	
	/* ------------ Gets/Sets ------------------------ */
	public T getEntity() {
		return this.entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public List<T> getResultList() {
		return this.resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

}
