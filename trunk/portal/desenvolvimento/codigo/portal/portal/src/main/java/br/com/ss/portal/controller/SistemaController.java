package br.com.ss.portal.controller;

import java.sql.SQLException;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.ss.portal.model.entity.Sistema;
import br.com.ss.portal.service.IService;
import br.com.ss.portal.service.ISistemaService;

@Component("sistemaController")
@Named
@Scope("session")
public class SistemaController extends GenericBean<Sistema> {

	private static final long serialVersionUID = -587655477044734345L;

	@Autowired
	private ISistemaService service;

	@Override
	protected IService<Sistema> getService() {
		return service;
	}

	public String save() throws SQLException {
		this.entity.setNome(this.entity.getNome().toUpperCase());
		return super.save();

	}

}