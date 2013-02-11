package br.com.ss.centralaamar.component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TemFilho implements Serializable {

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

	public static List<TemFilho> list() {
		List<TemFilho> batizadoS = new ArrayList<TemFilho>();
		TemFilho s1 = new TemFilho("S", "SIM");
		batizadoS.add(s1);
		TemFilho s2 = new TemFilho("N", "NÃO");
		batizadoS.add(s2);
		return batizadoS;
	}

	public TemFilho(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
}
