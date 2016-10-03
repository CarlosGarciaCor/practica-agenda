package com.islasfilipinas.cj.interfaz;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.InputMismatchException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.islasfilipinas.cj.agenda.Agenda;
import com.islasfilipinas.cj.agenda.Contacto;
import com.islasfilipinas.cj.exceptions.ContactoRepetidoException;
/**
 * Esta directamente relacionada con la clase {@link ModificarBuscar}. En dicha clase
 * se establece que cuando el usuario elige un contacto de una tabla y pincha en el botón de
 * modificar, se abrirá esta ventana, se llamará a esta clase.
 * <br>
 * Esta clase, que extiende JDialog, es muy similar a la clase AgregarContacto. La diferencia
 * que tiene es que la función no es la misma. Lo que hace esta clase es borrar un contacto existente
 * para reemplazarlo añadiendo uno nuevo.
 * <br>
 * El formato gráfico es el siguiente:
 * <br><br>
 * <img src="http://i.imgur.com/1GzzGJu.jpg">
 * @author Carlos Garcia Corpas & Javier Sánchez Gómez
 * @version v1.3.1
 */
public class ModificarContacto extends JDialog{
	
	/**
	 * Identificador único de la clase AgregarContacto.
	 */
	private static final long serialVersionUID = -8836350213555478216L;
	/**
	 * Objeto Agenda obtenido del frame padre.
	 */
	private Agenda agenda;
	/**
	 * Componente gráfico correspondiente con el campo de texto donde se va a introducir el nombre.
	 */
	private JTextField textoNombre;
	/**
	 * Componente gráfico correspondiente con el campo de texto donde se va a introducir el número de teléfono.
	 */
	private JTextField textoTlfn;
	/**
	 * String que guarda el nombre del contacto que se desea modificar.
	 */
	private String nombreVie;
	/**
	 * String que guarda el teléfono del contacto que se desea modificar.
	 */
	private String tlfnVie;
	
	/**
	 * Constructor de {@link ModificarContacto}. Se encarga de dar formato a la pantalla de moficicar, 
	 * iniciar sus componentes y asignarles funciones mediante Listener.
	 * Recibe, además del JFrame padre, dos parámetros desde el JDialog anterior ({@link BuscarModificar}).
	 * Estos parámetros son el contacto que el usuario desea modificar.
	 * 
	 * @param padre Este parámetro es el JFrame principal.
	 * @param nombreVie Nombre del contacto a modificar.
	 * @param tlfnVie Teléfono del contacto a modificar.
	 */
	public ModificarContacto(JFrame padre, String nombreVie, String tlfnVie) {
		super(padre);
		agenda = ((MenuPrincipal) padre).getAgenda();
		this.nombreVie=nombreVie;
		this.tlfnVie=tlfnVie;
		
		setTitle("Modificar contacto: " + nombreVie);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/com/islasfilipinas/cj/interfaz/iconos/icono_agenda.png")));
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(padre.getX()+50, padre.getY()+50, 320, 268);
		setModal(true);
		getContentPane().setLayout(null);
		
		
		initComponents();
	}

