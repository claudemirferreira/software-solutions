package br.com.ss.academico.servico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ss.academico.dominio.Empresa;
import br.com.ss.academico.repositorio.EmpresaRepositorio;

@Service
public class EmpresaServicoImpl implements EmpresaServico, Serializable {

	private static final long serialVersionUID = -4305564891244729963L;

	@Autowired
	private EmpresaRepositorio repositorio;

	@Override
	public List<Empresa> listarTodos() {
		return this.repositorio.findAll();
	}

	@Override
	public Empresa salvar(Empresa empresa) {
		return this.repositorio.save(empresa);
	}

	@Override
	public void remover(Empresa empresa) {
		this.repositorio.delete(empresa);
	}

	@Override
	public List<Empresa> findByNomeLike(String nome) {
		return this.repositorio.findByNomeLike(nome);
	}

	@Override
	public Empresa findOne(Long primaryKey) {
		return this.repositorio.findOne(primaryKey);
	}

}