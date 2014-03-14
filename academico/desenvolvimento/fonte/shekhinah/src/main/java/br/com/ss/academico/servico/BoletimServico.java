package br.com.ss.academico.servico;

import java.util.List;

import br.com.ss.academico.dominio.Boletim;

public interface BoletimServico {

	public List<Boletim> listarTodos();

	public Boletim salvar(Boletim boletim);

	public void remover(Boletim boletim);

}