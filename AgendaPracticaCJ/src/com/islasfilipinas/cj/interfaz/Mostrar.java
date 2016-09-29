package com.islasfilipinas.cj.interfaz;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import java.awt.Toolkit;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import com.islasfilipinas.cj.agenda.Agenda;
import com.islasfilipinas.cj.agenda.Contacto;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class Mostrar extends JDialog {
	private Agenda agenda;
	// HASTA QUE PUNTO ES ESTO NECESARIOOO?
	private JFrame padre;
	private JTable table;

	/**
	 * Constructor del JDialog encargado de mostrar el contenido de la agenda.
	 */
	public Mostrar(JFrame padre) {
		super(padre);
		this.padre=padre;
		agenda = ((MenuPrincipal) padre).getAgenda();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Mostrar.class.getResource("/com/islasfilipinas/cj/interfaz/iconos/icono_agenda.png")));
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		
		iniciarComponentes();
		setVisible(true);
		
	}

	private void iniciarComponentes() {	
		String[] nombrecolumnas = {"Nombre", "Teléfono"};
		DefaultTableModel modeloTabla = new DefaultTableModel(nombrecolumnas,0);
		for(Contacto item : agenda.getContactos()){
			String nombre = item.getNombre();
			String telefono = item.getTelefono();
			Object[] contacto = {nombre, telefono};
			modeloTabla.addRow(contacto);
		}
		
		table = new JTable();
		table.setBounds(27, 24, 377, 192);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(modeloTabla);
		getContentPane().add(table);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(239, 144, 2, 2);
		table.add(scrollPane);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(351, 227, 73, 23);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				padre.setVisible(true);
				
			}
		});
		getContentPane().add(btnVolver);
		
	}
}
