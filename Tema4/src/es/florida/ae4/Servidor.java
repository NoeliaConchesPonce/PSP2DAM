package es.florida.ae4;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] arg) throws IOException, ClassNotFoundException {
		int numeroPuerto = 5000;
		ServerSocket servidor = new ServerSocket(numeroPuerto);
		System.err.println("SERVIDOR >> Escuchando...");
		Socket cliente = servidor.accept();
		ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream());
		Persona p = new Persona("Nombre", 0);
		outObjeto.writeObject(p);
		System.err.println("SERVIDOR >> Envio a cliente: " + p.getNombre() + " - " + p.getEdad());
		ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
		Persona pMod = (Persona) inObjeto.readObject();
		System.err.println("SERVIDOR >> Recibo de cliente: " + pMod.getNombre() + " - " + pMod.getEdad());
		outObjeto.close();
		inObjeto.close();
		cliente.close();
		servidor.close();
	}

}
