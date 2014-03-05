package br.com.ss.academico.servico;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.academico.dominio.Aluno;
import br.com.ss.academico.dominio.Matricula;
import br.com.ss.academico.dominio.Turma;
import br.com.ss.academico.enumerated.StatusMatricula;
import br.com.ss.academico.repositorio.MatriculaRepositorio;

@Service
public class MatriculaServicoImpl implements MatriculaServico, Serializable {

	private static final long serialVersionUID = -4305564891244729963L;

	@Autowired
	private MatriculaRepositorio repositorio;

	@Override
	public List<Matricula> listarTodos() {
		return this.repositorio.findAll();
	}

	@Override
	public Matricula salvar(Matricula matricula) {
		return this.repositorio.save(matricula);
	}

	@Override
	public void remover(Matricula matricula) {
		this.repositorio.delete(matricula);
	}

	@Override
	public List<Matricula> findByAluno(Aluno aluno) {
		return this.repositorio.findByAluno(aluno);
	}

	@Override
	public Long countVagasDisponiveis(Turma turma) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		Long count = repositorio.countMatriculas(turma, StatusMatricula.ATIVA, cal.get(Calendar.YEAR));
		return turma.getNumeroVagas() - count;
	}

}