package br.com.ss.data.servico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.data.entities.Sistema;
import br.com.ss.data.repositories.SistemaRepositorio;

@Service
public class SistemaServicoImpl implements SistemaServico, Serializable {

	private static final long serialVersionUID = -2207760247399955070L;
	
	@Autowired
	private SistemaRepositorio rotinaRepositorio;

	@Override
	public List<Sistema> listarTodos() {
		return this.rotinaRepositorio.findAll();
	}

	@Override
	public Sistema salvar(Sistema sistema) {
		return this.rotinaRepositorio.save(sistema);
	}

	@Override
	public void remover(Sistema sistema) {
		this.rotinaRepositorio.delete(sistema);

	}
}
