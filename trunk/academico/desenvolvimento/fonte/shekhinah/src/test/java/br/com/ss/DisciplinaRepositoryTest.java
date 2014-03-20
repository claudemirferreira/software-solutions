package br.com.ss;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.ss.academico.dominio.Disciplina;
import br.com.ss.academico.repositorio.DisciplinaRepositorioSql;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/spring/applicationContext.xml")
// @ContextConfiguration(locations="classpath*:META-INF/spring/test-context.xml")
// @Ignore
public class DisciplinaRepositoryTest {

	@Autowired
	DisciplinaRepositorioSql repository;

	@Test
	public void test() {
		System.out.println("1");
		List<Disciplina> lista = repository.listaDisciplinaPorCurso(1l);
		for (Disciplina disciplina : lista) {
			System.out.println(disciplina.getNome());
		}

		System.out.println("2");
	}

}