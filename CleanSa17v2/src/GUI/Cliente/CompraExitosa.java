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

public class CompraExitosa extends BaseFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public CompraExitosa(Cliente cliente) {

		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlShadow);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel icono = new JLabel("");
		icono.setBounds(86, 11, 341, 111);
		icono.setIcon(new ImageIcon(CompraExitosa.class.getResource("/img/logo.png")));
		contentPane.add(icono);
		
		JLabel SI = new JLabel("Producto agregado con exito");
		SI.setBounds(122, 147, 274, 26);
		SI.setFont(new Font("Verdana", Font.BOLD, 16));
		contentPane.add(SI);
		
		JLabel SI2 = new JLabel("Para volver a la pantalla de cliente haga click en el botón \"Salir\"");
		SI2.setBounds(25, 184, 475, 26);
		SI2.setFont(new Font("Verdana", Font.ITALIC, 14));
		contentPane.add(SI2);
		
		JButton Volver = new JButton("Volver");
		Volver.setBounds(236, 282, 137, 20);
		Volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cliente.getTipo()==1) {
					Tabla2 tabla2 =new Tabla2(cliente);
					tabla2.setVisible(true);
					dispose();
				} else {
					Tabla3 tabla3 =new Tabla3(cliente);
					tabla3.setVisible(true);
					dispose();
				}
			}
		});
		Volver.setBackground(SystemColor.menu);
		Volver.setFont(new Font("Verdana", Font.PLAIN, 12));
		contentPane.add(Volver);
		
		JLabel lblParaVolverA = new JLabel("Para volver a la pantalla de compra haga click en el botón \"Volver\"");
		lblParaVolverA.setFont(new Font("Verdana", Font.ITALIC, 14));
		lblParaVolverA.setBounds(25, 221, 475, 26);
		contentPane.add(lblParaVolverA);
		
		JButton Salir = new JButton("Salir");
		Salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generalCliente generalcliente = new generalCliente(cliente);
				generalcliente.setVisible(true);;
				dispose();
			}
		});
		Salir.setFont(new Font("Verdana", Font.PLAIN, 12));
		Salir.setBackground(SystemColor.menu);
		Salir.setBounds(56, 282, 137, 20);
		contentPane.add(Salir);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(CompraExitosa.class.getResource("/img/carrito1.png")));
		lblNewLabel.setBounds(394, 269, 106, 74);
		contentPane.add(lblNewLabel);
	}
}
