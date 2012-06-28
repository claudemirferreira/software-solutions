package br.fpf.testeapp.constant;

import lombok.Getter;

public enum AtivoInativoConstant {
	
	ATIVO( true, "Ativo"),
	
	INATIVO( false, "Inativo");
	
	@Getter
	private boolean value;
	
	@Getter
	private String descricao;
	
	private AtivoInativoConstant( boolean v, String d ) {
		this.value = v;
		this.descricao = d;
	}
	
	
	public static AtivoInativoConstant findByValue( boolean v ) {
		if ( v == ATIVO.value )
			return ATIVO;
		return INATIVO;
	}
	

}
