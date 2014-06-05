package br.com.ss.core.web.utils;

import java.io.IOException;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.ss.core.web.enumerated.Constants;

@ManagedBean
@ApplicationScoped
public class PageUtils {
	
	/**
	 * Adiciona o redirect para o url informada.
	 * ex: paginas/rotina/lista.xhtml?faces-redirect=true
	 * @param url
	 * @return
	 */
	public String returnUrlRedirect(String url) {
		return url + Constants.REDIRECT;
	}
	
	/**
	 * Redireciona para a url informada.
	 * @param url
	 */
	public void redirectForUrl(String url) {
	    try {
	    	ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			ec.redirect(ec.getRequestContextPath() + "/" + url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
