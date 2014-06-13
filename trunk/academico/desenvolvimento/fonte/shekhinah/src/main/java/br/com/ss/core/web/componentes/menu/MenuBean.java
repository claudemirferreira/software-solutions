package br.com.ss.core.web.componentes.menu;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.fpf.components.web.menu.MenuObject;

@ManagedBean
public class MenuBean {

    private List<MenuObject> menuObjectList;
	
	@PostConstruct
	public void init() {
		
		menuObjectList = new ArrayList<MenuObject>();
		// FIXME regra para carregar o menu
		
	}
	
}
