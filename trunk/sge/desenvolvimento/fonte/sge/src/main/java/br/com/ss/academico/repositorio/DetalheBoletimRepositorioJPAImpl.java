package br.com.ss.academico.repositorio;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ss.academico.dominio.DetalheBoletim;
import br.com.ss.academico.dominio.MediaTurma;
import br.com.ss.academico.dominio.Turma;
import br.com.ss.academico.enumerated.StatusMatricula;
import br.com.ss.core.seguranca.repositorio.RepositorioGenerico;

@SuppressWarnings("unchecked")
@Repository
public class DetalheBoletimRepositorioJPAImpl extends RepositorioGenerico
		implements DetalheBoletimRepositorioJPA {

	@Override
	public List<DetalheBoletim> listaDetalheBoletimPorTurma(Turma turma) {
		String hql = " select detBol from DetalheBoletim detBol "
				+ "join detBol.boletim bol join bol.matricula mat "
				+ "join mat.turma tur " + "where mat.turma = :turma "
				+ "and mat.status = :ativa "
				+ " order by detBol.disciplina.nome ";
		Query query = entityManager.createQuery(hql);
		query.setParameter("turma", turma);
		query.setParameter("ativa", StatusMatricula.ATIVA);

		return query.getResultList();
	}

	@Override
	public List<MediaTurma> listaMediaTurma(Turma turma) {
		String SQL = " select id id_media_turma, disciplina, (media_1 / total) as media1 ,(media_2 / total) as media2 "
				+ " ,(media_3 / total) as media3,(media_4 / total) as media4  "
				+ " from ( select c.id_detalhe_boletim id, d.nome disciplina,  sum(media_1) as media_1, sum(media_2) as media_2, "
				+ " sum(media_3)  as media_3, sum(media_4)  as media_4, "
				+ " (select count(*) from sge.acad_matricula a where a.id_turma = "
				+ turma.getId()
				+ "  ) as total "
				+ " from  sge.acad_matricula a, sge.acad_boletim b, "
				+ " sge.acad_detalhe_boletim c, sge.acad_disciplina d "
				+ " where a.id_turma = "
				+ turma.getId()
				+ " and a.id_matricula = b.id_matricula "
				+ " and b.id_boletim = c.id_boletim "
				+ " and d.id_disciplina = c.id_disciplina "
				+ " group by  nome " + " order by nome " + " ) boletim ";

		List<MediaTurma> resultList = entityManager.createNativeQuery(SQL,
				MediaTurma.class).getResultList();

		return resultList;

	}
}