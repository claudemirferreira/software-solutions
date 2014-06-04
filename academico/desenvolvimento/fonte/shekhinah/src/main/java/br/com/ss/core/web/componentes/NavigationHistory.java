package br.com.ss.core.web.componentes;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpServletRequest;

import br.com.ss.core.web.utils.FacesUtils;

@ManagedBean
@SessionScoped
public class NavigationHistory {

	private List<NavigationDTO> history;
	private static final String INDEX = "index.xhtml?faces-redirect=true";

	private String fullContextPath; 
	
	@PostConstruct
	public void init() {
		
		HttpServletRequest request = FacesUtils.getRequest();
		String protocolHostPort = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		String contextPath = request.getContextPath();
		fullContextPath = protocolHostPort + contextPath + "/";
		
		clear();
	}
	
	
	public void addHistory(String label, String url) {
		history.add( new NavigationDTO( label, montarUrl( url ) ) );
	}
	
	
	public void clear() {
		history = new ArrayList<NavigationDTO>();
		addHome();
	}
	
	
	private void addHome() {
		history.add( new NavigationDTO("Home", montarUrl(INDEX ) ) );
	}
	
	private String montarUrl(String link) {
		return fullContextPath + link;
	}


	/* ------- Inner DTO ------------ */
	public class NavigationDTO {
		private String label;
		private String url;
		
		public NavigationDTO(String label, String url) {
			super();
			this.label = label;
			this.url = url;
		}

		public String getLabel() {
			return label;
		}
		public String getUrl() {
			return url;
		}
	}


	public List<NavigationDTO> getHistory() {
		return history;
	}
	
}
