package br.com.ss.academico.servico;

import java.util.Date;
import java.util.List;

import br.com.ss.academico.dominio.Mensalidade;
import br.com.ss.academico.enumerated.StatusPagamento;

public interface MensalidadeServico extends IService<Mensalidade, Long>{

	public List<Mensalidade> findByStatusPagamento(
			StatusPagamento statusPagamento, Date dataInicio, Date dataFim);
	
	public List<Mensalidade> findByStatusAndDataVencimento(
			StatusPagamento statusPagamento, Date dataInicio, Date dataFim);

	List<Mensalidade> loadMensalidades(Long idMatricula);
	
}