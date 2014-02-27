package br.com.ss;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.ss.academico.dominio.Perfil;
import br.com.ss.academico.repositorio.PerfilRotinaRepositorio;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/spring/applicationContext.xml")
// @ContextConfiguration(locations="classpath:META-INF/test-context.xml")
@Ignore
public class PerfilRotinaRepositoryTest {

	@Autowired
	PerfilRotinaRepositorio repository;

	@Test
	public void test() {
//		Perfil p = new Perfil();
//		p.setIdPerfil(1l);
//
//		Rotina rot = new Rotina();
//		rot.setIdRotina(1l);
//
//		PerfilRotinaPk pk = new PerfilRotinaPk();
//		pk.setPerfil(p);
//		pk.setRotina(rot);
//
//		PerfilRotina up = new PerfilRotina();
//		up.setPerfilRotinaPk(pk);
//
//		repository.delete(up);

	}
}
