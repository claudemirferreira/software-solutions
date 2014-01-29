package br.com.ss.data.servico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.data.entities.TipoEntrada;
import br.com.ss.data.repositories.TipoEntradaRepositorio;

@Service
public class TipoEntradaServicoImpl implements TipoEntradaServico, Serializable {

	private static final long serialVersionUID = 2123311057062733602L;
	
	@Autowired
	private TipoEntradaRepositorio tipoEntradaRepositorio;

	@Override
	public List<TipoEntrada> listarTodos() {
		return this.tipoEntradaRepositorio.findAll();
	}

	@Override
	public TipoEntrada salvar(TipoEntrada tipoEntrada) {
		return this.tipoEntradaRepositorio.save(tipoEntrada);
	}

	@Override
	public void remover(TipoEntrada tipoEntrada) {
		this.tipoEntradaRepositorio.delete(tipoEntrada);

	}
}