package br.com.ss.academico.servico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.academico.dominio.Boletim;
import br.com.ss.academico.repositorio.BoletimRepositorio;

@Service
public class BoletimServicoImpl implements BoletimServico, Serializable {

	private static final long serialVersionUID = -4305564891244729963L;

	@Autowired
	private BoletimRepositorio repositorio;

	@Override
	public List<Boletim> listarTodos() {
		return this.repositorio.findAll();
	}

	@Override
	public Boletim salvar(Boletim boletim) {
		return this.repositorio.save(boletim);
	}

	@Override
	public void remover(Boletim curso) {
		this.repositorio.delete(curso);
	}
	
}