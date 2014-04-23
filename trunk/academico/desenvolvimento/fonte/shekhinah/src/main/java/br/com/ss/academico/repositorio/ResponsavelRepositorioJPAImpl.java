package br.com.ss.academico.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ss.academico.dominio.Responsavel;

@Repository
public class ResponsavelRepositorioJPAImpl implements ResponsavelRepositorioJPA {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@SuppressWarnings("unchecked")
	public List<Responsavel> findByNomeLike(String nome) {
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select res from Responsavel res ");
		sb.append(" where lower( res.nome ) like :nome ");
		
		Query query = entityManager.createQuery(sb.toString());
		query.setParameter("nome", "%" + nome.trim().toLowerCase() + "%" );
		
		return query.getResultList();
	}
	

	@Override
	public Responsavel findByNome(String nome) {
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select res from Responsavel res ");
		sb.append(" where lower( res.nome ) = :nome ");
		
		Query query = entityManager.createQuery(sb.toString());
		query.setParameter("nome", nome.trim().toLowerCase());
		
		return (Responsavel) query.getSingleResult();
	}

}