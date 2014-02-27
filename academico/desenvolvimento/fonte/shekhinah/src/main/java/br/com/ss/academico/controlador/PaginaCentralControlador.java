package br.com.ss.academico.controlador;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class PaginaCentralControlador implements Serializable{

	private static final long serialVersionUID = -1155931039018244331L;
	
	private String paginaCentral;
	
	public PaginaCentralControlador() {
		setPaginaCentral("paginas/perfil/lista.xhtml");
	}

	public String getPaginaCentral() {
		return paginaCentral;
	}

	public void setPaginaCentral(String paginaCentral) {
		this.paginaCentral = paginaCentral;
	}
	
}
