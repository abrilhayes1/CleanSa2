package GUI.common;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI.Admin.LoginAdmin;
import GUI.Cliente.menuCliente;
import GUI.common.BaseFrame;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JTree;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JToggleButton;
import javax.swing.JSlider;
import javax.swing.JProgressBar;

public class menuprincipal extends BaseFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menuprincipal frame = new menuprincipal();
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
	public menuprincipal() {
;
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlShadow);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(menuprincipal.class.getResource("/img/logochico.png")));
		lblNewLabel.setBounds(117, 205, 171, 56);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ingrese credenciales");
		lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(140, 67, 234, 29);
		contentPane.add(lblNewLabel_1);
		
		JButton btnAdministrador = new JButton("Administrador");
		btnAdministrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginAdmin LoginAdmin = new LoginAdmin();
				LoginAdmin.setVisible(true);;
				dispose();
			}
		});
		
		btnAdministrador.setFont(new Font("Verdana", Font.ITALIC, 11));
		btnAdministrador.setBackground(SystemColor.menu);
		btnAdministrador.setBounds(158, 154, 120, 23);
		contentPane.add(btnAdministrador);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				System.exit(0);
			}
		});
		
		btnSalir.setFont(new Font("Verdana", Font.ITALIC, 11));
		btnSalir.setBackground(SystemColor.menu);
		btnSalir.setBounds(288, 154, 120, 23);
		contentPane.add(btnSalir);
		
		JButton btnCliente = new JButton("Cliente");
		btnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuCliente menucliente = new menuCliente();
				menucliente.setVisible(true);;
				dispose();
				
				
				
			}
		});
		btnCliente.setFont(new Font("Verdana", Font.ITALIC, 11));
		btnCliente.setBackground(SystemColor.menu);
		btnCliente.setBounds(28, 154, 120, 23);
		contentPane.add(btnCliente);
	}
}
