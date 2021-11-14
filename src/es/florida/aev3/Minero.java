package es.florida.aev3;

public class Minero implements Runnable {
	int bolsa;
	int tiempoExtraccion = 1000;
	static int totalExtraido;
	Mina mina;
	
	/*M�tode: Minero
	 * Descripci�: constructor de la classe minero 
	 * Par�metres d'entrada: objecte Mina
	 * Par�metres d'eixida:no
	 * */
	public Minero(Mina mina) {
		bolsa = 0;
		this.mina = mina;
	}
	
	/*M�tode: extraerRecurso
	 * Descripci�: m�tode que calcula els recursos restants que hi ha d'estoc en la mina y els retorna per seguir extraguent-ho
	 * Par�metres d'entrada:no
	 * Par�metres d'eixida:no
	 * */
	
	synchronized public void extraerRecurso() {
		// metodo que extraiga de la mina un recurso en cada turno y lo guarde en su
		// bolsa.
		
			while (mina.stock > 0) {
				// recursos
				int cantidad = (int) (Math.random() * 5 + 1);
				
				if(mina.stock >= cantidad) {
					
					// guardar la cantidad de recursos extraidos en la bolsa
					bolsa += cantidad;
					//le digo que la cantidad es lo que me ha extraido
					totalExtraido += cantidad;
					//le quito a la mina la cantidad extraida
					mina.stock -= cantidad;
				}else {
					//en caso de que en la mina haya menos que la cantidad a extraer
					//suma en la bolsa lo que queda en la mina
					bolsa += mina.stock;
					//suma al total extraido lo que queda en la mina
					totalExtraido += mina.stock;
					//
					mina.stock = 0;
				}
				
				

				// esperar a que termine el turno
				try {
					Thread.sleep(tiempoExtraccion);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	
	/*M�tode: run
	 * Descripci�: sobreescribeix el m�tode de extraerRecurso i executa eixe m�tode
	 * Par�mtres d'entrada: no
	 * Par�metres d'eixida: no
	 * */
	@Override
	public void run() {
		extraerRecurso();
	}
}
