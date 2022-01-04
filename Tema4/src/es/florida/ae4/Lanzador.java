package es.florida.ae4;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Lanzador {
	public static void lanzar(String clase) {
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

			// System.out.println("Comando que se pasa a ProcessBuilder: " + command);
			// System.out.println("Comando a ejecutar en cmd.exe: " +
			// command.toString().replace(",",""));

			ProcessBuilder builder = new ProcessBuilder(command);
			Process process = builder.inheritIO().start();// inherit sirve para poder ver el resultado de la ejecucion
			// Process process = builder.start();
			process.waitFor();
			// System.out.println(process.exitValue());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		lanzar("es.florida.ae4.Cliente");
		lanzar("es.florida.ae4.Servidor" );
	}
}
