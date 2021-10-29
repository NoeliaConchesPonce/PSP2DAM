package es.florida.AE2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ejercicio2 {
	public void lanzarSumador(Integer n1,Integer n2){
		String clase = "es.florida.AE2.Ejercicio1";
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
		command.add(n1.toString());
		command.add(n2.toString());

		// System.out.println("Comando que se pasa a ProcessBuilder: " + command);
		// System.out.println("Comando a ejecutar en cmd.exe: " + command.toString().replace(",",""));

		ProcessBuilder builder = new ProcessBuilder(command);
		Process process = builder.inheritIO().start();//inherit sirve para poder ver el resultado de la ejecucion
		// Process process = builder.start();
		process.waitFor();
		System.out.println(process.exitValue());

		} catch (Exception e) {
		e.printStackTrace();
		}
		}
		public static void main(String[] args){
		Ejercicio2 l = new Ejercicio2();
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce el primer numero: ");
		int num1 = sc.nextInt();
		System.out.println("Introduce el segundo numero: ");
		int num2 = sc.nextInt();
		l.lanzarSumador(num1, num2);

		System.out.println("Ok");
		}

	

}
