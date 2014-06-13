package br.com.ss.academico.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.ss.academico.dominio.Boletim;
import br.com.ss.academico.dominio.Disciplina;
import br.com.ss.academico.dominio.Matricula;
import br.com.ss.academico.repositorio.BoletimRepositorio;
import br.com.ss.academico.repositorio.DisciplinaRepositorioSql;
import br.com.ss.core.seguranca.repositorio.ServicoImpl;

@Service
public class BoletimServicoImpl extends ServicoImpl<Boletim, Long> implements
		BoletimServico {

	private static final long serialVersionUID = -4305564891244729963L;

	@Autowired
	private BoletimRepositorio repositorio;

	@Autowired
	private DisciplinaRepositorioSql disciplinaRepositorioSql;

	@Override
	public List<Boletim> listarTodos() {
		return this.repositorio.findAll();
	}

	@Override
	public Boletim salvar(Boletim boletim) {
		return this.repositorio.save(boletim);
	}

	@Override
	public void remover(Boletim curso) {
		this.repositorio.delete(curso);
	}

	@Override
	public List<Boletim> pesquisarBoletim(Matricula matricula) {
		return this.repositorio.pesquisarBoletim(matricula);
	}

	@Override
	public void gerarBoletim(Matricula matricula) {
		Boletim boletim;

		List<Disciplina> disciplinas = disciplinaRepositorioSql
				.listaDisciplinaPorCurso(matricula.getTurma().getCurso()
						.getId());

		for (Disciplina disciplina : disciplinas) {
			// FIXME validar se Boletim está atendendo toda a regra paa o mesmo
			boletim = new Boletim();

			boletim.setMatricula(matricula);
			boletim.setDisciplina(disciplina);

			this.repositorio.save(boletim);
		}
	}

	@Override
	public List<Boletim> pesquisar(Boletim entity) {
		return null;
	}

	@Override
	protected JpaRepository<Boletim, Long> getRepository() {
		return null;
	}
}