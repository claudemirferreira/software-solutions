package br.com.ss;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.ss.academico.dominio.Boletim;
import br.com.ss.academico.dominio.Matricula;
import br.com.ss.academico.repositorio.BoletimRepositorio;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/spring/applicationContext.xml")
// @ContextConfiguration(locations="classpath*:META-INF/spring/test-context.xml")
// @Ignore
public class BoletimRepositoryTest {

	@Autowired
	BoletimRepositorio repository;

//	@Test
	public void test() {
		System.out.println("1");
		Matricula matricula = new Matricula();
		matricula.setIdMatricula(4l);
		
		List<Boletim> lista = repository.pesquisarBoletim(matricula);
		for (Boletim b : lista) {
//			System.out.println(b.getDisciplina().getNome());
		}

		System.out.println("2");
	}
	
//	@Test
	public void salvar(){
//		Boletim boletim = repository.findOne(1l);
//		
//		boletim.setMedia1( (float) 7.0);
//		repository.save(boletim);
//		System.out.println("salvou");
		
	}

}