package br.com.ss.portal.controller;

import java.sql.SQLException;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.ss.portal.model.entity.PerfilRotina;
import br.com.ss.portal.service.IPerfilRotinaService;
import br.com.ss.portal.service.IService;

@Component("perfilRotinaController")
@Named
@Scope("session")
public class PerfilRotinaController extends GenericBean<PerfilRotina> {

	private static final long serialVersionUID = -7056309405822788580L;
	
	@Autowired
	private IPerfilRotinaService service;

	@Override
	protected IService<PerfilRotina> getService() {
		return service;
	}

	public String save() throws SQLException {
		//TODO SETAR OS RELACIONAMENTO
		return super.save();

	}

}