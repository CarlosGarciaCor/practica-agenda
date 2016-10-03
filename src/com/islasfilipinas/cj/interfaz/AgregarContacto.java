package com.islasfilipinas.cj.interfaz;

import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.ImageIcon;

import com.islasfilipinas.cj.agenda.Agenda;
import com.islasfilipinas.cj.agenda.Contacto;
import com.islasfilipinas.cj.exceptions.ContactoRepetidoException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.InputMismatchException;

/**
 * Esta clase es la encargada de generar la ventana que permita al usuario a�adir un contacto
 * a una agenda.
 * Para poder generar la ventana esta clase extiende JDialog. Esta clase es llamada desde el m�todo
 * {@link MenuPrincipal.mostrarAgenda()} del frame principal. La ventana generada es modal, por lo cual
 * se situar� sobre el JFrame sin que este deje de ser visualizable. S�lo se podr� volver a la ventana
 * anterior mediante un bot�n de volver.
 * <br>
 * El formato de la ventana es el siguiente:
 * <br><br>
 * <img src="http://i.imgur.com/YQbFuwn.jpg">
 * @author Carlos Garcia Corpas & Javier S�nchez G�mez
 * @version v1.3.1
 */
public class AgregarContacto extends JDialog{
	/**
	 * Identificador �nico de la clase AgregarContacto.
	 */
	private static final long serialVersionUID = -8350866977294136115L;
	/**
	 * Componente gr�fico correspondiente con el campo de texto donde se va a introducir el nombre.
	 */
	private JTextField textoNombre;
	/**
	 * Componente gr�fico correspondiente con el campo de texto donde se va a introducir el n�mero de tel�fono.
	 */
	private JTextField textoTlfn;
	/**
	 * Objeto Agenda obtenido del frame padre.
	 */
	private Agenda agenda;
	
	/**
	 * Permite agregar un contacto a la agenda previamente cargada.
	 * Este constructor establece el formato que va a tener la ventana, inicia los componentes
	 * que contiene con sus debidos Listeners ({@link initComponents()}) y permite al usuario
	 * a�adir un contacto a la agenda cargada ({@link annadirContacto()}).
	 * @param padre Este par�metro es el JFrame principal.
	 */
	public AgregarContacto(JFrame padre){
		super(padre);
		agenda=((MenuPrincipal) padre).getAgenda();
		/* 
		 * Cinco m�todos b�sicos que sirven para colocar el t�tulo, el icono, 
		 * la operaci�n de cierre, el tama�o y el layout.
		 */
		setTitle("A�adir contacto");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/com/islasfilipinas/cj/interfaz/iconos/icono_agenda.png")));
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(padre.getX()+50, padre.getY()+50, 320, 289);
		setModal(true);
		getContentPane().setLayout(null);
		
		initComponents();
	}
	
