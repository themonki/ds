package Utilidades;

/**
 * Autor: Yerminson Gonzalez Munoz
 * Responsabilidad: Realizar la debida conexion con la base de datos asi como cerrarla.
 * 
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class FachadaBD {
	String url, usuario, password;
	Connection conexion;
	Statement instruccion;
	ResultSet tabla;

	public FachadaBD() {
		url = "jdbc:postgresql://localhost:5432/proyecto";
		usuario = "yerdogm";
		password = "yerdogm";
	}

	public Connection conectar() {
		try {
			// Se carga el driver
			Class.forName("org.postgresql.Driver");
			System.out.println("Driver Cargado");
		} catch (Exception e) {
			System.out.println("No se pudo cargar el driver.");
		}

		try {
			// Crear el objeto de conexion a la base de datos
			conexion = DriverManager.getConnection(url, usuario, password);
			System.out.println("Conexion Abierta");
			return conexion;

		} catch (Exception e) {
			System.out.println("No se pudo abrir.");
			return null;
		}

	}// end connectar

	public void cerrarConexion(Connection c) {
		try {
			c.close();
			System.out.println("Cerrada la conexion a la base de datos");
		} catch (Exception e) {
			System.out.println("No se pudo cerrar la BD");
		}
	}
}// end class
