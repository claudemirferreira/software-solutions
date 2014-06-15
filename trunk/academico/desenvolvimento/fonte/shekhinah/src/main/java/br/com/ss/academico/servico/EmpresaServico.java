package br.com.ss.academico.servico;

import java.util.List;

import br.com.ss.academico.dominio.Empresa;
import br.com.ss.core.seguranca.servico.IService;

public interface EmpresaServico extends IService<Empresa, Long> {

	public List<Empresa> findByNomeLike(String nome);
	
	public Empresa findOne(Long primaryKey);

}