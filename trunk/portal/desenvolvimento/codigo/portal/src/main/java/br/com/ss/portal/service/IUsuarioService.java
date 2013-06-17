package br.com.ss.portal.service;

import java.util.List;

import br.com.ss.portal.model.entity.Sistema;
import br.com.ss.portal.model.entity.Usuario;

public interface IUsuarioService extends IService<Usuario> {

	public Usuario logar(Usuario usuario);
	
	public List<Sistema> listarSistemaPorUsuario(Usuario usuario);

}