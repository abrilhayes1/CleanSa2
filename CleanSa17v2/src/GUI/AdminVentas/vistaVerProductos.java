package GUI.AdminVentas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BLL.Producto;
import DLL.ControllerProducto;
import GUI.common.BaseFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

public class vistaVerProductos extends BaseFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JTable table;
	private DefaultTableModel model;
	private Producto productoSeleccionado;

	private JTextField txtBuscar;
	private JButton btnEditar;
	private JButton btnEliminar;

	public vistaVerProductos(JFrame anterior) {
		super();
		setTitle("Productos");

		contentPane = new JPanel(new BorderLayout(10, 10));
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
		setContentPane(contentPane);

		// ====== NORTH: Título + buscador ======
		JPanel topPanel = new JPanel(new BorderLayout(10, 10));
		JLabel lblTitulo = new JLabel("Listado de productos");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
		topPanel.add(lblTitulo, BorderLayout.WEST);

		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
		txtBuscar = new JTextField(18);
		JButton btnBuscar = new JButton("Buscar");
		JButton btnLimpiar = new JButton("Limpiar");

		searchPanel.add(new JLabel("Nombre:"));
		searchPanel.add(txtBuscar);
		searchPanel.add(btnBuscar);
		searchPanel.add(btnLimpiar);

		topPanel.add(searchPanel, BorderLayout.EAST);
		contentPane.add(topPanel, BorderLayout.NORTH);

		model = new DefaultTableModel(new String[] { "Nombre", "Precio", "Stock", "Categoría" }, 0) {
			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			} // solo lectura
		};

		table = new JTable(model);
		table.setRowHeight(22);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 13));

		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane, BorderLayout.CENTER);

		JPanel southPanel = new JPanel(new BorderLayout(10, 10));

		JLabel lblSeleccionado = new JLabel("Seleccionado: -");
		lblSeleccionado.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		southPanel.add(lblSeleccionado, BorderLayout.NORTH);

		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));

		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(210, 153, 150, 23);
		btnVolver.addActionListener(e -> {
			vistaMenuAdminVentas ventana = new vistaMenuAdminVentas();
			ventana.setVisible(true);
			dispose();
		});
		JButton btnActualizar = new JButton("Actualizar");
		btnEditar = new JButton("Editar");
		btnEliminar = new JButton("Eliminar");

		btnEditar.setEnabled(false);
		btnEliminar.setEnabled(false);

		buttonsPanel.add(btnVolver);
		buttonsPanel.add(btnActualizar);
		buttonsPanel.add(btnEditar);
		buttonsPanel.add(btnEliminar);

		southPanel.add(buttonsPanel, BorderLayout.SOUTH);
		contentPane.add(southPanel, BorderLayout.SOUTH);

		// ====== Cargar ======
		cargarTabla(null);

		// ====== Selección tabla ======
		table.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				int row = table.getSelectedRow();
				if (row != -1) {
					productoSeleccionado = new Producto((String) model.getValueAt(row, 0),
							(double) model.getValueAt(row, 1), (int) model.getValueAt(row, 2),
							(int) model.getValueAt(row, 3), 0, 0);

					lblSeleccionado.setText("Seleccionado: " + productoSeleccionado.getNombre());
					btnEditar.setEnabled(true);
					btnEliminar.setEnabled(true);
				} else {
					productoSeleccionado = null;
					lblSeleccionado.setText("Seleccionado: -");
					btnEditar.setEnabled(false);
					btnEliminar.setEnabled(false);
				}
			}
		});

		// ====== Botones ======
		btnActualizar.addActionListener(e -> cargarTabla(null));

		btnBuscar.addActionListener((ActionEvent e) -> {
			String filtro = txtBuscar.getText().trim();
			cargarTabla(filtro.isEmpty() ? null : filtro);
		});

		btnLimpiar.addActionListener(e -> {
			txtBuscar.setText("");
			cargarTabla(null);
		});

		btnVolver.addActionListener(e -> {
			if (anterior != null)
				anterior.setVisible(true);
			dispose();
		});

		btnEliminar.addActionListener(e -> {
			if (productoSeleccionado != null) {
				vistaDestroyProducto ventana = new vistaDestroyProducto(productoSeleccionado);
				ventana.setVisible(true);
				this.setVisible(false);
			}
		});

		btnEditar.addActionListener(e -> {
			// Acá después conectás tu vista de editar (cuando la hagas)
			// Ej: new vistaEditarProducto(productoSeleccionado).setVisible(true);
			JOptionPane.showMessageDialog(null, "Pendiente: vista de editar producto.");
		});
	}

	public vistaVerProductos() {
		this(null);
	}

	private void cargarTabla(String filtroNombre) {
		model.setRowCount(0);
		ControllerProducto controller = new ControllerProducto();
		LinkedList<Producto> productos = controller.mostrarProductos();

		for (Producto p : productos) {
			// filtro simple por nombre (sin tocar controller)
			if (filtroNombre != null && !p.getNombre().toLowerCase().contains(filtroNombre.toLowerCase())) {
				continue;
			}
			model.addRow(new Object[] { p.getNombre(), p.getPrecio(), p.getStcok(), p.getCategoria() });
		}
	}
}
