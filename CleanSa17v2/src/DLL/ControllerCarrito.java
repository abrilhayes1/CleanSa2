package DLL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import BLL.*;
import repositorio.*;


public class ControllerCarrito implements  CarritoRepository{
	
	private static Connection con = Conexion.getInstance().getConnection();

	public ControllerCarrito() {
		super();
	}

	public static LinkedList<Carrito> mostrarCarrito() {
		LinkedList<Carrito> carrito = new LinkedList<>();
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM carrito");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
            	int id_carrito=rs.getInt("id_carrito");
            	Date fecha= rs.getDate("fecha");
            	String estado=rs.getString("estado");
            	double total=rs.getDouble("total");
            	int codigo_envio=rs.getInt("codigoenvio");
            	int fk_cliente=rs.getInt("fk_cliente");
            	Carrito carritos=new Carrito(id_carrito, fecha, total, codigo_envio, fk_cliente);
            	
            	carrito.add(carritos);
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return carrito;
	}
	
	
	public static LinkedList<Carrito> mostrarCarritoEnProseso() {
		LinkedList<Carrito> carrito = new LinkedList<>();
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM carrito where estado=?");
			stmt.setString(1, "en proseso");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
            	int id_carrito=rs.getInt("id_carrito");
            	Date fecha= rs.getDate("fecha");
            	String estado=rs.getString("estado");
            	double total=rs.getDouble("total");
            	int codigo_envio=rs.getInt("codigoenvio");
            	int fk_cliente=rs.getInt("fk_cliente");
            	Carrito carritos=new Carrito(id_carrito, fecha, total, codigo_envio, fk_cliente);
            	
            	carrito.add(carritos);
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return carrito;
	}

	
	public static LinkedList<Carrito> mostrarCarritosPagados() {
		LinkedList<Carrito> carrito = new LinkedList<>();
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM carrito where estado=?");
			stmt.setString(1, "pagado");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
            	int id_carrito=rs.getInt("id_carrito");
            	Date fecha= rs.getDate("fecha");
            	String estado=rs.getString("estado");
            	double total=rs.getDouble("total");
            	int codigo_envio=rs.getInt("codigoenvio");
            	int fk_cliente=rs.getInt("fk_cliente");
            	Carrito carritos=new Carrito(id_carrito, fecha, total, codigo_envio, fk_cliente);
            	
            	carrito.add(carritos);
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return carrito;
	}
	

