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

import br.com.ss.portal.model.entity.Perfil;
import br.com.ss.portal.model.entity.PerfilRotina;
import br.com.ss.portal.model.entity.Rotina;
import br.com.ss.portal.model.entity.Sistema;
import br.com.ss.portal.service.IPerfilRotinaService;
import br.com.ss.portal.service.IPerfilService;
import br.com.ss.portal.service.IRotinaService;
import br.com.ss.portal.service.IService;
import br.com.ss.portal.service.ISistemaService;

@Component("perfilRotinaController")
@Named
@Scope("session")
public class PerfilRotinaController extends GenericBean<PerfilRotina> {

	private static final long serialVersionUID = -7056309405822788580L;

	@Getter
	@Setter
	private Sistema sistema;

	@Getter
	@Setter
	private Perfil perfil;

	@Getter
	@Setter
	private Sistema searchSistema;

	@Getter
	@Setter
	private Rotina rotina;

	@Getter
	@Setter
	private List<Sistema> sistemas = new ArrayList<Sistema>();

	@Getter
	@Setter
	private List<Perfil> perfils = new ArrayList<Perfil>();

	@Getter
	@Setter
	private List<Rotina> rotinas = new ArrayList<Rotina>();

	@Autowired
	private IPerfilRotinaService service;

	@Autowired
	private IRotinaService rotinaService;

	@Autowired
	private IPerfilService perfilService;

	@Autowired
	private ISistemaService sistemaService;

	@Override
	protected IService<PerfilRotina> getService() {
		return service;
	}

	public String save() throws SQLException {

		this.entity.setPerfil(this.perfil);
		this.entity.setRotina(this.rotina);
		super.save();
		
		this.resultList = getService().search(entity);
		
		return null;

	}

	@PostConstruct
	protected void setup() throws InstantiationException,
			IllegalAccessException {
		this.sistema = new Sistema();
		this.perfil = new Perfil();
		this.rotina = new Rotina();

		this.sistemas = sistemaService.search(sistema);
		this.perfils = perfilService.search(perfil);
		this.rotinas = rotinaService.search(rotina);

		super.setup();

	}

	public void search() {
		this.perfils = perfilService.searchByEntity(searchSistema);
	}

	public String editar(Perfil entity) {
		this.perfil = entity;
		this.rotinas = rotinaService.searchRotinasDisponivel(this.perfil);
		
		this.resultList = service.searchByEntity(entity);
		String url = "/pages/"
				+ this.entity.getClass().getSimpleName().toLowerCase() + "/search.jsf";
		System.out.println("url == " + url );
		return url;
	}
	
	public String editar(PerfilRotina entity) {
		System.out.println("entrou no editar");
		this.entity = entity;
		return resolveNavigation(true);
	}
	
	protected String resolveNavigation(boolean crud) {
		String url = "/pages/"
				+ entity.getClass().getSimpleName().toLowerCase() + "/";
		url += crud ? "create.jsf" : "search.jsf";
		return url;
	}
	
}