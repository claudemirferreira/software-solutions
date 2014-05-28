package br.com.ss.academico.servico;

import java.util.Date;
import java.util.List;

import br.com.ss.academico.dominio.Mensalidade;
import br.com.ss.academico.enumerated.StatusPagamento;
import br.com.ss.academico.enumerated.TipoPesquisaData;

public interface MensalidadeServico extends IService<Mensalidade, Long>{

	List<Mensalidade> pesquisar(Mensalidade entidade, Date dataInicio, Date dataFim, TipoPesquisaData tipoPesquisaData);
	
	
	public List<Mensalidade> findByStatusAndDataVencimento(
			StatusPagamento statusPagamento, Date dataInicio, Date dataFim);

	List<Mensalidade> loadMensalidades(Long idMatricula);
	
}