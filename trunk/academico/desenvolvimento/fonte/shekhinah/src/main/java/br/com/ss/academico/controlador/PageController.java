package br.com.ss.academico.controlador;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.ss.academico.dominio.Rotina;


@ManagedBean
@RequestScoped
public class PageController {
	
	@PostConstruct
	public void init() {
		
	}
	
	// TODO obsoleto - usando navegacao direta na pagina
	public String parseUrl( Rotina rotina ) {
		return rotina.getAcao() + "?faces-redirect=true";
	}
	
}
