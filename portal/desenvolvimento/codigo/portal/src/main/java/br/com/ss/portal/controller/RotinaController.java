package br.com.ss.portal.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.ss.portal.model.entity.Rotina;
import br.com.ss.portal.model.entity.Sistema;
import br.com.ss.portal.service.IRotinaService;
import br.com.ss.portal.service.IService;
import br.com.ss.portal.service.ISistemaService;

@Component("rotinaController")
@Named
@Scope("session")
public class RotinaController extends GenericBean<Rotina> {

	private static final long serialVersionUID = 5368939191352412350L;

	@Getter
	@Setter
	private Sistema sistema = new Sistema();

	@Getter
	@Setter
	private List<Sistema> sistemas;

	@Autowired
	private IRotinaService service;

	@Autowired
	private ISistemaService sistemaService;

	@Override
	protected IService<Rotina> getService() {
		return service;
	}

	public String save() throws SQLException {

		if (this.sistema.getIdSistema() != 0)
			this.entity.setSistema(this.sistema);

		this.entity.setNome(this.entity.getNome().toUpperCase());
		return super.save();

	}

	public String cadastrar() throws InstantiationException,
			IllegalAccessException {
		this.sistemas = sistemaService.search(sistema);
		return super.cadastrar();
	}

	@PostConstruct
	protected void setup() throws InstantiationException,
			IllegalAccessException {
		this.sistemas = sistemaService.search(sistema);
		super.setup();

	}

	@Override
	public String editar(Rotina entity) {
		this.sistemas = new ArrayList<Sistema>();
		this.sistemas.add(entity.getSistema());
		return super.editar(entity);
	}

	public void search() {
		if (this.sistema.getIdSistema() != null)
			this.search.setSistema(this.sistema);
		super.search();
	}

}