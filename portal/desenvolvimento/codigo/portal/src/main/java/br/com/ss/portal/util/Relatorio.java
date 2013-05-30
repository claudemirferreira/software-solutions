package br.com.ss.portal.util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.ss.portal.model.entity.AbstractEntity;

public class Relatorio<T extends AbstractEntity> implements Serializable {

	private Date dataInicial;
	private Date dataFinal;

	private String path;

	private String titulo;

	private Map parametros = new HashMap();

	private List<T> resultList;

	public static String converterDataToString(Date data) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("" + f.format(data));
		return f.format(data);
	}

	public static String converterAnoToString(Date data) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy");
		return f.format(data);
	}

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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Map getParametros() {
		return parametros;
	}

	public void setParametros(Map parametros) {
		this.parametros = parametros;
	}

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

}
