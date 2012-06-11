package br.ss.core.dao;

/**
 * Interface para ser usada em todas as contants do entityBean, para padronizar a chamada da identificacao e descricao.
 * 
 * @param <T>
 */
public interface Constant<T> {

    /**
     * Metodo que retorna a identificacao na base de dados.
     * 
     * @return int, identificacao na base de dados
     */
    int getId();

    /**
     * Metodo que retorna a descricao para ser mostrada ao usuario.
     * 
     * @return String, descricao amigavel para o usuario
     */
    String getDescricao();

    /**
     * M�todo que retorna um enumerado de acordo com seu id.
     */
    T getEnum( int id );

}
