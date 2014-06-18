package br.com.ss.academico.servico;

import java.util.Date;
import java.util.List;

import br.com.ss.academico.dominio.Mensalidade;
import br.com.ss.academico.enumerated.StatusPagamento;
import br.com.ss.academico.enumerated.TipoPesquisaData;
import br.com.ss.core.seguranca.servico.IService;

public interface MensalidadeServico extends IService<Mensalidade, Long>{

	List<Mensalidade> pesquisar(Mensalidade entidade, Date dataInicio, Date dataFim, TipoPesquisaData tipoPesquisaData);
	
	List<Mensalidade> findByStatusAndDataVencimento(StatusPagamento statusPagamento, Date dataInicio, Date dataFim);

	List<Mensalidade> loadMensalidades(Long idMatricula);

	List<Mensalidade> listarMensalidadesEmAtraso();
	
}