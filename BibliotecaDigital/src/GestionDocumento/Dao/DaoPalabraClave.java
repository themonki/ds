/*
 * DaoPalabraClave.java
 * 
 * Clase que permite el acceso a la base de datos usando la clase FachadaBD
 * para la inserción, actualizacion y consulta de información que esta rel-
 * acionada con las Palabras Clave de la Biblioteca Digital.
 * 
 *
 * JAVA version "1.6.0"
 * 
 * 
 * Autor:     Cristian Rios
 * Version:   4.0
 */
package GestionDocumento.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import GestionDocumento.Logica.PalabraClave;
import Utilidades.FachadaBD;

/**
 * Clase que permite la inserccion, actualizacion y consulta de lo que tenga que ver 
 * con la palabra clave mediante la Clase {@link Utilidades.FachadaBD FachadaBD}.
 * @author Cristian Leonardo Rios
 *
 */
public class DaoPalabraClave {
	/**
	 * Permite la conexion con la base de datos
	 */
	private FachadaBD fachada;

	/**
	 * Constructor por defecto que inicia la variable fachada
	 */
	public DaoPalabraClave() {
		this.fachada = new FachadaBD();
	}

	/**
	 * Metodo que inserta los datos de la palabra clave
	 * @param pc - PalabraClave con los datos a insertar
	 * @return retorna 1 si se inserto la plabra correctamente, -1 de ser lo contrario
	 */
	public int guardarPalabraClave(PalabraClave pc) {
		String sqlInsert;
		sqlInsert = "INSERT INTO Palabra_Clave(nombre, descripcion) VALUES('"
				+ pc.getNombre() + "','" + pc.getDescripcion() + "')";

		try {
			Connection conn = this.fachada.conectar();
			Statement sentencia = conn.createStatement();
			int numFilas = sentencia.executeUpdate(sqlInsert);
			this.fachada.cerrarConexion(conn);
			return numFilas;
		} catch (SQLException se) {
			System.out.println(se.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return -1;
	}

	/**
	 * Metodo que devuelve la palabra clave que coincida con el parametro
	 * @param palabra - String con la palabra a buscar en el nombre
	 * @return PalabraCLave que coincida con la busqueda
	 */
	public PalabraClave consultarPalabraClave(String palabra) {
		PalabraClave pal = new PalabraClave();
		String sqlSelect;

		sqlSelect = "SELECT * FROM Palabra_Clave WHERE Palabra_Clave.nombre='"
				+ palabra + "'";
		try {
			Connection conn = this.fachada.conectar();
			Statement sentencia = conn.createStatement();
			ResultSet tabla = sentencia.executeQuery(sqlSelect);

			if (tabla.next()) {

				pal.setNombre(tabla.getString("nombre"));
				pal.setDescripcion(tabla.getString("descripcion"));
			}
			this.fachada.cerrarConexion(conn);

		} catch (SQLException se) {
			System.out.println(se.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return pal;
	}

	/**
	 * Metodo que consulta todas las palabras clave disponibles y los devuelve en un vector
	 * @return Vector<PalabraClave> con todas las palabras clave almacenadas
	 */
	public Vector<PalabraClave> consultarPalabras() {
		Vector<PalabraClave> palabras = new Vector<PalabraClave>();
		String sqlSelect;
		sqlSelect = "SELECT * FROM Palabra_Clave";

		try {
			Connection conn = this.fachada.conectar();
			Statement sentencia = conn.createStatement();
			ResultSet tabla = sentencia.executeQuery(sqlSelect);

			while (tabla.next()) {
				PalabraClave palabra = new PalabraClave();

				palabra.setNombre(tabla.getString("nombre"));
				palabra.setDescripcion(tabla.getString("descripcion"));
				palabras.add(palabra);
			}
			this.fachada.cerrarConexion(conn);

		} catch (SQLException se) {
			System.out.println(se.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return palabras;
	}
	/**
	 * Metodo que devuelve las palabras clave de un documento dado su id_documento
	 * @param id_documento - String con la llave del documento
	 * @return Vector <PalabraClave> con las palabras clave asociadas al documento
	 * @author Edgar Andres Moncada
	 */
	public Vector <PalabraClave> consultarPalabrasClaveDocumento(String id_documento) {
		Vector <PalabraClave> vpc = new Vector<PalabraClave>();
		String sqlSelect;
		sqlSelect = "SELECT * FROM palabra_clave pc NATURAL JOIN " +
				"tiene_documento_palabra_clave dpc WHERE dpc.id_documento='"
				+ id_documento + "'";
		try {
			Connection conn = this.fachada.conectar();
			Statement sentencia = conn.createStatement();
			ResultSet tabla = sentencia.executeQuery(sqlSelect);

			while (tabla.next()) {
				PalabraClave pc = new PalabraClave();
				pc.setNombre(tabla.getString("nombre"));
				pc.setDescripcion(tabla.getString("descripcion"));
				vpc.add(pc);
			}
			this.fachada.cerrarConexion(conn);

		} catch (SQLException se) {
			System.out.println(se.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return vpc;
	}
	
	public int modificarPalabraClave(PalabraClave pc){
		String sqlUpdate;
		sqlUpdate = "UPDATE palabra_clave SET "+
			"descripcion = '" + pc.getDescripcion()+ "' "+
			"WHERE nombre = '"+ pc.getNombre()+"'; "
		;

		try {
			Connection conn = this.fachada.conectar();
			Statement sentencia = conn.createStatement();
			int numFilas = sentencia.executeUpdate(sqlUpdate);
			this.fachada.cerrarConexion(conn);
			return numFilas;
		} catch (SQLException se) {
			System.out.println(se.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return -1;
	}
	
}