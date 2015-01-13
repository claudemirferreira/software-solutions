package br.com.ss.academico.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.ss.academico.dominio.QuestaoAvaliacao;
import br.com.ss.academico.repositorio.QuestaoAvaliacaoRepositorioJPA;
import br.com.ss.core.seguranca.repositorio.ServicoImpl;

@Service
public class QuestaoAvaliacaoServicoImpl extends ServicoImpl<QuestaoAvaliacao, Long> implements QuestaoAvaliacaoServico {

	private static final long serialVersionUID = -4305564891244729963L;

	@Autowired
	private QuestaoAvaliacaoRepositorioJPA questaoAvaliacaoRepositorioJPA;


	@Override
	protected JpaRepository<QuestaoAvaliacao, Long> getRepository() {
		return null;
	}

	@Override
	public List<QuestaoAvaliacao> pesquisar(QuestaoAvaliacao entity) {
		return questaoAvaliacaoRepositorioJPA.listarTodos();
	}
}