	//Metodo para usar los carritos sin envio asignado 
	public List<Carrito> mostrarCarritosPagadosSinEnvio() {
	    LinkedList<Carrito> carrito = new LinkedList<>();
	    try {
	        PreparedStatement stmt = con.prepareStatement("SELECT * FROM carrito WHERE estado = ? AND codigo_envio = 0");
	        stmt.setString(1, "pagado");
	        ResultSet rs = stmt.executeQuery();
	        while(rs.next()) {
	            int id_carrito = rs.getInt("id_carrito");
	            Date fecha = rs.getDate("fecha");
	            String estado = rs.getString("estado");
	            double total = rs.getDouble("total");
	            int codigo_envio = rs.getInt("codigoenvio");
	            int fk_cliente = rs.getInt("fk_cliente");
	            Carrito carritos = new Carrito(id_carrito, fecha, estado, total, codigo_envio, fk_cliente);
	            carrito.add(carritos);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return carrito;
	}

	

	public static LinkedList<Carrito> mostrarCarritoCancelados() {

		LinkedList<Carrito> carrito = new LinkedList<>();
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM carrito where estado=?");
			stmt.setString(1, "cancelado");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
            	int id_carrito=rs.getInt("id_carrito");
            	Date fecha= rs.getDate("fecha");
            	String estado=rs.getString("estado");
            	double total=rs.getDouble("total");
            	int codigo_envio=rs.getInt("codigoenvio");
            	int fk_cliente=rs.getInt("fk_cliente");
            	Carrito carritos=new Carrito(id_carrito, fecha, total, codigo_envio, fk_cliente);
            	
            	carrito.add(carritos);
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return carrito;
	}
	public LinkedList<Carrito> mostrarCarritoEnviados() {
		LinkedList<Carrito> carrito = new LinkedList<>();
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM carrito where estado=?");
			stmt.setString(1, "enviado");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
            	int id_carrito=rs.getInt("id_carrito");
            	Date fecha= rs.getDate("fecha");
            	String estado=rs.getString("estado");
            	double total=rs.getDouble("total");
            	int codigo_envio=rs.getInt("codigoenvio");
            	int fk_cliente=rs.getInt("fk_cliente");
            	Carrito carritos=new Carrito(id_carrito, fecha, estado, total, codigo_envio, fk_cliente);
            	
            	carrito.add(carritos);
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return carrito;
	}

	
	public static LinkedList<Carrito> mostrarCarritoporCliente(int id) {
		LinkedList<Carrito> carrito = new LinkedList<>();
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM carrito where fk_cliente=?");
			stmt.setInt(1, id);
			
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
            	int id_carrito=rs.getInt("id_carrito");
            	Date fecha= rs.getDate("fecha");
            	String estado=rs.getString("estado");
            	double total=rs.getDouble("total");
            	int codigo_envio=rs.getInt("codigoenvio");
            	int fk_cliente=rs.getInt("fk_cliente");
            	Carrito carritos=new Carrito(id_carrito, fecha, total, codigo_envio, fk_cliente);
            	
            	carrito.add(carritos);
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return carrito;
	}

	




	
	public static LinkedList<Carrito> mostrarCarritoporClientePagados(int id) {
		LinkedList<Carrito> carrito = new LinkedList<>();
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM carrito where fk_cliente=? and estado=?");
			stmt.setLong(1, id);
			stmt.setString(2, "pagado");
			
			
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
            	int id_carrito=rs.getInt("id_carrito");
            	Date fecha= rs.getDate("fecha");
            	String estado=rs.getString("estado");
            	double total=rs.getDouble("total");
            	int codigo_envio=rs.getInt("codigoenvio");
            	int fk_cliente=rs.getInt("fk_cliente");
            	Carrito carritos=new Carrito(id_carrito, fecha, estado,total, codigo_envio, fk_cliente);
            	
            	
            	carrito.add(carritos);
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return carrito;
	}
	
	public Carrito mostrarCarritoporClienteEnProceso(int id) {
		Carrito carrito = null;
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM carrito where fk_cliente=? and estado=?");
			stmt.setInt(1, id);
			stmt.setString(2, "en proceso");
			
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
            	int id_carrito=rs.getInt("id_carrito");
            	Date fecha= rs.getDate("fecha");
            	String estado=rs.getString("estado");
            	double total=rs.getDouble("total");
            	int codigo_envio=rs.getInt("codigoenvio");
            	int fk_cliente=rs.getInt("fk_cliente");
            	carrito=new Carrito(id_carrito, fecha, estado, total, codigo_envio, fk_cliente);

            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return carrito;
	}

	
	public static LinkedList<Carrito> mostrarCarritoporClienteCancelados(int id) {
		LinkedList<Carrito> carrito = new LinkedList<>();
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM carrito where fk_cliente=? and estado=?");
			stmt.setInt(1, id);
			stmt.setString(2, "cancelado");
			
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
            	int id_carrito=rs.getInt("id_carrito");
            	Date fecha= rs.getDate("fecha");
            	String estado=rs.getString("estado");
            	double total=rs.getDouble("total");
            	int codigo_envio=rs.getInt("codigoenvio");
            	int fk_cliente=rs.getInt("fk_cliente");
            	Carrito carritos=new Carrito(id_carrito, fecha, estado,total, codigo_envio, fk_cliente);
            	
            	carrito.add(carritos);
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return carrito;
	}

	
	public List<Carrito> mostrarCarritoporClienteEnviados(int id) {
		LinkedList<Carrito> carrito = new LinkedList<>();
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM carrito where fk_cliente=? and estado=?");
			stmt.setInt(1, id);
			stmt.setString(2, "enviado");
			
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
            	int id_carrito=rs.getInt("id_carrito");
            	Date fecha= rs.getDate("fecha");
            	String estado=rs.getString("estado");
            	double total=rs.getDouble("total");
            	int codigo_envio=rs.getInt("codigoenvio");
            	int fk_cliente=rs.getInt("fk_cliente");
            	Carrito carritos=new Carrito(id_carrito, fecha, estado, total, codigo_envio, fk_cliente);
            	
            	carrito.add(carritos);

            }
		} catch (Exception e) {
			e.printStackTrace();
		}

		return carrito;
	}
	
