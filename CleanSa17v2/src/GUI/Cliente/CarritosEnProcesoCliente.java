package GUI.Cliente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Carrito;
import BLL.Cliente;
import GUI.common.BaseFrame;

import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CarritosEnProcesoCliente extends BaseFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public CarritosEnProcesoCliente(Cliente cliente, Carrito carrito) {

		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlShadow);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(CarritosEnProcesoCliente.class.getResource("/img/logo.png")));
		lblNewLabel.setBounds(163, 23, 319, 102);
		contentPane.add(lblNewLabel);
		
		JLabel id_carrito = new JLabel("id_carrito: "+carrito.getId_carrito());
		id_carrito.setBounds(89, 161, 425, 14);
		contentPane.add(id_carrito);
		
		JLabel Fecha = new JLabel("Fecha: "+carrito.getFecha());
		Fecha.setBounds(89, 186, 397, 14);
		contentPane.add(Fecha);
		
		JLabel Estado = new JLabel("Estado: "+carrito.getEstado());
		Estado.setBounds(89, 211, 393, 14);
		contentPane.add(Estado);
		
		JLabel total = new JLabel("Total: "+carrito.getTotal_compra());
		total.setBounds(89, 236, 393, 14);
		contentPane.add(total);
		
		JLabel codigo_envio = new JLabel("Codigo de envio: "+carrito.getCodigo_envio());
		codigo_envio.setBounds(89, 261, 393, 14);
		contentPane.add(codigo_envio);
		
		JLabel fk_cliente = new JLabel("fk_cliente: "+carrito.getFk_cliente());
		fk_cliente.setBounds(89, 286, 397, 14);
		contentPane.add(fk_cliente);
		
		JButton Salir = new JButton("Salir");
		Salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generalCliente generalcliente = new generalCliente(cliente);
				generalcliente.setVisible(true);;
				dispose();
			}
		});
		Salir.setBackground(SystemColor.menu);
		Salir.setBounds(467, 349, 121, 42);
		contentPane.add(Salir);
		
		JButton Detalle = new JButton("Ver detalle");
		Detalle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CarritosDetalle carritosdetalle = new CarritosDetalle(cliente,carrito.getId_carrito());
				carritosdetalle.setVisible(true);;
				dispose();
			}
		});
		Detalle.setBackground(SystemColor.menu);
		Detalle.setBounds(70, 349, 121, 42);
		contentPane.add(Detalle);
	}

}