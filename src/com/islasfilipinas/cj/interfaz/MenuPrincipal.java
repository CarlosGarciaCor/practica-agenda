package com.islasfilipinas.cj.interfaz;

import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.islasfilipinas.cj.agenda.Agenda;
import com.islasfilipinas.cj.exceptions.FicheroNoValidoException;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
/**
 * La clase MenuPrincipal es la clase principal, contiene el m�todo main,
 * y hace referencia a la pantalla principal de nuestra aplicaci�n. Esta clase es un
 * frame, y por tanto, es necesario que herede de JFrame. Gracias a ello, podr� contener
 * todos los elementos gr�ficos necesarios para ser construida.
 * <br>
 * El funcionamiento de la clase es sencillo. En el m�todo main se inicia un nuevo hilo
 * de ejecucci�n en el cual se instancia esta propia clase. Este hilo o thread es necesario
 * siempre que se trate con aplicaciones que incluyan interfaces gr�ficas de usuario. 
 * En este nuevo hilo, se instanciar� un objeto de esta misma clase, es decir, llamar� al
 * constructor de MenuPrincipal, en el cual se encuentra todo lo que va a poder hacer la 
 * aplicaci�n.
 * <br><br>
 * El constructor de esta clase iniciar� los componentes gr�ficos y les otorgar� funcionalidad
 * llamando al resto de clases del programa.
 * En la imagen se muestra el formato de esta ventana.
 * <br><br>
 * <img src="http://i.imgur.com/CUsk7m1.jpg">
 * 
 * @author Carlos Garcia Corpas & Javier S�nchez G�mez
 * @version v1.3.1
 */
public class MenuPrincipal extends JFrame {
	
	/**
	 * Identificador �nico de la clase MenuPrincipal
	 */
	private static final long serialVersionUID = -3438877133873551605L;
	/**
	 * Objeto de la clase Agenda, el cual va a estar cargado en memoria durante toda la ejecucci�n del programa.
	 */
	private Agenda agenda;
	/**
	 * Objeto File que va a hacer referencia al fichero de datos externo al programa. 
	 * Se usar� este atributo tanto como para guardar y cargar, as� como crear una nueva agenda.
	 */
	private File file=null;
	
	/**
	 * Main de la aplicaci�n. Lanza el men� principal desde un nuevo hilo de ejecucci�n.
	 * El men� lanzado cargar� la GUI y permitir� al usuario interactuar con el programa.
	 * @param args. Argumentos, par�metros que se le pueden pasar a la aplicaci�n.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Instancio el men� principal para que aparezca a la ejecuci�n del programa.
					MenuPrincipal menuPrincipal = new MenuPrincipal();
					menuPrincipal.setVisible(true);
				} catch (Exception e) {
					
				}
			}
		});
	}

	/**
	 * �nico constructor del men� principal. Se encarga, en resumen, de tres cosas:
	 * <ul>
	 * 	<li>Dar formato a la pantalla principal.</li>
	 *  <li>Inicializar todos los componentes de dicha pantalla</li>
	 *  <li>Asignar a esos componentes una serie de eventos y asignarlos a funcionalidades</li>
	 * </ul>
	 * <br>
	 * Tambi�n inicia el objeto Agenda, que ya se ha comentado que estar� en memoria durante
	 * toda la ejecucci�n del programa.
	 */
	public MenuPrincipal() {
		// Estos cinco m�todos colocan el t�tulo, el icono, la operaci�n de cierre, el tama�o y el layout respectivamente.
		setTitle("Agenda de contactos");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/com/islasfilipinas/cj/interfaz/iconos/icono_agenda.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 250, 600, 400);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/com/islasfilipinas/cj/interfaz/iconos/rsz_3menuppal.png")));
		lblNewLabel.setBounds(65, 44, 202, 225);
		getContentPane().add(lblNewLabel);
		
