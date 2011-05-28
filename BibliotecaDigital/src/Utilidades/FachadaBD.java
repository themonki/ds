package Utilidades;


import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Clase que permite realizar la debida conexion con la base de datos asi como cerrarla.
 * @version 1.0
 * @author Yerminson Gonzalez Munoz
 */
public class FachadaBD {
	String url, usuario, password;
	Connection conexion;
	/**
	 * Constructor de la clase fachada incia los parametros String para la conexion a la base
	 * de datos por defecto: url, usuario y password
	 * */
	public FachadaBD() {
		url = "jdbc:postgresql://pgsql:5432/edgaramt";
		usuario = "edgaramt";
		password = "edgaramt";
	}
	/**
	 * Metodo que establece la conexion a la base de datos 
	 * @return la conexion establecida con el servidor de postgres
	 * */
	public Connection conectar() {
		try {
			// Se carga el driver
			Class.forName("org.postgresql.Driver");
			//System.out.println("Driver Cargado");
		} catch (Exception e) {
			System.out.println("No se pudo cargar el driver.");
		}

		try {
			// Crear el objeto de conexion a la base de datos
			conexion = DriverManager.getConnection(url, usuario, password);
			//System.out.println("Conexion Abierta");
			return conexion;

		} catch (Exception e) {
			System.out.println("No se pudo abrir.");
			return null;
		}

	}// end connectar
	/**
	 * Meotodo para cerrar una conexion
	 * @param c La conexion a la base de datos
	 * */
	
	public void cerrarConexion(Connection c) {
		try {
			c.close();
			//System.out.println("Cerrada la conexion a la base de datos");
		} catch (Exception e) {
			System.out.println("No se pudo cerrar la BD");
		}
	}
}// end class
