package es.florida.ejercicio1;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Ejercicio6 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Integer[] nums = new Integer[5]; 
		Integer suma = 0;
		for (int i = 0; i < 5; i++) {
			System.out.println("Introduce un numero: ");
			int numero = sc.nextInt();
			nums[i] = numero;
			suma += numero;
		}
		
		Arrays.sort(nums, Collections.reverseOrder());
		for(Integer numero : nums) {
			System.out.println(numero);
		}
		
		System.out.println("la suma de los numeros introducidos es : " + suma);
		
		
		
		
		
	}

}
