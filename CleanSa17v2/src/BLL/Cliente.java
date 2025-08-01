package BLL;

import java.util.LinkedList;
import DLL.*;

import javax.swing.JOptionPane;

import repositorio.Encriptador;
import repositorio.Validador;

public class Cliente implements Validador, Encriptador {

	private String nombre;
	private String contrasena;
	private String direccion;
	private String dni;
	private static LinkedList<Cliente> clientes = new LinkedList<Cliente>();
	private int tipo;
	private int id;
	
	public Cliente() {}

	public Cliente(String nombre, String contrasena, String direccion, String DNI, int tipo) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.contrasena = contrasena;
		this.dni = DNI;
	}

	public Cliente(String nombre, String contrasena, String direccion, String DNI, int id, int tipo) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.contrasena = contrasena;
		this.dni = DNI;
		this.id = id;
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public static LinkedList<Cliente> getClientes() {
		return clientes;
	}
	public static void setClientes(LinkedList<Cliente> clientes) {
		Cliente.clientes = clientes;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + "\n contrasena=" + contrasena + "\n direccion=" + direccion + "\n dni="
				+ dni + "\n tipo=" + tipo + "\n id=" + id + "]";
	}
	public static Cliente Login(String DNI, String contrasena) {
		if (DNI.isEmpty() || contrasena.isEmpty()) {
			return null;
		} else {
			ControllerCliente controllercliente = new ControllerCliente();
			return controllercliente.login(DNI, contrasena);
		}

	}
	

}
