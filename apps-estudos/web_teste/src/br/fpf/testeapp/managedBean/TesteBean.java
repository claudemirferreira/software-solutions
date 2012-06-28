package br.fpf.testeapp.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
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
	
	@Inject
	private Conversation conversation;
	
	public TesteBean() {

		
		
	}
	
	@PostConstruct
	public void init() {
		this.resultList = new ArrayList<Entity>();
		this.detail = new  Detail();
		this.entity = new Entity();
		detail.setStatus( ( short ) 0 );
		entity.setAtivo(true);
		
		populateList();
		
		initConversation();
	}

	private void initConversation() {
		System.out.println( " # Conversation is transient: " + conversation.isTransient() );
		if ( conversation.isTransient() ) {
			conversation.begin();
		}
		System.out.println( " # Conversation Id: " + conversation.getId() );
	}

	private void populateList() {
		Entity e;
		Detail d;
		for ( int i = 0 ; i < 15 ; i++ ) {
			e = new Entity( "Item " + ( i + 1 ), i % 2 == 0 );
			for ( int j = 0 ; j < 3 ; j++ ) {
				d = new Detail( "Detail " + j, ( short ) j );
				e.getDetails().add(d);
			}
			System.out.println( " > Entity: " + e.getNome() );
			resultList.add(e);
		}
	}
	
	public void remove( Entity itemRemove ) {
	}
	
	public void search() {
		// TODO
		System.out.println("search press ..");
		System.out.println( " # Conversation is transient: " + conversation.isTransient() );
		System.out.println( " # Conversation Id: " + conversation.getId() );
	}
	
	
    /**
     * Metodo utilizado para ir para a tela de cadastra da entidade.
     * @return string.
     */
    public String cadastrar() {
//    	conversation.begin();
    	
		System.out.println( " # Conversation Id: " + conversation.getId() );
		System.out.println( " # Conversation is transient: " + conversation.isTransient() );
    	
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
	
}
