package br.com.ss.academico.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ss.academico.dominio.DetalheBoletim;
import br.com.ss.academico.dominio.MediaTurma;
import br.com.ss.academico.dominio.Turma;
import br.com.ss.academico.repositorio.DetalheBoletimRepositorio;
import br.com.ss.academico.repositorio.DetalheBoletimRepositorioJPA;
import br.com.ss.core.seguranca.repositorio.ServicoImpl;

@Service
@Transactional
public class DetalheBoletimServicoImpl extends
		ServicoImpl<DetalheBoletim, Long> implements DetalheBoletimServico {

	private static final long serialVersionUID = 6473690277745525039L;

	@Autowired
	private DetalheBoletimRepositorio repositorio;

	@Autowired
	private DetalheBoletimRepositorioJPA repositorioHql;

	@Override
	public List<DetalheBoletim> listarTodos() {
		return this.repositorio.findAll();
	}

	@Override
	public void remover(DetalheBoletim curso) {
		this.repositorio.delete(curso);
	}

	@Override
	protected JpaRepository<DetalheBoletim, Long> getRepository() {
		return repositorio;
	}

	@Override
	public List<DetalheBoletim> pesquisar(DetalheBoletim entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DetalheBoletim> listDetalheBoletimPorTurma(Turma turma) {
		return repositorioHql.listaDetalheBoletimPorTurma(turma);
	}

	@Override
	public List<MediaTurma> listaMediaTurma(Turma turma) {
		return repositorioHql.listaMediaTurma(turma);
	}
}