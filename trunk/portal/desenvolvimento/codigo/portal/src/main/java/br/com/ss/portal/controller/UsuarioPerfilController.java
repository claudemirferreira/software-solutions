package br.com.ss.portal.controller;

import java.sql.SQLException;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.ss.portal.model.entity.UsuarioPerfil;
import br.com.ss.portal.service.IService;
import br.com.ss.portal.service.IUsuarioPerfilService;

@Component("usuarioPerfilController")
@Named
@Scope("session")
public class UsuarioPerfilController extends GenericBean<UsuarioPerfil> {

	@Autowired
	private IUsuarioPerfilService service;

	@Override
	protected IService<UsuarioPerfil> getService() {
		return service;
	}

	public String save() throws SQLException {
		// TODO SETAR OS RELACIONAMENTOS
		return super.save();

	}

}