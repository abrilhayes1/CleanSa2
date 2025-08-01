package DLL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import BLL.*;
import repositorio.*;
import DLL.*;

public class ControllerCliente <T extends Cliente> implements ClienteRepository, Validador, Encriptador{
	
	private static Connection con = Conexion.getInstance().getConnection();
	 
	
	 public ControllerCliente() {
		super();
	}

	@Override
	    public T login(String DNI, String contrasena) {
	        T cliente = null;
	        try {
	            PreparedStatement stmt = con.prepareStatement(
	                "SELECT * FROM cliente WHERE dni = ? AND contrasena = ?"
	            );//copiar consulta de insert para producto
	            stmt.setString(1, DNI);
	            stmt.setString(2,contrasena);
	            
	            
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                int id = rs.getInt("id_cliente");
	                String nombre = rs.getString("nombre");
	                String dirreccion = rs.getString("direccion");
	                int tipo=rs.getInt("fk_categoria_usuarios");

	                switch (tipo) {
	                    case 1:
	                        cliente = (T) new Personal(nombre,contrasena, dirreccion, DNI,id,tipo);
	                        break;
	                    case 2:
	                        cliente = (T) new Empresa(nombre,contrasena, dirreccion, DNI,id,tipo);
	                        break;
	                    default:
	                        System.out.println("Tipo de cliente desconocido: " + tipo);
	                        break;
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return cliente;
	    }
	 


	   public static void agregarCliente2(String nombre,String contrasena,String direccion,String dni,int tipo) {
	        
		   try {
	            PreparedStatement statement = con.prepareStatement(
	                "INSERT INTO cliente (nombre , direccion, dni, contrasena, fk_categoria_usuarios) VALUES (?, ?, ?, ?, ?)"
	            );
	            statement.setString(1, nombre );
	            statement.setString(2, direccion);
	            statement.setString(3, dni);
	            statement.setString(4, contrasena);
	            statement.setInt(5, tipo);

	            int filas = statement.executeUpdate();
	            if (filas > 0) {
	                System.out.println("Cliente agregado correctamente.");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }



	@Override
	public LinkedList<Cliente> mostrarClientes() {

        LinkedList<Cliente> Clientes = new LinkedList<>();
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM cliente");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
            	  int id_cliente = rs.getInt("id_cliente");
	                String nombre = rs.getString("nombre");
	                String direccion = rs.getString("direccion");
	                int tipo = rs.getInt("fk_categoria_usuarios");
	                String contrasena = rs.getString("contrasena");
	                String dni = rs.getString("dni");
	                Cliente cliente= new Cliente(nombre, contrasena, direccion, dni,id_cliente ,tipo);
	                
	                Clientes.add(cliente);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Clientes;
    }
	
	public static Cliente obtenerClientesPorID(int id) {
		Cliente cliente =new Cliente(null, null, null, null, 0);
	        try {
	            PreparedStatement stmt = con.prepareStatement(
	                "SELECT * FROM cliente WHERE id = ?"
	            );
	            stmt.setInt(1, id);
	            ResultSet rs = stmt.executeQuery();

	            if(rs.next()) {
	                int id_cliente = rs.getInt("id_cliente");
	                String nombre = rs.getString("nombre");
	                String direccion = rs.getString("direccion");
	                int tipo = rs.getInt("tipo");
	                String contrasena = rs.getString("contrasena");
	                String dni = rs.getString("dni");
	                cliente= new Cliente(nombre, contrasena, direccion, dni,id_cliente ,tipo);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return cliente;
	    }


	public static <T> T validar2(String dni) {
		 T cliente = null;
		try {
            PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM cliente WHERE dni = ?"
            );//copiar consulta de insert para producto
            
            stmt.setString(1, dni);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	
            	int id_cliente = rs.getInt("id_cliente");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                int tipo = rs.getInt("fk_categoria_usuarios");
                String contrasena = rs.getString("contrasena");
                 cliente= (T) new Cliente(nombre, contrasena, direccion, dni,id_cliente ,tipo);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            cliente=null;
        }
        return cliente;
	}


	
	@Override
	public void carrito(int id) {
		try {
			PreparedStatement validar = con.prepareStatement(
	                "Select * FROM carrito WHERE fk_cliente=? AND estado=?"
					);
			validar.setInt(1, id );
			validar.setString(2, "en proseso");
			ResultSet rs = validar.executeQuery();
			if (rs==null) {
				PreparedStatement statement = con.prepareStatement(
		                "INSERT INTO carrito (fecha , estado, total, codigoenvio, fk_cliente) VALUES (?,?, ?, ?, ?, ?)"
					 );
			 Carrito carrito = null;
			 Date fecha= Date.valueOf(LocalDate.now());
			 String estado="en proceso";
			 double total = 0;
			// boolean estadoenvio= false;
			 int codigoenvio= (int)Math.random()*1001+100;
			 int fk_cliente= id;
			 
			 	statement.setDate(1, fecha );
	            statement.setString(2, estado);
	            statement.setDouble(3, total);
	            //ya no hay estado envio
	         //   statement.setBoolean(4, estadoenvio );
	            statement.setInt(4, codigoenvio);
	            statement.setInt(5, fk_cliente);
			} else {
				JOptionPane.showMessageDialog(null, "no se puede agregar otro carrito porque ya existe uno");
			}
			
			
			
            
		} catch (Exception e) {
			
		}
			 
		
	}
	
	public static void carrito2(Cliente cliente) {
		try {
			PreparedStatement validar = con.prepareStatement(
	                "Select * FROM carrito WHERE fk_cliente=? AND estado=?"
					);
			validar.setInt(1, cliente.getId());
			validar.setString(2, "en proceso");
			ResultSet rs = validar.executeQuery();
			
			if (!rs.next()) {
				System.out.println("Carrito nuevo");
				JOptionPane.showMessageDialog(null, "creemos un carrito para usted");
				PreparedStatement statement = con.prepareStatement(
		                "INSERT INTO carrito (fecha , estado, total, codigoenvio, fk_cliente) VALUES (?, ?, ?, ?, ?)"
					 );
			 Carrito carrito = null;
			 Date fecha= Date.valueOf(LocalDate.now());
			 String estado="en proceso";
			 double total = 0;
			// boolean estadoenvio= false;
			 int codigoenvio= (int)(Math.random()*1001+100);
			 int fk_cliente= cliente.getId();
			 
			 	statement.setDate(1, fecha );
	            statement.setString(2, estado);
	            statement.setDouble(3, total);
	            //ya no hay estado envio
	         //   statement.setBoolean(4, estadoenvio );
	            statement.setInt(4, codigoenvio);
	            statement.setInt(5, fk_cliente);
	            int filas = statement.executeUpdate();
	            if (filas > 0) {
	                System.out.println("Carrito agregado correctamente.");
	                JOptionPane.showMessageDialog(null, "carrito agregado correctamene");
	                //getgeneratekeys
	                
	                
	                
	            }
			}else {
				System.out.println("Carrito ya eistente");
                JOptionPane.showMessageDialog(null, "continuemos con la compra");
			}
				
            
		} catch (Exception e) {
			System.out.println(e);
			
		}
		 
		
	}
	public static String agregarClienteProfe(String nombre, String contrasena, String direccion, String dni, String tipo) {
		//String contrasena2 = controller.validarPassword(contrasena);
		//if (contrasena2.isEmpty()) {
		  //  return "Contraseña inválida";
		//}
		//String validar="";s
		ControllerCliente controller=new ControllerCliente();
		Cliente prueba = null;	
			if (nombre.isEmpty()) {
				
				return "El nombre esta vacio";
			}
			
			if (contrasena.isEmpty()) {
				return "Se produjo un error con la contraseña. Ingresela nuevamente";
			}

			if (direccion.isEmpty()) {
				
				return "La direccion esta vacia";
			}

			if (dni.isEmpty()) {
				
				return "El dni esta vacio";
				
			}else if (dni.length()<8||dni.length()>=9) {
				
				return "El dni debe ser igual a 8 caracteres";
			}
				int tamaño=dni.length();
				for (int i = 0; i < tamaño; i++) {
					if (!Character.isDigit(dni.charAt(i))) {
						return "El dni tiene letras";
					}
					
				}
			prueba = (Cliente) ControllerCliente.validar2(dni);
			if (prueba!=null) {
				
				return "Ese cliente ya existe";
			}
			
		int tipo2 = 0;
			String tipo1 = tipo;
			if (tipo1.equalsIgnoreCase("general")) {
				tipo2 = 1;
			} else if (tipo1.equalsIgnoreCase("empresa")) {
				tipo2 = 2;
			}

		ControllerCliente.agregarCliente2(nombre, contrasena, direccion, dni, tipo2);
		return "si";
	}

	@Override
	public void agregarCliente() {
		// TODO Auto-generated method stub
		
	}
}
