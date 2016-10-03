package com.islasfilipinas.cj.agenda;

import java.io.Serializable;
import java.util.InputMismatchException;

/**
 * Representaci�n abstracta de un Contacto. Un contacto t�cnicamente es una persona pero en el caso de la agenda solo necesitamos saber dos datos, su nombre y su n�mero.
 * <br>
 * El usuario va a ser capaz de realizar cambios concernientes a esta clase a trav�s de la interfaz del programa. Los cambios que puede realizar son:
 * <ul>
 * <li>A�adir un contacto. El usuario ser� capaz de crear nuevos contactos, que el sistema asignar� directamente a la agenda cargada en memoria.</li>
 * <li>Modificar un contacto. El usuario ser� capaz de elegir un contacto previamente creado y modificar sus datos, ya sea su nombre, su n�mero o ambas.</li>
 * <li>Borrar un contacto. El usuario ser� capaz de elegir un contacto previamente creado y eliminarlo de la agenda.</li>
 * <li>Buscar un contacto. El usuario ser� capaz de buscar, ya sea por nombre o por n�mero de tel�fono, un contacto dentro de la agenda cargada.</li>
 * </ul>
 * 
 * @author Carlos Garc�a Corpas & Javier S�nchez G�mez
 *
 */
public class Contacto implements Serializable{
	private static final long serialVersionUID = 3575857020519478314L;
	/**
	 * Nombre del contacto.
	 */
	private String nombre;
	/**
	 * N�mero de tel�fono del contacto.
	 */
	private String telefono;
	
	/**
	 * Constructor de contactos. Establece limitaciones de longitud al nombre y al tel�fono. Se ha decidido en el dise�o no limitar la longitud del n�mero de tel�fono a nueve
	 * para permitir el uso de prefijos extranjeros.
	 * @param _nombre El nombre del contacto.
	 * @param _telefono El n�mero de tel�fono del contacto.
	 * @throws InputMismatchException Si el contacto no respeta la longitud m�xima de caracteres, ya sea en el n�mero de tel�fono o en el nombre.
	 */
	public Contacto (String _nombre, String _telefono){
		if (_nombre.length()<=20 && _nombre.length()>=1
				&& _telefono.length()>=3 && _telefono.length()<=13){
			this.nombre=_nombre;
			this.telefono=_telefono;
		}
		else
			throw new InputMismatchException("El contacto que intenta a�adir tiene un formato no v�lido.");
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
	 * Getter del tel�fono. Para acceder al tel�fono del contacto.
	 * @return El tel�fono del contacto.
	 */
	public String getTelefono() {
		return telefono;
	}
	
	/**
	 * Setter de tel�fono. Establece el tel�fono del contacto.
	 * @param telefono El tel�fono a dar al contacto.
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}
