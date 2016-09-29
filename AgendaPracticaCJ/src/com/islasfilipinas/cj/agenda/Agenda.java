package com.islasfilipinas.cj.agenda;

import java.io.*;
import java.util.ArrayList;

import com.islasfilipinas.cj.exceptions.ContactoRepetidoException;
import com.islasfilipinas.cj.exceptions.FicheroNoValidoException;
import com.islasfilipinas.cj.ficheros.Fichero;

//Faltan m�todos modificar y buscar, y hacer validaciones de contacto
public class Agenda implements Serializable{
	
	private static final long serialVersionUID = 6832112334979262272L;
	private ArrayList<Contacto> contactos;
	
	//Para crear una agenda es necesario este contructor
	public Agenda(){
		this.contactos=new ArrayList<Contacto>();
	}
	
	public void cargar(File file) throws ClassNotFoundException, IOException, FicheroNoValidoException{
		if (file.exists() && file.getName().endsWith(".ag")){
			this.contactos=Fichero.leerAgenda(file);
		}
		else
			//RETOCAR
			throw new FicheroNoValidoException("El fichero no existe o no es v�lido");
	}

	public void guardar(File file) throws IOException{
		Fichero.guardar(this, file);
	}
	
	//M�todo para comprobar si un contacto existe en la agenda:
	//Es necesario porque el contains de Object es puta mierda
	private boolean containsContacto(Contacto contacto){
		for (Contacto item: this.contactos){
			if (contacto.getNombre().equals(item.getNombre())
					&& contacto.getTelefono().equals(item.getNombre()))
				return true;
		}
		return false;
	}
	
	//Si existe el contacto lanza una excepci�n que hay que programar (no miro a nadie)
	public void annadir(Contacto contacto) throws ContactoRepetidoException{
		if (!this.containsContacto(contacto)){
			this.contactos.add(contacto);
		}
		else
			throw new ContactoRepetidoException("El contacto que intenta a�adir ya existe.");
	}
	
	//M�s de lo mismo
	public void borrar(Contacto contacto) throws ContactoRepetidoException{
		if (this.containsContacto(contacto))
			this.contactos.remove(contacto);
		else
			throw new ContactoRepetidoException("El contacto que intenta borrar no existe.");
	}
	
	//Dependiendo de como programemos la GUI har�n falta o no todas estas excepciones.
	public void modificar(Contacto contacto, Contacto contactoMod) throws ContactoRepetidoException{
		//Si el contacto a modificar existe y la modificaci�n realizada no:
		if (this.containsContacto(contacto) && !this.containsContacto(contactoMod)){
			this.contactos.remove(contacto);
			this.contactos.add(contactoMod);
		}
		else if(this.containsContacto(contactoMod))
			throw new ContactoRepetidoException("Est� intentando modificar un contacto por otro que ya existe.");
		else
			throw new ContactoRepetidoException("El contacto que intenta modificar no existe");
	}
	
	//Los m�todos de buscar recorren la agenda y devuelven el objeto contacto si lo encuentran
	//o null si no.
	public Contacto buscarPorNombre(String nombre){
		for (Contacto contacto: this.contactos){
			if (nombre==contacto.getNombre())
				return contacto;
		}
		return null;
	}
	
	public Contacto buscarPorTelefono(String tfno){
		for (Contacto contacto: this.contactos){
			if (tfno==contacto.getTelefono())
				return contacto;
		}
		return null;
	}
	
	//Un getter de esos de moda
	public ArrayList<Contacto> getContactos() {
		return contactos;
	}
}
