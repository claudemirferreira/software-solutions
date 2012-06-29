package br.fpf.testeapp.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;
import br.fpf.testeapp.entity.Detail;
import br.fpf.testeapp.entity.Entity;

@Named
@ConversationScoped
public class TesteBean implements Serializable {
	
	private static final long serialVersionUID = 8059424911056476775L;

	/** Alias para redirecionar para a tela de cadastro. Mapear o mesmo no page.xml. */
	public static final String CADASTRO = "cadastro";
	
	/** Alias para redirecionar para a tela de pesquisa. Mapear o mesmo no page.xml. */
	public static final String PESQUISA = "pesquisa";
	
	/** Alias para redirecionamento apos uma operacao de SUCESSO. Mapear o mesmo no page.xml. */
	public static final String SUCESSO = "sucesso";
	
	/** Alias para redirecionamento apos uma operacao com ERRO. Mapear o mesmo no page.xml. */
	public static final String ERRO = "erro";
	
	@Getter
	private List<Entity> resultList;
	
	@Getter
	private Entity entity;
	
	@Getter
	private Detail detail;
	
	@Getter @Setter
	protected Entity itemToRemove;
	
	@Inject
	private Conversation conversation;
	
	
	@PostConstruct
	public void init() {
		
		initConversation();

		this.resultList = new ArrayList<Entity>();
		this.detail = new  Detail();
		this.entity = new Entity();
		detail.setStatus( ( short ) 0 );
		entity.setAtivo(true);
		
		populateList();
	}

	private void initConversation() {
		System.out.println( " # Conversation is transient: " + conversation.isTransient() );
		if ( conversation.isTransient() ) {
			conversation.begin();
		}
		System.out.println( " # Conversation Id: " + conversation.getId() );
	}

	private void populateList() {
		for ( int i = 0 ; i < 15 ; i++ ) {
			addItem(i);
		}
	}

	/**
	 * @param i
	 * @return
	 */
	public Entity addItem(int i) {
		Entity e;
		Detail d;
		e = new Entity( "Item " + ( i + 1 ), i % 2 == 0 );
		for ( int j = 0 ; j < 3 ; j++ ) {
			d = new Detail( "Detail " + j, ( short ) j );
			e.getDetails().add(d);
		}
		resultList.add(e);
		System.out.println( " > Entity: " + e.getNome() );
		return e;
	}
	
	public void remove() {
		resultList.remove(itemToRemove);
		sendInfoMessageToUser( "Item removido: "  + itemToRemove.getNome() );
		
	}
	
	public void search() {
		// TODO
		System.out.println("search press ..");
		debug();
	}

	/**
	 * 
	 */
	public void debug() {
		System.out.println( " # Conversation Id: " + conversation.getId() );
		System.out.println( " # Conversation is transient: " + conversation.isTransient() );
	}
	
	
    /**
     * Metodo utilizado para ir para a tela de cadastra da entidade.
     * @return string.
     */
    public String cadastrar() {
    	debug();
    	
    	return "create.seam?faces-redirect=true";
    }

    /**
     * Metodo utilizado para editar uma entidade.
     * @return string.
     */
    public String editar( Entity entity ) {
    	this.entity = entity;
    	conversation.begin();
    	return CADASTRO;
    }
    
    public void end() {
    	conversation.end();
    }
    
    /**
     * Metodo utilizado para editar uma entidade.
     * @return string.
     */
    public String cancel() {
    	end();
    	return "search.seam?faces-redirect=true";
    }
    
    
    /**
     * Metodo utilizado para editar uma entidade.
     * @return string.
     */
    public String save() {
    	debug();
    	addItem(resultList.size());
    	return "search.seam?faces-redirect=true";
    }
    
    
    /* ---- Others ------------- */

    protected void sendInfoMessageToUser(String message){
        FacesContext context = getContext();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
    }

    protected void sendWarnMessageToUser(String message){
        FacesContext context = getContext();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, message));
    }
 
    protected void sendErrorMessageToUser(String message){
        FacesContext context = getContext();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
    }
 
    protected FacesContext getContext() {
        return FacesContext.getCurrentInstance();
    }
    
}
