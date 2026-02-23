package GUI.AdminVentas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import BLL.Producto;
import DLL.ControllerProducto;
import GUI.common.BaseFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.Dimension;

public class vistaDestroyProducto extends BaseFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Producto productoSeleccionado;

	public vistaDestroyProducto(Producto producto) {
	    super();
	    this.productoSeleccionado = producto;

	    setTitle("Eliminar Producto");

	    contentPane = new JPanel(new BorderLayout());
	    contentPane.setBackground(Color.WHITE);
	    contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
	    setContentPane(contentPane);

	    // ====== CENTER: Texto centrado ======
	    JPanel centerWrapper = new JPanel(new GridBagLayout());
	    centerWrapper.setOpaque(false);

	    JPanel centerPanel = new JPanel(new GridBagLayout());
	    centerPanel.setOpaque(false);

	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.insets = new Insets(10, 10, 10, 10);
	    gbc.gridx = 0;
	    gbc.fill = GridBagConstraints.NONE;
	    gbc.anchor = GridBagConstraints.CENTER;

	    JLabel lblTitulo = new JLabel("¿Deseás eliminar este producto?");
	    lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
	    gbc.gridy = 0;
	    centerPanel.add(lblTitulo, gbc);

	    JLabel lblProducto = new JLabel("Producto: " + productoSeleccionado.getNombre());
	    lblProducto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	    gbc.gridy = 1;
	    centerPanel.add(lblProducto, gbc);

	    centerWrapper.add(centerPanel);
	    contentPane.add(centerWrapper, BorderLayout.CENTER);

	    // ====== SOUTH: Botones abajo ======
	    JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 0));
	    bottomPanel.setOpaque(false);

	    JButton btnEliminar = new JButton("Eliminar");
	    btnEliminar.setFont(new Font("Segoe UI", Font.BOLD, 14));
	    btnEliminar.setBackground(new Color(255, 102, 102));
	    btnEliminar.setPreferredSize(new Dimension(130, 40));
	    bottomPanel.add(btnEliminar);

	    JButton btnAtras = new JButton("Atrás");
	    btnAtras.setFont(new Font("Segoe UI", Font.BOLD, 14));
	    btnAtras.setPreferredSize(new Dimension(130, 40));
	    bottomPanel.add(btnAtras);

	    contentPane.add(bottomPanel, BorderLayout.SOUTH);

	    // ====== Acción del botón Eliminar (MISMA LÓGICA) ======
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

	    // ====== Acción del botón Atrás (MISMA LÓGICA) ======
	    btnAtras.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            vistaEliminarProducto ventana = new vistaEliminarProducto();
	            ventana.setVisible(true);
	            dispose();
	        }
	    });
	}
}