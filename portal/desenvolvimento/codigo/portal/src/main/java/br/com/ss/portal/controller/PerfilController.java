package br.com.ss.portal.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.ss.portal.model.entity.Perfil;
import br.com.ss.portal.model.entity.Sistema;
import br.com.ss.portal.service.IPerfilService;
import br.com.ss.portal.service.IService;
import br.com.ss.portal.service.ISistemaService;

@Component("perfilController")
@Named
@Scope("session")
public class PerfilController extends GenericBean<Perfil> {

	private static final long serialVersionUID = 557784413066030360L;

	private Sistema sistema = new Sistema();

	private List<Sistema> sistemas;

	@Autowired
	private IPerfilService service;

	@Autowired
	private ISistemaService sistemaService;

	@Override
	protected IService<Perfil> getService() {
		return service;
	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	public List<Sistema> getSistemas() {
		return sistemas;
	}

	public void setSistemas(List<Sistema> sistemas) {
		this.sistemas = sistemas;
	}

	public ISistemaService getSistemaService() {
		return sistemaService;
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
	public String editar(Perfil entity) {
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