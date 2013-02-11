package br.com.ss.centralaamar.domain;

/**
 * Interface para ser usada em todas as contants de entityBean, para padronizar a chamada da identificacao e descricao.
 * @param <T>
 */
public interface AbstractContant<T> {

    /**
     * Metodo que retorna a identificacao na base de dados.
     * @return short, identificacao na base de dados
     */
    public short getId();

    /**
     * Metodo que retorna a descricao para ser mostrada ao usuario.
     * @return String, descricao amigavel para o usuario
     */
    public String getDescricao();

    /**
     * Método que retorna um enumerado de acordo com seu id.
     */
    public T getEnum( short id );

}
