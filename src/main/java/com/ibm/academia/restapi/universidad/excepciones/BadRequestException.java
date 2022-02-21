package com.ibm.academia.restapi.universidad.excepciones;

public class BadRequestException extends RuntimeException{
	
	public BadRequestException(String mensaje){
		super(mensaje);
	}
	
	private static final long serialVersionUID = 853712358452000931L;	
}
