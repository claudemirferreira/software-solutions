package br.com.ss.portal.model.dao;

import java.util.List;

import br.com.ss.portal.model.entity.Sistema;
import br.com.ss.portal.model.entity.Usuario;

public interface ISistemaDAO extends IAbstractDAO<Sistema> {
	
	public List<Sistema> listarSistemaPorUsuario(Usuario usuario);

}