package GUI.Cliente;

import java.awt.Color;
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.*;
import DLL.ControllerCliente;

import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class Registro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField Inpnombre;
	private JPasswordField inpcontrasena;
	private JTextField inpDNI;
	private JTextField inpdireccion;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public Registro() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 499, 457);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlShadow);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Registro.class.getResource("/img/logo.png")));
		lblNewLabel.setBounds(72, 11, 357, 72);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Registrate");
		lblNewLabel_1.setBackground(SystemColor.desktop);
		lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(182, 94, 88, 24);
		contentPane.add(lblNewLabel_1);
		
		Inpnombre = new JTextField();
		Inpnombre.setBackground(SystemColor.menu);
		Inpnombre.setBounds(83, 140, 133, 20);
		contentPane.add(Inpnombre);
		Inpnombre.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre");
		lblNewLabel_2.setBackground(SystemColor.desktop);
		lblNewLabel_2.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(83, 118, 68, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Contraseña");
		lblNewLabel_2_1.setBackground(SystemColor.desktop);
		lblNewLabel_2_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblNewLabel_2_1.setBounds(83, 171, 81, 14);
		contentPane.add(lblNewLabel_2_1);
		
		inpcontrasena = new JPasswordField();
		inpcontrasena.setBackground(SystemColor.menu);
		inpcontrasena.setBounds(83, 196, 133, 20);
		contentPane.add(inpcontrasena);
		
		JLabel lblNewLabel_2_2 = new JLabel("DNI");
		lblNewLabel_2_2.setBackground(SystemColor.desktop);
		lblNewLabel_2_2.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblNewLabel_2_2.setBounds(83, 227, 68, 14);
		contentPane.add(lblNewLabel_2_2);
		
		inpDNI = new JTextField();
		inpDNI.setBackground(SystemColor.menu);
		inpDNI.setColumns(10);
		inpDNI.setBounds(83, 252, 133, 20);
		contentPane.add(inpDNI);
		
		JLabel direccion = new JLabel("Dirección");
		direccion.setFont(new Font("Verdana", Font.PLAIN, 13));
		direccion.setBackground(SystemColor.desktop);
		direccion.setBounds(247, 171, 68, 14);
		contentPane.add(direccion);
		
		inpdireccion = new JTextField();
		inpdireccion.setColumns(10);
		inpdireccion.setBackground(SystemColor.menu);
		inpdireccion.setBounds(241, 196, 133, 20);
		contentPane.add(inpdireccion);
		
		JComboBox tipoUsuario = new JComboBox();
			tipoUsuario.addItem("General");
			tipoUsuario.addItem("Empresa");

		//tipoUsuario.setModel(new DefaultComboBoxModel(OpcionCliente.values()));
		tipoUsuario.setBackground(SystemColor.menu);
		tipoUsuario.setBounds(241, 251, 133, 22);
		contentPane.add(tipoUsuario);
		
		
		
		
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Escoja su tipo de cliente");
		lblNewLabel_2_1_1.setBackground(SystemColor.desktop);
		lblNewLabel_2_1_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblNewLabel_2_1_1.setBounds(241, 227, 171, 14);
		contentPane.add(lblNewLabel_2_1_1);
		
		JLabel LblError = new JLabel("");
		LblError.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		LblError.setForeground(new Color(255, 0, 0));
		LblError.setBounds(54, 330, 372, 33);
		contentPane.add(LblError);
		JButton registrar = new JButton("Regitrar");
		registrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String validar = ControllerCliente.agregarClienteProfe(
					Inpnombre.getText(),
					inpcontrasena.getText(),
					inpdireccion.getText(),
					inpDNI.getText(),
					(String) tipoUsuario.getSelectedItem()
				);

				if (validar.equals("si")) {
					RegistroCorrecto registrocorrecto = new RegistroCorrecto();
					registrocorrecto.setVisible(true);
					dispose();
				} else {
					LblError.setText(validar);
				}
			}
		});
		registrar.setBackground(SystemColor.menu);
		registrar.setFont(new Font("Verdana", Font.ITALIC, 12));
		registrar.setBounds(83, 296, 291, 23);
		contentPane.add(registrar);
		
		
		
		
		
		JButton salir = new JButton("salir");
		salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuCliente menucliente = new menuCliente();
				menucliente.setVisible(true);;
				dispose();
			}
		});
		salir.setBackground(SystemColor.menu);
		salir.setBounds(10, 384, 89, 23);
		contentPane.add(salir);
		
		
		
	}
}
