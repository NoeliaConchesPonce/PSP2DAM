package es.florida.aev4;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Hilo  implements Runnable{
	Socket cliente;

	/*
	 * Metode: Hilo 
	 * Descripcio: objeto hilo al que le paso como socket el cliente
	 * Parametre d'entrada: socket cliente 
	 * Parametre d'eixida: no
	 */
	public Hilo(Socket cliente) {
		this.cliente = cliente;
	}

	/*
	 * Metode: encriptarContra 
	 * Descripcio: genera una contrasenya encriptada a
	 * traves de una vacia 
	 * Parametre d'entrada: objecte contrasenya, String opcion
	 * Parametre d'eixida: contrasenya
	 */
	public Contrasenya encriptarContra(Contrasenya contrasenya, String opcion) throws NoSuchAlgorithmException {
		String contrasenyaTextoPlano = contrasenya.getTextoPlano();
		String contrasenyaEncriptada = "";
		switch (opcion) {
		case "1":
			for (int i = 0; i < contrasenyaTextoPlano.length(); i++) {
				char caracterActual = contrasenyaTextoPlano.charAt(i);
				int asciiCaracterActual = (int) caracterActual;
				int asciiCaracterSiguiente = asciiCaracterActual + 1;
				if (asciiCaracterSiguiente < 32 || asciiCaracterSiguiente > 126) {
					asciiCaracterSiguiente = 42;
				}
				char nuevoCaracter = (char) asciiCaracterSiguiente;
				contrasenyaEncriptada = contrasenyaEncriptada + nuevoCaracter;
			}
			contrasenya.setTextoEncriptada(contrasenyaEncriptada);

			break;

		case "2":
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] resultado = md.digest(contrasenya.getTextoPlano().getBytes(StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();
			for (byte b : resultado) {
				sb.append(String.format("%02x", b));
			}
			contrasenyaEncriptada = sb.toString();
			contrasenya.setTextoEncriptada(contrasenyaEncriptada);

			break;

		default:
		}
		return contrasenya;
	}

	/*
	 * Metode: run 
	 * Descripcio: lanza el hilo generando peticiones y enviando objetos
	 * Parametre d'entrada: no 
	 * Parametre d'eixida: no
	 */
	public void run() {
		
		InputStream is = null;
		try {
			is = cliente.getInputStream();
		} catch (IOException e6) {
			// TODO Auto-generated catch block
			e6.printStackTrace();
		}
		// lanza un hilo que envia a cliente el objeto contraseña vacia
		System.err.println(
				"SERVIDOR lanzando hilo y enviando objeto contrasenya vacia " + Thread.currentThread().getName());
		// guarda la contraseña vacia en cliente
		ObjectOutputStream outObjeto = null;
		try {
			outObjeto = new ObjectOutputStream(cliente.getOutputStream());
		} catch (IOException e5) {
			// TODO Auto-generated catch block
			e5.printStackTrace();
		}
		// creamos un objeto contraseña vacio
		Contrasenya contrasenya = new Contrasenya("null", "null");
		// escribe el objeto contraseña recien creado, en este caso una vacia
		try {
			outObjeto.writeObject(contrasenya);
		} catch (IOException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}

		// muestra un mensaje que va a enviar dos opciones al cliente
		System.err.println(
				"SERVIDOR " + Thread.currentThread().getName() + " >>> Envia opciones para encriptar la contrasenya al cliente");
		// guarda la opcion elegida por el cliente
		OutputStream os = null;
		try {
			os = cliente.getOutputStream();
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		// imprime la opcion del cliente
		PrintWriter pw = new PrintWriter(os);
		// escribe el mensaje al cliente para que elija
		pw.write("Escribe 1 para encriptar metodo ASCII, 2 para encriptar en metodo MD5)\n");
		// enviar
		pw.flush();

		// muestra un mensaje de espera a la opcion elegida por el cliente
		System.err.println("SERVIDOR " + Thread.currentThread().getName() + " >>> Esperando opcion elegida por el cliente...");

		// imprime una linea indicando que va a recibir el objeto contrasenya ya
		// completo
		System.err.println("SERVIDOR  " + Thread.currentThread().getName() + " >>> Recibe opcion elegida para la creacion de la contrasenya ");
		// extrae la opcion elegida por el cliente
		ObjectInputStream inObjeto = null;
		try {
			inObjeto = new ObjectInputStream(cliente.getInputStream());
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		// guarda en una variable contrasenyaMod la contrasenya leida
		Contrasenya contrasenyaMod = null;
		try {
			contrasenyaMod = (Contrasenya) inObjeto.readObject();
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// muestra un mensaje de lo que va a hacer
		System.err.println(
				"SERVIDOR " + Thread.currentThread().getName() + " >>> Recibe opcion del cliente para encriptacion");
		// va a leer la peticion del cliente
		InputStreamReader isr = new InputStreamReader(is);
		// guarda en un buffer la lectura
		BufferedReader br = new BufferedReader(isr);
		// lee la opcion
		String opcion = null;
		try {
			opcion = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// va a llamar al metodo de encriptacion para generar la contrasenya
		System.err.println("SERVIDOR " + Thread.currentThread().getName() + " >>> Inicia la encriptacion");
		Contrasenya contrasenyaEncriptada = null;
		try {
			contrasenyaEncriptada = encriptarContra(contrasenyaMod, opcion);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// manda al cliente la contrasenya ya encriptada
		System.err.println("SERVIDOR " + Thread.currentThread().getName() + " >>> Devuelve contrasenya ya encriptada");
		try {
			outObjeto.writeObject(contrasenyaEncriptada);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// enseña dos mensajes como que va a finalizar el hilo
		System.err.println("SERVIDOR " + Thread.currentThread().getName() + " >>> Fin del hilo");
		System.err.println("SERVIDOR >>> Espera peticion...");
	}
}
