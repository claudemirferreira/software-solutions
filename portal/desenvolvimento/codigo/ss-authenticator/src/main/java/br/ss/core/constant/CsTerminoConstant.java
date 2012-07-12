package br.ss.core.constant;

import lombok.Getter;

public enum CsTerminoConstant implements AbstractContant<CsTerminoConstant> {
    
    /**
     * Tipo de termino.
     */
    INDETERMINADO( ( short ) 0, "Indeterminado" ),
    /**
     * Tipo de termino.
     */
    LIMITADO( ( short ) 1, "Limitado" );
    
    @Getter
    private short id;
    
    @Getter
    private String descricao;

    private CsTerminoConstant( short id, String descricao ) {
        this.id = id;
        this.descricao = descricao;
    }

	@Override
    public CsTerminoConstant getEnum( short id ) {
        for ( CsTerminoConstant u : CsTerminoConstant.values() ) {
            if ( u.getId() == id ) {
                return u;
            }
        }
        return null;
    }

}
