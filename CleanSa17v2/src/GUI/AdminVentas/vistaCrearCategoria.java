package GUI.AdminVentas;

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
import GUI.common.BaseFrame;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.Dimension;

public class vistaCrearCategoria extends BaseFrame {
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
	    super();
	    controller = new ControllerCategoria();

	    contentPane = new JPanel(new BorderLayout());
	    contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
	    setContentPane(contentPane);

	    // ====== TOP: Logo centrado ======
	    JLabel logoLabel = new JLabel("");
	    logoLabel.setIcon(new ImageIcon(vistaCrearCategoria.class.getResource("/img/logo.png")));
	    logoLabel.setHorizontalAlignment(JLabel.CENTER);

	    JPanel topPanel = new JPanel(new BorderLayout());
	    topPanel.add(logoLabel, BorderLayout.CENTER);
	    contentPane.add(topPanel, BorderLayout.NORTH);

	    // ====== CENTER: Título + Form centrados ======
	    JPanel centerWrapper = new JPanel(new GridBagLayout());
	    JPanel centerPanel = new JPanel(new GridBagLayout());
	    centerPanel.setOpaque(false);

	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.insets = new Insets(8, 8, 8, 8);
	    gbc.fill = GridBagConstraints.HORIZONTAL;

	    // Título
	    JLabel tituloLabel = new JLabel("Crear categoría");
	    tituloLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
	    gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 3; gbc.weightx = 1;
	    gbc.anchor = GridBagConstraints.CENTER;
	    centerPanel.add(tituloLabel, gbc);

	    // Label "Nombre"
	    JLabel nombre_categoria_label = new JLabel("Nombre");
	    nombre_categoria_label.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1; gbc.weightx = 0;
	    gbc.anchor = GridBagConstraints.WEST;
	    centerPanel.add(nombre_categoria_label, gbc);

	    // TextField
	    nombre_categoria = new JTextField();
	    nombre_categoria.setColumns(22);
	    gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1;
	    centerPanel.add(nombre_categoria, gbc);

	    // Botón Crear
	    JButton crear_categoria_btn = new JButton("Crear");
	    crear_categoria_btn.setFont(new Font("Tahoma", Font.BOLD, 12));
	    gbc.gridx = 2; gbc.gridy = 1; gbc.weightx = 0;
	    gbc.fill = GridBagConstraints.NONE;
	    gbc.anchor = GridBagConstraints.CENTER;
	    centerPanel.add(crear_categoria_btn, gbc);

	    // Mensaje (Agregado)
	    Agregado = new JLabel("");
	    Agregado.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    Agregado.setHorizontalAlignment(JLabel.CENTER);
	    Agregado.setVisible(false);
	    Agregado.setPreferredSize(new Dimension(380, 22));

	    gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 3;
	    gbc.fill = GridBagConstraints.NONE;
	    gbc.anchor = GridBagConstraints.CENTER;
	    centerPanel.add(Agregado, gbc);

	    // Listener: MISMA LÓGICA
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

	    centerWrapper.add(centerPanel);
	    contentPane.add(centerWrapper, BorderLayout.CENTER);

	    // ====== SOUTH: Volver abajo a la derecha ======
	    JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
	    JButton volver_atras_btn = new JButton("Volver");
	    volver_atras_btn.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    volver_atras_btn.setPreferredSize(new Dimension(110, 34));
	    bottomPanel.add(volver_atras_btn);

	    volver_atras_btn.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            vistaAdminVentas ventana = new vistaAdminVentas();
	            ventana.setVisible(true);
	            dispose();
	        }
	    });

	    contentPane.add(bottomPanel, BorderLayout.SOUTH);
	}
}
