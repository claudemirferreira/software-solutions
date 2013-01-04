package br.com.ss.centralaamar.component;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.ss.centralaamar.model.entity.Membro;

public class Relatorio implements Serializable {

	private Date dataInicial;
	private Date dataFinal;

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public static String converterDataToString(Date data) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("" + f.format(data));
		return f.format(data);
	}

	public static String converterAnoToString(Date data) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy");
		return f.format(data);
	}

	public static String montarSql(String d1, String d2, String ano) {

		String sql = "SELECT t FROM Membro t " + "where CONCAT(" + ano
				+ ", SUBSTRING(dataNascimento,5,6)) between '" + d1 + "' and '"
				+ d2 + "' order by dataNascimento ";
		System.out.println(sql);
		return sql;
	}
	
	public static String montarSqlPais(String sexo) {

		String sql = "SELECT t FROM Membro t " + "where temFilho ='S' and sexo = '"+sexo+"'" ;
		System.out.println(sql);
		return sql;
	}
	
	public static String montarSqlPesquisa(Membro membro) {

		String sql = "SELECT t FROM Membro t ";
		
//		"where temFilho ='S' and sexo = '"+sexo+"'" ;
		System.out.println(sql);
		return sql;
	}

}
