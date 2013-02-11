package br.com.ss.centralaamar.domain;

import lombok.Getter;

public enum SimNaoDomain {
	
	SIM ((short) 0, "Sim", true),
	NAO((short) 1, "Não", false);

    @Getter
    private short id;
    
    @Getter
	private String descricao;

    @Getter
	private boolean value;
	
	private SimNaoDomain( short id, String desc, boolean value ) {
		this.id = id;
		this.descricao = desc;
		this.value = value;
	}
	
	public static SimNaoDomain getEnumByValue( boolean v ) {
		if ( v == SimNaoDomain.SIM.value ) {
			return SimNaoDomain.SIM;
		} else {
			return SimNaoDomain.NAO;
		}
	}

	public String getDescricao() {
		return descricao;
	}

	public boolean isValue() {
		return value;
	}
	
	

}
