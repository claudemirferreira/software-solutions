package br.com.ss.centralaamar.model.dao;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericDAO {

	public boolean notEmpty( Object obj ) {
		return obj != null;
	}
	
	public boolean notEmpty( String str ) {
		return str != null && str.length() > 0;
	}
	
	protected String generateHql(String select, List<String> cond, String orderby) {
		StringBuilder hql = new StringBuilder();
		hql.append(select);
		boolean addWhere = false;
		for ( String str : cond ) {
			if ( !addWhere) {
				addWhere = true;
				hql.append(" where " + str);
			} else {
				hql.append(" and " + str);
			}
		}
		if ( notEmpty(orderby) ) {
			hql.append(orderby);
		}
		return hql.toString();
	}
	
	
	public static void main(String[] args) {
		StringBuilder hql = new StringBuilder();
		List<String> cond = new ArrayList<String>();

		hql.append( "select m from Membro m ");
			hql.append(" join m.pequenoGrupo pg ");
			hql.append(" join m.profissao prof ");
			cond.add(" upper(m.nome) like :nome ");
			cond.add(" m.pequenoGrupo = :pg ");
			cond.add(" m.profissao = :prof ");
		String orderby = " order by m.nome asc ";
		
		MembroDAO dao = new MembroDAO();
		System.out.println( dao.generateHql(hql.toString(), cond, orderby) );
		
	}
	
	
}
