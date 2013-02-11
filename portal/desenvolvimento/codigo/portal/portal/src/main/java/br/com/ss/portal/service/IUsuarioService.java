package br.com.ss.portal.service;

import br.com.ss.portal.model.entity.Usuario;

public interface IUsuarioService extends IService<Usuario> {

	public Usuario logar(Usuario usuario);

}