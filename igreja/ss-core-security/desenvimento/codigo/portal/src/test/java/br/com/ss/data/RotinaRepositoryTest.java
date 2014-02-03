package br.com.ss.data;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.ss.data.entities.Perfil;
import br.com.ss.data.entities.Rotina;
import br.com.ss.data.repositories.RotinaRepositorio;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/applicationContext.xml")
// @ContextConfiguration(locations="classpath:META-INF/test-context.xml")
public class RotinaRepositoryTest {

	@Autowired
	RotinaRepositorio repository;

	@Test
	@Ignore
	public void test() {
		Perfil perfil = new Perfil();
		perfil.setId(1);

		List<Rotina> rotinas = repository.findByPerfil(1);

		for (Rotina rotina : rotinas) {
			System.out.println(rotina.getNome());

		}
	}

}
