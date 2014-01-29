package br.com.ss.data.servico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.data.entities.Saida;
import br.com.ss.data.repositories.SaidaRepositorio;

@Service
public class SaidaServicoImpl implements SaidaServico, Serializable {

	private static final long serialVersionUID = -1030189936970812400L;
	
	@Autowired
	private SaidaRepositorio SaidaRepositorio;

	@Override
	public List<Saida> listarTodos() {
		return this.SaidaRepositorio.findAll();
	}

	@Override
	public Saida salvar(Saida Saida) {
		return this.SaidaRepositorio.save(Saida);
	}

	@Override
	public void remover(Saida Saida) {
		this.SaidaRepositorio.delete(Saida);

	}
}