package es.florida.ae4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//System.out.print("Host: ");
		//String host = br.readLine();
		String host = "host";
		//System.out.print("Puerto: ");
		//int puerto = Integer.parseInt(br.readLine());
		int puerto = 5000;
		System.out.println("CLIENTE >> Arranca cliente");
		Socket cliente = new Socket(host, puerto);
		ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
		Persona p = (Persona) inObjeto.readObject();
		System.out.println("CLIENTE >> Recibo del servidor: " + p.getNombre() + " - " + p.getEdad());
		System.out.print("Nombre: ");
		p.setNombre(br.readLine());
		System.out.print("Edad: ");
		p.setEdad(Integer.parseInt(br.readLine()));
		ObjectOutputStream pMod = new ObjectOutputStream(cliente.getOutputStream());
		pMod.writeObject(p);
		System.out.println("CLIENTE >> Envio al servidor: " + p.getNombre() + " - " + p.getEdad());
		inObjeto.close();
		pMod.close();
		cliente.close();
	}

}