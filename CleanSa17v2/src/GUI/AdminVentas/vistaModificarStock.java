package GUI.AdminVentas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Producto;
import GUI.common.BaseFrame;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;

public class vistaModificarStock extends BaseFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vistaModificarStock frame = new vistaModificarStock();
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
	public vistaModificarStock() {
		super();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<?> lista_productos = new JComboBox();
		lista_productos.setBounds(20, 43, 146, 22);
		contentPane.add(lista_productos);
		
		JLabel titulo = new JLabel("Seleccione el producto a modificar");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		titulo.setBounds(10, 11, 354, 22);
		contentPane.add(titulo);
		
		JButton boton_modificar = new JButton("Modificar");
		boton_modificar.setBounds(91, 72, 75, 23);
		contentPane.add(boton_modificar);
		
		JButton btnSalir = new JButton("Volver");
		btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnSalir.setBounds(240, 350, 120, 40); // CENTRADO
		btnSalir.addActionListener(e -> {
			vistaMenuAdminVentas vistaAdminVentas = new vistaMenuAdminVentas();
			vistaAdminVentas.setVisible(true);
		    dispose(); // CIERRA ESTA VENTANA
		});
		contentPane.add(btnSalir);
	}
	
	public Producto traerProductos() {
		
		return null;
		
	}
}
