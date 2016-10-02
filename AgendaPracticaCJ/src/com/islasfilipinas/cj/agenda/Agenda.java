package com.islasfilipinas.cj.agenda;

import java.io.*;
import java.util.ArrayList;

import com.islasfilipinas.cj.exceptions.ContactoRepetidoException;
import com.islasfilipinas.cj.exceptions.FicheroNoValidoException;
import com.islasfilipinas.cj.ficheros.Fichero;

/**
 * Representaci�n abstracta de una agenda. El usuario a trav�s de la interfaz utilizada en este proyecto puede interactuar con la agenda de diferentes maneras.
 * Puede crear agendas, cargarlas, guardar una agenda previamente creada y mostrar una agenda para ver el listado de los contactos ({@link Contacto}).
 * 
 * La clase utiliza de la clase {@link ArrayList} para crear una colecci�n de contactos. Adem�s sobreescribe el m�todo .contains() ({@link #containsContacto(Contacto)}
 * para adecuarlo a las necesidades de la clase.
 * 
 * En lo referente al almacenamiento de ficheros esta clase hace uso de la clase {@link Fichero}.
 * 
 * @author Carlos Garcia Corpas & Javier S�nchez G�mez
 * @version v1.3.1
 *
 */
public class Agenda implements Serializable{
	private static final long serialVersionUID = 6832112334979262272L;
	
	/**
	 * Colecci�n de contactos, estructurada mediante un ArrayList.
	 */
	private ArrayList<Contacto> contactos;
	
	/**
	 * �nico constructor de la clase. Carga automaticamente el ArrayList de contactos vac�o.
	 */
	public Agenda(){
		this.contactos=new ArrayList<Contacto>();
	}
	
	/**
	 * M�todo cargar. Se utiliza como bien dice su nombre para cargar una agenda. Internamente llama a la clase {@link Fichero} para que mediante 
	 * el m�todo {@link Fichero#leerAgenda(File)} recoja una lista de contactos y se los asigne a la agenda.
	 * <br>
	 * Tambi�n valida que el fichero tenga extensi�n .ag y si no lo tiene lanzala excepci�n.
	 * @param file Un archivo conteniendo una agenda.
	 * @throws ClassNotFoundException Si no encuentra la clase Fichero.
	 * @throws IOException Si el m�todo {@link Fichero#leerAgenda(File)} no es capaz de leer el fichero.
	 * @throws FicheroNoValidoException Si el fichero cargado no es v�lido.
	 */
	public void cargar(File file) throws ClassNotFoundException, IOException, FicheroNoValidoException{
		if (file.exists() && file.getName().endsWith(".ag")){
			this.contactos=Fichero.leerAgenda(file);
		}
		else
			throw new FicheroNoValidoException("El fichero no existe o no es v�lido");
	}
	
	/**
	 * M�todo guardar. Se utiliza para guardar agendas y t�cnicamente lo �nico que hace es pasarle al m�todo {@link Fichero#guardar(Agenda, File)} de la clase {@link Fichero}
	 * esta clase en s� y un archivo para que guarde esta agenda en ese archivo.
	 * @param file El archivo donde se va a guardar la agenda.
	 * @throws IOException Si el archivo no puede ser le�do o escrito.
	 */
	public void guardar(File file) throws IOException{
		Fichero.guardar(this, file);
	}
	
	/**
	 * Sobreescribe el contains de la clase {@link ArrayList}. Esto se hace para especificar que dos contactos son iguales si coinciden sus n�meros de tel�fono.
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
	 * M�todo a�adir. A�ade un contacto a la colecci�n de contactos.
	 * @param contacto El contacto a a�adir.
	 * @throws ContactoRepetidoException Si el contacto que deseas a�adir ya existe.
	 */
	public void annadir(Contacto contacto) throws ContactoRepetidoException{
		if (!this.containsContacto(contacto)){
			this.contactos.add(contacto);
		}
		else
			throw new ContactoRepetidoException("El contacto que intenta a�adir ya existe.");
	}
	
	/**
	 * M�todo borrar. Utiliza el m�todo {@link ArrayList#remove(Object)} de la clase {@link ArrayList} para borrar un contacto de la agenda cargada en memoria.
	 * <br>
	 * Se utiliza un for que recore los contactos y los compara para que el .remove de la clase ArrayList borre exactamente el contacto deseado ya que por defecto usar� el
	 * .contains de ArrayList y comparar� los contactos por direcci�n de memoria.
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
	 * M�todo empleado para modificar un contacto. Internamente borra el contacto y vuelve a crear uno con los datos ya modificados.
	 * @param contacto Contacto con los datos antiguos.
	 * @param contactoMod Contacto con los datos nuevos.
	 * @throws ContactoRepetidoException Si la modificaci�n tiene datos de contactos ya existentes.
	 */
	public void modificar(Contacto contacto, Contacto contactoMod) throws ContactoRepetidoException{
		//Si el contacto a modificar existe y la modificaci�n realizada no:
		if (this.containsContacto(contacto) && !this.containsContacto(contactoMod)){
			// aux es una variable utilizada como auxiliar para compararla con el resto de contactos, encontrando as� al contacto que queremos modificar dentro del ArrayList.
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
			throw new ContactoRepetidoException("Est� intentando modificar un contacto por otro que ya existe.");
		else
			throw new ContactoRepetidoException("El contacto que intenta modificar no existe");
	}
	
	/**
	 * M�todo buscar por nombre. Recorre la agenda comparando los nombres de los Contactos.
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
	 * M�todo buscar por n�mero. Recorre la agenda comparando los n�meros de los Contactos.
	 * @param tfno El tel�fono a buscar.
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
	 * M�todo getter del ArrayList de contactos. Se utiliza para acceder a los contactos de la agenda desde otras clases.
	 * @return Un ArrayList con Contactos.
	 */
	public ArrayList<Contacto> getContactos() {
		return contactos;
	}
}
