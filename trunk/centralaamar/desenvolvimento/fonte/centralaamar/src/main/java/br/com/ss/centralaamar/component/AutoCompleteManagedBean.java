package br.com.ss.centralaamar.component;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.TabChangeEvent;

@ManagedBean
@ViewScoped
public class AutoCompleteManagedBean {

	public AutoCompleteManagedBean() {
	}

	public void handleTabChange(TabChangeEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Selected:" + event.getTab().getId(), null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}