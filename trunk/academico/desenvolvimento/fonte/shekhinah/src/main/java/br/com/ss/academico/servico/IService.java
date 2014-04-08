package br.com.ss.academico.servico;

import java.util.List;

public interface IService<T> {

	public List<T> listarTodos();

	public List<T> pesquisar(T entity);
	
	public T salvar(T entity);

	public void remover(T entity);
	
}