	/**
	 * Inicia los componentes de este JDialog.
	 * Los componentes son los siguientes:
	 * <ul>
	 * 	<li>Caja de texto para el nombre - JLabel</li>
	 *  <li>Caja de texto para el tel�fono - JLabel</li>
	 *  <li>Bot�n de a�adir - JButton</li>
	 *  <li>Bot�n de volver - JButton</li>
	 * </ul>
	 * <br>
	 * Asignar� a ambos campos de texto un KeyListener que permitir� al usuario 
	 * escribir los datos.
	 */
	private void initComponents() {
		/*
		 * Etiqueta de texto. Indica donde deber� escribir el nombre el usuario.
		 * El JTextField que le sigue es el campo de texto donde el usuario puede escribir.
		 */
		JLabel labelNombre = new JLabel("Nombre:");
		labelNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelNombre.setBounds(72, 63, 55, 14);
		getContentPane().add(labelNombre);
		
		textoNombre = new JTextField();
		int limiteNombre = 20;
		textoNombre.setBounds(128, 61, 143, 20);
		textoNombre.setColumns(10);
		textoNombre.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				if (textoNombre.getText().length() >= limiteNombre){
					e.consume();
					Toolkit.getDefaultToolkit().beep();}
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		getContentPane().add(textoNombre);
		
		/*
		 * Etiqueta de texto. Indica donde deber� escribir el tel�fono el usuario.
		 * El JTextField que le sigue es el campo de texto donde el usuario puede escribir.
		 */
		JLabel labelTelfono = new JLabel("Tel\u00E9fono:");
		labelTelfono.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelTelfono.setBounds(72, 112, 68, 17);
		getContentPane().add(labelTelfono);
		
		textoTlfn = new JTextField();
		int limiteTelefono = 13;
		textoTlfn.setBounds(128, 111, 143, 20);
		textoTlfn.setColumns(10);
		textoTlfn.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				if (textoTlfn.getText().length() >= limiteTelefono){
					e.consume();
					Toolkit.getDefaultToolkit().beep();}
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		getContentPane().add(textoTlfn);
		
		/*
		 * Estas dos etiquetas son dos im�genes que acompa�an a las etiquetas de texto.
		 */
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AgregarContacto.class.getResource("/com/islasfilipinas/cj/interfaz/iconos/aniadircont.png")));
		label.setBounds(33, 48, 32, 41);
		getContentPane().add(label);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AgregarContacto.class.getResource("/com/islasfilipinas/cj/interfaz/iconos/aniadirtlf.png")));
		lblNewLabel.setBounds(33, 100, 32, 32);
		getContentPane().add(lblNewLabel);
		
		/*
		 * Los botones inferiores para bien a�adir el contacto o bien volver al men� anterior.
		 * Se a�aden junto a ellos los correspondientes listener y eventos.
		 */
		JButton botonAniadir = new JButton("A\u00F1adir");
		botonAniadir.setBounds(47, 203, 89, 23);
		botonAniadir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				annadirContacto();
			}

			
		});
		getContentPane().add(botonAniadir);
		
		JButton botonVolver = new JButton("Volver");
		botonVolver.setBounds(169, 203, 89, 23);
		botonVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		getContentPane().add(botonVolver);
		
		setVisible(true);
	}
	
	/**
	 * Permite a�adir un contacto a la agenda en memoria.
	 * Este m�todo llama a la funcionalidad a�adir contacto teniendo en cuenta una serie
	 * de validaciones. 
	 * <br>
	 * Es peculiar de este m�todo que juzga si un nombre de contacto es normal o no, pudiendo 
	 * resultar esto de que el usuario se haya equivocado al escribir.
	 * Considera nombres normales aquellos que s�lo est�n formados por letras y el car�cter espacio.
	 * Si considera que el nombre no es normal, le preguntar� al usuario si la informaci�n que 
	 * quiere guardar es correcta.
	 */
	private void annadirContacto() {
		try {
			Contacto nuevo = new Contacto (textoNombre.getText(), textoTlfn.getText());
			if (textoNombre.getText().matches("[a-zA-Z ]+")
					&& textoTlfn.getText().matches("[0-9]+")){
				agenda.annadir(nuevo);
				mostrarPopupAniadido();
				textoNombre.setText("");
				textoTlfn.setText("");
			}
			else {
				if (mostrarPopupContactoRaro()==0){
					agenda.annadir(nuevo);
					mostrarPopupAniadido();
					textoNombre.setText("");
					textoTlfn.setText("");
				}
			}
				
		} catch (ContactoRepetidoException e1) {
			mostrarPopupYaExiste();
		} catch (InputMismatchException e2) {
			mostrarPopupCampoSinRellenar();
		}
	}
	
	/**
	 * "Ya existe un contacto con este nombre o n�mero de tel�fono.
	 * Int�ntelo de nuevo por favor."
	 */
	private void mostrarPopupYaExiste() {
		JOptionPane.showMessageDialog(this,
			    "Ya existe un contacto con este nombre o n�mero de tel�fono.\n"
			    + "Int�ntelo de nuevo por favor.",
			    "Advertencia",
			    JOptionPane.WARNING_MESSAGE);
	}
	
	/**
	 * "El contacto "ejemplo" ha sido a�adido."
	 */
	private void mostrarPopupAniadido() {
		JOptionPane.showMessageDialog(this,
			    "El contacto " + textoNombre.getText() + " ha sido a�adido.",
			    "Completado",
			    JOptionPane.INFORMATION_MESSAGE,
			    new ImageIcon(AgregarContacto.class.getResource("/com/islasfilipinas/cj/interfaz/iconos/aniadircont.png")));
	}
	
	/**
	 * "El contacto que intenta a�adir es algo inusual �est� seguro de que quiere a�adirlo?"
	 * @return 0 - S�, 1 - No
	 */
	private int mostrarPopupContactoRaro() {
		return JOptionPane.showConfirmDialog(this, 
				"El contacto que intenta a�adir es algo inusual, "
				+ "\n�est� seguro de que quiere a�adirlo?", "Contacto inusual", 
				JOptionPane.YES_NO_OPTION);
	}
	
	/**
	 * "Es necesario que rellene ambos campos para continuar."
	 */
	private void mostrarPopupCampoSinRellenar() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this,
			    "Es necesario que rellene ambos campos para continuar.",
			    "Advertencia",
			    JOptionPane.WARNING_MESSAGE);
	}
}
