package br.com.ss.centralaamar.model.entity;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = -5098410375621527509L;

	private String adress;

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

}
