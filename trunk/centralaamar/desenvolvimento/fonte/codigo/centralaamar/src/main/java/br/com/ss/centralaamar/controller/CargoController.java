package br.com.ss.centralaamar.controller;

import java.sql.SQLException;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ss.centralaamar.model.entity.Cargo;
import br.com.ss.centralaamar.service.ICargoService;
import br.com.ss.centralaamar.service.IService;

@Controller
@Named
@Scope("session")
public class CargoController extends GenericBean<Cargo> {

	private static final long serialVersionUID = 6031730028318406591L;

	@Autowired
	private ICargoService service;

	@Override
	protected IService<Cargo> getService() {
		return service;
	}

	public String save() throws SQLException {
		this.entity.setNome(this.entity.getNome().toUpperCase());
		return super.save();

	}

}