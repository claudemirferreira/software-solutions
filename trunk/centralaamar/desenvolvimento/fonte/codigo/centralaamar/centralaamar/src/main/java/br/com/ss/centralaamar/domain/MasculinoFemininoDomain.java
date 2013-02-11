package br.com.ss.centralaamar.domain;

import lombok.Getter;

public enum MasculinoFemininoDomain implements AbstractContant<MasculinoFemininoDomain> {
	
	MASCULINO( ( short ) 0, true, "Masculino"),
	
	FEMININO( ( short ) 1, false, "Feminino");
	
    @Getter
    private short id;
    
	@Getter
	private Boolean value;
	
	@Getter
	private String descricao;
	
	private MasculinoFemininoDomain( short id, Boolean v, String d ) {
		this.value = v;
		this.descricao = d;
	}
	
	public static MasculinoFemininoDomain findByValue( Boolean v ) {
		for ( MasculinoFemininoDomain aic : values() ) {
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
	public MasculinoFemininoDomain getEnum(short id) {
		for ( MasculinoFemininoDomain aic : values() ) {
			if (aic.getId() == id ) {
				return aic;
			}
		}
		return null;
	}
}
