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
 * La clase MenuPrincipal es la clase principal, contiene el método main,
 * y hace referencia a la pantalla principal de nuestra aplicación. Esta clase es un
 * frame, y por tanto, es necesario que herede de JFrame. Gracias a ello, podrá contener
 * todos los elementos gráficos necesarios para ser construida.
 * <br>
 * El funcionamiento de la clase es sencillo. En el método main se inicia un nuevo hilo
 * de ejecucción en el cual se instancia esta propia clase. Este hilo o thread es necesario
 * siempre que se trate con aplicaciones que incluyan interfaces gráficas de usuario. 
 * En este nuevo hilo, se instanciará un objeto de esta misma clase, es decir, llamará al
 * constructor de MenuPrincipal, en el cual se encuentra todo lo que va a poder hacer la 
 * aplicación.
 * <br><br>
 * El constructor de esta clase iniciará los componentes gráficos y les otorgará funcionalidad
 * llamando al resto de clases del programa.
 * En la imagen se muestra el formato de esta ventana.
 * <br><br>
 * <img src="http://i.imgur.com/CUsk7m1.jpg">
 * 
 * @author Carlos Garcia Corpas & Javier Sánchez Gómez
 * @version v1.3.1
 */
public class MenuPrincipal extends JFrame {
	
	/**
	 * Identificador único de la clase MenuPrincipal
	 */
	private static final long serialVersionUID = -3438877133873551605L;
	/**
	 * Objeto de la clase Agenda, el cual va a estar cargado en memoria durante toda la ejecucción del programa.
	 */
	private Agenda agenda;
	/**
	 * Objeto File que va a hacer referencia al fichero de datos externo al programa. 
	 * Se usará este atributo tanto como para guardar y cargar, así como crear una nueva agenda.
	 */
	private File file=null;
	
