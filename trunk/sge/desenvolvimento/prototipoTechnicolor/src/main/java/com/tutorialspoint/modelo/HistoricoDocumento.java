package com.tutorialspoint.modelo;

public class HistoricoDocumento {

	private double versao;
	private String autorAlteracao;
	private String DataAlteracao;

	public double getVersao() {
		return versao;
	}

	public void setVersao(double d) {
		this.versao = d;
	}

	public String getAutorAlteracao() {
		return autorAlteracao;
	}

	public void setAutorAlteracao(String autorAlteracao) {
		this.autorAlteracao = autorAlteracao;
	}

	public String getDataAlteracao() {
		return DataAlteracao;
	}

	public void setDataAlteracao(String dataAlteracao) {
		DataAlteracao = dataAlteracao;
	}
}
