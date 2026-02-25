package GUI.Cliente;

import repositorio.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import BLL.*;
import DLL.*;
import GUI.common.BaseFrame;
import repositorio.Validador;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class Tabla3 extends BaseFrame implements Validador {
	private static Connection con = Conexion.getInstance().getConnection();

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private Producto productoSeleccionado;
	private JTextField inpFiltro;
	private JTextField cantidad;


	public Tabla3(Cliente cliente) {


		contentPane = new JPanel();
		contentPane.setBackground(new Color(174, 174, 174));
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSeleccionado = new JLabel("Seleccionado:");
		lblSeleccionado.setBounds(10, 10, 760, 20);
		contentPane.add(lblSeleccionado);

		model = new DefaultTableModel(
				new String[] { "nombre", "precio", "stock", "fk_categoria", "peligroso", "id_producto" }, 0);
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 40, 760, 128);
		contentPane.add(scrollPane);

		// Acción al seleccionar fila
		table.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				int row = table.getSelectedRow();
				if (row != -1) {

					productoSeleccionado = new Producto((String) model.getValueAt(row, 0),
							(double) model.getValueAt(row, 1), (int) model.getValueAt(row, 2),
							(int) model.getValueAt(row, 3), (int) model.getValueAt(row, 4),
							(int) model.getValueAt(row, 5)

					);

					lblSeleccionado.setText(productoSeleccionado.toString());

				}
			}
		});
		inpFiltro = new JTextField();
		inpFiltro.setBounds(10, 204, 118, 40);
		contentPane.add(inpFiltro);
		inpFiltro.setColumns(10);
		inpFiltro.setVisible(true);
		JButton btnNewButton = new JButton("Filtrar nombre");
		btnNewButton.setVisible(true);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				cargarTablaFiltro(inpFiltro.getText());
				
			}
		});
		btnNewButton.setBounds(136, 199, 118, 51);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Filtro");
		lblNewLabel.setBounds(10, 179, 117, 14);
		contentPane.add(lblNewLabel);

		JButton volver = new JButton("Recargar");
		volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTabla();
				inpFiltro.setText("");
			}
		});
		volver.setBounds(264, 199, 118, 51);
		contentPane.add(volver);

		cantidad = new JTextField();
		cantidad.setBounds(10, 413, 182, 34);
		contentPane.add(cantidad);
		cantidad.setColumns(10);

		JLabel LblError = new JLabel("");
		LblError.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		LblError.setForeground(new Color(255, 0, 0));
		LblError.setBounds(54, 330, 372, 33);
		contentPane.add(LblError);

		JButton compra = new JButton("Agregar al carrito");
		compra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (productoSeleccionado==null) {
					LblError.setText("no eligio ningun producto");
					return;
				}
				if (cantidad.getText().isEmpty()) {
					LblError.setText("Tiene que ingresar la cantidad de producto");
					return;
				} 
				
					String cantidad3=cantidad.getText();
					int espacio=cantidad3.length();
					for (int i = 0; i < espacio; i++) {
						if (!Character.isDigit(cantidad3.charAt(i))) {
							LblError.setText("Solo se pueden ingresar numeros en la cantidad");
							return;
						}
					}
					int cantidad2 = Integer.parseInt(cantidad3);
					if (cantidad2 <= 0) {
						LblError.setText("Tiene que ingresar una cantidad de producto, tiene que ser mayor a 0");
						return;
					} else {
						if (cantidad2 <= productoSeleccionado.getStcok()) {
							
							
							
							try {
								PreparedStatement validar = con.prepareStatement(
				    	                "Select * FROM carrito WHERE fk_cliente=? AND estado=?"
				    					);
				                
				                validar.setInt(1, cliente.getId());
				                validar.setString(2, "en proceso");
				    			ResultSet rs2 = validar.executeQuery();
				    			int id_carrito=0;
				    			Date fecha=null;
				    			String estado="";
				    			double total=0;
				    			int codigoenvio=0;
				    			int fk_cliente=0;
				    			
				    			if (rs2.next()) {
				    				id_carrito=rs2.getInt("id_carrito");
				    				fecha=rs2.getDate("fecha");
				    				estado=rs2.getString("estado");
				    				total=rs2.getDouble("total");
				    				codigoenvio=rs2.getInt("codigoenvio");
				    				fk_cliente=rs2.getInt("fk_cliente");
								}
				    			
				                Carrito carrito=new Carrito(id_carrito,fecha, estado, total,codigoenvio,fk_cliente);
				                
				                
				                PreparedStatement carrito_detalle= con.prepareStatement(
									"SELECT * FROM carrito_detalle WHERE fk_carrito = ? AND fk_producto = ? ");
							
				                carrito_detalle.setInt(1, carrito.getId_carrito());
				                carrito_detalle.setInt(2, productoSeleccionado.getId());
							
				                ResultSet rs3 = carrito_detalle.executeQuery();
								
				                int opcion=0;
				            
				                if (rs3.next()) {
				                	opcion=rs3.getInt("id_carrito_detalle");
				                	
								}
																
								if (opcion>=1) {
									
									int cantidad_antigua=rs3.getInt("cantidad");
									double total_antigua=rs3.getDouble("total_producto");
									
									
									
										int cantidad_nueva=cantidad_antigua+cantidad2;
										
									
									
										double total_carrito=productoSeleccionado.getPrecio()*cantidad2;
										
										
										double total_nuevo=total_antigua+total_carrito;
										
										
										
										PreparedStatement updateCarrito = con
												.prepareStatement("UPDATE carrito_detalle SET total_producto=?, cantidad=?  WHERE fk_carrito=? and fk_producto=?");
										
										
										updateCarrito.setDouble(1, total_nuevo);
										updateCarrito.setInt(2, cantidad_nueva);
										updateCarrito.setInt(3, carrito.getId_carrito());
										updateCarrito.setInt(4,productoSeleccionado.getId() );
										int filas = updateCarrito.executeUpdate();
							            if (filas > 0) {
							                System.out.println("carrito_detalle modificado correctamente cuando ya existe");  
							            }
										
										
										PreparedStatement updateCarrito2 = con
												.prepareStatement("UPDATE producto SET stock=? WHERE id_producto=?");
										updateCarrito2.setInt(1, productoSeleccionado.getStcok() - cantidad2);
										updateCarrito2.setInt(2, productoSeleccionado.getId());
										int filas2 = updateCarrito2.executeUpdate();
							            if (filas2 > 0) {
							                System.out.println("Producto modificado correctamente cuando ya existe");  
							            }
							            
							            PreparedStatement updateCarrito3 = con
												.prepareStatement("UPDATE carrito SET total=? WHERE id_carrito=? and estado=?");
							            
							            total_carrito=carrito.getTotal_compra()+total_carrito;
							            updateCarrito3.setDouble(1, total_carrito);
							            updateCarrito3.setInt(2, carrito.getId_carrito());
							            updateCarrito3.setString(3, carrito.getEstado());
							            int filas3 = updateCarrito3.executeUpdate();
							            if (filas3 > 0) {
							                System.out.println("Carrito total modificado correctamente cuando ya existe el producto");  
							            }
										
							            CompraExitosa compraexitosa =new CompraExitosa(cliente);
										compraexitosa.setVisible(true);
										dispose();
									return;
								} else {
									try {
										PreparedStatement stmt = con.prepareStatement(
												"INSERT INTO carrito_detalle(fk_carrito, fk_producto, total_producto, cantidad) VALUES (?,?,?,?)");
										double total2=productoSeleccionado.getPrecio() * cantidad2;
										stmt.setInt(1, carrito.getId_carrito());
										stmt.setInt(2, productoSeleccionado.getId());
										stmt.setDouble(3,total2);
										stmt.setInt(4, cantidad2);
										int filas = stmt.executeUpdate();
							            if (filas > 0) {
							                System.out.println("Producto agregado correctamente.");
							                
							            }

										PreparedStatement stmt2 = con
												.prepareStatement("UPDATE producto SET stock=? WHERE id_producto=?");
										stmt2.setInt(1, productoSeleccionado.getStcok() - cantidad2);
										stmt2.setInt(2, productoSeleccionado.getId());
										int filas2 = stmt2.executeUpdate();
							            if (filas2 > 0) {
							                System.out.println("Producto modificado correctamente.");  
							            }
							            
							            PreparedStatement stmt3 = con
												.prepareStatement("UPDATE carrito SET total=? WHERE id_carrito=? and estado=?");
							            
							            
							            double total_carrito=carrito.getTotal_compra()+total2;
							            stmt3.setDouble(1, total_carrito);
							            stmt3.setInt(2, carrito.getId_carrito());
							            stmt3.setString(3, carrito.getEstado());
							            int filas3 = stmt3.executeUpdate();
							            if (filas3 > 0) {
							                System.out.println("Carrito total modificado correctamente.");  
							            }
							            CompraExitosa compraexitosa =new CompraExitosa(cliente);
										compraexitosa.setVisible(true);
										dispose();
							            return;
										
									} catch (SQLException e1) {

										e1.printStackTrace();
										LblError.setText("error");
										return;
									}
								}
								
								
							} catch (SQLException e1) {																							
								e1.printStackTrace();
								LblError.setText("error");
								return;
							}
							
																
							
						} else {
							LblError.setText( "Tiene que ingresar una cantidad de producto, tiene que ser menor al stock del producto");
							return;
						}
					}
				
					
			}
		});
		compra.setBounds(202, 408, 152, 45);
		contentPane.add(compra);

		JLabel lblNewLabel_1 = new JLabel("Cantidad de producto");
		lblNewLabel_1.setBounds(10, 388, 104, 14);
		contentPane.add(lblNewLabel_1);

		JButton Salir = new JButton("Salir");
		Salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "despues podra continuar su compra tranquilamente");
				generalCliente generalcliente = new generalCliente(cliente);
				generalcliente.setVisible(true);;
				dispose();
			}
		});
		Salir.setBounds(670, 419, 104, 31);
		contentPane.add(Salir);

		// Cargar datos
		cargarTabla();

	}

	private void cargarTabla() {
		model.setRowCount(0);
		LinkedList<Producto> productos = ControllerProducto.mostrarProductos();
		for (Producto u : productos) {
			model.addRow(

					new Object[] { u.getNombre(), u.getPrecio(),u.getStcok(), u.getCategoria(), u.getPeligroso(),
							u.getId()

					});
		}
	}

	private void cargarTablaFiltro(String filtro) {
		model.setRowCount(0);
		LinkedList<Producto> productos = ControllerProducto.mostrarProductos();
		for (Producto u : productos) {
			if (u.getNombre().startsWith(filtro)) {

				model.addRow(

						new Object[] { u.getNombre(),u.getPrecio(),u.getStcok() ,u.getCategoria(), u.getPeligroso(),
								u.getId()

						});
			}

		}
	}
}
