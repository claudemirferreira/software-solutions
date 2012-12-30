package br.com.ss.centralaamar.component;

import br.com.ss.centralaamar.model.entity.Membro;
import br.com.ss.centralaamar.model.entity.PequenoGrupo;
import br.com.ss.centralaamar.model.entity.Profissao;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Membro m = new Membro();
		m.setNome("AD");
		Profissao p = new Profissao();
		p.setId(1);
		m.setProfissao(p);
		PequenoGrupo pg = new PequenoGrupo();
		pg.setId(1);
		m.setPequenoGrupo(pg);
		
		String sql = "select t from membro t where nome like '%AD%' ";
		
		if(m.getProfissao() != null)
			sql = sql + " AND pro_id = 1 ";
		if(m.getPequenoGrupo() != null)
			sql = sql + " AND peq_id = 1 ";
		System.out.println("sql == "+sql);

	}

}
