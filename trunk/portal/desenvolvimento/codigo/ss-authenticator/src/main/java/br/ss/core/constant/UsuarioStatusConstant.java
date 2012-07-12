package br.ss.core.constant;

import br.ss.authenticator.model.entity.Usuario;
import lombok.Getter;

/**
 * Enumerado para os status da entidade {@link Usuario}.
 */
public enum UsuarioStatusConstant implements AbstractContant<UsuarioStatusConstant> {
    /**
     * constrashort para Cadastro Solicitado.
     */
    CADASTRO_SOLICITADO( ( short ) 0, "Cadastro Solicitado" ),
    /**
     * constrashort para Usuario Ativo.
     */
    ATIVO( ( short ) 1, "Ativo" ),
    /**
     * constrashort para Usuario Inativo.
     */
    INATIVO( ( short ) 2, "Inativo" ),
    /**
     * constrashort para Usuario Bloqueado.
     */
    BLOQUEADO( ( short ) 3, "Bloqueado" ),
    /**
     * constrashort para senha inspirada.
     */
    SENHA_EXPIRADA( ( short ) 4, "Senha expirada" ),
    /**
     * constrashort para Cadastro Rejeitado.
     */
    CADASTRO_REJEITADO( ( short ) 5, "Cadastro Rejeitado" ),
    /**
     * constrashort para Cadastro incorreto.
     */
    CADASTRO_INCORRETO( ( short ) 6, "Cadastro Incorreto" ),
    /**
     * constrashort para senha gerada pelo administrador.
     */
    SENHA_REGERADA_PELO_ADMNISTRADOR( ( short ) 7, "Senha regerada pelo administrador" );

    @Getter
    private short id;

    @Getter
    private String descricao;

    private UsuarioStatusConstant( short id, String descricao ) {
        this.id = (short) id;
        this.descricao = descricao;
    }

    public UsuarioStatusConstant getEnum( short id ) {
        for ( UsuarioStatusConstant u : UsuarioStatusConstant.values() ) {
            if ( u.getId() == id ) {
                return u;
            }
        }
        return null;
    }

}