	/**
	 * Inicialización de los componentes de este JDialog.
	 * Son los siguientes:
	 * <ul>
	 * 	<li>Caja de texto para el nombre - JLabel</li>
	 *  <li>Caja de texto para el teléfono - JLabel</li>
	 *  <li>Botón de modificar - JButton</li>
	 *  <li>Botón de volver - JButton</li>
	 * </ul>
	 * <br>
	 */
	private void initComponents() {
		JLabel labelNombre = new JLabel("Nuevo nombre:");
		labelNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelNombre.setBounds(31, 74, 93, 14);
		getContentPane().add(labelNombre);
		
		
		textoNombre = new JTextField();
		int limiteNombre = 20;
		textoNombre.setBounds(128, 72, 143, 20);
		textoNombre.setColumns(10);
		textoNombre.setText(nombreVie);
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
		
		
		JLabel labelTelfono = new JLabel("Nuevo tel\u00E9fono:");
		labelTelfono.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelTelfono.setBounds(31, 121, 93, 17);
		getContentPane().add(labelTelfono);
		
		textoTlfn = new JTextField();
		int limiteTelefono = 13;
		textoTlfn.setBounds(128, 120, 143, 20);
		textoTlfn.setColumns(10);
		textoTlfn.setText(tlfnVie);
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
		
		
		JButton botonAniadir = new JButton("Modificar");
		botonAniadir.setBounds(41, 175, 89, 23);
		botonAniadir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modificar();
			}
		});
		getContentPane().add(botonAniadir);
		
		JButton botonVolver = new JButton("Volver");
		botonVolver.setBounds(164, 175, 89, 23);
		botonVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		getContentPane().add(botonVolver);
		
		JLabel lblModiqueElCampo = new JLabel("Modique el campo o campos deseados.");
		lblModiqueElCampo.setBounds(61, 11, 186, 14);
		getContentPane().add(lblModiqueElCampo);
		
		JLabel lbllosCamposPor = new JLabel("(Los campos por defecto son los propios del contacto)");
		lbllosCamposPor.setBounds(21, 30, 261, 20);
		getContentPane().add(lbllosCamposPor);
		
		setVisible(true);
	}
	
	/**
	 * Funcionalidad de modificar. Recibe un contacto que desea editar y se comunica con la clase
	 * {@link Agenda} para modificarlo. Esto lo hace en dos pasos, primero se borra el contacto
	 * sin modificar para luego añadir el contacto modificado.
	 */
	private void modificar() {
		
		Contacto viejo = new Contacto (nombreVie, tlfnVie);
		
		try {
			
			if (textoNombre.getText().matches("[a-zA-Z]+")
					&& textoTlfn.getText().matches("[0-9]+")){
				//agenda.modificar(viejo, nuevo);;
				agenda.borrar(viejo);
				agenda.annadir(new Contacto(textoNombre.getText(), textoTlfn.getText()));
				mostrarPopupModificado();
				dispose();
			}
			else {
				if (mostrarPopupContactoRaro()==0){
					agenda.borrar(viejo);
					agenda.annadir(new Contacto(textoNombre.getText(), textoTlfn.getText()));
					mostrarPopupModificado();
					dispose();
				}
			}
				
		} catch (ContactoRepetidoException e1) {
			mostrarPopupYaExiste();
		} catch (InputMismatchException e2){
			mostrarPopupCampoSinRellenar();
		}
	}
	
	/**
	 * "Está escribiendo el número o nombre de un contacto ya existente. Inténtelo de nuevo por favor.",
	 */
	private void mostrarPopupYaExiste() {
		JOptionPane.showMessageDialog(this,
			    "Está escribiendo el número o nombre de un contacto ya existente.\n Inténtelo de nuevo por favor.",
			    "Advertencia",
			    JOptionPane.WARNING_MESSAGE);
	}
	
	/**
	 * "El nuevo nombre de su contacto es "ejemplo". La modificación ha sido guardada.",
	 */
	private void mostrarPopupModificado() {
		JOptionPane.showMessageDialog(this,
			    "El nuevo nombre de su contacto es " + textoNombre.getText() + ".\nLa modificación ha sido guardada.",
			    "Completado",
			    JOptionPane.INFORMATION_MESSAGE,
			    new ImageIcon(AgregarContacto.class.getResource("/com/islasfilipinas/cj/interfaz/iconos/aniadircont.png")));
	}
	
	/**
	 * "El contacto que intenta añadir es algo inusual, ¿está seguro de que quiere añadirlo?"
	 * @return 0 - Sí, 1 - No
	 */
	private int mostrarPopupContactoRaro() {
		return JOptionPane.showConfirmDialog(this, 
				"El contacto que intenta añadir es algo inusual, "
				+ "\n¿está seguro de que quiere añadirlo?", "Contacto inusual", 
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
