package GUI.AdminENvios;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BLL.*;
import DLL.*;
import GUI.common.BaseFrame;

public class Admin_carrito_pagado extends BaseFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private Carrito carritoSeleccionado;
    private JTextField inpFiltro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_carrito_proceso frame = new Admin_carrito_proceso();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Admin_carrito_pagado() {
		super();
	        contentPane = new JPanel();
	        contentPane.setBackground(SystemColor.controlShadow);
	        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
	        setContentPane(contentPane);
	        contentPane.setLayout(null);

	        JLabel lblSeleccionado = new JLabel("Seleccionado:");
	        lblSeleccionado.setBounds(10, 10, 760, 20);
	        contentPane.add(lblSeleccionado);

	        model = new DefaultTableModel(new String[]{
	        	"nombre",
	     		"stock",
	      		"precio",
	      		"fk_categoria",
	      		"peligroso",
	          	"id_producto"}, 0);
	        table = new JTable(model);
	        JScrollPane scrollPane = new JScrollPane(table);
	        scrollPane.setBounds(10, 40, 760, 200);
	        contentPane.add(scrollPane);

	        // Acción al seleccionar fila
	        table.getSelectionModel().addListSelectionListener(e -> {
	            if (!e.getValueIsAdjusting()) {
	                int row = table.getSelectedRow();
	                if (row != -1) {

	         

	                	carritoSeleccionado = new Carrito(
	                        (int) model.getValueAt(row, 0),
	                        (Date) model.getValueAt(row, 1),
	                        (double) model.getValueAt(row, 2),
	                        (int) model.getValueAt(row, 3),
	                        (int) model.getValueAt(row,4)
	                        
	                        
	                    );
	                	
	                	lblSeleccionado.setText(carritoSeleccionado.toString());
	                	
	                	
	          }
	                }
	                });
	                    
	        // Cargar datos
	        cargarTabla();

	        
	    }

	    private void cargarTabla() {
	        model.setRowCount(0);
	        LinkedList<Carrito> carritos = ControllerCarrito.mostrarCarritosPagados();
	        for (Carrito u : carritos) {
	            model.addRow(

	            		new Object[]{
	            				u.getId_carrito(),
	            				u.getFecha(),
	            				u.getTotal_compra(),
	            				u.getCodigo_envio(),
	            				u.getFk_cliente()
	            				
	            		}
	            		);
	        }
	    
	}
	    private void cargarTabla_productos(int id) {
	        model.setRowCount(0);
	        LinkedList<Producto> productos = ControllerDetalle.mostrarCarrito_detalle(id);
	        for (Producto u : productos) {
	            model.addRow(

	            		new Object[]{
	            				u.getNombre(),
	            				u.getStcok(),
	            				u.getPrecio(),
	            				u.getCategoria(),
	            				u.getPrecio()
	            				
	            		}
	            		);
	        }
	    
	}

}