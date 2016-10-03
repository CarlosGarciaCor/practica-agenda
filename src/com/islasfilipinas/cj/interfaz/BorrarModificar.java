package com.islasfilipinas.cj.interfaz;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.islasfilipinas.cj.agenda.Agenda;
import com.islasfilipinas.cj.agenda.Contacto;
import com.islasfilipinas.cj.exceptions.ContactoRepetidoException;

import javax.swing.ListSelectionModel;

import java.awt.event.MouseEvent;
/**
 * Esta clase se encarga de formar la ventana que va a permitir al usuario tanto modificar como
 * borrar contactos. Esta clase extiende de JDialog y va a ser llamada por la clase {@link MenuPrincipal}.
 * La ventana generada es una ventana modal, enmarcada dentro del frame padre.
 * Esta ventana no podrá ser cerrada, sólo se podrá salir dando al botón de volver.
 * <br>
 * La forma en la que esta ventana va a permitir borrar o modificar contactos va a ser mediante
 * ciertos botones que van a responder sobre el contacto seleccionado sobre una tabla (JTable).
 * Se podría haber hecho también con un combo box (JComboBox).
 * <br>
 * La ventana generada es la siguiente:
 * <br><br>
 * <img src="http://i.imgur.com/RxUbRHC.jpg">
 * @author Carlos Garcia Corpas & Javier Sánchez Gómez
 * @version v1.3.1
 */
public class BorrarModificar extends JDialog{
	/**
	 * Idenficador único de la clase BorrarModificar
	 */
	private static final long serialVersionUID = 8473507689855826234L;
	/**
	 * Objeto Agenda obtenido del frame padre.
	 */
	private Agenda agenda;
	/**
	 * Componente gráfico referente a la tabla donde se van a mostrar los contactos.
	 */
	private JTable tabla;
	/**
	 * Índice de la tabla donde ha hecho click el usuario.
	 */
	private int filaSeleccionada = -1;
	
	/**
	 * Constructor de la clase {@link BorrarModificar}.
	 * Este constructor se encarga de tres cosas. Da formato a la ventana, inicia los componentes, y por último
	 * indica mediante Listeners cual va a ser la función de esos componentes.
	 * @param padre Este parámetro es el JFrame principal.
	 */
	public BorrarModificar(JFrame padre){
		super(padre);
		agenda = ((MenuPrincipal) padre).getAgenda();
		setTitle("Editar contacto");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Mostrar.class.getResource("/com/islasfilipinas/cj/interfaz/iconos/icono_agenda.png")));
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(padre.getX()+50, padre.getY()+50, 450, 300);
		getContentPane().setLayout(null);
		
		
		iniciarComponentes(padre);
		setVisible(true);
	}
	
	/**
	 * Este método inicia los componentes del JDialog. 
	 * Son los siguientes:
	 * <ul>
	 * 	 <li>Panel con opción de scroll - JScrollPane</li>
	 *   <li>Tabla con la agenda cargada - JTable</li>
	 *   <li>Botón de modificar - JButton</li>
	 *   <li>Botón de borrar - JButton</li>
	 *   <li>Botón de volver - JButton</li>
	 * </ul>
	 * <br>Este método es llamado desde el constructor.
	 * @param padre
	 */
	private void iniciarComponentes(JFrame padre) {		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 22, 337, 170);
		getContentPane().add(scrollPane);
		
		tabla = new JTable(){
			
			private static final long serialVersionUID = -4918548115363685990L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		tabla.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				 BorrarModificar.this.filaSeleccionada = tabla.rowAtPoint(e.getPoint());
			}
		});
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabla.setFillsViewportHeight(true);
		tabla.setEnabled(true);
		hacerModeloTabla(tabla);
		scrollPane.setViewportView(tabla);

		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(351, 227, 73, 23);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				padre.setVisible(true);
			}
		});
		getContentPane().add(btnVolver);
		
		JButton botonBorrar = new JButton("Borrar");
		botonBorrar.setBounds(228, 206, 89, 23);
		botonBorrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				borrar();
			}
		});
		getContentPane().add(botonBorrar);
		 
		JButton botonModificar = new JButton("Modificar");
		botonModificar.setBounds(99, 206, 89, 23);
		botonModificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (filaSeleccionada != -1){
					ModificarContacto modificar = new ModificarContacto(padre, tabla.getValueAt(filaSeleccionada, 0).toString(), tabla.getValueAt(filaSeleccionada, 1).toString());
					hacerModeloTabla(tabla);
				}
				else{
					JOptionPane.showMessageDialog(BorrarModificar.this, "Necesitas elegir un contacto para poder modificarlo.",
							"Información", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		getContentPane().add(botonModificar);
		
	}
	/**
	 * Este método imprime la agenda cargada en el componente tabla. 
	 * Elige los nombres de las columnas y le asigna un modelo de datos.
	 * <br>Este modelo de datos, creado con la clase DefaultTableModel, permite recoger
	 * objetos de una colección y añadirlos a una tabla, lo cual es básicamente lo que hace este método.
	 * @param tabla Necesita recibir el componente tabla creado en el método {@link initComponents()}
	 */
	private void hacerModeloTabla(JTable tabla) {
		String[] nombrecolumnas = {"Nombre", "Teléfono"};
		DefaultTableModel modeloTabla = new DefaultTableModel(nombrecolumnas,0);
		agenda.getContactos().sort(new Comparator<Contacto>() {

			@Override
			public int compare(Contacto o1, Contacto o2) {
				 return (o1.getNombre().compareToIgnoreCase(o2.getNombre()));
			}
		});
		for(Contacto item : agenda.getContactos()){
			String nombre = item.getNombre();
			String telefono = item.getTelefono();
			Object[] contacto = {nombre, telefono};
			modeloTabla.addRow(contacto);
		}
		tabla.setModel(modeloTabla);
	}

	/**
	 * Borra un contacto de la agenda.
	 * Siempre que el usuario haya selecccionado uno de los contactos cargados en la tabla, 
	 * este método, previa confirmación, borrará dicho contacto.
	 */
	private void borrar() {
		if (filaSeleccionada != -1){
			int eleccion = JOptionPane.showConfirmDialog(BorrarModificar.this, "¿Está seguro de que desea borrar el contacto: " + tabla.getValueAt(filaSeleccionada, 0).toString(),
															"Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (eleccion==0){
				try {
					Contacto aBorrar = new Contacto(tabla.getValueAt(filaSeleccionada, 0).toString(), tabla.getValueAt(filaSeleccionada, 1).toString());
					agenda.borrar(aBorrar);
					hacerModeloTabla(tabla);
				} catch (ContactoRepetidoException e1) {
					
				}
			}
		}
		else{
			JOptionPane.showMessageDialog(BorrarModificar.this, "Necesitas elegir un contacto para poder borrarlo.",
					"Información", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
