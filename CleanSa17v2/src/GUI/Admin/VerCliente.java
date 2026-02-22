package GUI.Admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Carrito;
import BLL.Cliente;
import GUI.Cliente.generalCliente;

import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VerCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public VerCliente(Cliente cliente) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 654, 441);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlShadow);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(VerCliente.class.getResource("/img/logo.png")));
		lblNewLabel.setBounds(163, 23, 319, 102);
		contentPane.add(lblNewLabel);
		
		JLabel id_cliente = new JLabel("id_cliente: "+cliente.getId());
		id_cliente.setBounds(89, 161, 425, 14);
		contentPane.add(id_cliente);
		
		JLabel Nombre = new JLabel("Nombre: "+cliente.getNombre());
		Nombre.setBounds(89, 186, 397, 14);
		contentPane.add(Nombre);
		
		JLabel Direccion = new JLabel("Direccion: "+cliente.getDireccion());
		Direccion.setBounds(89, 211, 393, 14);
		contentPane.add(Direccion);
		
		JLabel DNI = new JLabel("Dni: "+cliente.getDni());
		DNI.setBounds(89, 236, 393, 14);
		contentPane.add(DNI);
		
		JLabel fk_categoria_usuario = new JLabel("Tipo de cliente: "+cliente.getTipo());
		fk_categoria_usuario.setBounds(89, 261, 397, 14);
		contentPane.add(fk_categoria_usuario);
		
		JButton Salir = new JButton("Salir");
		Salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generalCliente generalcliente = new generalCliente(cliente);
				generalcliente.setVisible(true);;
				dispose();
			}
		});
		Salir.setBackground(SystemColor.menu);
		Salir.setBounds(477, 368, 111, 23);
		contentPane.add(Salir);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(VerCliente.class.getResource("/img/foto_user.png")));
		lblNewLabel_1.setBounds(384, 161, 98, 107);
		contentPane.add(lblNewLabel_1);
	}

}
