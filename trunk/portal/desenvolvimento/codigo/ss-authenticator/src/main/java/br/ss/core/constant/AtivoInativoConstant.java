package br.ss.core.constant;

import lombok.Getter;

public enum AtivoInativoConstant {
	
    TODOS( ( short ) 0, null, "-- Todos os registros --" ),
    
	ATIVO( ( short ) 1, true, "Ativo"),
	
	INATIVO( ( short ) 2, false, "Inativo");
	
    @Getter
    private short id;
    
	@Getter
	private Boolean value;
	
	@Getter
	private String descricao;
	
	private AtivoInativoConstant( short id, Boolean v, String d ) {
		this.value = v;
		this.descricao = d;
	}
	
	
	public static AtivoInativoConstant findByValue( Boolean v ) {
		for ( AtivoInativoConstant aic : values() ) {
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
	

}
