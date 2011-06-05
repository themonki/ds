/*
 * DaoAutor.java
 * 
 * Clase que permite el acceso a la base de datos usando la clase FachadaBD
 * para la inserción, actualizacion y consulta de información que esta rel-
 * acionada con los Autores de la Biblioteca Digital.
 * 
 *
 * JAVA version "1.6.0"
 * 
 * 
 * Autor:     Yerminson Gonzales
 * Version:   4.0
 */
package GestionDocumento.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import GestionDocumento.Logica.Autor;
import Utilidades.FachadaBD;

/**
 * Clase que permite la inserccion, actualizacion y consulta de lo que tenga que ver 
 * con el autor
 * @author Yerminson Gonzalez Muñoz
 *
 */
public class DaoAutor {
	/**
	 * Permite la conexion con la base de datos
	 */
	private FachadaBD fachada;

	/**
	 * Constructor por defecto que inicia la variable fachada
	 */
	public DaoAutor() {
		fachada = new FachadaBD();

	}

	/**
	 * Metodo que inserta los datos del autor
	 * @param autor - Autor con los datos a insertar
	 * @return 1 si se inserto el autor correctamente, -1 de ser lo contrario
	 */
	public int guardarAutor(Autor autor) {
		String sqlGuardar;
		sqlGuardar = "INSERT INTO autor(id_autor, nombre, email, apellido,acronimo) VALUES ("
				+ "NEXTVAL('id_autor_seq'),'"
				+ autor.getNombre()
				+ "', '"
				+ autor.getCorreo()
				+ "', '"
				+ autor.getApellido()
				+ "', '"
				+ autor.getAcronimo() + "')";
		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();
			int numFilas = sentencia.executeUpdate(sqlGuardar);
			conn.close();
			return numFilas;
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}

	/**
	 * Metodo que devuelve el autor que coincida con el parametro de busqueda
	 * @param parametro - String con el valor a buscar (el acronimo)
	 * @return Autor que coincidio con el parametro
	 */
	public Autor consultarAutor(String parametro) {

		Autor autor = new Autor();
		String sqlSelect;
		// Por ahora se asume que el parametro discriminador es el acronimo
		sqlSelect = "SELECT * FROM autor WHERE autor.acronimo='" + parametro
				+ "'";
		
		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();
			ResultSet tabla = sentencia.executeQuery(sqlSelect);
			while (tabla.next()) {
				autor.setId(tabla.getString(1));
				autor.setNombre(tabla.getString(2));
				autor.setCorreo(tabla.getString(3));
				autor.setApellido(tabla.getString(4));
				autor.setAcronimo(tabla.getString(5));
			}
			fachada.cerrarConexion(conn);
		}

		catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return autor;
	}

	/**
	 * Metodo que consulta todos los autores disponibles y los devuelve en un vector
	 * @return Vector<Autor> con todos los autores almacenados
	 */
	public Vector<Autor> consultarAutores() {

		Vector<Autor> autores = new Vector<Autor>();
		String sqlSelect;
		// Por ahora se asume que el parametro discriminador es el acronimo
		sqlSelect = "SELECT * FROM autor";
		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();
			ResultSet tabla = sentencia.executeQuery(sqlSelect);

			while (tabla.next()) {
				Autor autorAux = new Autor();
				autorAux.setId(tabla.getString(1));
				autorAux.setNombre(tabla.getString(2));
				autorAux.setCorreo(tabla.getString(3));
				autorAux.setApellido(tabla.getString(4));
				autorAux.setAcronimo(tabla.getString(5));
				autores.add(autorAux);
			}
			fachada.cerrarConexion(conn);
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return autores;

	}
	/**
	 * Metodo que devuelve los autores de un documento dado su id_documento
	 * @param id_documento - Strign con la llave del documento
	 * @return Vector <Autor> con los autores que escribieron el documento
	 * @author Edgar Andres Moncada
	 */
	public Vector <Autor> consultarAutoresDocumento(String id_documento) {
		Vector <Autor> va = new Vector<Autor>();
		String sqlSelect;
		sqlSelect = "SELECT * FROM autor a NATURAL JOIN " +
				"escribe_autor_documento da WHERE da.id_documento='"
				+ id_documento + "'";
		try {
			Connection conn = this.fachada.conectar();
			Statement sentencia = conn.createStatement();
			ResultSet tabla = sentencia.executeQuery(sqlSelect);

			while (tabla.next()) {
				Autor autor = new Autor();
				autor.setId(""+tabla.getInt("id_autor"));
				autor.setNombre(tabla.getString("nombre"));
				autor.setCorreo(tabla.getString("email"));
				autor.setApellido(tabla.getString("apellido"));
				autor.setAcronimo(tabla.getString("acronimo"));
				va.add(autor);
			}
			this.fachada.cerrarConexion(conn);

		} catch (SQLException se) {
			System.out.println(se.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return va;
	}
	
	public int modificarAutor(Autor a){
		String sqlUpdate;
		sqlUpdate = "UPDATE autor SET "+
			"nombre = '" + a.getNombre() + "', "+
			"apellido = '" + a.getApellido() + "', "+
			"email = '" + a.getCorreo() + "', "+
			"acronimo = '" + a.getAcronimo() + "' " +
			"WHERE id_autor = '"+a.getId()+"'; "
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
