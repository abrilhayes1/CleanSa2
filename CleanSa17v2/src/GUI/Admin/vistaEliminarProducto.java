package GUI.Admin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BLL.Producto;
import DLL.ControllerProducto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

public class vistaEliminarProducto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private Producto productoSeleccionado;
	private JFrame ventanaAnterior;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				vistaEliminarProducto frame = new vistaEliminarProducto();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public vistaEliminarProducto(JFrame anterior) {
		this.ventanaAnterior = anterior;

		setTitle("Seleccionar producto para eliminar");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Seleccioná el producto a eliminar:");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblTitulo.setBounds(10, 10, 400, 30);
		contentPane.add(lblTitulo);

		model = new DefaultTableModel(new String[] { "Nombre", "Precio", "Stock", "Categoría" }, 0);
		table = new JTable(model);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		table.setRowHeight(22);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 50, 540, 200);
		contentPane.add(scrollPane);

		JLabel lblSeleccionado = new JLabel("Seleccionado: ");
		lblSeleccionado.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblSeleccionado.setBounds(30, 260, 540, 25);
		contentPane.add(lblSeleccionado);

		JButton btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnSiguiente.setBounds(430, 295, 130, 40);
		btnSiguiente.setEnabled(false);
		contentPane.add(btnSiguiente);

		JButton btnAtras = new JButton("Atrás");
		btnAtras.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnAtras.setBounds(20, 295, 130, 40);
		contentPane.add(btnAtras);

		cargarTabla();

		table.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				int row = table.getSelectedRow();
				if (row != -1) {
					productoSeleccionado = new Producto(
							(String) model.getValueAt(row, 0), 
							(double) model.getValueAt(row, 1), 
							(int) model.getValueAt(row, 2),    
							(int) model.getValueAt(row, 3),    
							0,
							0
						);

					lblSeleccionado.setText("Seleccionado: " + productoSeleccionado.getNombre());
					btnSiguiente.setEnabled(true);
				}
			}
		});

		btnSiguiente.addActionListener((ActionEvent e) -> {
			if (productoSeleccionado != null) {
				vistaDestroyProducto ventana = new vistaDestroyProducto(productoSeleccionado);
				ventana.setVisible(true);
				this.setVisible(false);
			}
		});

		btnAtras.addActionListener(e -> {
			if (ventanaAnterior != null)
				ventanaAnterior.setVisible(true);
			dispose();
		});
	}

	public vistaEliminarProducto() {
		this(null);
	}

	private void cargarTabla() {
		model.setRowCount(0);
		ControllerProducto controller = new ControllerProducto();
		LinkedList<Producto> productos = controller.mostrarProductos();
		for (Producto p : productos) {
			model.addRow(new Object[] { p.getNombre(), p.getPrecio(), p.getStcok(), p.getCategoria() });
		}
	}
}
