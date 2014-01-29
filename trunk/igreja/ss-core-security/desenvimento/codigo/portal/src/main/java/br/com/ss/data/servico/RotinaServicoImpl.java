package br.com.ss.data.servico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.data.entities.Rotina;
import br.com.ss.data.repositories.RotinaRepositorio;

@Service
public class RotinaServicoImpl implements RotinaServico, Serializable {

	private static final long serialVersionUID = -7015513743088712661L;
	
	@Autowired
	private RotinaRepositorio rotinaRepositorio;

	@Override
	public List<Rotina> listarTodos() {
		return this.rotinaRepositorio.findAll();
	}

	@Override
	public Rotina salvar(Rotina rotina) {
		return this.rotinaRepositorio.save(rotina);
	}

	@Override
	public void remover(Rotina rotina) {
		this.rotinaRepositorio.delete(rotina);

	}
}
