package br.ss.core.constant;

import lombok.Getter;

public enum AtivoInativoConstant implements AbstractContant<AtivoInativoConstant> {
	
	ATIVO( ( short ) 0, true, "Ativo"),
	
	INATIVO( ( short ) 1, false, "Inativo");
	
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
	
	@Override
	public AtivoInativoConstant getEnum(short id) {
		for ( AtivoInativoConstant aic : values() ) {
			if (aic.getId() == id ) {
				return aic;
			}
		}
		return null;
	}
}
