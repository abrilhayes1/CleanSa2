package GUI.AdminVentas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

import BLL.Cliente;
import DLL.ControllerCliente;
import GUI.common.BaseFrame;

import java.awt.event.ActionEvent;
import java.util.LinkedList;

public class vistaVerCliente extends BaseFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;

	public vistaVerCliente() {
		super();
		setTitle("Ver Clientes");

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Logo
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(vistaVerCliente.class.getResource("/img/logo.png")));
		lblLogo.setBounds(150, 10, 350, 80);
		contentPane.add(lblLogo);

		// Título
		JLabel lblTitulo = new JLabel("Listado de Clientes");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblTitulo.setBounds(220, 100, 250, 25);
		contentPane.add(lblTitulo);

		// Tabla
		model = new DefaultTableModel(new String[] { "Nombre", "Dirección", "DNI", "Tipo" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Para que la tabla no sea editable
			}
		};

		table = new JTable(model);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		table.setRowHeight(22);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(50, 140, 540, 200);
		contentPane.add(scrollPane);

		// Botón Volver
		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnVolver.setBounds(250, 360, 130, 35);
		contentPane.add(btnVolver);

		btnVolver.addActionListener(e -> {
			vistaAdminVentas ventana = new vistaAdminVentas();
			ventana.setVisible(true);
			dispose();
		});
		cargarClientes();
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnSalir.setBounds(240, 350, 120, 40); // CENTRADO
		btnSalir.addActionListener(e -> {
			vistaAdminVentas vistaAdminVentas = new vistaAdminVentas();
			vistaAdminVentas.setVisible(true);
		    dispose(); // CIERRA ESTA VENTANA
		});
		contentPane.add(btnSalir);
	}

	private void cargarClientes() {
		model.setRowCount(0);
		ControllerCliente controller = new ControllerCliente();
		LinkedList<Cliente> clientes = controller.mostrarClientes();

		for (Cliente c : clientes) {
			String tipoTexto = c.getTipo() == 1 ? "Personal" : "Empresa";
			model.addRow(new Object[] { c.getNombre(), c.getDireccion(), c.getDni(), tipoTexto });
		}
	}
}
