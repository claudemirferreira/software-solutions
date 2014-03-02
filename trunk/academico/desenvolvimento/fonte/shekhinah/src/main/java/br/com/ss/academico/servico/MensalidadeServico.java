package br.com.ss.academico.servico;

import java.util.List;

import br.com.ss.academico.dominio.Mensalidade;

public interface MensalidadeServico {

	public List<Mensalidade> listarTodos();

	public Mensalidade salvar(Mensalidade mensalidade);

	public void remover(Mensalidade mensalidade);

}