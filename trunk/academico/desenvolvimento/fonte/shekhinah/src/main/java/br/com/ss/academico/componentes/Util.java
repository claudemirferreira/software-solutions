package br.com.ss.academico.componentes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Util {

	public static int definirTamanhoColuna(int tamanho) {

		double col = Math.sqrt(tamanho);
		int colunas = (int) col;

		if (colunas < col)
			colunas++;

		return colunas;
	}

	public static List<Integer> pegarAnos() {
		List<Integer> anos = new ArrayList<Integer>();
		Calendar cal = Calendar.getInstance();
		anos.add(cal.get(Calendar.YEAR) + 1);
		anos.add(cal.get(Calendar.YEAR));
		return anos;
	}

}
