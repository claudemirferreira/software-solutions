package br.com.ss.centralaamar.component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MembroIgreja implements Serializable {

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

	public static List<MembroIgreja> list() {
		List<MembroIgreja> membroIgrejas = new ArrayList<MembroIgreja>();
		MembroIgreja s1 = new MembroIgreja("S", "SIM");
		membroIgrejas.add(s1);
		MembroIgreja s2 = new MembroIgreja("N", "NÃO");
		membroIgrejas.add(s2);
		return membroIgrejas;
	}

	public MembroIgreja(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
}
