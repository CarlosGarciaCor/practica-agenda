package com.islasfilipinas.cj.agenda;

import java.io.*;
import java.util.ArrayList;

import com.islasfilipinas.cj.exceptions.ContactoRepetidoException;
import com.islasfilipinas.cj.exceptions.FicheroNoValidoException;
import com.islasfilipinas.cj.ficheros.Fichero;

/**
 * Representación abstracta de una agenda. El usuario a través de la interfaz utilizada en este proyecto puede interactuar con la agenda de diferentes maneras.
 * Puede crear agendas, cargarlas, guardar una agenda previamente creada y mostrar una agenda para ver el listado de los contactos ({@link Contacto}).
 * 
 * La clase utiliza de la clase {@link ArrayList} para crear una colección de contactos. Además sobreescribe el método .contains() ({@link #containsContacto(Contacto)}
 * para adecuarlo a las necesidades de la clase.
 * 
 * En lo referente al almacenamiento de ficheros esta clase hace uso de la clase {@link Fichero}.
 * 
 * @author Carlos Garcia Corpas & Javier Sánchez Gómez
 * @version v1.3.1
 *
 */
public class Agenda implements Serializable{
	private static final long serialVersionUID = 6832112334979262272L;
	
	/**
	 * Colección de contactos, estructurada mediante un ArrayList.
	 */
	private ArrayList<Contacto> contactos;
	
	/**
	 * Único constructor de la clase. Carga automaticamente el ArrayList de contactos vacío.
	 */
	public Agenda(){
		this.contactos=new ArrayList<Contacto>();
	}
	
	/**
	 * Método cargar. Se utiliza como bien dice su nombre para cargar una agenda. Internamente llama a la clase {@link Fichero} para que mediante 
	 * el método {@link Fichero#leerAgenda(File)} recoja una lista de contactos y se los asigne a la agenda.
	 * <br>
	 * También valida que el fichero tenga extensión .ag y si no lo tiene lanzala excepción.
	 * @param file Un archivo conteniendo una agenda.
	 * @throws ClassNotFoundException Si no encuentra la clase Fichero.
	 * @throws IOException Si el método {@link Fichero#leerAgenda(File)} no es capaz de leer el fichero.
	 * @throws FicheroNoValidoException Si el fichero cargado no es válido.
	 */
	public void cargar(File file) throws ClassNotFoundException, IOException, FicheroNoValidoException{
		if (file.exists() && file.getName().endsWith(".ag")){
			this.contactos=Fichero.leerAgenda(file);
		}
		else
			throw new FicheroNoValidoException("El fichero no existe o no es válido");
	}
	
	/**
	 * Método guardar. Se utiliza para guardar agendas y técnicamente lo único que hace es pasarle al método {@link Fichero#guardar(Agenda, File)} de la clase {@link Fichero}
	 * esta clase en sí y un archivo para que guarde esta agenda en ese archivo.
	 * @param file El archivo donde se va a guardar la agenda.
	 * @throws IOException Si el archivo no puede ser leído o escrito.
	 */
	public void guardar(File file) throws IOException{
		Fichero.guardar(this, file);
	}
	
	/**
	 * Sobreescribe el contains de la clase {@link ArrayList}. Esto se hace para especificar que dos contactos son iguales si coinciden sus números de teléfono.
	 * @param contacto El contacto con el que se quiere comparar.
	 * @return Un booleano. Este es true si el contacto ya existe o false si no.
	 */
	private boolean containsContacto(Contacto contacto){
		for (Contacto item: this.contactos){
			if (contacto.getTelefono().equals(item.getTelefono())
					|| contacto.getNombre().equals(item.getNombre()))
				return true;
		}
		return false;
	}
	
	/**
	 * Método añadir. Añade un contacto a la colección de contactos.
	 * @param contacto El contacto a añadir.
	 * @throws ContactoRepetidoException Si el contacto que deseas añadir ya existe.
	 */
	public void annadir(Contacto contacto) throws ContactoRepetidoException{
		if (!this.containsContacto(contacto)){
			this.contactos.add(contacto);
		}
		else
			throw new ContactoRepetidoException("El contacto que intenta añadir ya existe.");
	}
	
	/**
	 * Método borrar. Utiliza el método {@link ArrayList#remove(Object)} de la clase {@link ArrayList} para borrar un contacto de la agenda cargada en memoria.
	 * <br>
	 * Se utiliza un for que recore los contactos y los compara para que el .remove de la clase ArrayList borre exactamente el contacto deseado ya que por defecto usará el
	 * .contains de ArrayList y comparará los contactos por dirección de memoria.
	 * @param contacto El contacto a borrar.
	 * @throws ContactoRepetidoException Si el contacto no existe.
	 */
	public void borrar(Contacto contacto) throws ContactoRepetidoException{

		if (this.containsContacto(contacto)){
			for (Contacto item: this.contactos){
				if (contacto.getNombre()==item.getNombre()){
					contacto=item;
					this.contactos.remove(contacto);
					break;
				}
			}
		}
		else
			throw new ContactoRepetidoException("El contacto que intenta borrar no existe.");
	}
	
	/**
	 * Método empleado para modificar un contacto. Internamente borra el contacto y vuelve a crear uno con los datos ya modificados.
	 * @param contacto Contacto con los datos antiguos.
	 * @param contactoMod Contacto con los datos nuevos.
	 * @throws ContactoRepetidoException Si la modificación tiene datos de contactos ya existentes.
	 */
	public void modificar(Contacto contacto, Contacto contactoMod) throws ContactoRepetidoException{
		//Si el contacto a modificar existe y la modificación realizada no:
		if (this.containsContacto(contacto) && !this.containsContacto(contactoMod)){
			// aux es una variable utilizada como auxiliar para compararla con el resto de contactos, encontrando así al contacto que queremos modificar dentro del ArrayList.
			Contacto aux=null;
			for (Contacto item: this.contactos){
				if (contacto.getNombre()==item.getNombre()){
					aux=item;
				}
			}
			if (aux!=null){
				this.contactos.remove(aux);
				this.contactos.add(contactoMod);
			}
		}
		else if(this.containsContacto(contactoMod))
			throw new ContactoRepetidoException("Está intentando modificar un contacto por otro que ya existe.");
		else
			throw new ContactoRepetidoException("El contacto que intenta modificar no existe");
	}
	
	/**
	 * Método buscar por nombre. Recorre la agenda comparando los nombres de los Contactos.
	 * @param nombre El nombre a buscar.
	 * @return El contacto si lo encuentre. Si no encuentra nada devuelve null.
	 */
	public Contacto buscarPorNombre(String nombre){
		for (Contacto contacto: this.contactos){
			if (nombre.equalsIgnoreCase(contacto.getNombre()))
				return contacto;
		}
		return null;
	}
	
	/**
	 * Método buscar por número. Recorre la agenda comparando los números de los Contactos.
	 * @param tfno El teléfono a buscar.
	 * @return El contacto si lo encuentre. Si no encuentra nada devuelve null.
	 */
	public Contacto buscarPorTelefono(String tfno){
		for (Contacto contacto: this.contactos){
			if (tfno.equalsIgnoreCase(contacto.getTelefono()))
				return contacto;
		}
		return null;
	}
	
	/**
	 * Método getter del ArrayList de contactos. Se utiliza para acceder a los contactos de la agenda desde otras clases.
	 * @return Un ArrayList con Contactos.
	 */
	public ArrayList<Contacto> getContactos() {
		return contactos;
	}
}
