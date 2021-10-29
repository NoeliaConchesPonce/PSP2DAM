package es.florida.AE2;

import java.util.Scanner;

public class Ejercicio1 {

	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Introduce el primer numero: ");
//		int num1 = sc.nextInt();
//		System.out.println("Introduce el segundo numero: ");
//		int num2 = sc.nextInt();
		
		int num1 = Integer.parseInt(args[0]);
		int num2 = Integer.parseInt(args[1]);

		

		
		System.out.println("La suma desde " + num1 + " hasta " + num2 + " es " + suma(num1, num2));



	}
	
	public static int suma (int n1, int n2) {
		
		
		int suma = 0;
		for (int i = n1; i <= n2; i++) {
			suma += i;
		}
		
		return suma;
	}

}
