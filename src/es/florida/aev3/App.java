package es.florida.aev3;

public class App {
	/*Métode: main
	 *Descripció: crea els hilos i executa els mateixos hilos cridant als métodes de les classes
	 *Paràmetres d'entrada: String [] args
	 *Paràmetres d'eixida: no
	 * */
	public static void main(String[] args) throws InterruptedException {
		int stock = 20;
		Mina mina1 = new Mina(stock);
		Ventilador ventilador = new Ventilador();

		Thread hiloEncender = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					ventilador.encenderVentilador();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Thread hiloApagar = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					ventilador.apagarVentilador();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		hiloEncender.start();
		hiloApagar.start();

		Thread hilo1;
		for (int i = 0; i < 10; i++) {
			hilo1 = new Thread(new Minero(mina1));
			hilo1.start();
		}

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("El total extraido es: " + Minero.totalExtraido + " y en la mina habia " + stock);
		
		try {
			hiloEncender.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hiloApagar.join();
	}
}
