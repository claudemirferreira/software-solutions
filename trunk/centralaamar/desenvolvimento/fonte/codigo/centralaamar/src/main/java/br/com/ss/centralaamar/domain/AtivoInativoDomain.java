package br.com.ss.centralaamar.domain;

import lombok.Getter;

public enum AtivoInativoDomain implements AbstractContant<AtivoInativoDomain> {
	
	ATIVO( ( short ) 0, true, "Ativo"),
	
	INATIVO( ( short ) 1, false, "Inativo");
	
    @Getter
    private short id;
    
	@Getter
	private Boolean value;
	
	@Getter
	private String descricao;
	
	private AtivoInativoDomain( short id, Boolean v, String d ) {
		this.value = v;
		this.descricao = d;
	}
	
	
	public static AtivoInativoDomain findByValue( Boolean v ) {
		for ( AtivoInativoDomain aic : values() ) {
			if ( aic.value == null ) {
				if ( aic.value == null && v == null ) {
					return aic;
				}
			} else {
				if ( aic.value.equals( v ) ) {
					return aic;
				}
			}
		}
		return null;
	}
	
	@Override
	public AtivoInativoDomain getEnum(short id) {
		for ( AtivoInativoDomain aic : values() ) {
			if (aic.getId() == id ) {
				return aic;
			}
		}
		return null;
	}
}
