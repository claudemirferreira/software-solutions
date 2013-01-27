package br.com.ss.centralaamar.component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Batizado implements Serializable {

	private static final long serialVersionUID = -9132226054781979390L;

	private String codigo;
	private String descricao;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static List<Batizado> list() {
		List<Batizado> batizadoS = new ArrayList<Batizado>();
		Batizado s1 = new Batizado("S", "SIM");
		batizadoS.add(s1);
		Batizado s2 = new Batizado("N", "NÃO");
		batizadoS.add(s2);
		return batizadoS;
	}

	public Batizado(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
}
