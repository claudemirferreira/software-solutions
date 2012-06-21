package br.ss.core.web.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import lombok.Getter;
import br.ss.core.model.dao.IAbstractDAO;
import br.ss.core.model.entity.AbstractEntity;

public abstract class GenericBean<T extends AbstractEntity> implements Serializable {

	private static final long serialVersionUID = -1229239475130268144L;

	/* ---------- Atributos ----------------------- */
	
	/** Alias para redirecionar para a tela de cadastro. Mapear o mesmo no page.xml. */
	public static final String CADASTRO = "cadastro";
	
	/** Alias para redirecionar para a tela de pesquisa. Mapear o mesmo no page.xml. */
	public static final String PESQUISA = "pesquisa";
	
	/** Alias para redirecionamento apos uma operacao de SUCESSO. Mapear o mesmo no page.xml. */
	public static final String SUCESSO = "sucesso";
	
	/** Alias para redirecionamento apos uma operacao com ERRO. Mapear o mesmo no page.xml. */
	public static final String ERRO = "erro";
	
	
	@Getter
	protected T entity;

	@Getter
	protected List<T> resultList;

//	@Inject @RequestScoped @Database
//	protected EntityManager entityManager;
	
//	/** Mensagem. */
//    protected CoreMessage message;
    
    /** Flag para indicar se deve exibir o resultado da pesquisa.
     * Após realizar a pesquisa, setar para true. */
    @Getter
    protected boolean renderSearhResults;
    
	
	public GenericBean() {
		this.initEntity();
	}

	
	/* ---------- Metodos ----------------------- */
	
	protected abstract void initEntity();
	
	protected abstract IAbstractDAO<T> getDAO();
	
	
	public List<T> search() {
		this.resultList = getDAO().listAll();
		return resultList;
	}
	
	
	public String save() {
		try {
			
			getDAO().saveOrUpdate(entity);
			return PESQUISA;
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO message
		}
		return ERRO;
	}
	
	
	public void remove( T itemRemove ) {
		
		try {
			
			getDAO().remove(itemRemove);
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO message
		}
		
	}
	
	
    /**
     * Metodo utilizado para ir para a tela de cadastra da entidade.
     * @return string.
     */
    public String cadastrar() {
    	this.initEntity();
    	return CADASTRO;
    }

    /**
     * Metodo utilizado para editar uma entidade.
     * Sobrescrever este metodo caso necessário realizar outras operaçoes.
     * 
     * @return string.
     */
    public String editar( T entity ) {
    	this.entity = entity;
    	return CADASTRO;
    }
    

    /**
     * Retorna a qtde de itens retornados na pesquisa.
     * @return Integer
     */
    public Integer resultCount() {
    	return resultList == null ? 0 : resultList.size();
    }
    
    protected void sendInfoMessageToUser(String message){
        FacesContext context = getContext();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
    }
 
    protected void sendErrorMessageToUser(String message){
        FacesContext context = getContext();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
    }
 
    protected FacesContext getContext() {
        return FacesContext.getCurrentInstance();
    }
    
}
