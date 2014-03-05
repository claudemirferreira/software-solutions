package br.com.ss.academico.servico;

import java.util.List;

import br.com.ss.academico.dominio.Empresa;

public interface EmpresaServico {

	public List<Empresa> listarTodos();

	public Empresa salvar(Empresa empresa);

	public void remover(Empresa empresa);

	public List<Empresa> findByNomeLike(String nome);
	
	public Empresa findOne(Long primaryKey);

}