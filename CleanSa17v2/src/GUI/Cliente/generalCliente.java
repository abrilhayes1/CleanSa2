package GUI.Cliente;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.*;

import DLL.*;
import GUI.AdminVentas.VerCliente;
import GUI.common.BaseFrame;
import GUI.common.menuprincipal;

import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class generalCliente extends BaseFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public generalCliente(Cliente cliente) {

		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlShadow);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Que acción quiere realizar...");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(142, 44, 176, 38);
		contentPane.add(lblNewLabel);
		
		JButton btnverinfo = new JButton("Ver mi info");
		btnverinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VerCliente vercliente =new VerCliente(cliente);
				vercliente.setVisible(true);
				dispose();
			}
		});
		btnverinfo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnverinfo.setBackground(SystemColor.menu);
		btnverinfo.setBounds(26, 117, 129, 23);
		contentPane.add(btnverinfo);
		
		JButton btnVerCarrito = new JButton("Ver carrito");
		btnVerCarrito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpcionesCarritosCliente opcionescarritocliente =new OpcionesCarritosCliente(cliente);
				opcionescarritocliente.setVisible(true);
				dispose();
			}
		});
		btnVerCarrito.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVerCarrito.setBackground(SystemColor.menu);
		btnVerCarrito.setBounds(304, 117, 129, 23);
		contentPane.add(btnVerCarrito);
		
		JButton btnComprar = new JButton("Comprar");
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControllerCliente.carrito2(cliente);
				
					if (cliente.getTipo()==1) {
						Tabla2 tabla2 =new Tabla2(cliente);
						tabla2.setVisible(true);
						dispose();
						
					} else if (cliente.getTipo()==2) {
						
						Tabla3 tabla3 =new Tabla3(cliente);
						tabla3.setVisible(true);
						dispose();
					}
			
				}
				
					
				
				
			
		});
		btnComprar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnComprar.setBackground(SystemColor.menu);
		btnComprar.setBounds(165, 117, 129, 23);
		contentPane.add(btnComprar);
		
		JButton btnNewButton_1_1 = new JButton("salir");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuprincipal Menupricipal = new menuprincipal();
				Menupricipal.setVisible(true);;
				dispose();
			}
		});
		btnNewButton_1_1.setBackground(SystemColor.menu);
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_1_1.setBounds(10, 255, 64, 23);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_2_1_1 = new JButton("Pagar");
		btnNewButton_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControllerCarrito controllercarrito=new ControllerCarrito();
				String validar=controllercarrito.pagar(cliente.getId());
				if (validar.equals("si")) {
					//	MODIFICAR PARA QUE VALLA A UNA PANTALLA DE AGRADECIMIENTO
					String primero="Gracias por elegirnos";
					String segundo="Para regresar al menu de cliente haga click en el botón Volver";
					String tercero="Pago realizado con exito";
					
					Pagar pagar = new Pagar(primero, segundo, tercero, cliente);
					pagar.setVisible(true);;
					dispose();
					
				}else {
					String primero="Se produjo un error en el pago";
					String segundo="Para intentarlo nuevamente haga click en el botón Volver y regresara al menu de cliente";
					String tercero=validar;
					
					Pagar pagar = new Pagar(primero, segundo, tercero, cliente);
					pagar.setVisible(true);;
					dispose();
				}
			}
		});
		
		btnNewButton_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_2_1_1.setBackground(SystemColor.menu);
		btnNewButton_2_1_1.setBounds(29, 167, 176, 23);
		contentPane.add(btnNewButton_2_1_1);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControllerCarrito controllercarrito=new ControllerCarrito();
				String validar=controllercarrito.cancelar(cliente.getId());
				if (validar.equals("si")) {
					//	MODIFICAR PARA QUE VALLA A UNA PANTALLA DE CANCELAMIENTO
					String primero="Lamentamos que cancele su pedido";
					String segundo="Para regresar al menu de cliente haga click en el botón Volver";
					String tercero="Cancelación realizada con exito";
					
					Cancelar cancelar = new Cancelar(primero, segundo, tercero, cliente);
					cancelar.setVisible(true);
					dispose();
					
				}else {
					String primero="Se produjo un error en la cancelación";
					String segundo="Para intentarlo nuevamente haga click en el botón Volver y regresara al menu de cliente";
					String tercero=validar;
					
					Cancelar cancelar = new Cancelar(primero, segundo, tercero, cliente);
					cancelar.setVisible(true);
					dispose();
					
				}
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancelar.setBackground(SystemColor.menu);
		btnCancelar.setBounds(241, 167, 176, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(generalCliente.class.getResource("/img/logochico.png")));
		lblNewLabel_1.setBounds(125, 177, 193, 112);
		contentPane.add(lblNewLabel_1);
	}
}
