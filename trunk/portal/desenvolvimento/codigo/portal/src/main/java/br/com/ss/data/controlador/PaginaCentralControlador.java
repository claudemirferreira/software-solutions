package br.com.ss.data.controlador;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class PaginaCentralControlador implements Serializable {

	private static final long serialVersionUID = 1158917634101679865L;

	private String paginaCentral;

	public PaginaCentralControlador() {
		setPaginaCentral("home.xhtml");
	}

	public String getPaginaCentral() {
		return paginaCentral;
	}

	public void setPaginaCentral(String paginaCentral) {
		this.paginaCentral = paginaCentral;
	}

	public void teste() {

		System.out.println("www");
	}

}
