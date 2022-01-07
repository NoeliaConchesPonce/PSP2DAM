package es.florida.aev4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Peticion implements Runnable{

	
		BufferedReader br;
		PrintWriter pw;
		Socket socket;
		
		/* Metode: Peticion
		 * Descripcio: Constructor que rep el socket.
		 * Parametre d'entrada: String socket
		 * Parametre d'eixida: no
		 * */
		public Peticion(Socket socket){
			this.socket = socket;
		} 
		
		/* Metode: ASCII
		 * Descripcio: Encripta la contrasenya en codi ASCII 
		 * Parametre d'entrada: String contrasenya
		 * Parametre d'eixida: pwdGenerada
		 * */
		public String ASCII(String contrasenya) {
	        char pwd[] = contrasenya.toCharArray();
	        String pwdGenerada = "";
	        for (int i = 0; i < pwd.length; i++) {
	            if ( pwd[i] <= 31 || pwd[i] == 127) {
	            	pwdGenerada += '*';
	            } else {
	            	pwdGenerada += (char) (pwd[i] + 1);
	            }
	        }
	        return pwdGenerada;
	    }
		
		/* Metode: MD5
		 * Descripcio: Encripta la contrasenya en codi MD5 
		 * Parametre d'entrada: String contrasenya
		 * Parametre d'eixida: hashtext
		 * 
		 * Bibliografia: https://www.yoelprogramador.com/como-encriptar-contrasenas-en-md5-en-java/
		 * */
		public static String MD5(String contrasenya) {
	        try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            byte[] messageDigest = md.digest(contrasenya.getBytes());
	            BigInteger number = new BigInteger(1, messageDigest);
	            String hashtext = number.toString(16);

	            while (hashtext.length() < 32) {
	                hashtext = "0" + hashtext;
	            }
	            return hashtext;
	        } catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException(e);
	        }
	    }
		
		/* Metode: run
		 * Descripcio: executa el servidor
		 * Parametre d'entrada: no
		 * Parametre d'eixida: no
		 * */
		public void run() {
			try {
				
				ObjectOutput outObjeto = new ObjectOutputStream(socket.getOutputStream());
				Password contrasenya = new Password("", "");
				outObjeto.writeObject(contrasenya);
				

				ObjectInputStream inObjeto = new ObjectInputStream(socket.getInputStream());
				Password pwdRecibida = (Password) inObjeto.readObject();
				System.err.println("SERVIDOR >>> Contrasenya recibida del cliente: "+pwdRecibida.getNuevaPassword());
				

				System.err.println("SERVIDOR >> Seleccione el metodo de encriptacion que desea aplicar: ");
				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				br = new BufferedReader(isr);
				OutputStream os = socket.getOutputStream();
				
		
				pw = new PrintWriter(os);
				int opcion = Integer.parseInt(br.readLine());
				String tipo = null;
				if(opcion == 1) {
					tipo = "ASCII";
				} 
				if(opcion == 2) {
					tipo = "MD5";
				}
				System.err.println("SERVIDOR >>> La opcion recibida ha sido: " + tipo);
				String nuevaPwd = pwdRecibida.getNuevaPassword();
				String pwdEcriptada = "";
				System.err.println("SERVIDOR >>> Encriptando...");
				if(opcion == 1) {
					pwdEcriptada = ASCII(nuevaPwd);
				} 
				if(opcion == 2) {
					pwdEcriptada = MD5(nuevaPwd);
				} 
				
				
				System.err.println("SERVIDOR >>> Su contrasenya encriptada es: "+pwdEcriptada);
				pw.write(pwdEcriptada.toString()+"\n");
				pwdRecibida = new Password(nuevaPwd, pwdEcriptada);
				System.err.println("SERVIDOR >>> Enviado a cliente ");
				outObjeto.writeObject(pwdRecibida); 
				
				pw.flush();
				outObjeto.close();
				inObjeto.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("SERVIDOR >>> ERROR :(");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}

