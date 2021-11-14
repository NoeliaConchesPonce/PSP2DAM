package es.florida.aev3;

public class Ventilador {
	boolean encendido = true;
	int tiempo;
	
	/*M�tode: encenderVentilador
	 * Descripci�: comprova que el ventilador estiga enc�s i si no es aixina l'enc�n
	 * Par�metres d'entrada:no
	 * Par�metres d'eixida:no
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

	
	/*M�tode:apagarVentilador
	 * Descripci�: comprova que el ventilador estiga enc�s i si no es aixina l'apaga
	 * Par�metres d'entrada:no
	 * Par�metres d'eixida:no
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
