package GUI.Admin;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Categoria;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import DLL.*;

public class vistaCrearCategoria extends JFrame {
	private ControllerCategoria controller;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nombre_categoria;
	private JLabel Agregado;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vistaCrearCategoria frame = new vistaCrearCategoria();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public vistaCrearCategoria() {

		controller = new ControllerCategoria();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 400); // Tamaño ajustado para el logo y botones
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Logo
		JLabel logoLabel = new JLabel("");
		logoLabel.setIcon(new ImageIcon(vistaCrearCategoria.class.getResource("/img/logo.png")));
		logoLabel.setBounds(85, 10, 369, 90); // Ajustá este tamaño si tu logo es más chico o más grande
		contentPane.add(logoLabel);

		JLabel tituloLabel = new JLabel("Crear categoría");
		tituloLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		tituloLabel.setBounds(10, 110, 200, 26);
		contentPane.add(tituloLabel);

		JLabel nombre_categoria_label = new JLabel("Nombre");
		nombre_categoria_label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nombre_categoria_label.setBounds(10, 150, 100, 22);
		contentPane.add(nombre_categoria_label);

		nombre_categoria = new JTextField();
		nombre_categoria.setBounds(10, 175, 250, 30);
		contentPane.add(nombre_categoria);
		nombre_categoria.setColumns(10);

		JButton crear_categoria_btn = new JButton("Crear");
		crear_categoria_btn.setFont(new Font("Tahoma", Font.BOLD, 12));
		crear_categoria_btn.setBounds(270, 175, 100, 30);
		contentPane.add(crear_categoria_btn);

		Agregado = new JLabel("");
		Agregado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Agregado.setBounds(10, 220, 400, 20);
		Agregado.setVisible(false);
		contentPane.add(Agregado);

		crear_categoria_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String nombre = nombre_categoria.getText().trim();

					if (controller.encontrarCategorias(nombre)) {
						Agregado.setText("Ya existe una categoría con ese nombre.");
						Agregado.setForeground(Color.RED);
						Agregado.setVisible(true);
						return;
					}

					Categoria nuevaCategoria = new Categoria(0, nombre);
					nombre_categoria_label.setText("");
					controller.crearCategoria(nuevaCategoria);

					Agregado.setText("Nueva categoría agregada con éxito");
					Agregado.setForeground(Color.GREEN);
					Agregado.setVisible(true);
					nombre_categoria.setText("");

				} catch (Exception e2) {
					Agregado.setText("Error al cargar la nueva categoría.");
					Agregado.setForeground(Color.RED);
					Agregado.setVisible(true);
				}
			}
		});

		JButton volver_atras_btn = new JButton("Volver");
		volver_atras_btn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		volver_atras_btn.setBounds(10, 300, 100, 30);
		contentPane.add(volver_atras_btn);

		volver_atras_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vistaAdminVentas ventana = new vistaAdminVentas();
				ventana.setVisible(true);
				dispose();
			}
		});
	}
}
