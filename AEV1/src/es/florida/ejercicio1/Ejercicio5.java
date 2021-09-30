package es.florida.ejercicio1;

public class Ejercicio5 {

	public static void main(String[] args) {
		String[] nombres  = {"noelia", "manel", "ana", "nestora"};
		System.out.println(mayor(nombres));
	}
	
	public static String mayor(String[] nombres) {
		String mayor = nombres [0];
		
		for ( int i = 0; i < nombres.length; i++) {
			if (nombres[i].length() > mayor.length() ) {
				mayor = nombres[i];
			}
		}
		
		return mayor;
	}
}
