package es.florida.aev3;

public class Ventilador {
	boolean encendido = true;
	int tiempo;
	
	/*Métode: encenderVentilador
	 * Descripció: comprova que el ventilador estiga encés i si no es aixina l'encén
	 * Paràmetres d'entrada:no
	 * Paràmetres d'eixida:no
	 * */
	public void encenderVentilador() throws InterruptedException {
		while (true) {
			synchronized (this) {
				// El hilo ventiladorEncender espera mientras el ventilador este encendido
				while (encendido)
					wait();

				System.out.println("El ventilador esta encendido");
				encendido = true;
				// Notifica al usuario que esta encendido
				notify();
				// Pausa para ver el proceso paso a paso
				Thread.sleep(1000);
			}
		}
	}

	
	/*Métode:apagarVentilador
	 * Descripció: comprova que el ventilador estiga encés i si no es aixina l'apaga
	 * Paràmetres d'entrada:no
	 * Paràmetres d'eixida:no
	 * */
	public void apagarVentilador() throws InterruptedException {
		while (true) {
			synchronized (this) {
				// El hilo ventiladorApagar espera mientras el ventilador este apagado
				while (!encendido)
					wait();

				System.out.println("El ventilador esta apagado");
				encendido=false;
				// Notifica al consumidor que el ventilador esta apagado
				notify();
				// Pausa para ver el proceso paso a paso
				Thread.sleep(1000);
			}
		}
	}
}
