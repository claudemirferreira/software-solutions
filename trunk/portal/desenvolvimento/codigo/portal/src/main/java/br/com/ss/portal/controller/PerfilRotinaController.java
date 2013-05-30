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

	private Sistema sistema;

	private Perfil perfil;

	private Sistema searchSistema;

	private Rotina rotina;

	private List<Sistema> sistemas = new ArrayList<Sistema>();

	private List<Perfil> perfils = new ArrayList<Perfil>();

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

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Sistema getSearchSistema() {
		return searchSistema;
	}

	public void setSearchSistema(Sistema searchSistema) {
		this.searchSistema = searchSistema;
	}

	public Rotina getRotina() {
		return rotina;
	}

	public void setRotina(Rotina rotina) {
		this.rotina = rotina;
	}

	public List<Sistema> getSistemas() {
		return sistemas;
	}

	public void setSistemas(List<Sistema> sistemas) {
		this.sistemas = sistemas;
	}

	public List<Perfil> getPerfils() {
		return perfils;
	}

	public void setPerfils(List<Perfil> perfils) {
		this.perfils = perfils;
	}

	public List<Rotina> getRotinas() {
		return rotinas;
	}

	public void setRotinas(List<Rotina> rotinas) {
		this.rotinas = rotinas;
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
				+ this.entity.getClass().getSimpleName().toLowerCase()
				+ "/search.jsf";
		System.out.println("url == " + url);
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