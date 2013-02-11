package br.com.ss.centralaamar.domain;

import lombok.Getter;

public enum BatizadoDomain {

	SIM( 		( short ) 0, 	"SIM"),

	NAO( 		( short ) 1, 	"NÃO"),
	
	VISITANTE( 	( short ) 2, 	"VISITANTE");

    @Getter
    private short id;
	
	@Getter
	private String descricao;

	private BatizadoDomain(short id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public static BatizadoDomain findByValue( String desc ) {
		for ( BatizadoDomain aic : values() ) {
			if ( aic.descricao.equals(desc) ) {
					return aic;
			}
		}
		return null;
	}
	
	public static BatizadoDomain getEnum( short id ) {
		for ( BatizadoDomain aic : values() ) {
			if (aic.getId() == id ) {
				return aic;
			}
		}
		return null;
	}
}
