package br.com.ss.portal.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.ss.portal.component.Status;
import br.com.ss.portal.model.entity.Sistema;
import br.com.ss.portal.model.entity.Usuario;
import br.com.ss.portal.service.IService;
import br.com.ss.portal.service.ISistemaService;
import br.com.ss.portal.service.IUsuarioService;

@Component("usuarioController")
@Named
@Scope("session")
public class UsuarioController extends GenericBean<Usuario> {

	private static final long serialVersionUID = 140215018150690453L;

	@Autowired
	private IUsuarioService service;
	@Autowired
	private ISistemaService sistemaService;

	@Getter
	@Setter
	private List<Sistema> sistemas = new ArrayList<Sistema>();

	@Getter
	@Setter
	private List<Status> listStatus = Status.list();

	@Override
	protected IService<Usuario> getService() {
		return service;
	}

	public String save() throws SQLException {
		this.entity.setNome(this.entity.getNome().toUpperCase());
		return super.save();
	}

	public String logar() throws SQLException {
		this.search = service.logar(this.search);
		if (this.search.getIdUsuario() < 1)
			return "login.xhtml";
		else {

			this.sistemas = sistemaService.search(new Sistema());
			return "home.xhtml";
		}

	}

}