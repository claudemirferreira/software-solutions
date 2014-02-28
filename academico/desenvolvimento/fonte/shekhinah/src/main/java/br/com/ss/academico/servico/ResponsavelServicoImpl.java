package br.com.ss.academico.servico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.academico.dominio.Responsavel;
import br.com.ss.academico.repositorio.ResponsavelRepositorio;

@Service
public class ResponsavelServicoImpl implements ResponsavelServico, Serializable {

	private static final long serialVersionUID = -4305564891244729963L;

	@Autowired
	private ResponsavelRepositorio repositorio;

	@Override
	public List<Responsavel> listarTodos() {
		return this.repositorio.findAll();
	}

	@Override
	public Responsavel salvar(Responsavel responsavel) {
		return this.repositorio.save(responsavel);
	}

	@Override
	public void remover(Responsavel responsavel) {
		this.repositorio.delete(responsavel);
	}

	@Override
	public List<Responsavel> findByNomeLike(String nome) {
		return this.repositorio.findByNomeLike(nome);
	}
}