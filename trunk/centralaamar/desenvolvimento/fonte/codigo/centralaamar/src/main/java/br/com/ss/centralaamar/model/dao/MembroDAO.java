package br.com.ss.centralaamar.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.ss.centralaamar.component.Relatorio;
import br.com.ss.centralaamar.model.entity.Membro;
import br.com.ss.centralaamar.model.entity.Pastor;
import br.com.ss.centralaamar.model.entity.PequenoGrupo;
import br.com.ss.centralaamar.model.entity.Sabado;

@Component
public class MembroDAO extends AbstractDAO<Membro> implements IMembroDAO {

//	@PersistenceContext
//	private EntityManager entityManager;
//
//	@Transactional
//	public void save(Membro membro) {
//		entityManager.persist(membro);
//	}
//
//	@Transactional
//	public void merge(Membro membro) {
//		entityManager.merge(membro);
//	}
//
//	@Transactional
//	public void remove(Membro membro) {
//		Membro entity = entityManager.merge(membro);
//		entityManager.remove(entity);
//	}
//
//	@SuppressWarnings("unchecked")
//	public List<Membro> list() {
//		return entityManager.createQuery(
//				"select t from Membro t order by t.nome").getResultList();
//	}

	@SuppressWarnings("unchecked")
	public List<Membro> listAniversariantes(String dataInicial,
			String dataFinal, String ano) {
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


	@Override
	public List<Membro> searchByEntity(Membro entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
