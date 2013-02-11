package br.com.ss.centralaamar.domain;

import lombok.Getter;

public enum ModoConversaoDomain {

	COMPORTAGEM( 		( short ) 0, 	"COMPORTAGEM"),

	ESCOLA_SABATINA( 	( short ) 1, 	"ESCOLA SABATINA"),
	
	ESTUDO_BIBLICO( 	( short ) 2, 	"ESTUDO BÌBLICO"),
	
	EVANGELISMO( 		( short ) 3, 	"EVANGELISMO"),

	OPERACAO_RESGATE( 	( short ) 4, 	"OPERA«√O RESGATE"),
	
	PEQUENO_GRUPO( 		( short ) 5, 	"PEQUENO GRUPO");

    @Getter
    private short id;
	
	@Getter
	private String descricao;

	private ModoConversaoDomain(short id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public static ModoConversaoDomain findByValue( String desc ) {
		for ( ModoConversaoDomain aic : values() ) {
			if ( aic.descricao.equals(desc) ) {
					return aic;
			}
		}
		return null;
	}
	
	public static ModoConversaoDomain getEnum( short id ) {
		for ( ModoConversaoDomain aic : values() ) {
			if (aic.getId() == id ) {
				return aic;
			}
		}
		return null;
	}
}
