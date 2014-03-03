package br.com.ss.academico.servico;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.academico.dominio.Mensalidade;
import br.com.ss.academico.enumerated.StatusPagamento;
import br.com.ss.academico.repositorio.MensalidadeRepositorio;

@Service
public class MensalidadeServicoImpl implements MensalidadeServico, Serializable {

	private static final long serialVersionUID = -4305564891244729963L;

	@Autowired
	private MensalidadeRepositorio repositorio;

	@Override
	public List<Mensalidade> listarTodos() {
		return this.repositorio.findAll();
	}

	@Override
	public Mensalidade salvar(Mensalidade mensalidade) {
		return this.repositorio.save(mensalidade);
	}

	@Override
	public void remover(Mensalidade mensalidade) {
		this.repositorio.delete(mensalidade);
	}

	@Override
	public List<Mensalidade> findByStatusPagamento(
			StatusPagamento statusPagamento, Date dataInicio, Date dataFim) {
		return this.repositorio.findByStatusPagamento(statusPagamento,
				dataInicio, dataFim);
	}

}