package br.ss.core.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;


public class Selection<R, D extends AbstractDAO<?,?>> {

	private D dao;
	
	private javax.persistence.criteria.Selection<R> selected;
	
	public Selection( D  dao ) {
		this.dao = dao;
	}
	
	public D select() {
		dao.select();
		return dao;
	}

	@SuppressWarnings("unchecked")
	public Selection<R, D> count() {
		selected = (Expression<R>) dao.getDelegate().getBuilder().count( dao.getDelegate().getRootOrJoin() );
		return this;
	}
	
	public CriteriaBuilder customizedAsClass() {
		return dao.getDelegate().getBuilder();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void set( javax.persistence.criteria.Selection ex ) {
		selected = ex;
	}
	
	public javax.persistence.criteria.Selection<R> get() {
		return selected;
	}
	
}
