package GUI.Admin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import BLL.Producto;
import DLL.ControllerProducto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class vistaDestroyProducto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Producto productoSeleccionado;

	public vistaDestroyProducto(Producto producto) {
		this.productoSeleccionado = producto;

		setTitle("Eliminar Producto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("¿Deseás eliminar este producto?");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblTitulo.setBounds(20, 20, 400, 30);
		contentPane.add(lblTitulo);

		JLabel lblProducto = new JLabel("Producto: " + productoSeleccionado.getNombre());
		lblProducto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblProducto.setBounds(20, 70, 400, 25);
		contentPane.add(lblProducto);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnEliminar.setBackground(new Color(255, 102, 102));
		btnEliminar.setBounds(50, 180, 130, 40);
		contentPane.add(btnEliminar);

		JButton btnAtras = new JButton("Atrás");
		btnAtras.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnAtras.setBounds(300, 180, 130, 40);
		contentPane.add(btnAtras);

		// Acción del botón Eliminar
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControllerProducto controller = new ControllerProducto();
				boolean eliminado = controller.eliminarProductoPorNombre(productoSeleccionado.getNombre());

				if (eliminado) {
					JOptionPane.showMessageDialog(null, "Producto eliminado correctamente.");
					vistaAdminVentas ventana = new vistaAdminVentas();
					ventana.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Error al eliminar el producto.");
				}
			}
		});

		// Acción del botón Atrás
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vistaEliminarProducto ventana = new vistaEliminarProducto();
				ventana.setVisible(true);
				dispose();
			}
		});
	}
}