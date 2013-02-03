package br.com.ss.centralaamar.component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ModoConversao implements Serializable {

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

	public static List<ModoConversao> list() {
		List<ModoConversao> modoConversoes = new ArrayList<ModoConversao>();
		ModoConversao s1 = new ModoConversao("1", "ESTUDO BIBLICO");
		modoConversoes.add(s1);
		ModoConversao s2 = new ModoConversao("2", "ESCOLA SABATINA");
		modoConversoes.add(s2);
		ModoConversao s3 = new ModoConversao("3", "PEQUENO GRUPO");
		modoConversoes.add(s3);
		ModoConversao s4 = new ModoConversao("4", "EVANGELISMO");
		modoConversoes.add(s4);
		ModoConversao s5 = new ModoConversao("5", "ESCOLA SABATINA");
		modoConversoes.add(s5);
		ModoConversao s6 = new ModoConversao("6", "OPERAÇÃO RESGATE");
		modoConversoes.add(s6);
		ModoConversao s7 = new ModoConversao("7", "COMPORTOR");
		modoConversoes.add(s7);
		ModoConversao s8 = new ModoConversao("8", "DE BERCO");
		modoConversoes.add(s8);
		ModoConversao s9 = new ModoConversao("9", "NENHUMA DAS OPCOES");
		modoConversoes.add(s9);

		return modoConversoes;
	}

	public ModoConversao(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
}
