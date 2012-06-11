package br.ss.core.builder;


public interface Builder<T extends Builder<T,K>, K> {

	T newInstance();
	K build();
	T withInstance( K o );
	
}
