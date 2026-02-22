package GUI.Admin;

import java.awt.EventQueue;
import java.awt.TextField;

import DLL.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Categoria;
import BLL.Producto;
import DLL.ControllerProducto;
import DLL.ControllerCategoria;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;

public class vistaCargarProducto extends JFrame {
	private ControllerProducto controller;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JComboBox<Categoria> comboBox;
	private JLabel Agregado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vistaCargarProducto frame = new vistaCargarProducto();
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
	public vistaCargarProducto() {
		controller = new ControllerProducto(); // Llama a la clase ControllerProducto para poder usar sus metodos
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 27, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(10, 11, 46, 14);
		contentPane.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 70, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(10, 54, 46, 14);
		contentPane.add(lblPrecio);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setBounds(10, 101, 46, 14);
		contentPane.add(lblStock);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(10, 117, 86, 20);
		contentPane.add(textField_2);
		
		comboBox = new JComboBox<>();
		comboBox.setBounds(10, 164, 105, 22);
		contentPane.add(comboBox);
		traerCategoria();
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(10, 148, 78, 14);
		contentPane.add(lblCategoria);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Producto Peligroso");
		chckbxNewCheckBox.setBounds(6, 195, 129, 23);
		contentPane.add(chckbxNewCheckBox);
		
		JButton cargarProducto = new JButton("Agregar Producto");
		cargarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String nombre = textField.getText();
					double precio = Double.parseDouble(textField_1.getText());
					int stock = Integer.parseInt(textField_2.getText());
					Categoria categoriaSeleccionada = (Categoria) comboBox.getSelectedItem();
					int idCategoria = categoriaSeleccionada.getId_categoria();
					boolean peligroso = chckbxNewCheckBox.isSelected();
					
					int peligrosoInt = peligroso ? 1 : 2; // Lo convierte a boolean
					
					if (controller.encontrarProductos(nombre)) { //Implementa el metodo de encontrar productos para validar si existe
						Agregado.setText("Ya existe un producto con ese nombre.");
					    Agregado.setForeground(Color.RED);
					    Agregado.setVisible(true);
					    return; 
						
					}
					
					Producto nuevoProducto = new Producto (nombre, precio, stock, idCategoria, peligrosoInt, 0);
					controller.agregarProducto(nuevoProducto);
					
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					comboBox.setSelectedIndex(0);
					chckbxNewCheckBox.setSelected(false);
					
					Agregado.setText("Producto agregado exitosamente.");
					Agregado.setForeground(Color.GREEN);
					Agregado.setVisible(true);
				 
				} catch (Exception e2) {
					Agregado.setText("Error al cargar producto.");
					Agregado.setForeground(Color.RED);
					Agregado.setVisible(true);
				}
				
			}
		});
		cargarProducto.setBounds(10, 227, 129, 23);
		contentPane.add(cargarProducto);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(vistaCargarProducto.class.getResource("/img/logo.png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setBounds(117, 0, 309, 137);
		contentPane.add(lblNewLabel_1);

		
		//Label de mensaje de confirmacion 
		Agregado = new JLabel("");
		Agregado.setOpaque(true);                
		Agregado.setBackground(Color.GRAY);
		Agregado.setBounds(159, 231, 250, 14); 
		Agregado.setVisible(false); 
		contentPane.add(Agregado);
		
		
		
		
	}
	private void traerCategoria() { //Trae las categorias desde la base de datos y las junta en una lista para el desplegable
		ControllerCategoria controllercategoria = new ControllerCategoria();
		LinkedList<Categoria> categorias = controllercategoria.mostrarCategorias(); //Guarda las categorias
		
		for (Categoria c : categorias) {
			comboBox.addItem(c);
		}
	}
}
