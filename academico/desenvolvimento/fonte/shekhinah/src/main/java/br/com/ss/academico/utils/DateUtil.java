package br.com.ss.academico.utils;

import java.util.Date;

public class DateUtil {
	
	private DateUtil() { }

	/**
	 * Valida se a data informada está no futuro.
	 * @param date
	 * @return boolean
	 */
	public static boolean isDataFuturo( Date date) {
		return date.getTime() > new Date().getTime();
	}
	
}
