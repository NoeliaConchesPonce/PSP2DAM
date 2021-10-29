package es.florida.AEV2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Sumador {
	/*
	 * Metode: sumar 
	 	 * Descripcio: Fa els calculs necesaris per a medir el percentatje.
		 * Parametres d'entrada: nombreNEO, posicioNEO, velocidadNEO
		 * Parametres d'exida: resultado.
	 */

	public static double sumar(String nombreNEO, double posicionNEO, double velocidadNEO){
		double posicionTierra = 1;
		double velocidadTierra = 100;
		for (int i = 0; i < (50 * 365 * 24 * 60 * 60); i++) {
		posicionNEO = posicionNEO + velocidadNEO * i;
		posicionTierra = posicionTierra + velocidadTierra * i;
		}
		double resultado = 100 * Math.random() *
			Math.pow( ((posicionNEO-posicionTierra)/(posicionNEO+posicionTierra)), 2);
		
		return resultado;
		}
		
	/*
	 * Metode: main 
	 	 * Descripcio: comproba el resultat obtés en el métode anterior y conforme al resultat mostra un missatge o un altre.
		 * Parametres d'entrada: no
		 * Parametres d'exida: no
	 */
	
		public static void main(String[] args){
		Sumador s = new Sumador();
		String nombreNEO = args[0]; 
		double posicionNEO = Double.parseDouble(args[1]);
		double velocidadNEO = Double.parseDouble(args[2]);
		double resultado = s.sumar(nombreNEO, posicionNEO, velocidadNEO);
		
		
		if(resultado > 10) {
			System.err.println("ALERTA MUNDIAL El asteroide " + nombreNEO + "tiene un porcentaje de colision de " + String.format("%.2f", resultado) + "%" + "\n");
		}else {
			System.out.println("TRANQUILIDAD  El asteroide " + nombreNEO + "tiene un porcentaje de colision de " + String.format("%.2f", resultado) + "%" + "\n");
		}
		
		//mostrar la probabilidad de colision 
		try {
            File fichero = new File(nombreNEO + ".txt");
            FileWriter fw = new FileWriter(fichero);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("La probilidad de colision es de: " + String.format("%.2f", resultado) + "%");
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		}

}
