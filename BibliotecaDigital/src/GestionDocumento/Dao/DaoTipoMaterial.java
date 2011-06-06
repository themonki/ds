/*
 * DaoTipoMaterial.java
 * 
 * Clase que permite el acceso a la base de datos usando la clase FachadaBD
 * para la inserción, actualizacion y consulta de información que esta rel-
 * acionada con los Tipos de Material de los Documentos de la Biblioteca D-
 * igital.
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


import GestionDocumento.Logica.TipoMaterial;
import Utilidades.FachadaBD;

/**
 * Clase que permite la inserccion, actualizacion y consulta de lo que tenga que ver 
 * con el tipo de material mediante la Clase {@link Utilidades.FachadaBD FachadaBD}.
 * @author Cristian Leonardo Rios
 *
 */
public class DaoTipoMaterial {
	/**
	 * Permite la conexion con la base de datos
	 */
	private FachadaBD fachada;

	/**
	 * Constructor por defecto que inicia la variable fachada
	 */
	public DaoTipoMaterial() {
		this.fachada = new FachadaBD();
	}

	/**
	 * Metodo que inserta los datos del tipo de material 
	 * @param tp - TipoMaterial con los datos a almacenar
	 * @return 1 si se inserto el tipo de material correctamente, -1 de ser lo contrario
	 */
	public int guardarTipoMaterial(TipoMaterial tp) {
		String sqlInsert;
		sqlInsert = "INSERT INTO TipoMaterial(tipo_nombre, descripcion) VALUES('"
				+ tp.getNombre() + "','" + tp.getDescripcion() + "')";

		try {
			Connection conn = this.fachada.conectar();
			Statement sentencia = conn.createStatement();
			int value = sentencia.executeUpdate(sqlInsert);
			this.fachada.cerrarConexion(conn);
			return value;
		} catch (SQLException se) {
			System.out.println(se.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return -1;
	}
	/**
	 * Metodo que devuelve un TipoMaterial que coincida con el parametro
	 * @param parametro - String con el criterio de busqueda en el nombre
	 * @return TipoMaterial que coincide con la busqueda
	 */
	public TipoMaterial consultarTipoMaterial(String parametro) {
		TipoMaterial tipo = new TipoMaterial();
		String sqlSelect;

		sqlSelect = "SELECT * FROM TipoMaterial WHERE TipoMaterial.tipo_nombre='"
				+ parametro + "'";
		try {
			Connection conn = this.fachada.conectar();
			Statement sentencia = conn.createStatement();
			ResultSet tabla = sentencia.executeQuery(sqlSelect);

			if (tabla.next()) {

				tipo.setNombre(tabla.getString("tipo_nombre"));
				tipo.setDescripcion(tabla.getString("descripcion"));
		}
			this.fachada.cerrarConexion(conn);
		

		} catch (SQLException se) {
			System.out.println(se.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return tipo;
	}

	/**
	 * Metodo que consulta todos los tipos de materiales disponibles y los devuelve en un vector
	 * @return Vector<TipoMaterial> con todos los tipos de materiales almacenados
	 */
	public Vector<TipoMaterial> consultarTodosTipoMaterial() {
		Vector<TipoMaterial> tipos = new Vector<TipoMaterial>();
		String sqlSelect;
		sqlSelect = "SELECT * FROM TipoMaterial";

		try {
			Connection conn = this.fachada.conectar();
			Statement sentencia = conn.createStatement();
			ResultSet tabla = sentencia.executeQuery(sqlSelect);

			while (tabla.next()) {
				TipoMaterial tipo = new TipoMaterial();

				tipo.setNombre(tabla.getString("tipo_nombre"));
				tipo.setDescripcion(tabla.getString("descripcion"));
				tipos.add(tipo);
			}
			this.fachada.cerrarConexion(conn);

		} catch (SQLException se) {
			System.out.println(se.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return tipos;
	}
	
	public int modificarTipoMaterial(TipoMaterial tp){
		String sqlUpdate;
		sqlUpdate = "UPDATE tipomaterial SET "+
			"descripcion = '" + tp.getDescripcion()+ "' "+
			"WHERE tipo_nombre = '"+ tp.getNombre()+"'; "
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