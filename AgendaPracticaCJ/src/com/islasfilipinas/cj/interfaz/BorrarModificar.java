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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class BorrarModificar extends JDialog{
	private Agenda agenda;
	private JTable tabla;
	private int filaSeleccionada = -1;
	
	public BorrarModificar(JFrame padre){
		super(padre);
		agenda = ((MenuPrincipal) padre).getAgenda();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Mostrar.class.getResource("/com/islasfilipinas/cj/interfaz/iconos/icono_agenda.png")));
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(padre.getX()+50, padre.getY()+50, 450, 300);
		getContentPane().setLayout(null);
		
		
		iniciarComponentes(padre);
		setVisible(true);
	}
	
	private void iniciarComponentes(JFrame padre) {

		String[] nombrecolumnas = {"Nombre", "Teléfono"};
		DefaultTableModel modeloTabla = new DefaultTableModel(nombrecolumnas,0);
		agenda.getContactos().sort(new Comparator<Contacto>() {

			@Override
			public int compare(Contacto o1, Contacto o2) {
				 return (o1.getNombre().compareTo(o2.getNombre()));
			}
		});
		for(Contacto item : agenda.getContactos()){
			String nombre = item.getNombre();
			String telefono = item.getTelefono();
			Object[] contacto = {nombre, telefono};
			modeloTabla.addRow(contacto);
		}
		
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 22, 337, 170);
		getContentPane().add(scrollPane);
		
		tabla = new JTable(modeloTabla){
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
				if (filaSeleccionada != -1){
					int eleccion = JOptionPane.showConfirmDialog(BorrarModificar.this, "¿Está seguro de que desea borrar el contacto: " + tabla.getValueAt(filaSeleccionada, 0).toString(),
																	"Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (eleccion==0){
						try {
							Contacto aBorrar = new Contacto(tabla.getValueAt(filaSeleccionada, 0).toString(), tabla.getValueAt(filaSeleccionada, 1).toString());
							agenda.borrar(aBorrar);
						} catch (ContactoRepetidoException e1) {
							
						}
					}
				}
				else{
					JOptionPane.showMessageDialog(BorrarModificar.this, "Necesitas elegir un contacto para poder borrarlo.",
							"Información", JOptionPane.INFORMATION_MESSAGE);
				}
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
				}
				else{
					JOptionPane.showMessageDialog(BorrarModificar.this, "Necesitas elegir un contacto para poder modificarlo.",
							"Información", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		getContentPane().add(botonModificar);
		
	}
}
