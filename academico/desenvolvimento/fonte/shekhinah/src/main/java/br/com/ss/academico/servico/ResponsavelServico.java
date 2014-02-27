package br.com.ss.academico.servico;

import java.util.List;

import br.com.ss.academico.dominio.Responsavel;

public interface ResponsavelServico {

	public List<Responsavel> listarTodos();

	public Responsavel salvar(Responsavel responsavel);

	public void remover(Responsavel responsavel);

	public List<Responsavel> findByNomeLike(String nome);

}