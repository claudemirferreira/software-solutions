package com.tutorialspoint.modelo;

import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the iansa_curso database table.
 */
public class ConfirmacaoLeitura implements Serializable {

	private static final long serialVersionUID = -8267695195052327303L;

	private String businessKey;

	private String login;

	private Date data;

	private Date dataConfirmacao;

	private Boolean status;

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getDataConfirmacao() {
		return dataConfirmacao;
	}

	public void setDataConfirmacao(Date dataConfirmacao) {
		this.dataConfirmacao = dataConfirmacao;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}