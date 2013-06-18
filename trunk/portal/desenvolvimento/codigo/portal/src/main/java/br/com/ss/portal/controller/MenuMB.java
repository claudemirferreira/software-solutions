package br.com.ss.portal.controller;

import java.util.ArrayList;
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
import br.com.ss.portal.model.entity.Rotina;
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

	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}

	public MenuModel getMenuModel() {
		menuModel = new DefaultMenuModel();
		
		Sistema sistema = new Sistema();
		sistema.setIdSistema(1l);
		
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(1l);
		
		List<Rotina> rotinas = new ArrayList<Rotina>();
		
		List<Perfil> perfis = service.listarPerfilPorUsuarioSistema(sistema, usuario);
		for (Perfil perfil : perfis) {
			Submenu submenu = new Submenu();
			submenu.setLabel(perfil.getNome());
			
			rotinas = rotinaService.listarRotinaPorPerfil(perfil);
			for (Rotina rotina : rotinas) {
				MenuItem menuItem = new MenuItem();
				menuItem.setValue(rotina.getNome());
				menuItem.setUrl(rotina.getPath());
				submenu.getChildren().add(menuItem);
				
				menuModel.addSubmenu(submenu);
			}
		}
		return menuModel;
	}

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

}