/*
 * Nombre: Cristian R�os.
 * Responsabilidad: Realizar la debida manipulacion de datos referentes a el area de conocimiento
 * entre el controlador y la base de datos.
 * Nombre archivo: DaoAreaConocimiento.java
 * Fecha Creacion: Mayo 03 2011
 * Fecha ultima modificaci�n: Mayo 03 2011
 * */

package GestionDocumento.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import GestionDocumento.Logica.AreaConocimiento;
import Utilidades.FachadaBD;

public class DaoAreaConocimiento {
	private FachadaBD fachada;

	public DaoAreaConocimiento() {
		fachada = new FachadaBD();
	}

	public int guardarAreaConocimiento(AreaConocimiento ac) {
		String sqlInsert;
		sqlInsert = "INSERT INTO Area_Conocimiento(id_area, nombre, descripcion, area_padre) VALUES('"
				+ ac.getIdArea()
				+ "','"
				+ ac.getNombre()
				+ "','"
				+ ac.getDescripcion() + "','" + ac.getAreaPadre() + "')";

		try {
			Connection conn = this.fachada.conectar();
			Statement sentencia = conn.createStatement();
			int numFilas = sentencia.executeUpdate(sqlInsert);System.out.println(sqlInsert);
			this.fachada.cerrarConexion(conn);
			return numFilas;
		} catch (SQLException se) {
			System.out.println(se.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return -1;
	}

	/*
	 * consulta area_conocimiento por id_area, como id_area es pk encontrar� un
	 * dato
	 */
	public AreaConocimiento consultarArea(String parametro) {
		AreaConocimiento area = new AreaConocimiento();
		String sqlSelect;

		sqlSelect = "SELECT * FROM area_conocimiento WHERE area_conocimiento.id_area='"
				+ parametro + "'";
		try {
			Connection conn = this.fachada.conectar();
			Statement sentencia = conn.createStatement();
			ResultSet tabla = sentencia.executeQuery(sqlSelect);

			if (tabla.next()) {

				area.setIdArea(tabla.getString("id_area"));
				area.setNombre(tabla.getString("nombre"));
				area.setDescripcion(tabla.getString("descripcion"));
				area.setIdArea(tabla.getString("area_padre"));

				/* probando 
				System.out.println("Id area: " + tabla.getString("id_area")
						+ " Nombre: " + tabla.getString("nombre")
						+ " Descripcion: " + tabla.getString("descripcion")
						+ " Area Padre: " + tabla.getString("area_padre"));
*/
			}
			this.fachada.cerrarConexion(conn);

		} catch (SQLException se) {
			System.out.println(se.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return area;
	}

	public Vector<AreaConocimiento> consultarAreas() {
		Vector<AreaConocimiento> areas = new Vector<AreaConocimiento>();
		String sqlSelect;
		sqlSelect = "SELECT * FROM Area_Conocimiento";

		try {
			Connection conn = this.fachada.conectar();
			Statement sentencia = conn.createStatement();
			ResultSet tabla = sentencia.executeQuery(sqlSelect);

			while (tabla.next()) {
				AreaConocimiento area = new AreaConocimiento();

				area.setIdArea(tabla.getString("id_area"));
				area.setNombre(tabla.getString("nombre"));
				area.setDescripcion(tabla.getString("descripcion"));
				area.setAreaPadre(tabla.getString("area_padre"));

				/* probando 
				System.out.println("Id area: " + tabla.getString("id_area")
						+ " Nombre: " + tabla.getString("nombre")
						+ " Descripcion: " + tabla.getString("descripcion")
						+ " Drea Padre: " + tabla.getString("area_padre"));
				 */
				areas.add(area);
			}
			this.fachada.cerrarConexion(conn);

		} catch (SQLException se) {
			System.out.println(se.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return areas;
	}

	public int modificarArea(String idArea, String atributo,
			String valorAtributo) {
		String sqlUpdate;
		sqlUpdate = "UPDATE Area_Conocimiento SET " + atributo + " = '"
				+ valorAtributo + "' WHERE id_area = '" + idArea + "'";

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

	public int eliminarArea(String idArea) {
		String sqlDelete;
		sqlDelete = "DELETE FROM Area_Conocimiento WHERE id_area = '" + idArea
				+ "'";

		try {
			Connection conn = this.fachada.conectar();
			Statement sentencia = conn.createStatement();
			int numFilas = sentencia.executeUpdate(sqlDelete);
			this.fachada.cerrarConexion(conn);
			return numFilas;
		} catch (SQLException se) {
			System.out.println(se.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return -1;
	}

	/* main para prueba OK */
	/*
	 * public static void main(String args[]) { AreaConocimiento a = new
	 * AreaConocimiento("3","metodos numericos computacionales","","");
	 * DaoAreaConocimiento da = new DaoAreaConocimiento();
	 * System.out.println(da.guardarAreaConocimiento(a)); da.consultarArea("1");
	 * da.consultarAreas(); da.modificarArea("1",
	 * "nombre","computacion centrada"); da.eliminarArea("3"); }
	 */

}