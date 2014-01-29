package br.com.ss.data;

import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.ss.data.entities.Usuario;
import br.com.ss.data.repositories.UsuarioRepositorio;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/applicationContext.xml")
// @ContextConfiguration(locations="classpath:META-INF/test-context.xml")
public class UsuarioRepositoryTest {

	@Autowired
	UsuarioRepositorio repository;

	@Test
	@Ignore
	public void test() {
		Usuario usuario = new Usuario();

		usuario.setCpf("60735090220");
		usuario.setNome("CLAUDEMIR RAMOS FERREIRA");
		usuario.setEmail("claudemirramosferreira@gmail.com");
		usuario.setSenha("admin");
		usuario.setStatus("A");

		repository.save(usuario);

		Usuario dbpost = repository.findOne(usuario.getId());
		assertNotNull(dbpost);
		System.out.println(dbpost.getNome());
	}

}
