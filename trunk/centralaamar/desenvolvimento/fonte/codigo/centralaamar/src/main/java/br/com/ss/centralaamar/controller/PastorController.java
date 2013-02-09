package br.com.ss.centralaamar.controller;

import java.sql.SQLException;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ss.centralaamar.model.entity.Pastor;
import br.com.ss.centralaamar.service.IPastorService;
import br.com.ss.centralaamar.service.IService;

@Controller
@Named
@Scope("session")
public class PastorController extends GenericBean<Pastor> {

	private static final long serialVersionUID = 6031730028318406591L;

	@Autowired
	private IPastorService service;

	@Override
	protected IService<Pastor> getService() {
		return service;
	}

	public String save() throws SQLException {
		this.entity.setNome(this.entity.getNome().toUpperCase());
		return super.save();

	}

}