	/**
	 * Main de la aplicación. Lanza el menú principal desde un nuevo hilo de ejecucción.
	 * El menú lanzado cargará la GUI y permitirá al usuario interactuar con el programa.
	 * @param args. Argumentos, parámetros que se le pueden pasar a la aplicación.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Instancio el menú principal para que aparezca a la ejecución del programa.
					MenuPrincipal menuPrincipal = new MenuPrincipal();
					menuPrincipal.setVisible(true);
				} catch (Exception e) {
					
				}
			}
		});
	}

	/**
	 * Único constructor del menú principal. Se encarga, en resumen, de tres cosas:
	 * <ul>
	 * 	<li>Dar formato a la pantalla principal.</li>
	 *  <li>Inicializar todos los componentes de dicha pantalla</li>
	 *  <li>Asignar a esos componentes una serie de eventos y asignarlos a funcionalidades</li>
	 * </ul>
	 * <br>
	 * También inicia el objeto Agenda, que ya se ha comentado que estará en memoria durante
	 * toda la ejecucción del programa.
	 */
	public MenuPrincipal() {
		// Estos cinco métodos colocan el título, el icono, la operación de cierre, el tamaño y el layout respectivamente.
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
	 * Método getter del atributo Agenda agenda
	 * @return Agenda
	 */
	public Agenda getAgenda() {
		return agenda;
	}
	
	/**
	 * Inicia los componentes de la GUI.
	 * Este método es llamado por el constructor e inicia una serie de componentes.
	 * Estos componentes, junto con las clases de Swing que permiten crearlos, son los siguientes:
	 * <br>
	 * <ul>
	 *  <li>Barra de menú - JMenuBar</li>
	 *   <ul>
	 *   	<li>Pestañas del menú - JMenu</li>
	 *   		<ul>
	 *   			<li>Opciones de cada pestaña - JMenuItem</li>
	 *   		</ul>
	 *   </ul>
	 *  <li>Logotipo de agenda - JLabel</li>
	 *  <li>Texto plano - JLabel</li>
	 * </ul>
	 */
	private void iniciarComponentes() {
		// Constructor de la barra de menú, dentro tendremos todas las acciones que el programa puede llevar a cabo. 
		// También está el método que coloca la barra dentro del frame.
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		
		
		// ------------------------------------- AGENDA -------------------------------------------------
		
		
		
		// Constructor de una de las opciones del menú, la de la agenda.
		// También está el método que coloca la opción en la barra de menú.
		JMenu menuAgenda = new JMenu("Agenda");
		menuBar.add(menuAgenda);
		
		/* 
		 * Los tres constructores a continuación añaden opciones al menú de agenda, además se programan 
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
		
		
		// Constructor de otra opción de la barra de menú, los contactos.
		// También está el método que coloca la opción en la barra de menú.
		JMenu menuContactos = new JMenu("Contactos");
		menuBar.add(menuContactos);
		
		// Constructores, listeners y actionEvents para las dos opciones del menú de contactos.
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
		 * Constructor de la opción de menú para ver la ayuda referente al programa.
		 * También está el método para capturar el evento mediante un listener y el
		 * método para añadir la opción a la barra de menú.
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
	 * Este método crea una agenda tanto si la agenda cargada en memoria esta vacía (como cuando se inicia el programa)
	 * o si tiene contactos dentro. En este segundo caso, preguntará al usuario si está seguro de querer 
	 * crear la nueva agenda.
	 * <br>
	 * Este método llama a {@link crearArchivo()}.
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
	 * Generación del fichero para la nueva agenda.
	 * Este método es llamado desde {@link crearAgenda()}. Su función es abrir una nueva
	 * ventana en la cual el usuario pueda elegir un directorio en el que crear una nueva agenda
	 * como fichero físico. Este método utiliza el cuadro de diálogo de JFileChooser.
	 * <br>
	 * Si el usuario se decide por un directorio, este método creará allí un fichero con el nombre
	 * dado y extensión de agenda ("*.ag"). También vaciará la agenda cargada en memoria.
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
	 * Llama a la clase {@link Mostrar} para lanzar una nueva ventana donde se podrá mostrar la agenda.
	 */
	private void mostrarAgenda(){
		Mostrar mostrarAgenda = new Mostrar(this);
	}
	/**
	 * Permite guardar la agenda en un fichero.
	 * Siempre que la agenda no este vacía, este método guardará los datos de una agenda
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
	 * Haciendo las validaciones necesarias, este método llama a {@link cargarArchivo} 
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
	 * Utilizando el cuadro de diálogo de JFileChooser, se le permite al usuario elegir un archivo
	 * que guarde una agenda para poder cargarla en memoria.
	 * <br>
	 * Los archivos que contengan una agenda han de tener la extensión ".ag". Este método impide
	 * que el usuario pueda cargar un archivo que no es una agenda.
	 */
	private void cargarArchivo() {
		
		JFileChooser jfc=new JFileChooser();
		FileNameExtensionFilter agFiltro = new FileNameExtensionFilter("Ficheros agenda (*.ag)", "ag");
		jfc.addChoosableFileFilter(agFiltro);
        jfc.setFileFilter(agFiltro);
		
		jfc.setDialogType(JFileChooser.APPROVE_OPTION);
		
		//Si esta ruta absoluta no existe, se coloca en el directorio del proyecto
		//de forma automática
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
	 * Lanza la ventana de añadir contacto.
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
	 * Lanza la ventana de búsqueda de contactos.
	 * Se comunica con la clase {@link BuscarContacto} que genera la ventana para la búsqueda de contactos.
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
	 * Antes de finalizar lanza un mensaje de comprobación al usuario.
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
	 * "La agenda está vacía."
	 * 
	 */
	private void mostrarPopupAgendaVacia() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this, 
				"La agenda está vacía.", "Información",
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * "No existe ninguna agenda cargada."
	 */
	private void mostrarPopupAgendaNoCargada() {
		JOptionPane.showMessageDialog(this, 
				"No existe ninguna agenda cargada.", "Información",
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * "Agenda cargada con éxito."
	 */
	private void mostrarPopupAgendaCargada() {
		JOptionPane.showMessageDialog(this, 
				"Agenda cargada con éxito.", "Cargar agenda",
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * "¡El fichero que está intentando cargar no es una agenda!"
	 */
	private void mostrarPopupFicheroInvalido() {
		JOptionPane.showMessageDialog(this, 
				"¡El fichero que está intentando cargar no es una agenda!", "Fichero inválido",
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
	 * "Agenda guardada con éxito."
	 */
	private void mostrarPopupAgendaGuardadaExito() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this, 
				"Agenda guardada con éxito.", "Guardar",
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
	 * "Si crea una nueva agenda los cambios que no haya guardado se perderán, ¿está seguro de que quiere crearla? "
	 * @return 0 - Sí, 1 - No
	 */
	private int mostrarPopupPreguntaCrearAgenda() {
		// TODO Auto-generated method stub
		return JOptionPane.showConfirmDialog(this, 
				"Si crea una nueva agenda los cambios"
				+ "\nque no haya guardado se perderán,"
				+ "\n¿está seguro de que quiere crearla? ", "Crear agenda", 
				JOptionPane.YES_NO_OPTION);
	}
	
	/**
	 * "Si carga una otra agenda los cambios que no haya guardado se perderán,
		¿está seguro de que quiere cargar?"
	 * @return 0 - Sí, 1 - No
	 */
	private int mostrarPopupPreguntaCargarAgenda() {
		// TODO Auto-generated method stub
		return JOptionPane.showConfirmDialog(this, 
				"Si carga una otra agenda los cambios"
				+ "\nque no haya guardado se perderán,"
				+ "\n¿está seguro de que quiere cargar? ", "Crear agenda", 
				JOptionPane.YES_NO_OPTION);
	}
	
	/**
	 * "¿Está seguro de que desea salir?
		Los cambios no guardados se perderán."
	 * @return 0 - Sí, 1 - No
	 */
	private int mostrarPopupSalir() {
		return JOptionPane.showConfirmDialog(this, 
				"¿Está seguro de que desea salir?"
				+ "\nLos cambios no guardados se perderán.", "Salir", 
				JOptionPane.YES_NO_OPTION);
	}
}
