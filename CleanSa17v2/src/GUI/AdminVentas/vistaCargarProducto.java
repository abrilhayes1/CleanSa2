package GUI.AdminVentas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextField;

import DLL.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Categoria;
import BLL.Producto;
import DLL.ControllerProducto;
import GUI.common.BaseFrame;
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

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.Dimension;

public class vistaCargarProducto extends BaseFrame {
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
	    super();
	    controller = new ControllerProducto();

	    contentPane = new JPanel(new BorderLayout());
	    contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
	    setContentPane(contentPane);

	    // ====== TOP: Logo centrado ======
	    JLabel lblLogo = new JLabel("");
	    lblLogo.setIcon(new ImageIcon(vistaCargarProducto.class.getResource("/img/logo.png")));
	    lblLogo.setHorizontalAlignment(SwingConstants.CENTER);

	    JPanel topPanel = new JPanel(new BorderLayout());
	    topPanel.add(lblLogo, BorderLayout.CENTER);
	    contentPane.add(topPanel, BorderLayout.NORTH);

	    // ====== CENTER: Formulario centrado ======
	    JPanel centerWrapper = new JPanel(new GridBagLayout()); // centra el form en pantalla
	    JPanel formPanel = new JPanel(new GridBagLayout());     // ordena el form en grilla
	    formPanel.setOpaque(false);

	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.insets = new Insets(6, 6, 6, 6);
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.anchor = GridBagConstraints.WEST;

	    // Row 0: Nombre
	    JLabel lblNewLabel = new JLabel("Nombre");
	    gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
	    formPanel.add(lblNewLabel, gbc);

	    textField = new JTextField();
	    textField.setColumns(18);
	    gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1;
	    formPanel.add(textField, gbc);

	    // Row 1: Precio
	    JLabel lblPrecio = new JLabel("Precio");
	    gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
	    formPanel.add(lblPrecio, gbc);

	    textField_1 = new JTextField();
	    textField_1.setColumns(18);
	    gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1;
	    formPanel.add(textField_1, gbc);

	    // Row 2: Stock
	    JLabel lblStock = new JLabel("Stock");
	    gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
	    formPanel.add(lblStock, gbc);

	    textField_2 = new JTextField();
	    textField_2.setColumns(18);
	    gbc.gridx = 1; gbc.gridy = 2; gbc.weightx = 1;
	    formPanel.add(textField_2, gbc);

	    // Row 3: Categoria
	    JLabel lblCategoria = new JLabel("Categoria");
	    gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0;
	    formPanel.add(lblCategoria, gbc);

	    comboBox = new JComboBox<>();
	    gbc.gridx = 1; gbc.gridy = 3; gbc.weightx = 1;
	    formPanel.add(comboBox, gbc);
	    traerCategoria();

	    // Row 4: Checkbox (ocupa 2 columnas)
	    JCheckBox chckbxNewCheckBox = new JCheckBox("Producto Peligroso");
	    gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2; gbc.weightx = 1;
	    formPanel.add(chckbxNewCheckBox, gbc);
	    gbc.gridwidth = 1;

	    // Row 5: Botón agregar (ocupa 2 columnas)
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

	                int peligrosoInt = peligroso ? 1 : 2;

	                if (controller.encontrarProductos(nombre)) {
	                    Agregado.setText("Ya existe un producto con ese nombre.");
	                    Agregado.setForeground(Color.RED);
	                    Agregado.setVisible(true);
	                    return;
	                }

	                Producto nuevoProducto = new Producto(nombre, precio, stock, idCategoria, peligrosoInt, 0);
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

	    gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
	    gbc.fill = GridBagConstraints.NONE;
	    gbc.anchor = GridBagConstraints.CENTER;
	    formPanel.add(cargarProducto, gbc);

	    // Row 6: Label Agregado (centrado, debajo del botón)
	    Agregado = new JLabel("");
	    Agregado.setOpaque(true);
	    Agregado.setBackground(Color.GRAY);
	    Agregado.setHorizontalAlignment(SwingConstants.CENTER);
	    Agregado.setVisible(false);
	    Agregado.setPreferredSize(new Dimension(320, 22));

	    gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
	    gbc.fill = GridBagConstraints.NONE;
	    gbc.anchor = GridBagConstraints.CENTER;
	    formPanel.add(Agregado, gbc);

	    centerWrapper.add(formPanel);
	    contentPane.add(centerWrapper, BorderLayout.CENTER);

	    // ====== SOUTH: Volver abajo a la derecha ======
	    JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
	    JButton btnVolver = new JButton("Volver");
	    btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 14));
	    btnVolver.setPreferredSize(new Dimension(120, 40));
	    btnVolver.addActionListener(e -> {
	        vistaAdminVentas vistaAdminVentas = new vistaAdminVentas();
	        vistaAdminVentas.setVisible(true);
	        dispose();
	    });

	    bottomPanel.add(btnVolver);
	    contentPane.add(bottomPanel, BorderLayout.SOUTH);
	}
	private void traerCategoria() { //Trae las categorias desde la base de datos y las junta en una lista para el desplegable
		ControllerCategoria controllercategoria = new ControllerCategoria();
		LinkedList<Categoria> categorias = controllercategoria.mostrarCategorias(); //Guarda las categorias
		
		for (Categoria c : categorias) {
			comboBox.addItem(c);
		}
	}
}
