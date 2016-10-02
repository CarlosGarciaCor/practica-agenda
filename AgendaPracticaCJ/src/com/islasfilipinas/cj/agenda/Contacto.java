package com.islasfilipinas.cj.agenda;

import java.io.Serializable;
import java.util.InputMismatchException;

/**
 * Representación abstracta de un Contacto. Un contacto técnicamente es una persona pero en el caso de la agenda solo necesitamos saber dos datos, su nombre y su número.
 * <br>
 * El usuario va a ser capaz de realizar cambios concernientes a esta clase a través de la interfaz del programa. Los cambios que puede realizar son:
 * <ul>
 * <li>Añadir un contacto. El usuario será capaz de crear nuevos contactos, que el sistema asignará directamente a la agenda cargada en memoria.</li>
 * <li>Modificar un contacto. El usuario será capaz de elegir un contacto previamente creado y modificar sus datos, ya sea su nombre, su número o ambas.</li>
 * <li>Borrar un contacto. El usuario será capaz de elegir un contacto previamente creado y eliminarlo de la agenda.</li>
 * <li>Buscar un contacto. El usuario será capaz de buscar, ya sea por nombre o por número de teléfono, un contacto dentro de la agenda cargada.</li>
 * </ul>
 * 
 * @author Carlos García Corpas & Javier Sánchez Gómez
 *
 */
public class Contacto implements Serializable{
	private static final long serialVersionUID = 3575857020519478314L;
	/**
	 * Nombre del contacto.
	 */
	private String nombre;
	/**
	 * Número de teléfono del contacto.
	 */
	private String telefono;
	
	/**
	 * Constructor de contactos. Establece limitaciones de longitud al nombre y al teléfono. Se ha decidido en el diseño no limitar la longitud del número de teléfono a nueve
	 * para permitir el uso de prefijos extranjeros.
	 * @param _nombre El nombre del contacto.
	 * @param _telefono El número de teléfono del contacto.
	 * @throws InputMismatchException Si el contacto no respeta la longitud máxima de caracteres, ya sea en el número de teléfono o en el nombre.
	 */
	public Contacto (String _nombre, String _telefono){
		if (_nombre.length()<=20 && _nombre.length()>=1
				&& _telefono.length()>=3 && _telefono.length()<=13){
			this.nombre=_nombre;
			this.telefono=_telefono;
		}
		else
			throw new InputMismatchException("El contacto que intenta añadir tiene un formato no válido.");
	}
	
	
	/**
	 * Getter de nombre. Para acceder al nombre del contacto.
	 * @return El nombre del contacto.
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Setter de nombre. Establece el nombre del contacto.
	 * @param nombre El nombre a dar al contacto.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Getter del teléfono. Para acceder al teléfono del contacto.
	 * @return El teléfono del contacto.
	 */
	public String getTelefono() {
		return telefono;
	}
	
	/**
	 * Setter de teléfono. Establece el teléfono del contacto.
	 * @param telefono El teléfono a dar al contacto.
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}
