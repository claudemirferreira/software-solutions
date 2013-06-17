package br.com.ss.portal.controller;

import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.ss.portal.model.entity.Perfil;
import br.com.ss.portal.model.entity.Sistema;
import br.com.ss.portal.model.entity.Usuario;
import br.com.ss.portal.service.IPerfilService;
import br.com.ss.portal.service.IRotinaService;

@Component("menuMB")
@Named
@Scope("session")
public class MenuMB {

	private MenuModel menuModel = new DefaultMenuModel();
	
	@Autowired
	private IPerfilService service;
	
	@Autowired
	private IRotinaService rotinaService;

	public MenuMB() {
	}

	public MenuModel getMenuModel() {
		// if (UsuarioMB.getLoginPerfil() != null) {
		geraMenu("sicad");
		// }
		return menuModel;
	}

	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}

	public void geraMenu(String modulo) {
		menuModel = new DefaultMenuModel();
		// MenuDao menuDAO = new MenuDao();
		// String usuario = UsuarioMB.getLoginPerfil().getLogin();
		// List<Menu> listaMenu = menuDAO.buscaPorMenuUsuario(usuario, modulo);

		// Menu menu = new Menu();
		Submenu submenu = new Submenu();
		submenu.setLabel("Cadastro");
		MenuItem mi = new MenuItem();
		mi.setValue("cadasto 1");
		mi.setAjax(false);
		mi.setUrl("eeeeee");

		submenu.getChildren().add(mi);
		menuModel.addSubmenu(submenu);

		Submenu submenu1 = new Submenu();
		submenu1.setLabel("Relatorio");
		MenuItem mi1 = new MenuItem();
		mi1.setValue("rel 1");
		mi1.setAjax(false);
		mi1.setUrl("eeeeee");
		// mi.setActionExpression(new MethodExpression("sss") );
		submenu1.getChildren().add(mi1);
		menuModel.addSubmenu(submenu1);
		
		Sistema sistema = new Sistema();
		sistema.setIdSistema(1l);
		
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(1l);
		
		List<Perfil> perfis = service.listarPerfilPorUsuarioSistema(sistema, usuario);
		for (Perfil perfil : perfis) {
			submenu = new Submenu();
			submenu.setLabel(perfil.getNome());
			System.out.println("perfil ==== "+perfil.getNome());
			
			menuModel.addSubmenu(submenu);
		}

	}

	/*
	 * private MethodExpression criarAcao(String acao) { MethodExpression
	 * actionExpression = FacesContext .getCurrentInstance() .getApplication()
	 * .getExpressionFactory() .createMethodExpression(
	 * FacesContext.getCurrentInstance().getELContext(), acao, null, new
	 * Class[0]); return actionExpression; }
	 */

	private MethodExpression createMethodExpression(String action) {

		final Class<?>[] paramTypes = new Class<?>[0];

		MethodExpression methodExpression = getExpressionFactory()
				.createMethodExpression(getELContext(), action, null,
						paramTypes);

		return methodExpression;
	}

	private ValueExpression createValueExpression(String binding,
			Class<String> clazz) {
		final ValueExpression ve = getExpressionFactory()
				.createValueExpression(getELContext(), binding, String.class);

		return ve;
	}

	public static ELContext getELContext() {

		return FacesContext.getCurrentInstance().getELContext();

	}

	public static ExpressionFactory getExpressionFactory() {

		return getApplication().getExpressionFactory();

	}

	public static Application getApplication() {

		return FacesContext.getCurrentInstance().getApplication();

	}

	// public Submenu geraSubmenu(Menu menu) {
	// String usuario = UsuarioMB.getLoginPerfil().getLogin();
	// Submenu submenu = new Submenu();
	// MenuDao menuDAO = new MenuDao();
	// submenu.setLabel(menu.getDescricao());
	// for (Menu m : menuDAO.buscaPorMenu(menu, usuario)) {
	// if (!menuDAO.verificaSubMenu(m, usuario)) {
	// MenuItem mi = new MenuItem();
	// mi.setValue(m.getDescricao());
	// // mi.setActionExpression(createMethodExpression(m.getAcao()));
	// mi.setAjax(false);
	// if (m.getAcao() != null) {
	// mi.setActionExpression(createMethodExpression(m.getAcao()));
	// }
	// if (m.getUrl() != null) {
	// mi.setUrl(m.getUrl());
	// }
	// mi.setStyle("width:100px");
	// submenu.getChildren().add(mi);
	// } else {
	// submenu.getChildren().add(geraSubmenu(m));
	// }
	// }
	// return submenu;
	// }

}