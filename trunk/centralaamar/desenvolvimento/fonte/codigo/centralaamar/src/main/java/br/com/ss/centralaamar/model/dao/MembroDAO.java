package br.com.ss.centralaamar.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ss.centralaamar.component.Relatorio;
import br.com.ss.centralaamar.model.entity.Membro;
import br.com.ss.centralaamar.model.entity.PequenoGrupo;
import br.com.ss.centralaamar.model.entity.Sabado;

@Repository
public class MembroDAO extends AbstractDAO<Membro> implements IMembroDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Membro> searchByEntity(Membro entity) {
		StringBuilder s = new StringBuilder();
		List<String> condictions = new ArrayList<String>();
		
		s.append(" select m from Membro m ");
		if ( notEmpty(entity.getNome()) ) {
			condictions.add(" lower(m.nome) like :nome ");
		}
		if ( notEmpty(entity.getMae()) ) {
			condictions.add(" lower(m.mae) like :mae ");
		}
		if ( notEmpty(entity.getPai()) ) {
			condictions.add(" lower(m.pai) like :pai ");
		}
		if ( notEmpty(entity.getBatizado()) ) {
			condictions.add(" m.batizado = :batizado ");
		}
		
		// TODO demais campos
		
//		if ( notEmpty(entity.getDataBatismo() ) ) {
//			condictions.add(" m.dataBatismo) like :dataBatismo ");
//		}
		
		String orderBy = " order by m.nome ";
		
		Query q = this.entityManager.createQuery( generateHql(s.toString(), condictions) + orderBy );
		
		if ( notEmpty(entity.getNome() ) ) {
			q.setParameter("nome", "%" + entity.getNome().trim().toLowerCase() + "%" );
		}
		if ( notEmpty(entity.getNome() ) ) {
			q.setParameter("mae", "%" + entity.getMae().trim().toLowerCase() + "%" );
		}
		if ( notEmpty(entity.getNome() ) ) {
			q.setParameter("pai", "%" + entity.getPai().trim().toLowerCase() + "%" );
		}
		
		return q.getResultList();
	}

	/* ---------------- */
	@SuppressWarnings("unchecked")
	public List<Membro> listAniversariantes(String dataInicial, String dataFinal, String ano) {
		String sql = Relatorio.montarSql(dataInicial, dataFinal, ano);
		List<Membro> membros = entityManager.createQuery(sql).getResultList();
		return membros;
	}

	@SuppressWarnings("unchecked")
	public List<Membro> listPais(String sexo) {
		String sql = Relatorio.montarSqlPais(sexo);
		List<Membro> membros = entityManager.createQuery(sql).getResultList();
		return membros;
	}

	@SuppressWarnings("unchecked")
	public List<Membro> listPesquisa(Membro membro) {
		StringBuilder hql = new StringBuilder();
		List<String> cond = new ArrayList<String>();

		hql.append("select m from Membro m ");
		if (notEmpty(membro.getPequenoGrupo())) {
			hql.append(" join m.pequenoGrupo pg ");
		}
		if (notEmpty(membro.getProfissao())) {
			hql.append(" join m.profissao prof ");
		}
		if (notEmpty(membro.getNome())) {
			cond.add(" upper(m.nome) like :nome ");
		}
		if (notEmpty(membro.getPequenoGrupo())) {
			cond.add(" m.pequenoGrupo = :pg ");
		}
		if (notEmpty(membro.getProfissao())) {
			cond.add(" m.profissao = :prof ");
		}
		String orderby = " order by m.nome asc ";
		String generatedHql = generateHql(hql.toString(), cond, orderby);

		System.out.println("sql === " + generatedHql);
		Query q = entityManager.createQuery(generatedHql);
		if (notEmpty(membro.getNome())) {
			q.setParameter("nome", "'%" + membro.getNome().toUpperCase() + "%'");
		}
		if (notEmpty(membro.getPequenoGrupo())) {
			q.setParameter("pg", membro.getPequenoGrupo());
		}
		if (notEmpty(membro.getProfissao())) {
			q.setParameter("prof", membro.getProfissao());
		}
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Membro> listPorGrupo(PequenoGrupo pequenoGrupo, Sabado sabado) {
		StringBuilder hql = new StringBuilder();
		List<String> cond = new ArrayList<String>();
		List<Membro> listMembro = new ArrayList<Membro>();
		
		hql.append(" select m from Membro m ");
		hql.append(" where m.pequenoGrupo = :pg" );
		hql.append(" and m not in (  ");
		hql.append(" 	select c.membro from Chamada c");
		hql.append(" 	where c.sabado = :sab ");
		hql.append(" 	and c.pequenoGrupo = :pg ");
		hql.append(" ) ");

		String generatedHql = generateHql(hql.toString(), cond, null);
		System.out.println("sql ===================================================== " );
		System.out.println("sql == " + generatedHql);
		System.out.println("sql ===================================================== " );
		Query q = entityManager.createQuery(generatedHql);

		q.setParameter("pg", pequenoGrupo);
		q.setParameter("sab", sabado);
		
		listMembro = q.getResultList();
		return listMembro;
	}

}
