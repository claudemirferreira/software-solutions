package br.com.ss.academico.controlador;

import java.lang.reflect.Method;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.ss.academico.dominio.Rotina;

@ManagedBean
@RequestScoped
public class PageController {
	
	@PostConstruct
	public void init() {
		
	}
	
	public String parseUrl( Rotina rotina ) {

		String urlStr = rotina.getAcao();
		String bean = urlStr.substring(0, urlStr.indexOf("."));
		String method = urlStr.substring(urlStr.indexOf(".") + 1, urlStr.length());
		
		FacesContext facesContext = FacesContext.getCurrentInstance();  
		ELContext elContext = facesContext.getELContext();  
		ExpressionFactory factory = facesContext.getApplication().getExpressionFactory();  
		
		Object meuBean =  factory.createValueExpression(elContext, "#{" + bean + "}", Object.class).getValue(elContext);  
		
		try {

			Method mtd = meuBean.getClass().getMethod(method, new Class[] {});

			mtd.invoke(meuBean, new Object[] { });

		} catch (Exception e) {

			e.printStackTrace();

		}

		return rotina.getLink() + "?faces-redirect=true";
	}
	
}
