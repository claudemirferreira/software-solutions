package br.com.ss;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.ss.academico.dominio.Aluno;
import br.com.ss.academico.dominio.Matricula;
import br.com.ss.academico.dominio.Mensalidade;
import br.com.ss.academico.enumerated.StatusPagamento;
import br.com.ss.academico.repositorio.MensalidadeRepositorio;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/spring/applicationContext.xml")
// @ContextConfiguration(locations="classpath*:META-INF/spring/test-context.xml")
//@Ignore
public class MensalidadeRepositoryTest {

	@Autowired
	MensalidadeRepositorio repository;

	@Test
	public void test() {

		Matricula m = new Matricula();
		m.setIdMatricula(1l);
		Aluno a = new Aluno();
		a.setIdAluno(1l);

		Mensalidade u = new Mensalidade();
		u.setMatricula(m);
		u.setDataVencimento(new Date());
		u.setDataPagamento(new Date());
		u.setValorVencimento(222);
		u.setSequencial(1);
		u.setStatusPagamento(StatusPagamento.PENDENTE);

		repository.save(u);

	}

}