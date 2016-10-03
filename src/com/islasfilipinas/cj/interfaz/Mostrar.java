package com.islasfilipinas.cj.interfaz;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import java.awt.Toolkit;

import javax.swing.JScrollPane;

import com.islasfilipinas.cj.agenda.Agenda;
import com.islasfilipinas.cj.agenda.Contacto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Comparator;

import javax.swing.JTable;

/**
 * Esta clase se encarga de generar la ventana que va a mostrar la agenda cargada en memoria
 * al usuario. Esta clase extiende de JDialog y genera una ventana modal dentro del JFrame padre.
 * La forma de mostrar los datos es usando una tabla (JTable) de dos columnas, nombre y número de teléfono.
 * <br>
 * La funcionalidad de esta clase es simplemente visual, sólo sirve para que el usuario contemple
 * la agenda que hay cargada en memoria.
 * <br>
 * El formato gráfico de esta clase es el siguiente:
 * <br><br>
 * <img src="http://i.imgur.com/nfZEs9U.jpg">
 * @author Carlos Garcia Corpas & Javier Sánchez Gómez
 * @version v1.3.1
 */
public class Mostrar extends JDialog {
	/**
	 * Identificador único de la clase AgregarContacto.
	 */
	private static final long serialVersionUID = -430412081158001271L;
	/**
	 * Objeto Agenda obtenido del frame padre.
	 */
	private Agenda agenda;
	/**
	 * Componente gráfico correspondiente con el la tabla donde se va a mostrar la agenda.
	 */
	private JTable table;

	/**
	 * Constructor del JDialog encargado de mostrar el contenido de la agenda. Este constructor
	 * establece el formato de esta pantalla, inicia sus componentes y les otorga sus debidas funciones.
	 */
	public Mostrar(JFrame padre) {
		super(padre);
		agenda = ((MenuPrincipal) padre).getAgenda();
		setTitle("Mostrar");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Mostrar.class.getResource("/com/islasfilipinas/cj/interfaz/iconos/icono_agenda.png")));
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(padre.getX()+50, padre.getY()+50, 450, 300);
		getContentPane().setLayout(null);
		
		
		iniciarComponentes(padre);
		setVisible(true);
		
	}

	/**
	 * Inicialización de los componentes. Los componentes gráficos son los siguientes:
	 * <ul>
	 * 	 <li>Panel con opción de scroll - JScrollPane</li>
	 *   <li>Tabla con la agenda cargada - JTable</li>
	 *   <li>Botón de volver - JButton</li>
	 * </ul>
	 * <br>
	 * 
	 * La única funcionalidad con la que puede interactuar el usuario es la del botón volver.
	 * La forma de rellenar la tabla es utilizando un modelo de datos. Esto se puede hacer gracias
	 * a la clase DefaulttableModel. A este modelo se le añaden los objetos que se encuentren
	 * en la agenda (aprovechando para ordenarlos con un comparador) y se añaden a la tabla.
	 * <br>
	 * Cabe destacar que el scroll sólo aparecerá en caso de que los datos "desborden" el espacio
	 * asignado para la tabla.
	 * @param padre Este parámetro es el JFrame principal.
	 */
	private void iniciarComponentes(JFrame padre) {	
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
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(351, 227, 73, 23);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				padre.setVisible(true);
				
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 35, 364, 170);
		getContentPane().add(scrollPane);
		
		table = new JTable(modeloTabla){
			
			private static final long serialVersionUID = -6438617421635996392L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		getContentPane().add(btnVolver);
		
	}
}
