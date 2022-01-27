package es.florida.aev4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	/*Metodo: main
	 * Descripcio: inicializa el servidor y abre el puerto de escucha, lanza la peticion
	 * Parametres d'entrada: no
	 * Parametres d'eixida: no
	 * */
	public static void main(String[] args) {
		//muestro un mensaje para esperar la peticion del cliente
		System.err.println("SERVIDOR >>> Arranca el servidor, espera peticion...");
		//inicializo la variable socket escucha a 0
		ServerSocket socketEscucha = null;
		try {
			//creo el servidor pasandole el puerto 1234
			socketEscucha = new ServerSocket(1234);
		} catch (IOException e) {
			//mensaje de error por si hay algo que falla en la peticion del cliente
			System.err.println("SERVIDOR >>> Error");
			e.printStackTrace();
			return;
		}
		while (true) {	
			//creo la variable conexion
			Socket conexion;
			try {
				//lanzo un objto hilo con la peticion que hara el servidor
				conexion = socketEscucha.accept();
				System.err.println("SERVIDOR >>> Conexion recibida --> Lanza hilo clase Peticion");
				Hilo h = new Hilo(conexion);
				Thread hilo = new Thread(h);
				hilo.start();
			} catch (IOException e) {
				System.err.println("SERVIDOR >>> Error");
				e.printStackTrace();
			}
		}
	}
}
