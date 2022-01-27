package es.florida.aev4;

import java.io.Serializable;

//Objecte contrasenya
public class Contrasenya implements Serializable {
	String textoPlano, textoEncriptada;

	public Contrasenya(String textoPlano, String textoEncriptada) {
		super();
		this.textoPlano = textoPlano;
		this.textoEncriptada = textoEncriptada;
	}

	public String getTextoPlano() {
		return textoPlano;
	}

	public void setTextoPlano(String textoPlano) {
		this.textoPlano = textoPlano;
	}

	public String getTextoEncriptada() {
		return textoEncriptada;
	}

	public void setTextoEncriptada(String textoEncriptada) {
		this.textoEncriptada = textoEncriptada;
	}

	public Contrasenya() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
