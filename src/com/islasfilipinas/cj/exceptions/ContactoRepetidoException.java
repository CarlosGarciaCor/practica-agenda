package com.islasfilipinas.cj.exceptions;

/**
 * Excepción contacto repetido. Se lanza o bien cunado el contacto ya existe en la agenda o cuando intentamos borrar un contacto que no existe. 
 * 
 * @author Carlos García Corpas & Javier Sánchez Gómez
 */
public class ContactoRepetidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3477176804000918651L;
	
	public ContactoRepetidoException(String m){
		super(m);
	}

}