	public String pagar(int id) {
		String validar="";
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM carrito where fk_cliente=? and estado=?");
			stmt.setInt(1, id);
			stmt.setString(2, "en proceso");
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				PreparedStatement productos = con.prepareStatement("SELECT * FROM carrito_detalle where fk_carrito=?");
				productos.setInt(1, rs.getInt("id_carrito"));
				ResultSet rs2 = productos.executeQuery();
				if (!rs2.next()) {
					
					validar="No se encontraron productos en el carrito. Antes de pagar debe tener productos";
					return validar;
				}
				
				
					PreparedStatement cambio = con.prepareStatement("UPDATE carrito SET estado=? WHERE fk_cliente=? and estado=?");
					cambio.setString(1, "pagado");
					cambio.setInt(2, id);
					cambio.setString(3, "en proceso");
					int filas = cambio.executeUpdate();
					if (filas>0) {
						System.out.println("modifico carrito");
						validar="si";
						
					}
				
            }else {
				
				validar="No se encontro ningun carrito en proceso. Antes de pagar debe realizar una compra";
			}
		} catch (Exception e) {
			e.printStackTrace();
			validar="no";
		}
		return validar;
		
	}

	
	public String cancelar(int id) {
		String validar="";
		String validar2="";
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM carrito WHERE fk_cliente=? AND estado=?");
			stmt.setInt(1, id);
			stmt.setString(2, "en proceso");
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				
				stmt = con.prepareStatement("SELECT * FROM carrito_detalle WHERE fk_carrito=?");
				stmt.setInt(1, rs.getInt("id_carrito"));
					ResultSet rs2 = stmt.executeQuery();
					int vueltas=0;
					while(rs2.next()) {
		            	vueltas+=1;
		            	int fk_producto=rs2.getInt("fk_producto");

		            	int cantidad=rs2.getInt("cantidad");
		            	
		            	PreparedStatement producto = con.prepareStatement("SELECT * FROM producto WHERE id_producto=?");
		            	producto.setInt(1, fk_producto);
		            	ResultSet rs3 = producto.executeQuery();
		            	

		            	int cantidad_producto=0;
		            	if (rs3.next()) {
		            		cantidad_producto=rs3.getInt("stock");
						}
		            
		            	int total_cambio=cantidad+cantidad_producto;
		            	
		            	PreparedStatement producto_cambio = con.prepareStatement("UPDATE producto SET stock=? WHERE id_producto=?");
		            	producto_cambio.setInt(1, total_cambio);
		            	producto_cambio.setInt(2, fk_producto);
		            	int filas = producto_cambio.executeUpdate();
						if (filas>1) {
							System.out.println("modifico producto");  
						}
		            	
		            }
					if (vueltas==0) {
						
						validar2="el carrito que esta modificando no tiene productos";
						return validar2;
					}
					PreparedStatement cambio = con.prepareStatement("UPDATE carrito SET estado=? WHERE fk_cliente=? AND estado=?");
					cambio.setString(1, "cancelado");
					cambio.setInt(2, id);
					cambio.setString(3, "en proceso");
					int filas2 = cambio.executeUpdate();
					if (filas2>0) {
						System.out.println("modifico carrito");
						validar="si";
					}
				}else {
					validar2="el carrito que esta modificando no tiene productos";
					return "no se encontro ningun carrito en proceso. Antes de cancelar debe realizar una compra";
				}
            
		} catch (Exception e) {
			e.printStackTrace();
			validar="no";
		}
		return validar;
	}
	
	
	public List<Carrito_detalle> mostrarDetalleCarrito(int id) {
		LinkedList<Carrito_detalle> carrito_detalle = new LinkedList<>();
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM carrito_detalle WHERE fk_carrito=?");
			stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
            	int id_carrito_detalle=rs.getInt("id_carrito_detalle");
            	int fk_carrito=rs.getInt("fk_carrito");
            	int fk_producto=rs.getInt("fk_producto");
            	double total_producto=rs.getDouble("total_producto");
            	int cantidad=rs.getInt("cantidad");
            	
            	Carrito_detalle darritodetalle=new Carrito_detalle(id_carrito_detalle,fk_carrito,fk_producto,total_producto,cantidad);
            	carrito_detalle.add(darritodetalle);

            }
		} catch (Exception e) {
			e.printStackTrace();
		}

		return carrito_detalle;

	}
	
	public List<ItemVenta> obtenerItemsCarritoEnProceso(int idCliente){
		
		List<ItemVenta> items = new LinkedList<>();
		try {
			PreparedStatement stmt = con.prepareStatement(
					"SELECT * from carrtito WHERE fk_cliente = ? AND estado= ?"
					);
			stmt.setInt(1, idCliente);
			stmt.setString(2, "en proceso");
			
			ResultSet rs = stmt.executeQuery();
			if (!rs.next()) {
				return items;
			}
			
			int idCarrito = rs.getInt("id_carrito");
			PreparedStatement stmtDetalle = con.prepareStatement(
					"SELECT * from carrito_detalle WHERE fk_carrito =?"
					);
			stmtDetalle.setInt(1, idCarrito);
			ResultSet rsDetalle = stmtDetalle.executeQuery();
			
			while(rsDetalle.next()) {
				
				int idProducto = rsDetalle.getInt("fk_producto");
				double subtotal = rsDetalle.getDouble("total_producto");
				
				PreparedStatement stmtProd = con.prepareStatement(
						"SELECT fk_categoria FROM producto WHERE id_producto = ?"
						);
				
				stmtProd.setInt(1, idProducto);
				ResultSet rsProd = stmtProd.executeQuery();
				
				int idCategoria = 0;
				
				if (rsProd.next()) {
	                idCategoria = rsProd.getInt("fk_categoria");
	            }
				
				ItemVenta item = new ItemVenta(idProducto, idCategoria, subtotal);
				
				items.add(item);
				
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return items;
		
	}
}
