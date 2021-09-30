package es.florida.ejercicio1;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Ejercicio7 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Introduce el primer numero del intervalo: ");
		int num1 = sc.nextInt();
		System.out.print("Introduce el segundo numero del intervalo: ");
		int num2 = sc.nextInt();
		
		if(num1 < num2) {
			for (int i = num1; i <= num2; i++) {
				
				System.out.print(i);
				
				if(esPrimo(i)) {
					System.out.println(" es primo");
				}else {
					System.out.println(" no es primo");

				}
				
				
			}
		}else{
				for (int i = num2; i <= num1; i++) {
				
				System.out.print(i);
				
				if(esPrimo(i)) {
					System.out.println(" es primo");
				}else {
					System.out.println(" no es primo");

				
			
				}
			}
		}
				
		
	}
	
	public static boolean esPrimo(int num) {
		
		for (int i = 2; i < num; i++) {
			if (num % i == 0) {
				return false;
			}
			
		}
		
		return true;

	}

}
