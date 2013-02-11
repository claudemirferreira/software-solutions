package br.com.ss.portal.util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import br.com.ss.portal.model.entity.AbstractEntity;

public class Relatorio<T extends AbstractEntity> implements Serializable {

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
	private String titulo;

	@Getter
	@Setter
	private Map parametros = new HashMap();

	@Getter
	@Setter
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

}
