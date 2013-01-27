package br.com.ss.centralaamar.component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Sexo implements Serializable {

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

	public static List<Sexo> list() {
		List<Sexo> sexos = new ArrayList<Sexo>();
		Sexo s1 = new Sexo("M", "MASCULINO");
		sexos.add(s1);
		Sexo s2 = new Sexo("F", "FEMININO");
		sexos.add(s2);
		return sexos;
	}

	public Sexo(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
}
