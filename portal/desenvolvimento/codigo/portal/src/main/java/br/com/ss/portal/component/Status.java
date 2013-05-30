package br.com.ss.portal.component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class Status implements Serializable {

	private static final long serialVersionUID = -9132226054781979390L;

	private String codigo;
	
	private String nome;
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static List<Status> list() {
		List<Status> listStatus = new ArrayList<Status>();
		Status s1 = new Status("A", "ATIVO");
		listStatus.add(s1);
		Status s2 = new Status("B", "BLOQUEADO");
		listStatus.add(s2);
		Status s3 = new Status("I", "INATIVO");
		listStatus.add(s3);
		return listStatus;
	}

	public Status(String codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}
}
