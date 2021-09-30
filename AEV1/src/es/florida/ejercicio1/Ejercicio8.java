package es.florida.ejercicio1;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Ejercicio8 {

	public static void main(String[] args) {
		
		Scanner sc= new Scanner(System.in);
		String nombre;
		double anio;
		
		System.out.print("Introduce un nombre: ");
		nombre = sc.nextLine();
		System.out.print("Años de experiencia: ");
		anio = Integer.parseInt(sc.nextLine());
		
		if(anio < 1) {
			System.out.print(nombre + "\tDesarrollador Junior L1\t15000-18000");
		} else if(anio <= 3) {
			System.out.print(nombre + "\tDesarrollador Junior L2\t18000-22000");
		} else if(anio <= 5) {
			System.out.print(nombre + "\tDesarrollador Senior L1\t22000-28000");
		} else if(anio <= 8) {
			System.out.print(nombre + "\tDesarrollador Senior L2\t28000-36000");
		} else {
			System.out.print(nombre + "\tAnalista/Arquitecto\tSalario a convertir en base a rol");
		}
		
		
	}

}
