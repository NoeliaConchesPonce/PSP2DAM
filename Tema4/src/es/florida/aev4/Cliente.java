package es.florida.aev4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	
	/*Metode: main
	 * Descripcio: instancia el metodo lanzarCliente
	 * Parametre d'entrada: String[] args
	 * parametre d'eixida: no */
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// TODO Auto-generated method stub
		Scanner lee = new Scanner(System.in);
		
		Thread.sleep(5000);
		System.out.println("CLIENTE >> Arranca cliente");
		System.out.println("CLIENTE >> Conexion al servidor");
		String host = "localhost";
		int puerto = 1234;
		
		Socket cliente = new Socket(host, puerto);
		
		System.out.print("CLIENTE >>> Recibo contrasenya. ");
		ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
		Password pwd = (Password) inObjeto.readObject();

		System.out.print("SERVIDOR >>> Introduce una contrasenya: ");
		pwd.setNuevaPassword(lee.nextLine());
		ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream());
		outObjeto.writeObject(pwd);
		
		
		System.out.println("CLIENTE >>> Enviando contrasenya al servidor: "+pwd.getNuevaPassword());
		PrintWriter pw = new PrintWriter(cliente.getOutputStream());
		System.out.println("\nSERVIDOR >>> METODOS DE ENCRIPTACION:\n"
				+ "\t1: Encriptacion ASCII\n"
				+ "\t2: Encriptacion MD5 \n");
		System.out.print("Cual es el metodo de encriptacion que quieras usar?: ");
		String tipoEncriptacion = lee.nextLine();
		switch(tipoEncriptacion) {
			case "1":
				System.out.println("CLIENTE >>> Metodo de encriptacion seleccionado: ASCII");
				break;
			case "2":
				System.out.println("CLIENTE >>> Metodo de encriptacion seleccionado: MD5");
				break;
			default:
				System.err.println("SERVIDOR >>> ERROR! Parece ser que no has indicado un metodo de encriptacion valido :(");
		} // end-switch
		pw.print(tipoEncriptacion+"\n");
		pw.flush();
		
	
		pwd = (Password) inObjeto.readObject();
		System.out.println("CLIENTE >>> Contrasenya encriptada recibida del servidor: "+pwd.getPwdEncriptada());
		
		lee.close();
		inObjeto.close();
		outObjeto.close();
		cliente.close();
	}

}