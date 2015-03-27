package br.com.ss.academico.servico;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ss.academico.dominio.AvaliacaoEducacaoInfantil;
import br.com.ss.academico.dominio.Boletim;
import br.com.ss.academico.dominio.DetalheBoletim;
import br.com.ss.academico.dominio.Disciplina;
import br.com.ss.academico.dominio.Matricula;
import br.com.ss.academico.dominio.QuestaoAvaliacao;
import br.com.ss.academico.dominio.Turma;
import br.com.ss.academico.enumerated.TipoCurso;
import br.com.ss.academico.repositorio.BoletimRepositorio;
import br.com.ss.academico.repositorio.BoletimRepositorioJPA;
import br.com.ss.academico.repositorio.DisciplinaRepositorioSql;
import br.com.ss.academico.repositorio.QuestaoAvaliacaoRepositorioJPA;
import br.com.ss.core.seguranca.repositorio.ServicoImpl;

@Service
@Transactional
public class BoletimServicoImpl extends ServicoImpl<Boletim, Long> implements BoletimServico {

	private static final long serialVersionUID = -4305564891244729963L;

	@Autowired
	private BoletimRepositorio repositorio;
	
	@Autowired
	private BoletimRepositorioJPA repositorioHql;
	
	@Autowired
	private DisciplinaRepositorioSql disciplinaRepositorioSql;

	@Autowired
	private QuestaoAvaliacaoRepositorioJPA questaoAvaliacaoRepositorioJPA;

	
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
		List<Boletim> boletinsMatricula = this.repositorio.pesquisarBoletim(matricula);
		if (!boletinsMatricula.isEmpty()) {
			// atualiza o boletim adicionando novas materias caso exista
			boletim = boletinsMatricula.get(0);
			
			atualizarDisciplinasBoletim(matricula, boletim);
			
		} else {
			// cria o boletim
			boletim = new Boletim();
			
			boletim.setMatricula(matricula);
			
			if (TipoCurso.ENSINO_FUNDAMENTAL.equals(matricula.getTurma().getCurso().getTipoCurso())) {
				criarDetalhesBoletim(boletim);
			} else {
				criarAvaliacaoEducacaoInfantil(boletim);
			}
			
		}
		
		this.repositorio.save(boletim);
	}

	private void atualizarDisciplinasBoletim(Matricula matricula, Boletim boletim) {
		List<Disciplina> listaDisciplinaPorCurso = disciplinaRepositorioSql.listaDisciplinaPorCurso(matricula.getTurma().getCurso().getId());
		
		for (Disciplina disciplina : listaDisciplinaPorCurso) {
			boolean contains = false;
			
			List<DetalheBoletim> detalhesBol = new ArrayList<DetalheBoletim>( boletim.getDetalheBoletims() );
			for (DetalheBoletim detalhe : detalhesBol ) {
				if (detalhe.getDisciplina().equals(disciplina)) {
					contains = true;
				}
			}
			
			if (!contains) {
				// add a nova disciplina no boletim
				DetalheBoletim det = new DetalheBoletim();
				det.setDisciplina(disciplina);
				det.setBoletim(boletim);
				boletim.getDetalheBoletims().add(det);
			}
			
		}
	}
	
	

	private void criarAvaliacaoEducacaoInfantil(Boletim boletim) {
		for (QuestaoAvaliacao qa : questaoAvaliacaoRepositorioJPA.listarTodos() ) {
			AvaliacaoEducacaoInfantil aei = new AvaliacaoEducacaoInfantil();
			aei.setBoletim(boletim);
			aei.setQuestaoAvaliacao(qa);
			boletim.getAvaliacaoEducacaoInfantils().add(aei);
		}
	}
	

	private void criarDetalhesBoletim(Boletim boletim) {
		List<Disciplina> listaDisciplinaPorCurso = disciplinaRepositorioSql.listaDisciplinaPorCurso(boletim.getMatricula().getTurma().getCurso().getId());
		for (Disciplina disciplina : listaDisciplinaPorCurso) {
			DetalheBoletim det = new DetalheBoletim();
			det.setDisciplina(disciplina);
			det.setBoletim(boletim);
			boletim.getDetalheBoletims().add(det);
		}
	}

	@Override
	public List<Boletim> listBoletimPorTurma(Turma turma) {
		return this.repositorioHql.listaBoletimPorTurma(turma);
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