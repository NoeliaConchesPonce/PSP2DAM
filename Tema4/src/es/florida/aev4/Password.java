package es.florida.aev4;


	import java.io.Serializable;

	@SuppressWarnings("serial")
	public class Password implements Serializable {

		
			String pwd, encriptacion;
			

			
			
			/* Metodo: Password(String, String)
			 * Descripcion: Constructor que rep la contrasenya en texto plano i encriptada i les guarda .
			 * Parametre d'entrada: String contrasenya, String encriptada
			 * Parametre d'eixida: no
			 * */
			public Password(String pass, String encriptada) {
				this.pwd = pass;
				this.encriptacion = encriptada;
			}
			
			/* Metodo: Password()
			 * Descripcion: Constructor 
			 * Parametre d'entrada: no
			 * Parametre d'eixida: no
			 * */
			public Password() {
				// TODO Auto-generated constructor stub
			}

			/* Metodo: setNuevaPassword
			 * Descripcion: Introdueix la contrasenya pasada pel client
			 * Parametre d'entrada: String pwd
			 * Parametre d'eixida: no
			 * */
			public void setNuevaPassword(String pwd) {
				this.pwd = pwd;
			}
			
			/* Metodo: getNuevaPassword
			 * Descripcion: retorna la contrasenya 
			 * Parametre d'entrada: 
			 * Parametre d'eixida: String pwd
			 * */
			public String getNuevaPassword() {
				return pwd;
			} 

			/* Metodo: setPwdEncriptada
			 * Descripcion: estableix la contrasenya encriptada 
			 * Parametre d'entrada: String encriptada
			 * Parametre d'eixida: no
			 * */
			public void setPwdEncriptada(String encriptada) {
				this.encriptacion = encriptada;
			}

			/* Metodo: getPwdEncriptada
			 * Descripcion: Retorna la contrasenya generada 
			 * Parametre d'entrada: 
			 * Parametre d'eixida: String encriptacion
			 * */
			public String getPwdEncriptada() {
				return encriptacion;
			}
	}


