package es.florida.ejercicio1;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Ejercicio2 {

	public static void main(String[] args) {
		String[] nombres = new String[] {"Noelia", "Ximo", "Nestor", "Pablo", "Manel", "Alex"};
		
		for(int i=0; i<nombres.length; i++) {
			System.out.println(nombres[i]);

		}
		
		ArrayList<String> nombres2 = new ArrayList<String>();
		nombres2.add("Noelia");
		nombres2.add(1, "Ximo");
		nombres2.add(2, "Nestor");
		nombres2.add(3, "Pablo");
		nombres2.add(4, "Manel");
		nombres2.add(5, "Alex");
		
		for (int i= 0; i<nombres2.size(); i++) {
			System.out.println(nombres[i]);

		}
		
}
	
	
}
