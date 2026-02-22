package GUI.Cliente;

import repositorio.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BLL.*;
import DLL.*;
import repositorio.Validador;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class CarritosPagadosCliente extends JFrame implements Validador {
	private static Connection con = Conexion.getInstance().getConnection();

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private Carrito carritoSeleccionado;

	public CarritosPagadosCliente(Cliente cliente) {
		setTitle("carritopagados");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(174, 174, 174));
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSeleccionado = new JLabel("Seleccionado:");
		lblSeleccionado.setBounds(10, 10, 760, 24);
		contentPane.add(lblSeleccionado);

		model = new DefaultTableModel(new String[] { "Id_carrito", "Fecha", "Estado", "Total", "Codigo_envio", "fk_cliente" }, 0);
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 40, 760, 233);
		contentPane.add(scrollPane);

		table.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				int row = table.getSelectedRow();
				if (row != -1) {
					carritoSeleccionado = new Carrito((int) model.getValueAt(row, 0),
							(Date) model.getValueAt(row, 1), (String) model.getValueAt(row, 2),
							(double) model.getValueAt(row, 3), (int) model.getValueAt(row, 4),
							(int) model.getValueAt(row, 5));

					lblSeleccionado.setText(carritoSeleccionado.toString());
				}
			}
		});

		JButton Salir = new JButton("Salir");
		Salir.setBounds(651, 400, 123, 50);
		Salir.addActionListener(e -> {
			OpcionesCarritosCliente opcionescarritoscliente = new OpcionesCarritosCliente(cliente);
			opcionescarritoscliente.setVisible(true);
			dispose();
		});
		contentPane.add(Salir);

		JLabel LblError = new JLabel("");
		LblError.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		LblError.setForeground(new Color(255, 0, 0));
		LblError.setBounds(10, 312, 372, 33);
		contentPane.add(LblError);

		JButton Detalle = new JButton("Ver Detalle");
		Detalle.addActionListener(e -> {
			if (carritoSeleccionado == null) {
				LblError.setText("Error. No se eligió ningun producto");
			} else {
				CarritosDetalle carritosdetalle = new CarritosDetalle(cliente, carritoSeleccionado.getId_carrito());
				carritosdetalle.setVisible(true);
				dispose();
			}
		});
		Detalle.setBounds(25, 386, 136, 64);
		contentPane.add(Detalle);

		cargarTabla(cliente);
	}

	private void cargarTabla(Cliente cliente) {
		model.setRowCount(0);
		ControllerCarrito controller = new ControllerCarrito();
		List<Carrito> carritos = controller.mostrarCarritoporClientePagados(cliente.getId());
		for (Carrito u : carritos) {
			model.addRow(new Object[] { u.getId_carrito(), u.getFecha(), u.getEstado(), u.getTotal_compra(), u.getCodigo_envio(), u.getFk_cliente() });
		}
	}
}
