package br.com.ss.academico.servico;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.ss.academico.dominio.Mensalidade;
import br.com.ss.academico.enumerated.StatusPagamento;
import br.com.ss.academico.repositorio.MensalidadeRepositorio;

@Service
public class MensalidadeServicoImpl extends ServicoImpl<Mensalidade, Long> implements MensalidadeServico {

	private static final long serialVersionUID = -4305564891244729963L;

	@Autowired
	private MensalidadeRepositorio repositorio;

	@Override
	protected JpaRepository<Mensalidade, Long> getRepository() {
		return repositorio;
	}
	
	@Override
	public List<Mensalidade> findByStatusPagamento(
			StatusPagamento statusPagamento, Date dataInicio, Date dataFim) {
		
		// FIXME refazer a pesquisa usando hql ..
		
		return this.repositorio.findByStatusAndDataPagamento(statusPagamento,
				dataInicio, dataFim);
	}

	@Override
	public List<Mensalidade> findByStatusAndDataVencimento(
			StatusPagamento statusPagamento, Date dataInicio, Date dataFim) {
		return this.repositorio.findByStatusAndDataVencimento(statusPagamento,
				dataInicio, dataFim);
	}

	@Override
	public List<Mensalidade> loadMensalidades( Long idMatricula ) {
		return this.repositorio.loadMensalidades(idMatricula);
	}

	@Override
	public List<Mensalidade> pesquisar(Mensalidade entity) {
		return null;
	}

}