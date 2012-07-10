package br.ss.authenticator.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import br.ss.authenticator.web.exception.AuthenticatorException;
import br.ss.authenticator.web.message.AuthenticatorMessage;
import br.ss.core.model.dao.IAbstractDAO;
import br.ss.core.model.entity.AbstractEntity;

public abstract class BaseService<T extends AbstractEntity> implements Serializable, IService<T> {

	@Inject 
	private Event<AuthenticatorException> catchEvent;

	protected abstract IAbstractDAO<T> getDao();
	

	@PostConstruct
	protected void setup() {
		getDao();
		
	}
	
	
	@Override
	public void save(T entity) {
		try {
			
			getDao().saveOrUpdate(entity);
			AuthenticatorMessage.sendInfoMessageToUser(AuthenticatorMessage.MSG_SUCESSO);
			
		} catch (Exception e) {
			AuthenticatorMessage.sendErrorMessageToUser(AuthenticatorMessage.MSG_ERRO);
			catchEvent.fire( new AuthenticatorException( AuthenticatorMessage.MSG_ERRO ) );
			
		}
	}
	

	@Override
	public List<T> search( T entity ) {
		return getDao().searchByEntity(entity);
	}
	
	
	@Override
	public void remove( T entity ) {
		try {
			
			getDao().remove(entity);
			AuthenticatorMessage.sendInfoMessageToUser(AuthenticatorMessage.MSG_SUCESSO);
			
		} catch (Exception e) {
			AuthenticatorMessage.sendErrorMessageToUser(AuthenticatorMessage.MSG_ERRO);
			catchEvent.fire( new AuthenticatorException( AuthenticatorMessage.MSG_ERRO  ) );
		}
	}
	

}