		JLabel lblGestorDeAgendas = new JLabel("AGENDA DE CONTACTOS");
		lblGestorDeAgendas.setFont(new Font("Lucida Handwriting", Font.BOLD, 16));
		lblGestorDeAgendas.setBounds(277, 104, 255, 32);
		getContentPane().add(lblGestorDeAgendas);
		
		JLabel lblDesarrolladoPorCarlos = new JLabel("Desarrollado por Carlos Garc\u00EDa y Javier S\u00E1nchez");
		lblDesarrolladoPorCarlos.setFont(new Font("Cambria", Font.PLAIN, 12));
		lblDesarrolladoPorCarlos.setBounds(277, 136, 255, 25);
		getContentPane().add(lblDesarrolladoPorCarlos);
		
		JLabel lblPrcticaInicialP = new JLabel("Pr\u00E1ctica inicial Java");
		lblPrcticaInicialP.setFont(new Font("Cambria", Font.PLAIN, 12));
		lblPrcticaInicialP.setBounds(277, 161, 231, 14);
		getContentPane().add(lblPrcticaInicialP);
		
		iniciarComponentes();
		agenda = new Agenda();
	}
	/**
	 * M�todo getter del atributo Agenda agenda
	 * @return Agenda
	 */
	public Agenda getAgenda() {
		return agenda;
	}
	
	/**
	 * Inicia los componentes de la GUI.
	 * Este m�todo es llamado por el constructor e inicia una serie de componentes.
	 * Estos componentes, junto con las clases de Swing que permiten crearlos, son los siguientes:
	 * <br>
	 * <ul>
	 *  <li>Barra de men� - JMenuBar</li>
	 *   <ul>
	 *   	<li>Pesta�as del men� - JMenu</li>
	 *   		<ul>
	 *   			<li>Opciones de cada pesta�a - JMenuItem</li>
	 *   		</ul>
	 *   </ul>
	 *  <li>Logotipo de agenda - JLabel</li>
	 *  <li>Texto plano - JLabel</li>
	 * </ul>
	 */
	private void iniciarComponentes() {
		// Constructor de la barra de men�, dentro tendremos todas las acciones que el programa puede llevar a cabo. 
		// Tambi�n est� el m�todo que coloca la barra dentro del frame.
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		
		
		// ------------------------------------- AGENDA -------------------------------------------------
		
		
		
		// Constructor de una de las opciones del men�, la de la agenda.
		// Tambi�n est� el m�todo que coloca la opci�n en la barra de men�.
		JMenu menuAgenda = new JMenu("Agenda");
		menuBar.add(menuAgenda);
		
		/* 
		 * Los tres constructores a continuaci�n a�aden opciones al men� de agenda, adem�s se programan 
		 * sus listeners y actionEvents para hacer diferentes acciones al hacer click en las acciones.
		 */
		
		JMenuItem opcionNuevaAgenda = new JMenuItem("Nueva agenda...");
		opcionNuevaAgenda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				crearAgenda();
			}
			
		});
		menuAgenda.add(opcionNuevaAgenda);
	
		JMenuItem opcionCargarAgenda = new JMenuItem("Cargar agenda");
		opcionCargarAgenda.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarAgenda();
			}
		});
		menuAgenda.add(opcionCargarAgenda);
		
		JMenuItem opcionGuardarAgenda = new JMenuItem("Guardar agenda");
		opcionGuardarAgenda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				guardarAgenda();
			}
			
		});
		menuAgenda.add(opcionGuardarAgenda);
		
		JMenuItem opcionMostrarAgenda = new JMenuItem("Mostrar agenda");
		opcionMostrarAgenda.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (file == null)
					mostrarPopupAgendaNoCargada();
				else if (agenda.getContactos().isEmpty())
					mostrarPopupAgendaVacia();
				else
					mostrarAgenda();
			}
		});
		menuAgenda.add(opcionMostrarAgenda);
		
		
		// ------------------------------- CONTACTOS -----------------------------------
		
		
		// Constructor de otra opci�n de la barra de men�, los contactos.
		// Tambi�n est� el m�todo que coloca la opci�n en la barra de men�.
		JMenu menuContactos = new JMenu("Contactos");
		menuBar.add(menuContactos);
		
		// Constructores, listeners y actionEvents para las dos opciones del men� de contactos.
		JMenuItem opcionAgregarContacto = new JMenuItem("Agregar contacto");
		opcionAgregarContacto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (file==null)
					mostrarPopupAgendaNoCargada();
				else
					agregarContacto();
			}
		});
		menuContactos.add(opcionAgregarContacto);
		
		JMenuItem opcionModificarBorrarContacto = new JMenuItem("Modificar/Eliminar contacto");
		opcionModificarBorrarContacto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (file == null)
					mostrarPopupAgendaNoCargada();
				else if (agenda.getContactos().isEmpty())
					mostrarPopupAgendaVacia();
				else
					modificarBorrarContacto();
			}
		});
		menuContactos.add(opcionModificarBorrarContacto);
		
		JMenuItem opcionBuscarContacto = new JMenuItem("Buscar");
		opcionBuscarContacto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (file == null)
					mostrarPopupAgendaNoCargada();
				else if (agenda.getContactos().isEmpty())
					mostrarPopupAgendaVacia();
				else
					buscar();
			}
			
		});
		menuContactos.add(opcionBuscarContacto);
		
		
		// --------------------------------------- SISTEMA ----------------------------------------
		
		/*
		 * Constructor de la opci�n de men� para ver la ayuda referente al programa.
		 * Tambi�n est� el m�todo para capturar el evento mediante un listener y el
		 * m�todo para a�adir la opci�n a la barra de men�.
		 */
		JMenu menuSistema=new JMenu("Sistema");
		menuBar.add(menuSistema);
		
		JMenuItem opcionSalir = new JMenuItem("Salir");
		opcionSalir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				salir();
			}
		});
		menuSistema.add(opcionSalir);
	}
	
	//--------------------------FUNCIONALIDADES--------------------------
	/**
	 * Crea una agenda.
	 * Este m�todo crea una agenda tanto si la agenda cargada en memoria esta vac�a (como cuando se inicia el programa)
	 * o si tiene contactos dentro. En este segundo caso, preguntar� al usuario si est� seguro de querer 
	 * crear la nueva agenda.
	 * <br>
	 * Este m�todo llama a {@link crearArchivo()}.
	 *
	 */
	private void crearAgenda(){
		if (!agenda.getContactos().isEmpty()){
			int valorRetorno=mostrarPopupPreguntaCrearAgenda();
			if (valorRetorno==0){
				crearArchivo();
			}
		}
		else
			crearArchivo();
	}
	
	/**
	 * Generaci�n del fichero para la nueva agenda.
	 * Este m�todo es llamado desde {@link crearAgenda()}. Su funci�n es abrir una nueva
	 * ventana en la cual el usuario pueda elegir un directorio en el que crear una nueva agenda
	 * como fichero f�sico. Este m�todo utiliza el cuadro de di�logo de JFileChooser.
	 * <br>
	 * Si el usuario se decide por un directorio, este m�todo crear� all� un fichero con el nombre
	 * dado y extensi�n de agenda ("*.ag"). Tambi�n vaciar� la agenda cargada en memoria.
	 */
	private void crearArchivo(){
		
		JFileChooser jfc=new JFileChooser();
		
		FileNameExtensionFilter agFiltro = new FileNameExtensionFilter("Ficheros agenda (*.ag)", "ag");
		jfc.addChoosableFileFilter(agFiltro);
        jfc.setFileFilter(agFiltro);
        
		jfc.setDialogType(JFileChooser.SAVE_DIALOG);
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		jfc.setCurrentDirectory(new File("C:/Users/"+System.getProperty("user.name")+"/Desktop"));
		
		if (jfc.showOpenDialog(this)==JFileChooser.APPROVE_OPTION){
			this.file=jfc.getSelectedFile();
			
			if (this.file.exists()){
				mostrarPopupSobreescribir();
			}
			else {
				try {
					String nombreVie = file.getAbsolutePath();
					if (!this.file.getName().endsWith(".ag"))
						file = new File(nombreVie+".ag");
						this.file.createNewFile();
						agenda.getContactos().clear();
						agenda.guardar(file);
				} catch (IOException e) {
					mostrarPopupIOException();
				}
			}
		}
		
	}
	
	/**
	 * Muestra la agenda cargada. 
	 * Llama a la clase {@link Mostrar} para lanzar una nueva ventana donde se podr� mostrar la agenda.
	 */
	private void mostrarAgenda(){
		Mostrar mostrarAgenda = new Mostrar(this);
	}
	/**
	 * Permite guardar la agenda en un fichero.
	 * Siempre que la agenda no este vac�a, este m�todo guardar� los datos de una agenda
	 * en el fichero en el haya sido creada la agenda.
	 */
	private void guardarAgenda() {
		if (agenda.getContactos().isEmpty()){
			mostrarPopupAgendaVacia();
		}
		else if (this.file!=null){
			try {
				agenda.guardar(this.file);
				mostrarPopupAgendaGuardadaExito();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				mostrarPopupIOException();
			}
		}
		else {
			mostrarPopupAgendaVacia();
		}
	}
	
	
	/**
	 * Carga una agenda guardada en un fichero.
	 * Haciendo las validaciones necesarias, este m�todo llama a {@link cargarArchivo} 
	 * con el fin de cargar una agenda a memoria desde un fichero.
	 */
	private void cargarAgenda() {
		if (!agenda.getContactos().isEmpty()){
			int valorRetorno=mostrarPopupPreguntaCargarAgenda();
			if (valorRetorno==0){
				cargarArchivo();
			}
		}
		else
			cargarArchivo();
	}
	/**
	 * Selecciona el archivo desde el que se va a cargar una agenda.
	 * Utilizando el cuadro de di�logo de JFileChooser, se le permite al usuario elegir un archivo
	 * que guarde una agenda para poder cargarla en memoria.
	 * <br>
	 * Los archivos que contengan una agenda han de tener la extensi�n ".ag". Este m�todo impide
	 * que el usuario pueda cargar un archivo que no es una agenda.
	 */
	private void cargarArchivo() {
		
		JFileChooser jfc=new JFileChooser();
		FileNameExtensionFilter agFiltro = new FileNameExtensionFilter("Ficheros agenda (*.ag)", "ag");
		jfc.addChoosableFileFilter(agFiltro);
        jfc.setFileFilter(agFiltro);
		
		jfc.setDialogType(JFileChooser.APPROVE_OPTION);
		
		//Si esta ruta absoluta no existe, se coloca en el directorio del proyecto
		//de forma autom�tica
		File currentFile=new File("C:/Users/"+System.getProperty("user.name")+"/Desktop");
		
		if (currentFile.exists())
			jfc.setCurrentDirectory(currentFile);
		
		if (jfc.showOpenDialog(this)==JFileChooser.APPROVE_OPTION){
			try {
				this.file=jfc.getSelectedFile();
				agenda.cargar(jfc.getSelectedFile());
				mostrarPopupAgendaCargada();
			} catch (ClassNotFoundException | FicheroNoValidoException e1) {
				mostrarPopupFicheroInvalido();
			} catch (IOException e1) {
				mostrarPopupIOException();
			}
		}
	}
	
	/**
	 * Lanza la ventana de a�adir contacto.
	 * Se comunica con la clase {@link AgregarContacto} que genera la ventana correspondiente a esta funcionalidad.
	 */
	private void agregarContacto() {
			AgregarContacto agregar = new AgregarContacto(this);
	}
	/**
	 * Lanza la ventana de modificar y borrar contactos.
	 * Se comunica con la clase {@link BorrarModificar} que genera la ventana para modificar o borrar contactos.
	 */
	private void modificarBorrarContacto() {
		if (file != null){
			BorrarModificar borrarmodificar = new BorrarModificar(this);
		}else{
			mostrarPopupAgendaNoCargada();
		}
	}
	
	/**
	 * Lanza la ventana de b�squeda de contactos.
	 * Se comunica con la clase {@link BuscarContacto} que genera la ventana para la b�squeda de contactos.
	 */
	private void buscar(){
		BuscarContacto buscar = new BuscarContacto(this);
	}
	
	/**
	 * 
	 */
	private void mostrarAyuda(){
		
	}	
	
	/**
	 * Finaliza el programa.
	 * Antes de finalizar lanza un mensaje de comprobaci�n al usuario.
	 */
	private void salir() {
		try {
			int valorRetorno=mostrarPopupSalir();
			if (valorRetorno==0){
				System.exit(0);
			}
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//---------------------------MOSTRAR POPUPS-------------------------------
	/**
	 * "La agenda est� vac�a."
	 * 
	 */
	private void mostrarPopupAgendaVacia() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this, 
				"La agenda est� vac�a.", "Informaci�n",
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * "No existe ninguna agenda cargada."
	 */
	private void mostrarPopupAgendaNoCargada() {
		JOptionPane.showMessageDialog(this, 
				"No existe ninguna agenda cargada.", "Informaci�n",
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * "Agenda cargada con �xito."
	 */
	private void mostrarPopupAgendaCargada() {
		JOptionPane.showMessageDialog(this, 
				"Agenda cargada con �xito.", "Cargar agenda",
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * "�El fichero que est� intentando cargar no es una agenda!"
	 */
	private void mostrarPopupFicheroInvalido() {
		JOptionPane.showMessageDialog(this, 
				"�El fichero que est� intentando cargar no es una agenda!", "Fichero inv�lido",
				JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * "Ha ocurrido un error inesperado, por favor vuelva a intentarlo."
	 */
	private void mostrarPopupIOException() {
		JOptionPane.showMessageDialog(this, 
				"Ha ocurrido un error inesperado, "
				+ "\npor favor vuelva a intentarlo.", "Error",
				JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * "Agenda guardada con �xito."
	 */
	private void mostrarPopupAgendaGuardadaExito() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this, 
				"Agenda guardada con �xito.", "Guardar",
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * "Ese archivo ya existe."
	 */
	private void mostrarPopupSobreescribir() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this, 
				"Ese archivo ya existe.", "Archivo existente", 
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * "Si crea una nueva agenda los cambios que no haya guardado se perder�n, �est� seguro de que quiere crearla? "
	 * @return 0 - S�, 1 - No
	 */
	private int mostrarPopupPreguntaCrearAgenda() {
		// TODO Auto-generated method stub
		return JOptionPane.showConfirmDialog(this, 
				"Si crea una nueva agenda los cambios"
				+ "\nque no haya guardado se perder�n,"
				+ "\n�est� seguro de que quiere crearla? ", "Crear agenda", 
				JOptionPane.YES_NO_OPTION);
	}
	
	/**
	 * "Si carga una otra agenda los cambios que no haya guardado se perder�n,
		�est� seguro de que quiere cargar?"
	 * @return 0 - S�, 1 - No
	 */
	private int mostrarPopupPreguntaCargarAgenda() {
		// TODO Auto-generated method stub
		return JOptionPane.showConfirmDialog(this, 
				"Si carga una otra agenda los cambios"
				+ "\nque no haya guardado se perder�n,"
				+ "\n�est� seguro de que quiere cargar? ", "Crear agenda", 
				JOptionPane.YES_NO_OPTION);
	}
	
	/**
	 * "�Est� seguro de que desea salir?
		Los cambios no guardados se perder�n."
	 * @return 0 - S�, 1 - No
	 */
	private int mostrarPopupSalir() {
		return JOptionPane.showConfirmDialog(this, 
				"�Est� seguro de que desea salir?"
				+ "\nLos cambios no guardados se perder�n.", "Salir", 
				JOptionPane.YES_NO_OPTION);
	}
}
