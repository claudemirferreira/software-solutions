package br.com.ss.academico.servico;

import java.util.Date;
import java.util.List;

import br.com.ss.academico.dominio.Mensalidade;
import br.com.ss.academico.enumerated.StatusPagamento;

public interface MensalidadeServico {

	public List<Mensalidade> listarTodos();

	public Mensalidade salvar(Mensalidade mensalidade);

	public void remover(Mensalidade mensalidade);

	public List<Mensalidade> findByStatusPagamento(
			StatusPagamento statusPagamento, Date dataInicio, Date dataFim);
	
	public List<Mensalidade> findByStatusAndDataVencimento(
			StatusPagamento statusPagamento, Date dataInicio, Date dataFim);

	List<Mensalidade> loadMensalidades(Long idMatricula);
	
}