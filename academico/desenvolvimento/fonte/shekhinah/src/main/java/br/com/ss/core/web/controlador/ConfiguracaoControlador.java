package br.com.ss.core.web.controlador;

import java.util.Map;
import java.util.TreeMap;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import br.com.ss.academico.controlador.ControladorGenerico;
import br.com.ss.academico.dominio.Configuracao;
import br.com.ss.academico.servico.ConfiguracaoServico;
import br.com.ss.academico.servico.IService;

@ManagedBean
@ApplicationScoped
public class ConfiguracaoControlador extends ControladorGenerico<Configuracao> {

	private static final long serialVersionUID = -6765503632927328716L;

	@ManagedProperty(value = "#{configuracaoServicoImpl}")
	private ConfiguracaoServico servico;
	
    private Map<String, String> themes;
    
	// FIXME implementar

	private String tema = "aristo"; //default
	
	@Override
	protected void init() {
		
		// carrega o tema selecionado
		entidade.setTema(tema);

        themes = new TreeMap<String, String>();
        themes.put("Aristo", "aristo");
        themes.put("Black-Tie", "black-tie");
        themes.put("Blitzer", "blitzer");
        themes.put("Bluesky", "bluesky");
        themes.put("Casablanca", "casablanca");
        themes.put("Cupertino", "cupertino");
        themes.put("Dark-Hive", "dark-hive");
        themes.put("Dot-Luv", "dot-luv");
        themes.put("Eggplant", "eggplant");
        themes.put("Excite-Bike", "excite-bike");
        themes.put("Flick", "flick");
        themes.put("Glass-X", "glass-x");
        themes.put("Home", "home");
        themes.put("Hot-Sneaks", "hot-sneaks");
        themes.put("Humanity", "humanity");
        themes.put("Le-Frog", "le-frog");
        themes.put("Midnight", "midnight");
        themes.put("Mint-Choc", "mint-choc");
        themes.put("Overcast", "overcast");
        themes.put("Pepper-Grinder", "pepper-grinder");
        themes.put("Redmond", "redmond");
        themes.put("Rocket", "rocket");
        themes.put("Sam", "sam");
        themes.put("Smoothness", "smoothness");
        themes.put("South-Street", "south-street");
        themes.put("Start", "start");
        themes.put("Sunny", "sunny");
        themes.put("Swanky-Purse", "swanky-purse");
        themes.put("Trontastic", "trontastic");
        themes.put("UI-Darkness", "ui-darkness");
        themes.put("UI-Lightness", "ui-lightness");
        themes.put("Vader", "vader");
        
	}
	
	@Override
	protected String getNomeRelatorio() {
		return null;
	}

	@Override
	protected IService<Configuracao, Long> getService() {
		return servico;
	}

	public Map<String, String> getThemes() {
		return themes;
	}

	public ConfiguracaoServico getServico() {
		return servico;
	}

	public void setServico(ConfiguracaoServico servico) {
		this.servico = servico;
	}

}
