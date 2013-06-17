package br.com.ss.portal.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
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
import br.com.ss.portal.service.IPerfilService;

@Component("menuBean")
@Named
@Scope("session")
public class MenuBean implements Serializable{

	private static final long serialVersionUID = 441855469671712575L;
	private MenuModel model;
	
	@Autowired
	private IPerfilService service;

	public MenuBean() {
		model = new DefaultMenuModel();
		
		//First submenu
		Submenu submenu = new Submenu();
		submenu.setLabel("CADASTRO");
		
		MenuItem item = new MenuItem();
		item.setValue("Tipo");
		item.setUrl("#");
		submenu.getChildren().add(item);
		
		model.addSubmenu(submenu);
		
		//Second submenu
		submenu = new Submenu();
		submenu.setLabel("RELATORIOS");
		
		item = new MenuItem();
		item.setValue("Pagamento");
		item.setUrl("#");
		submenu.getChildren().add(item);
		
		item = new MenuItem();
		item.setValue("Despesas");
		item.setUrl("#");
		submenu.getChildren().add(item);
		Sistema entity = new Sistema();
		entity.setIdSistema(1l);
		List<Perfil> perfis = service.searchByEntity(entity);
		for (Perfil perfil : perfis) {
			submenu = new Submenu();
			submenu.setLabel(perfil.getNome());
			System.out.println("perfil ==== "+perfil.getNome());
			
		}
		
		model.addSubmenu(submenu);
	}

	public MenuModel getModel() {
		return model;
	}	
    
    public void save() {
		addMessage("Data saved");
	}
	
	public void update() {
		addMessage("Data updated");
	}
	
	public void delete() {
		addMessage("Data deleted");
	}
	
	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}