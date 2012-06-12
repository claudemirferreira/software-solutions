package br.ss.core.web.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import lombok.Getter;
import br.ss.core.database.Database;
import br.ss.core.entity.AbstractEntity;
import br.ss.core.message.CoreMessage;

public abstract class GenericBean<T extends AbstractEntity> implements Serializable {

	/* ---------- Atributos ----------------------- */
	@Getter
	protected T entity;

	@Getter
	protected List<T> resultList;

	@Inject @RequestScoped @Database
	protected EntityManager entityManager;
	
	/** Alias para redirecionar para a tela de cadastro. Mapear o mesmo no page.xml. */
	protected static final String CADASTRO = "cadastro";
	
	/** Alias para redirecionar para a tela de pesquisa. Mapear o mesmo no page.xml. */
	protected static final String PESQUISA = "pesquisa";

	/** Alias para redirecionamento apos uma operacao de SUCESSO. Mapear o mesmo no page.xml. */
	protected static final String SUCESSO = "sucesso";

	/** Alias para redirecionamento apos uma operacao com ERRO. Mapear o mesmo no page.xml. */
	protected static final String ERRO = "erro";
	
	/** Mensagem. */
    protected CoreMessage message;
    
    /** Flag para indicar se deve exibir o resultado da pesquisa.
     * Após realizar a pesquisa, setar para true. */
    @Getter
    protected boolean renderSearhResults;
    
    
	
	public GenericBean() {
		this.initEntity();
	}

	
	/* ---------- Metodos ----------------------- */
	protected abstract void initEntity();
	public abstract List<T> search();
	public abstract String save();
	public abstract String remove();
//	public abstract String persist();
//	public abstract String merge();
	
//	public String save() {
//		return PESQUISA;
//	}
	
	
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
    

}
