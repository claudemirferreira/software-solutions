package br.com.ss.portal.model.dao;

import br.com.ss.portal.model.entity.Usuario;

public interface IUsuarioDAO extends IAbstractDAO<Usuario> {
	
	public Usuario logar(Usuario usuario);

}