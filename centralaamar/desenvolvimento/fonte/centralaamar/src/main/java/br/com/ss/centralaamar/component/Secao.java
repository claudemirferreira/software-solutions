package br.com.ss.centralaamar.component;

import java.util.ArrayList;
import java.util.List;

public class Secao {

	public Secao(String numero) {
		this.numero = numero;
	}

	public Secao() {
	}

	private String numero;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	private List<Secao> secoes = new ArrayList<Secao>();

	public List<Secao> getSecoes() {
		return secoes;
	}

	public void setSecoes(List<Secao> secoes) {
		this.secoes = secoes;
	}

	public static List<Secao> findSecoes() {
		List<Secao> list = new ArrayList<Secao>();
		list.add(new Secao("1"));
		list.add(new Secao("2"));
		list.add(new Secao("3"));
		list.add(new Secao("4"));
		list.add(new Secao("5"));
		list.add(new Secao("6"));
		list.add(new Secao("7"));
		list.add(new Secao("8"));
		list.add(new Secao("9"));
		list.add(new Secao("10"));
		list.add(new Secao("11"));
		list.add(new Secao("12"));
		list.add(new Secao("13"));
		list.add(new Secao("14"));
		list.add(new Secao("15"));
		list.add(new Secao("16"));
		list.add(new Secao("17"));
		list.add(new Secao("18"));
		list.add(new Secao("19"));
		list.add(new Secao("20"));
		list.add(new Secao("21"));
		list.add(new Secao("22"));
		list.add(new Secao("23"));
		list.add(new Secao("24"));
		list.add(new Secao("25"));
		list.add(new Secao("26"));
		list.add(new Secao("27"));
		list.add(new Secao("28"));
		list.add(new Secao("29"));
		list.add(new Secao("30"));
		list.add(new Secao("31"));
		list.add(new Secao("32"));
		list.add(new Secao("33"));
		list.add(new Secao("34"));
		list.add(new Secao("35"));
		list.add(new Secao("36"));
		list.add(new Secao("37"));
		list.add(new Secao("38"));
		list.add(new Secao("39"));
		list.add(new Secao("40"));
		list.add(new Secao("41"));
		list.add(new Secao("42"));
		list.add(new Secao("43"));
		list.add(new Secao("44"));
		list.add(new Secao("45"));
		list.add(new Secao("46"));
		list.add(new Secao("47"));
		list.add(new Secao("48"));
		list.add(new Secao("49"));
		list.add(new Secao("50"));
		list.add(new Secao("51"));
		list.add(new Secao("52"));
		list.add(new Secao("53"));
		list.add(new Secao("54"));
		list.add(new Secao("55"));
		list.add(new Secao("56"));
		list.add(new Secao("57"));
		list.add(new Secao("54"));
	

		System.out.println("total = " + list.size());

		return list;
	}
	
	

}
