package GUI.AdminENvios;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI.Admin.LoginAdmin;
import GUI.Cliente.verCarrito;
import GUI.common.BaseFrame;

public class vistaAdminEnvios extends BaseFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vistaAdminEnvios frame = new vistaAdminEnvios();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public vistaAdminEnvios() {
		super();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Logo
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(vistaAdminEnvios.class.getResource("/img/logo.png")));
		lblNewLabel.setBounds(95, 20, 369, 127); // Centrado horizontalmente
		contentPane.add(lblNewLabel);

		// Ver carritos
		JButton btnVerCarritos = new JButton("Ver carritos");
		btnVerCarritos.setBounds(40, 190, 140, 30);
		btnVerCarritos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verCarrito ventana = new verCarrito(null);
				ventana.setVisible(true);
			}
		});
		contentPane.add(btnVerCarritos);

		// Enviar pedidos
		JButton btnEnviarPedidos = new JButton("Enviar pedidos");
		btnEnviarPedidos.setBounds(200, 190, 140, 30);
		contentPane.add(btnEnviarPedidos);

		// Salir
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(360, 190, 140, 30);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginAdmin ventana = new LoginAdmin();
				ventana.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnSalir);
	}
}
