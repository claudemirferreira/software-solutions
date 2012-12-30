package br.com.ss.centralaamar.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.ss.centralaamar.model.entity.Chamada;

@Component
public class ChamadaDAO extends GenericDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void save(Chamada chamada) {
		entityManager.persist(chamada);
	}

	@Transactional
	public void merge(Chamada chamada) {
		entityManager.merge(chamada);
	}

	@Transactional
	public void remove(Chamada chamada) {

		try {
			Chamada entity = entityManager.merge(chamada);
			entityManager.remove(entity);

		} catch (org.hibernate.exception.ConstraintViolationException e) {
			e.printStackTrace();
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public List<Chamada> list() {
		return entityManager.createQuery("select t from Chamada t ")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Chamada> listPorGrupo(Chamada chamada) {
		StringBuilder hql = new StringBuilder();
		List<String> cond = new ArrayList<String>();
		List<Chamada> listChamada = new ArrayList<Chamada>();

		hql.append("select m from Chamada m ");

		//cond.add(" m.pequenoGrupo = :pg ");
		
		//cond.add(" m.sabado = :sb ");

		String orderby = " order by m.id asc ";
		String generatedHql = generateHql(hql.toString(), cond, orderby);

		System.out.println("sql === " + generatedHql);
		Query q = entityManager.createQuery(generatedHql);

		//q.setParameter("pg", chamada.getPequenoGrupo());
		//q.setParameter("sb", chamada.getSabado());

		listChamada = q.getResultList();
		return listChamada;
	}

}
