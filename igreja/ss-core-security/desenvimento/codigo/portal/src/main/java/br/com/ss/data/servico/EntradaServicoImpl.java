package br.com.ss.data.servico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.data.entities.Entrada;
import br.com.ss.data.repositories.EntradaRepositorio;

@Service
public class EntradaServicoImpl implements EntradaServico, Serializable {

	private static final long serialVersionUID = 2123311057062733602L;
	
	@Autowired
	private EntradaRepositorio EntradaRepositorio;

	@Override
	public List<Entrada> listarTodos() {
		return this.EntradaRepositorio.findAll();
	}

	@Override
	public Entrada salvar(Entrada Entrada) {
		return this.EntradaRepositorio.save(Entrada);
	}

	@Override
	public void remover(Entrada Entrada) {
		this.EntradaRepositorio.delete(Entrada);
	}
}