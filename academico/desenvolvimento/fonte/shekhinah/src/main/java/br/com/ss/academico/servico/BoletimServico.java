package br.com.ss.academico.servico;

import java.util.List;

import br.com.ss.academico.dominio.Boletim;
import br.com.ss.academico.dominio.Matricula;

public interface BoletimServico {

	public List<Boletim> listarTodos();

	public Boletim salvar(Boletim boletim);

	public void remover(Boletim boletim);
	
	public List<Boletim> pesquisarBoletim(Matricula matricula);
	
	public void gerarBoletim(Matricula matricula);

}