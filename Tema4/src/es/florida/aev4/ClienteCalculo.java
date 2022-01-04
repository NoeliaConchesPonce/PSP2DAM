package es.florida.aev4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClienteCalculo {
	
	public static void lanzar(String op, int n1, int n2, String cliente) throws IOException {
		System.out.println("CLIENTE >>> Arranca cliente");
		System.out.println("CLIENTE >>> Conexion al servidor");
		InetSocketAddress direccion = new InetSocketAddress("172.20.10.8", 9876);
		Socket socket = new Socket();
		socket.connect(direccion);
		System.out.println("CLIENTE >>> Preparado canal para recibir resultado");
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader bfr = new BufferedReader(isr);
		System.out.println("CLIENTE >>> Envio de datos para el calculo");
		PrintWriter pw = new PrintWriter(socket.getOutputStream());
		pw.print(op + "\n");
		pw.print(n1 + "\n");
		pw.print(n2 + "\n");
		pw.print(cliente + "\n");
		pw.flush();
		String resultado = bfr.readLine();
		System.out.println("CLIENTE >>> Recibe resultado: " + resultado);
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		String op, nom, seguir;
		int n1, n2;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		do {
			System.out.print("Operación (r/s/m/d): ");
			op = br.readLine();
			System.out.print("Número 1: ");
			n1 = Integer.parseInt(br.readLine());
			System.out.print("Número 2: ");
			n2 = Integer.parseInt(br.readLine());
			System.out.print("Nombre cliente: ");
			nom = br.readLine();
			lanzar(op, n1, n2, nom);
			System.out.print("Seguir? (s/n): ");
			seguir = br.readLine();
		} while(!seguir.equals("n"));
	}

}