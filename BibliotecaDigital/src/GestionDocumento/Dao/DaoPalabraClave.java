/*
 * Nombre: Cristian R�os.
 * Responsabilidad: Realizar la debida manipulacion de datos referentes a la palabra clave
 * entre el controlador y la base de datos.
 * Nombre archivo: DaoPalabraClave.java
 * Fecha Creacion: Mayo 03 2011
 * Fecha ultima modificaci�n: Mayo 03 2011
 * */

package GestionDocumento.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.Vector;
import GestionDocumento.Logica.PalabraClave;
import Utilidades.FachadaBD;

public class DaoPalabraClave {
	private FachadaBD fachada;

	public DaoPalabraClave() {
		this.fachada = new FachadaBD();
	}

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
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

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

				/* probando */
				System.out.println("Nombre: " + tabla.getString("nombre")
						+ " Descripcion: " + tabla.getString("descripcion"));

			}
			this.fachada.cerrarConexion(conn);

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pal;
	}

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

				/* probando */
				System.out.println("Nombre: " + tabla.getString("nombre")
						+ " Descripcion: " + tabla.getString("descripcion"));

				palabras.add(palabra);
			}
			this.fachada.cerrarConexion(conn);

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return palabras;
	}

	/* main para prueba OK */
	/*
	 * public static void main(String args[]) { PalabraClave a = new
	 * PalabraClave("permutacion","de cuantas maneras"); DaoPalabraClave da =
	 * new DaoPalabraClave(); System.out.println(da.guardarPalabraClave(a));
	 * da.consultarPalabraClave("permutacion");
	 * da.consultarPalabraClave("otra"); da.consultarPalabras(); }
	 */
}