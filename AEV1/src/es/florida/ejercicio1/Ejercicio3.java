package es.florida.ejercicio1;

public class Ejercicio3 {

	public static void main(String[] args) {
		
		
		System.out.println("La suma de los pares es " + SumaPares(18));
	}
	
	public static int SumaPares(int num) {
		
		int suma = 0;
		
		for(int i= 0; i<= num; i += 2 ) {
			
			suma += i;
		}
		
		return suma;
	}

}
