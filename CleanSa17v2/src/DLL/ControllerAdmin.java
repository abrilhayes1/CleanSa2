package DLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import BLL.*;
import repositorio.*;

public class ControllerAdmin <T extends Administrador> implements AdministradorRepository, Validador, Encriptador{

	public ControllerAdmin() {
		super();
	}

	private static Connection con = Conexion.getInstance().getConnection();// Poner esto en todos los controladores

	@SuppressWarnings("unchecked")
	@Override
	public T logIn(String nombre, String contrasena) {
	    System.out.println("Iniciando sesión para el administrador...");
	    System.out.println("Nombre ingresado: " + nombre);
	    System.out.println("Contraseña ingresada: " + contrasena);

	    T administrador = null;
	    try {
	        PreparedStatement stmt = con.prepareStatement(
	            "SELECT * FROM administrador WHERE nombre = ? AND contrasena = ?"
	        );
	        
	        String encriptar=encriptar(contrasena);

	        stmt.setString(1, nombre);
	        stmt.setString(2, encriptar);

	        ResultSet rs = stmt.executeQuery();

	        if (!rs.next()) {
	            System.out.println("No se encontró el administrador con el nombre: " + nombre);
	            return null;
	        }

	        int id = rs.getInt("id_administrador");
	        String apellido = rs.getString("apellido");
	        int tipo = rs.getInt("fk_categoria_administrasdor");

	        switch (tipo) {
	            case 1:
	                administrador = (T) new AdminEnvios(nombre, apellido, id, tipo, contrasena);
	                break;
	            case 2:
	                administrador = (T) new AdminVentas(nombre, apellido, id, tipo, contrasena);
	                break;
	            default:
	                System.out.println("Tipo de administrador desconocido: " + tipo);
	                break;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return administrador;
	}

	@Override
	public <T> T logIn() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void cupon () {
		
	}	
}
