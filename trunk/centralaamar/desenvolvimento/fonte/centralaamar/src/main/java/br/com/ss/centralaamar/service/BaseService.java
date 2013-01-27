package br.com.ss.centralaamar.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ss.centralaamar.message.DefaultMessage;
import br.com.ss.centralaamar.model.dao.IAbstractDAO;
import br.com.ss.centralaamar.model.entity.AbstractEntity;

@Service
public abstract class BaseService<T extends AbstractEntity> implements Serializable, IService<T> {

//	@Inject 
//	private Event<ExceptionToCatch> catchEvent;

	protected abstract IAbstractDAO<T> getDao();
	

	@PostConstruct
	protected void setup() {
		getDao();
	}
	
	@Transactional
	@Override
	public void save(T entity) {
		try {
			
			getDao().saveOrUpdate(entity);
			DefaultMessage.sendInfoMessageToUser(DefaultMessage.MSG_SUCESSO);
			
		} catch (Exception e) {
//			catchEvent.fire( new ExceptionToCatch( e ) );
			// TODO implementar
		}
	}
	

	@Override
	public List<T> search( T entity ) {
		return getDao().searchByEntity(entity);
	}
	

	@Transactional
	@Override
	public void remove( T entity ) {
		try {
			
			getDao().remove(entity);
			DefaultMessage.sendInfoMessageToUser(DefaultMessage.MSG_SUCESSO);
			
		} catch (Exception e) {
//			catchEvent.fire( new ExceptionToCatch( e ) );
		}
	}
	

}
