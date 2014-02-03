package br.com.ss.data.util;

public class Arredondar {
	
	public static void main(String[] args) {
		
		System.out.println(arredondar(9));
		
		System.out.println(arredondar(10));
		
		
	}

	public static int arredondar(int valor) {
		double valorfloat = Math.sqrt(valor);
		int valorint = (int) valorfloat;

		if (valorfloat > valorint)
			valorint++;
		return valorint;
	}

}
