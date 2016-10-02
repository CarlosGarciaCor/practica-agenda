package com.islasfilipinas.cj.ficheros;


import java.io.*;
import java.util.ArrayList;

import com.islasfilipinas.cj.agenda.*;

/**
 * Clase Fichero. Es la clase que se encarga de la interacci�n con los ficheros agenda. Se encarga de escribir y leer ficheros.
 * <br>
 * Adem�s de escribir y leer tambi�n valida que el formato del fichero sea v�lido, es decir, que no se haya manipulado externamente.
 * 
 * @author Carlos Garc�a Corpas & Javier S�nchez G�mez
 */
public class Fichero {
	
	/**
	 * M�todo guardar. Se utiliza para guardar un fichero con una agenda. En el fichero se guarda directamente el objeto utilizando la clase {@link ObjectOutputStream} y precisamente
	 * por esto no utilizamos ning�n tipo de validaci�n de contenido ya que es un fichero de bytes. Las validaciones realizadas son de lectura escritura.
	 * @param agenda La agenda que queremos guardar.
	 * @param file El archivo en el que queremos guardarla.
	 * @throws IOException Si existen problemas para guardar el fichero.
	 * @throws FileNotFoundException Si el archivo en donde queremos guardar no existe o no se encuentra.
	 */
	public static void guardar(Agenda agenda, File file) throws IOException, FileNotFoundException{
		
		if (file.exists()){
			ObjectOutputStream oOutputStream=null;
			
			try {
				oOutputStream=new ObjectOutputStream(new FileOutputStream(file));
				oOutputStream.writeObject(agenda);
			} catch (IOException e){
				throw e;
			} finally {
				oOutputStream.close();
			}
		}
		else
			//Si el fichero no existe se comprueba tambi�n en esta clase
			throw new FileNotFoundException();
	}
	
	/**
	 * M�todo cargar. Se utiliza para cargar un fichero que contiene una agenda. Recoge un fichero mediante la clase {@link ObjectInputStream}.
	 * @param file El fichero desde el que lee la agenda.
	 * @return Un ArrayList de contactos.
	 * @throws IOException Si se produce una excepci�n de entrada o salida, es decir, si no se puede leer el fichero.
	 * @throws FileNotFoundException Si el archivo a cargar no es encontrado por el sistema.
	 * @throws ClassNotFoundException Si no se ha instanciado correctamente el objeto de ObjectInputStream.
	 */
	public static ArrayList<Contacto> leerAgenda(File file) throws IOException, FileNotFoundException, ClassNotFoundException{
		
		if (file.exists()){
			
			ObjectInputStream oInputStream=null;
		
			try {
				oInputStream=new ObjectInputStream(new FileInputStream(file));
				Agenda agenda=(Agenda)oInputStream.readObject();
				return agenda.getContactos();
			} catch (IOException e){
				throw e;
			} finally {
				if (oInputStream!=null)
					oInputStream.close();
			}
		}
		else
			throw new FileNotFoundException();
	}
}
