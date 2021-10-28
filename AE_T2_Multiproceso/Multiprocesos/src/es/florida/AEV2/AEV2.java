package es.florida.AEV2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AEV2 {
	
	private static String fichero;

	public static void main(String[] args) {
		long startTime = System.nanoTime();
		fichero = "NEOs.txt";
		AEV2 actividad = new AEV2();
		
		int cores = Runtime.getRuntime().availableProcessors();
		for(int i = 0; i < cores; i++) {
			String[] lineas = actividad.contenidoFichero(fichero).get(i).split(",");
			actividad.lanzarSumador(lineas[0], lineas[1], lineas[2]);
		}
		
		long endTime = System.nanoTime();
		
		// calcula el tiempo total que ha durado la aplicacion
        long mSegundos =(long) ((endTime-startTime)/1e6);
        double tiempo = (mSegundos/1000);
        String segundos = String.format("%.2f", tiempo);
            System.out.println(segundos +" segundos");
            
        // calculo de la media del tiempo que ha durado por cada core
        double mediaTime = ( tiempo / cores);
        String media = String.format("%.2f", mediaTime);
        System.out.println("El tiempo medio es: "+media);
		
		
	}
	
	public ArrayList<String> contenidoFichero(String fichero){
		ArrayList<String> contenidoFichero = new ArrayList<String>();
		File f = new File(fichero);
		
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String linea = br.readLine();
			while(linea != null) {
				contenidoFichero.add(linea);
				linea = br.readLine();
				
			}
			br.close();
			fr.close();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
		return contenidoFichero;
	}
	
	
	public void lanzarSumador( String nombreNEO,String posicionNEO, String velocidadNEO){
		String clase = "es.florida.AEV2.Sumador";
		try {

		String javaHome = System.getProperty("java.home");
		String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
		String classpath = System.getProperty("java.class.path");
		// System.out.println(classpath);
		String className = clase;

		List<String> command = new ArrayList<>();
		command.add(javaBin);
		command.add("-cp");
		command.add(classpath);
		command.add(className);
		command.add(nombreNEO.toString());
		command.add(posicionNEO.toString());
		command.add(velocidadNEO.toString());


		// System.out.println("Comando que se pasa a ProcessBuilder: " + command);
		// System.out.println("Comando a ejecutar en cmd.exe: " + command.toString().replace(",",""));

		ProcessBuilder builder = new ProcessBuilder(command);
		Process process = builder.inheritIO().start();//inherit sirve para poder ver el resultado de la ejecucion
		// Process process = builder.start();
		process.waitFor();
		//System.out.println(process.exitValue());

		} catch (Exception e) {
		e.printStackTrace();
		}
		}

}
