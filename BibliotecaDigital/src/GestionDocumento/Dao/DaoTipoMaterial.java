/*
 * Nombre: Cristian R�os.
 * Responsabilidad: Realizar la debida manipulacion de datos referentes al tipo de material
 * entre el controlador y la base de datos.
 * Nombre archivo: DaoTipoMaterial.java
 * Fecha Creacion: Mayo 03 2011
 * Fecha ultima modificaci�n: Mayo 03 2011
 * */

package GestionDocumento.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import GestionDocumento.Logica.TipoMaterial;
import Utilidades.FachadaBD;

public class DaoTipoMaterial {
	private FachadaBD fachada;

	public DaoTipoMaterial() {
		this.fachada = new FachadaBD();
	}

	public int guardarTipoMaterial(TipoMaterial tp) {
		String sqlInsert;
		sqlInsert = "INSERT INTO TipoMaterial(tipo_nombre, descripcion) VALUES('"
				+ tp.getNombre() + "','" + tp.getDescripcion() + "')";

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

	/* consulta tipoMaterial por tipo_nombre */
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
}