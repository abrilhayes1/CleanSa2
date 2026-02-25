package GUI.Cliente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI.common.BaseFrame;
import GUI.common.menuprincipal;

import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class menuCliente extends BaseFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menuCliente frame = new menuCliente();
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
	public menuCliente() {

		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlShadow);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Que quiere realizar");
		lblNewLabel.setFont(new Font("Verdana", Font.ITALIC, 14));
		lblNewLabel.setBounds(138, 67, 141, 33);
		contentPane.add(lblNewLabel);
		
		JButton btnRegistro = new JButton("Registrarse");
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Registro registro = new Registro();
				registro.setVisible(true);;
				dispose();
			}
		});
		btnRegistro.setBackground(SystemColor.menu);
		btnRegistro.setFont(new Font("Verdana", Font.ITALIC, 11));
		btnRegistro.setBounds(49, 129, 149, 23);
		contentPane.add(btnRegistro);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);;
				dispose();
			}
		});
		btnLogin.setFont(new Font("Verdana", Font.ITALIC, 11));
		btnLogin.setBackground(SystemColor.menu);
		btnLogin.setBounds(237, 129, 149, 23);
		contentPane.add(btnLogin);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(menuCliente.class.getResource("/img/logochico.png")));
		lblNewLabel_1.setBounds(273, 228, 177, 33);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("salir");
		btnNewButton.setBackground(SystemColor.menu);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuprincipal Menupricipal = new menuprincipal();
				Menupricipal.setVisible(true);;
				dispose();
				
			}
		});
		btnNewButton.setBounds(20, 228, 69, 23);
		contentPane.add(btnNewButton);
	}
} 