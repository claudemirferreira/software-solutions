package br.com.ss.academico.enumerated;

/**
 * Tipo enumerado para a representa��o dos Meses do Ano.
 * 
 * @author robson.ramos
 */
public enum MesesAnoConstant {

    /** Item do enum. */
    TODOS( (short) 0, "--Todos os registros--" ),

    /** Item do enum. */
    JANEIRO( (short) 1, "Janeiro" ),

    /** Item do enum. */
    FEVEREIRO( (short) 2, "Fevereiro" ),

    /** Item do enum. */
    MARCO( (short) 3, "Mar�o" ),

    /** Item do enum. */
    ABRIL( (short) 4, "Abril" ),

    /** Item do enum. */
    MAIO( (short) 5, "Maio" ),

    /** Item do enum. */
    JUNHO( (short) 6, "Junho" ),

    /** Item do enum. */
    JULHO( (short) 7, "Julho" ),

    /** Item do enum. */
    AGOSTO( (short) 8, "Agosto" ),

    /** Item do enum. */
    SETEMBRO( (short) 9, "Setembro" ),

    /** Item do enum. */
    OUTUBRO( (short) 10, "Outubro" ),

    /** Item do enum. */
    NOVEMBRO( (short) 11, "Novembro" ),

    /** Item do enum. */
    DEZEMBRO( (short) 12, "Dezembro" );

    /**
     * Identificador do enumarado.
     */
    private int id;

    /**
     * Descri��o do enumerado.
     */
    private String descricao;

    /**
     * Constru��o de lista a ser utiliza em p�ginas de pesquisa.
     */

    /**
     * Lista de indice.
     */
    static final int IND0 = 0;

    /**
     * Lista de indice.
     */
    static final int IND1 = 1;

    /**
     * Lista de indice.
     */
    static final int IND2 = 2;

    /**
     * Lista de indice.
     */
    static final int IND3 = 3;

    /**
     * Lista de indice.
     */
    static final int IND4 = 4;

    /**
     * Lista de indice.
     */
    static final int IND5 = 5;

    /**
     * Lista de indice.
     */
    static final int IND6 = 6;

    /**
     * Lista de indice.
     */
    static final int IND7 = 7;

    /**
     * Lista de indice.
     */
    static final int IND8 = 8;

    /**
     * Lista de indice.
     */
    static final int IND9 = 9;

    /**
     * Lista de indice.
     */
    static final int IND10 = 10;

    /**
     * Lista de indice.
     */
    static final int IND11 = 11;

    /**
     * Lista de indice.
     */
    static final int IND12 = 12;

    /**
     * falta o javadoc.
     */

    private static final MesesAnoConstant[] VALORES_PESQUISA = {

    MesesAnoConstant.values()[MesesAnoConstant.IND0], MesesAnoConstant.values()[MesesAnoConstant.IND1],
            MesesAnoConstant.values()[MesesAnoConstant.IND2], MesesAnoConstant.values()[MesesAnoConstant.IND3],
            MesesAnoConstant.values()[MesesAnoConstant.IND4], MesesAnoConstant.values()[MesesAnoConstant.IND5],
            MesesAnoConstant.values()[MesesAnoConstant.IND6], MesesAnoConstant.values()[MesesAnoConstant.IND7],
            MesesAnoConstant.values()[MesesAnoConstant.IND8], MesesAnoConstant.values()[MesesAnoConstant.IND9],
            MesesAnoConstant.values()[MesesAnoConstant.IND10], MesesAnoConstant.values()[MesesAnoConstant.IND11],
            MesesAnoConstant.values()[MesesAnoConstant.IND12] };

    /**
     * Constru��o de lista a ser utilizada em p�ginas de inser��o e atualiza��o.
     */
    private static final MesesAnoConstant[] VALORES_EDICAO = {

    MesesAnoConstant.values()[MesesAnoConstant.IND1], MesesAnoConstant.values()[MesesAnoConstant.IND2],
            MesesAnoConstant.values()[MesesAnoConstant.IND3], MesesAnoConstant.values()[MesesAnoConstant.IND4],
            MesesAnoConstant.values()[MesesAnoConstant.IND5], MesesAnoConstant.values()[MesesAnoConstant.IND6],
            MesesAnoConstant.values()[MesesAnoConstant.IND7], MesesAnoConstant.values()[MesesAnoConstant.IND8],
            MesesAnoConstant.values()[MesesAnoConstant.IND9], MesesAnoConstant.values()[MesesAnoConstant.IND10],
            MesesAnoConstant.values()[MesesAnoConstant.IND11], MesesAnoConstant.values()[MesesAnoConstant.IND12] };

    /**
     * Construtor do enum onde o identificador, a descri��o e o valor boleano s�o informados.
     * 
     * @param id
     * @param descricao
     */
    private MesesAnoConstant( Short id, String descricao ) {
        this.id = id;
        this.descricao = descricao;
    }

    /**
     * Obt�m o status do enumerado cujo id � passado por par�metro.
     * 
     * @param id
     * @return tipo enumerado.
     */
    public static MesesAnoConstant getStatus( int id ) {
        for ( MesesAnoConstant t : MesesAnoConstant.values() ) {
            if ( t.id == id ) {
                return t;
            }
        }
        return null;
    }

    /**
     * Obt�m o status do enumerado cujo id � passado por par�metro.
     * 
     * @param id
     * @return tipo enumerado.
     */
    public static MesesAnoConstant getStatus( Short id ) {
        /*-------------------------------------------------------
         * Por default, se for -1, o enum Todos ser� retornado.
         * Isso foi necess�rio para utilizar a Tag ComboBox feita
         * pelo Barbosa. 
         *-------------------------------------------------------*/

        for ( MesesAnoConstant t : MesesAnoConstant.values() ) {
            if ( t.id == id ) {
                return t;
            }
        }
        return null;
    }

    public int getId() {
        return this.id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao( String descricao ) {
        this.descricao = descricao;
    }

    public static MesesAnoConstant[] getValoresPesquisa() {
        return MesesAnoConstant.VALORES_PESQUISA;
    }

    public static MesesAnoConstant[] getValoresEdicao() {
        return MesesAnoConstant.VALORES_EDICAO;
    }

}
