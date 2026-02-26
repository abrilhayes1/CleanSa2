package GUI.Cliente;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Carrito;
import BLL.Cliente;
import DLL.ControllerCarrito;
import GUI.common.BaseFrame;

import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class OpcionesCarritosCliente extends BaseFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public OpcionesCarritosCliente(Cliente cliente) {

		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlShadow);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Texto = new JLabel("Elija que tipo de carrito quiere ver");
		Texto.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		Texto.setBounds(125, 139, 342, 60);
		contentPane.add(Texto);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(OpcionesCarritosCliente.class.getResource("/img/logo.png")));
		lblNewLabel.setBounds(125, 11, 318, 117);
		contentPane.add(lblNewLabel);
		
		JLabel LblError = new JLabel("");
		LblError.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		LblError.setForeground(new Color(255, 0, 0));
		LblError.setBounds(10, 322, 372, 33);
		contentPane.add(LblError);
		
		JButton En_Proceso = new JButton("En Proceso");
		En_Proceso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControllerCarrito controller=new ControllerCarrito();
				Carrito carrito= controller.mostrarCarritoporClienteEnProceso(cliente.getId());
				if (carrito==null) {
					LblError.setText("Error. No se encontraron carritos en proceso");
				} else {
					CarritosEnProcesoCliente carritosenprosesocliente =new CarritosEnProcesoCliente(cliente, carrito);
					carritosenprosesocliente.setVisible(true);
					dispose();
				}
			}
		});
		En_Proceso.setBackground(SystemColor.menu);
		En_Proceso.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		En_Proceso.setBounds(89, 220, 171, 23);
		contentPane.add(En_Proceso);
		
		JButton Pagado = new JButton("Pagado");
		Pagado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				List<Carrito> carrito=ControllerCarrito.mostrarCarritoporClientePagados(cliente.getId());
				if (carrito.isEmpty()) {
					LblError.setText("Error. No se encontraron carritos pagados");
				} else {
					CarritosPagadosCliente carritospagadoscliente =new CarritosPagadosCliente(cliente);
					carritospagadoscliente.setVisible(true);
					dispose();
				}
			}
		});
		Pagado.setBackground(SystemColor.menu);
		Pagado.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		Pagado.setBounds(89, 267, 171, 23);
		contentPane.add(Pagado);
		
		JButton Cancelado = new JButton("Cancelado");
		Cancelado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Carrito> carrito=ControllerCarrito.mostrarCarritoporClienteCancelados(cliente.getId());
				if (carrito.isEmpty()) {
					LblError.setText("Error. No se encontraron carritos cancelados");
				} else {
					CarritosCanceladosCliente carritoscanceladoscliente =new CarritosCanceladosCliente(cliente);
					carritoscanceladoscliente.setVisible(true);
					dispose();
				}
			}
		});
		Cancelado.setBackground(SystemColor.menu);
		Cancelado.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		Cancelado.setBounds(320, 220, 171, 23);
		contentPane.add(Cancelado);
		
		JButton Salir = new JButton("Salir");
		Salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generalCliente generalcliente = new generalCliente(cliente);
				generalcliente.setVisible(true);;
				dispose();
			}
		});
		Salir.setBounds(20, 352, 108, 23);
		contentPane.add(Salir);
		
		JButton btnEnviados = new JButton("Enviados");
		btnEnviados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControllerCarrito controller=new ControllerCarrito();
				List<Carrito> carrito=controller.mostrarCarritoporClienteEnviados(cliente.getId());
				if (carrito.isEmpty()) {
					LblError.setText("Error. No se encontraron carritos enviados");
				} else {
					CarritosEnviadosCliente carritosenviadoscliente =new CarritosEnviadosCliente(cliente);
					carritosenviadoscliente.setVisible(true);
					dispose();
				}
				
			}
		});
		btnEnviados.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnEnviados.setBackground(SystemColor.menu);
		btnEnviados.setBounds(320, 268, 171, 23);
		contentPane.add(btnEnviados);
	}
}
