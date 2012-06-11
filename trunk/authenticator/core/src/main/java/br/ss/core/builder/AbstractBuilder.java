package br.ss.core.builder;

import java.lang.reflect.ParameterizedType;

import lombok.Getter;

public class AbstractBuilder<T extends AbstractBuilder<T,K> , K> implements Builder<T, K> {

	@Getter
	protected K instance;
	
	@SuppressWarnings( "unchecked" )
	@Override
	public T newInstance() {
		try {
			initEntityClass();
			instance = ( K ) instance.getClass().newInstance();
			afterNewInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return ( T ) this;
	}

	protected void afterNewInstance() {
		
	}
	
	@Override
	public K build() {
		K aux = instance;
		instance = null;
		return aux;
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public T withInstance( K o ) {
		instance = o;
		return ( T ) this;
	}
	
	@SuppressWarnings( "unchecked" )
	private void initEntityClass() throws InstantiationException, IllegalAccessException {
		ParameterizedType paramType = (ParameterizedType)  getClass().getGenericSuperclass();
		Class<K> klazz = ( Class<K> ) paramType.getActualTypeArguments()[1];
		instance =  ( K ) klazz.newInstance();
	}

	
}
