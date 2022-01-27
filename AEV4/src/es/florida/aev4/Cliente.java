package es.florida.aev4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
/*Metode: main
 * Descripcio: envia y recibe objetos del/al servidor
 * Parametres d'entrada: String[]args
 * Parametres d'eixida: no */
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);

		try {
			//muestra un mensaje y manda el host y el puerto de conexion al servidor
			System.out.println("CLIENTE >> Arranca cliente -> esperando mensaje del servidor...");
			Socket cliente = new Socket("localhost", 1234);
			
			//recibe una variable contrasenya vacia y la almacena en el objeto contrasenya
			System.out.println("CLIENTE >> Recibe contrasenya vacia del servidor");
			ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
			Contrasenya contrasenya = (Contrasenya) inObjeto.readObject();

			//muestra un mensaje como que va a recibir las opciones de encriptacion
			System.out.println("CLIENTE >> Recibe opciones para la encriptacion");
			//almacena en cliente la opcion elegida
			InputStream is = cliente.getInputStream();
			//introduce la opcion
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String opcionesEncriptacion = br.readLine();

			//pide al cliente que introduzca una contrasenya en texto plano
			System.out.print("CLIENTE >> Introduzca una contrasenya en sin caracteres extraordinarios, solo texto: ");
			String stringContrasenya = teclado.nextLine();
			//almacen ala contrasenya en una variable 
			contrasenya.setTextoPlano(stringContrasenya);
			
			//va a guardar la opcion seleccionada en una variable opcion
			System.out.print("CLIENTE >> Indique metodo encriptacion " + opcionesEncriptacion + ": ");
			String opcion = teclado.nextLine();

			//manda la variable almacenada en contrasenya y en opcion al servidor
			System.out.println("CLIENTE >> Envio contrasenya y la opcion  al servidor");
			ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream());
			outObjeto.writeObject(contrasenya);
			OutputStream os = cliente.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.write(opcion + "\n");
			pw.flush();

			//va a recibir la contrasenya encrptada ya por el servidor y la almacena en contrasenyaEncriptada
			Contrasenya contrasenyaEncriptada = (Contrasenya) inObjeto.readObject();
			System.out.println("CLIENTE >> Recibo la contrasenya ya encriptada: " + contrasenyaEncriptada.toString());
			//cierro cliente
			cliente.close();
			teclado.close();

			System.out.println("CLIENTE >> Fin");

		} catch (IOException | ClassNotFoundException e) {
			System.out.println("CLIENTE >> Error");
			e.printStackTrace();
		}
	}
}
