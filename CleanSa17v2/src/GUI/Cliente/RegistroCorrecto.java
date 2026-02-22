package GUI.Cliente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistroCorrecto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public RegistroCorrecto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 526, 393);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlShadow);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel icono = new JLabel("");
		icono.setIcon(new ImageIcon(RegistroCorrecto.class.getResource("/img/logo.png")));
		icono.setBounds(86, 11, 341, 111);
		contentPane.add(icono);
		
		JLabel SI = new JLabel("Gracias por registrarse");
		SI.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 16));
		SI.setBounds(150, 147, 210, 26);
		contentPane.add(SI);
		
		JLabel SI2 = new JLabel("Para volver a la pantalla de cliente haga click en el botón \"Volver\"");
		SI2.setFont(new Font("Verdana", Font.PLAIN, 14));
		SI2.setBounds(24, 204, 476, 26);
		contentPane.add(SI2);
		
		JButton Volver = new JButton("Volver");
		Volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuCliente menucliente = new menuCliente();
				menucliente.setVisible(true);;
				dispose();
			}
		});
		Volver.setBackground(SystemColor.menu);
		Volver.setFont(new Font("Verdana", Font.PLAIN, 12));
		Volver.setBounds(86, 273, 210, 26);
		contentPane.add(Volver);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(RegistroCorrecto.class.getResource("/img/foto_user.png")));
		lblNewLabel.setBounds(373, 251, 67, 70);
		contentPane.add(lblNewLabel);
	}
}
