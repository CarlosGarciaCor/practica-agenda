package com.islasfilipinas.cj.interfaz;

import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

import com.islasfilipinas.cj.agenda.Agenda;
import com.islasfilipinas.cj.agenda.Contacto;

import java.awt.Color;
/**
 * La funci�n de esta clase es generar una pantalla que permita al usuario buscar un contacto en la agenda.
 * Para ello, esta clase va a extender JDialog. Va a crear una ventana modal, enmarcada dentro
 * del JFrame padre.
 * <br>
 * Esta ventana permite buscar tanto por nombre como por n�mero de tel�fono. Si se producen 
 * coincidencias con un contacto ya existente en memoria, en esta pantalla se visualizar�n los datos
 * de dicho contacto. Si no se producen, se le comunicar� al usuario.
 * <br>
 * El formato de esta pantalla es el siguiente:
 * <br><br>
 * <img src="http://i.imgur.com/DaPAuP9.jpg">
 * @author Carlos Garcia Corpas & Javier S�nchez G�mez
 * @version v1.3.1
 */
public class BuscarContacto extends JDialog{
	/**
	 * Identificador �nico de la clase AgregarContacto.
	 */
	private static final long serialVersionUID = 3798787102785679849L;
	/**
	 * Componente gr�fico correspondiente con el campo de texto donde se va a introducir la b�squeda a realizar.
	 */
	private JTextField busqueda;
	/**
	 * Componente gr�fico correspondiente con el campo de texto donde se va a imprimir el nombre encontrado.
	 */
	private JTextField resulNombre;
	/**
	 * Componente gr�fico correspondiente con el campo de texto donde se va a imprimir el n�mero de tel�fono encontrado.
	 */
	private JTextField resulTlfn;
	/**
	 * Objeto Agenda obtenido del frame padre.
	 */
	private Agenda agenda;
	/**
	 * Objeto contacto resultado de la b�squeda.
	 */
	private Contacto resultado;
	
	/**
	 * Constructor de {@link BuscarContacto}. Este constructor se encarga de establecer el formato de la ventana, 
	 * iniciar sus componentes, y dar funci�n a dichos componentes.
	 * @param padre Este par�metro es el JFrame principal.
	 */
	public BuscarContacto(JFrame padre){
		super(padre);
		this.agenda = ((MenuPrincipal) padre).getAgenda();
		setTitle("Buscar contacto");
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Mostrar.class.getResource("/com/islasfilipinas/cj/interfaz/iconos/icono_agenda.png")));
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(padre.getX()+50, padre.getY()+50, 427, 300);
		getContentPane().setLayout(null);
		
		
		initComponents();
	}
	
	/**
	 * M�todo que inicia los componentes de este JDialog.
	 * Es llamado por el constructor, e inicia los siguientes componentes:
	 * <ul>
	 * 	<li>Bot�n radial para el nombre - JRadioButton</li>
	 *  <li>Bot�n radial para el tel�fono - JRadioButton</li>
	 *  <li>Campo donde se va a introducir la b�squeda - JTextField</li>
	 *  <li>Bot�n de buscar - JButton</li>
	 *  <li>Bot�n de buscar - JButton</li>
	 *  <li>Panel de resultados - JPanel</li>
	 *  <ul>
	 *  	<li>Campo donde aparece el nombre buscado - JLabel</li>
	 *  	<li>Campo donde aparece el tel�fono buscado - JLabel</li>
	 *  </ul>
	 *  <li>Bot�n de volver - JButton</li>
	 * </ul>
	 * <br>
	 */
	private void initComponents() {
		ButtonGroup grupoBotones = new ButtonGroup();
		
		JRadioButton radioNombre = new JRadioButton("Nombre", true);
		radioNombre.setBounds(17, 32, 67, 23);
		getContentPane().add(radioNombre);
		
		JRadioButton radioTel = new JRadioButton("Tel\u00E9fono");
		radioTel.setBounds(17, 58, 67, 23);
		getContentPane().add(radioTel);
		
		grupoBotones.add(radioNombre);
		grupoBotones.add(radioTel);
		
		busqueda = new JTextField();
		busqueda.setBounds(121, 46, 173, 20);
		busqueda.setColumns(10);
		busqueda.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (busqueda.getText().length() >= 20){
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
		getContentPane().add(busqueda);
		
		JButton botonBuscar = new JButton("Buscar");
		botonBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					buscar(radioNombre);
			}
		});
		botonBuscar.setBounds(312, 45, 89, 23);
		getContentPane().add(botonBuscar);
		
		JLabel labelBusqueda = new JLabel("Buscar por:");
		labelBusqueda.setBounds(10, 11, 67, 14);
		getContentPane().add(labelBusqueda);
		
		JPanel panelResul = new JPanel();
		panelResul.setBorder(new TitledBorder(new LineBorder(new Color(185, 209, 234), 2), "Resultado de b\u00FAsqueda: ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelResul.setBounds(37, 88, 340, 130);
		getContentPane().add(panelResul);
		panelResul.setLayout(null);
		
		JLabel labelTlfn = new JLabel("Tel\u00E9fono:");
		labelTlfn.setBounds(41, 83, 46, 14);
		panelResul.add(labelTlfn);
		
		JLabel labelNombre = new JLabel("Nombre:");
		labelNombre.setBounds(46, 42, 41, 14);
		panelResul.add(labelNombre);
		
		resulNombre = new JTextField();
		resulNombre.setColumns(10);
		resulNombre.setBounds(121, 39, 173, 20);
		resulNombre.setEditable(false);
		panelResul.add(resulNombre);
		
		resulTlfn = new JTextField();
		resulTlfn.setColumns(10);
		resulTlfn.setBounds(121, 80, 173, 20);
		resulTlfn.setEditable(false);
		panelResul.add(resulTlfn);
		
		JButton botonVolver = new JButton("Volver");
		botonVolver.setBounds(298, 227, 89, 23);
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
	 * Este m�todo realiza la b�squeda de contacto en funci�n del campo por el que 
	 * se est� buscando.
	 * Si no hay coincidencias se lo comunica al usuario. Si si las hay, las imprime en
	 * el JPanel.
	 * @param radioNombre Bot�n radial seleccionado (nombre o tel�fono).
	 */
	private void buscar(JRadioButton radioNombre) {
		if (radioNombre.isSelected()){
			resultado = agenda.buscarPorNombre(busqueda.getText().toString());
			if (resultado!=null){
				resulNombre.setText(resultado.getNombre());
				resulTlfn.setText(resultado.getTelefono());
			}
			else
				mostrarPopupNoHayCoincidencia();
		}
		else {
			resultado = agenda.buscarPorTelefono(busqueda.getText().toString());
			if (resultado!=null){
				resulNombre.setText(resultado.getNombre());
				resulTlfn.setText(resultado.getTelefono());
			}
		}
	}

	/**
	 * "No hay coincidencias."
	 */
	private void mostrarPopupNoHayCoincidencia() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this, 
				"No hay coincidencias.", "Informaci�n", 
				JOptionPane.INFORMATION_MESSAGE);
	}
}
