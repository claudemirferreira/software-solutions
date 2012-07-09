package br.ss.authenticator.model.entity;

import lombok.Getter;
import lombok.Setter;

public class Detail {

	@Getter @Setter
	private String txDetail;
	
	@Getter @Setter
	private short status;
	
	
	public Detail() {
	}


	public Detail(String txDetail, short status) {
		super();
		this.txDetail = txDetail;
		this.status = status;
	}
	
	
}
