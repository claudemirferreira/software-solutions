package br.com.ss.centralaamar.component;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import br.com.ss.centralaamar.model.entity.AbstractEntity;
import br.com.ss.centralaamar.model.entity.Membro;

public class Relatorio implements Serializable {

	@Getter
	@Setter
	private Date dataInicial;
	@Getter
	@Setter
	private Date dataFinal;

	@Getter
	@Setter
	private String path;

	@Getter
	@Setter
	private List<AbstractEntity> resultList = new ArrayList<AbstractEntity>();

	public static String converterDataToString(Date data) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("" + f.format(data));
		return f.format(data);
	}

	public static String converterAnoToString(Date data) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy");
		return f.format(data);
	}

	public static String montarSqlnew(String d1, String d2, String ano) {

		String sql = "SELECT DATE_ADD( CONCAT('2013', SUBSTRING(A.dataNascimento,5,6)), INTERVAL 0 DAY) dataNascimento, "
				+ "t.NOME, t.FONE_COMERCIAL, t.FONE_RESIDENCIAL"
				+ "FROM Membro t "
				+ "where CONCAT("
				+ ano
				+ ", SUBSTRING(dataNascimento,5,6)) between '"
				+ d1
				+ "' and '"
				+ d2 + "' order by dataNascimento ";
		System.out.println(sql);
		return sql;
	}

	public static String montarSql(String d1, String d2, String ano) {

		String sql = "SELECT t FROM Membro t " + "where CONCAT(" + ano
				+ ", SUBSTRING(dataNascimento,5,6)) between '" + d1 + "' and '"
				+ d2 + "' order by dataNascimento ";
		System.out.println(sql);
		return sql;
		/*
		 * SELECT DATE_ADD( CONCAT('2013', SUBSTRING(A.data_Nascimento,5,6)),
		 * INTERVAL 0 DAY) data1, NOME, FONE_COMERCIAL, FONE_RESIDENCIAL FROM
		 * Membro A where CONCAT(2013, SUBSTRING(A.data_Nascimento,5,6)) between
		 * '2013-02-01' and '2013-02-28'
		 * 
		 * order by data1 desc
		 */
	}

	public static String montarSqlPais(String sexo) {

		String sql = "SELECT t FROM Membro t "
				+ "where temFilho ='S' and sexo = '" + sexo + "'";
		System.out.println(sql);
		return sql;
	}

	public static String montarSqlPesquisa(Membro membro) {

		String sql = "SELECT t FROM Membro t ";

		// "where temFilho ='S' and sexo = '"+sexo+"'" ;
		System.out.println(sql);
		return sql;
	}

}
