package GUI.Cliente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Cliente;

import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Cancelar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public Cancelar(String primero, String segundo, String tercero, Cliente cliente) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 526, 393);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlShadow);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel icono = new JLabel("");
		icono.setIcon(new ImageIcon(Cancelar.class.getResource("/img/logo.png")));
		icono.setBounds(86, 11, 341, 111);
		contentPane.add(icono);
		
		JLabel SI = new JLabel(primero);
		SI.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		SI.setBounds(58, 133, 398, 26);
		contentPane.add(SI);
		
		JLabel SI3 = new JLabel(tercero);
		SI3.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		SI3.setBounds(10, 171, 471, 50);
		contentPane.add(SI3);
		
		JLabel SI2 = new JLabel(segundo);
		SI2.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		SI2.setBounds(10, 222, 490, 26);
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
		Volver.setFont(new Font("Verdana", Font.PLAIN, 12));
		Volver.setBounds(86, 289, 220, 26);
		contentPane.add(Volver);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Cancelar.class.getResource("/img/cancelado.png")));
		lblNewLabel.setBounds(355, 267, 145, 76);
		contentPane.add(lblNewLabel);
	}
}
