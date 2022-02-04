package es.florida.aev5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class GestorHTTP implements HttpHandler {
	int temperaturaActual = 18;
	int temperaturaTermostato = 18;


	public int getTemperaturaActual() {
		return temperaturaActual;
	}

	public void setTemperaturaActual(int temperaturaActual) {
		this.temperaturaActual = temperaturaActual;
	}

	public int getTemperaturaTermostato() {
		return temperaturaTermostato;
	}

	public void setTemperaturaTermostato(int temperaturaTermostato) {
		this.temperaturaTermostato = temperaturaTermostato;
	}

	public void handle(HttpExchange httpExchange) throws IOException {
		String requestParamValue = null;
		if ("GET".equals(httpExchange.getRequestMethod())) {
			requestParamValue = handleGetRequest(httpExchange);
			handleGETResponse(httpExchange, requestParamValue);
		} else if ("POST".equals(httpExchange.getRequestMethod())) {
			requestParamValue = handlePostRequest(httpExchange);
			handlePOSTResponse(httpExchange, requestParamValue);
		}
	}

	private String handleGetRequest(HttpExchange httpExchange) {
		return httpExchange.getRequestURI().toString().split("\\?")[1];
		
	}

	private void handleGETResponse(HttpExchange httpExchange, String requestParamValue) throws IOException {
		OutputStream outputStream = httpExchange.getResponseBody();
		
		String [] array1 = requestParamValue.split("=");
		
		if (requestParamValue.equals("temperaturaActual")) {

			String htmlResponse = "<html><body><p>La temperatura actual es: " + temperaturaActual + "</p><p>La temperatura del termostato es: " + temperaturaTermostato + "</p></body></html>";
			
			httpExchange.sendResponseHeaders(200, htmlResponse.length());
			outputStream.write(htmlResponse.getBytes());
			outputStream.flush();
			outputStream.close();
			
			temperaturaTermostato = Integer.parseInt(array1[1]);
			diferenciaTemperatura();

		}
	}

	private String handlePostRequest(HttpExchange httpExchange) throws IOException {
		InputStream inputStream = httpExchange.getRequestBody();
		/*Procesar lo que hay en inputStream, por ejemplo lineaa linea y guardarlo todo en un
		string, que sera el que devuelve el metodo*/
		InputStreamReader isr = new InputStreamReader (inputStream);
		BufferedReader br = new BufferedReader(isr);
		String postRequest = null;
		String brLinea = br.readLine();
		while(brLinea != null) {
			postRequest+=brLinea;
			brLinea = br.readLine();//pasa linea a linea
		}
		br.close();
		return postRequest;
	}

	private void handlePOSTResponse(HttpExchange httpExchange, String requestParamValue) throws IOException {
		OutputStream outputStream = httpExchange.getResponseBody();
		requestParamValue = requestParamValue.split(";")[0].split("=")[1];

		String htmlResponse = "Respuesta a la petición POST";
		httpExchange.sendResponseHeaders(200, htmlResponse.length());
		outputStream.write(htmlResponse.getBytes());
		outputStream.flush();
		outputStream.close();
		diferenciaTemperatura();

	}
	
	private void diferenciaTemperatura() {
		while (temperaturaTermostato != temperaturaActual){
			if(temperaturaTermostato>temperaturaActual) {
				temperaturaActual ++;
			}else {
				temperaturaActual --;
			}
		}
	}

}
