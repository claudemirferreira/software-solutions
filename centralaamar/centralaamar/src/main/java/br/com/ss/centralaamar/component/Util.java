package br.com.ss.centralaamar.component;

public class Util {
	
	public static String removeMask(String valor){
		valor = valor.trim();
		valor = valor.replace(" ","");
		valor = valor.replace("-","");
		valor = valor.replace("(","");
		valor = valor.replace(")","");
		return valor;
	}

}
