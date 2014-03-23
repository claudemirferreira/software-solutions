package br.com.ss.centralaamar.model.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.ss.centralaamar.model.dominio.Rotina;

@Repository
public class RotinaRepositorioSqlImpl implements RotinaRepositorioSql {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Rotina> listaRotinasPorPerfil(Long id) {
		return entityManager
				.createNativeQuery(
						"SELECT i.* FROM saa_rotina i, saa_perfil_rotina b where i.id_rotina = b.id_rotina and id_perfil = "
								+ id, Rotina.class).getResultList();

	}
}