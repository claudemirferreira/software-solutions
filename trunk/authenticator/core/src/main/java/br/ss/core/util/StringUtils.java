package br.ss.core.util;

import java.text.Normalizer;

public class StringUtils {

	public StringUtils() {
	}

	public static String removerAcento(String str) {
        str = Normalizer.normalize(str, Normalizer.Form.NFD);
        str = str.replaceAll("[^\\p{ASCII}]", "");
        return str;
    }
}
