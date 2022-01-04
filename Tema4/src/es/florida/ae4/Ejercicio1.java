package es.florida.ae4;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;




public class Ejercicio1 {
	
	public static void main (String[] args){
		
		String url="http://localhost:80"+"/PSPae4/recurso.txt";
		String fichero = "recurso.txt";
		descargarArchivo(url,fichero);
		} 
	
	
	public static void descargarArchivo(String url_descargar, String nombreArchivo) {
		System.out.println("Descargando " + url_descargar);
		try {
			URL laUrl = new URL(url_descargar);
			InputStream is = laUrl.openStream();
			InputStreamReader reader = new InputStreamReader(is);
			BufferedReader bReader = new BufferedReader(reader);
			FileWriter escritorFichero = new FileWriter(nombreArchivo);
			String linea;
			while ((linea = bReader.readLine()) != null) {
				escritorFichero.write(linea);
			}
			escritorFichero.close();
			bReader.close();
			reader.close();
			is.close();
		} catch (MalformedURLException e) {
			System.out.println("URL mal escrita!");
		} catch (IOException e) {
			System.out.println("Fallo en la lectura del fichero");
		}
	}
}
