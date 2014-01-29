package br.com.ss.data.controlador;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class GuestPreferences implements Serializable {
	static final long serialVersionUID = 1L;

	private String theme = "glass-x"; // default

	public String getTheme() {

		String themeTemp = FacesContext.getCurrentInstance()
				.getExternalContext().getInitParameter("primefaces.THEME");
//		if (themeTemp.equalsIgnoreCase("#{themeSwitcherControlador.gp.theme}")) {
//			return themeTemp;
//		}
		return themeTemp;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public void saveTheme() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put("themeSelecionado", this.theme);

	}
}