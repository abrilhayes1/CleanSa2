package GUI.Cliente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Cliente;
import GUI.common.BaseFrame;

import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Pagar extends BaseFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public Pagar(String primero, String segundo, String tercero, Cliente cliente) {

		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlShadow);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel icono = new JLabel("");
		icono.setIcon(new ImageIcon(Pagar.class.getResource("/img/logo.png")));
		icono.setBounds(86, 11, 341, 111);
		contentPane.add(icono);
		
		JLabel SI = new JLabel(primero);
		SI.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		SI.setBounds(86, 134, 319, 26);
		contentPane.add(SI);
		
		JLabel SI3 = new JLabel(tercero);
		SI3.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		SI3.setBounds(10, 171, 471, 50);
		contentPane.add(SI3);
		
		JLabel SI2 = new JLabel(segundo);
		SI2.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		SI2.setBounds(10, 233, 490, 26);
		contentPane.add(SI2);
		
		JButton Volver = new JButton("Volver");
		Volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generalCliente generalcliente = new generalCliente(cliente);
				generalcliente.setVisible(true);;
				dispose();
			}
		});
		Volver.setBackground(SystemColor.menu);
		Volver.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		Volver.setBounds(151, 270, 124, 59);
		contentPane.add(Volver);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Pagar.class.getResource("/img/pago1.png")));
		lblNewLabel.setBounds(311, 273, 87, 56);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Pagar.class.getResource("/img/pago2.png")));
		lblNewLabel_1.setBounds(408, 279, 85, 50);
		contentPane.add(lblNewLabel_1);
	}
}
