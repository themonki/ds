package GestionDocumento.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import GestionDocumento.Logica.AreaConocimiento;
import Utilidades.FachadaBD;

/**
 * Clase que permite la inserccion, actualizacion y consulta de lo que tenga que ver 
 * con el tipo de material
 * @author Cristian Leonardo Rios
 *
 */public class DaoAreaConocimiento {
	/**
	 * Permite la conexion con la base de datos
	 */
	private FachadaBD fachada;

	/**
	 * Constructor por defecto que inicia la variable fachada
	 */
	public DaoAreaConocimiento() {
		fachada = new FachadaBD();
	}

	/**
	 * Metodo que inserta el area de conocimiento
	 * @param ac - AreaConocimiento con los datos a almacenar
	 * @return 1 si se almaceno correctamente el area de conocimiento, -1 de ser lo contrario
	 */
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
	 * Metodo que retorna el area de conociemintoq ue coincida con el parametro, el id_area
	 * @param parametro - String con el id_area del area de conocimiento
	 * @return AreaConocimiento que coincide con la busqueda
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
			}
			this.fachada.cerrarConexion(conn);

		} catch (SQLException se) {
			System.out.println(se.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return area;
	}

	/**
	 * Metodo que consulta todas las areas de conocimiento disponibles y los devuelve en un vector
	 * @return Vector<AreaConocimiento> con todas las areas de conocimiento almacenadas
	 */
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

	/**
	 * Metodo que actualiza los valores de respectivos del area de conocimiento
	 * @param idArea - String con el id_area del area de conocimineto
	 * @param atributo - String con el nombre del campo o columna de la tabla area de conocimiento
	 * @param valorAtributo - String con el valor a actualizar del area de conocimineto
	 * @return 1 si se actualizo correctamente el area de conocimiento, -1 de ser lo contrario 
	 */
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

	/**
	 * Metodo que permite eliminar el area de conocimiento que coincida con el idArea
	 * @param idArea - String con la llave del area de conocimiento a eliminar
	 * @return 1 si se elimino adecuadamente, -1 de ser lo contrario
	 */
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
	/**
	 * Metodo que devuelve las areas de conocimiento de un documento dado su id_documento
	 * @param id_documento - String con la llave del documento
	 * @return Vector <AreaConocimiento> con las areas de conocimiento del documento
	 * @author Edgar Andres Moncada
	 */
	public Vector <AreaConocimiento> consultarAreasDocumento(String id_documento) {
		Vector <AreaConocimiento> vac = new Vector<AreaConocimiento>();
		String sqlSelect;
		sqlSelect = "SELECT * FROM area_conocimiento ac NATURAL JOIN " +
				"pertenece_documento_area_conocimiento dac WHERE dac.id_documento='"
				+ id_documento + "'";
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
				vac.add(area);
			}
			this.fachada.cerrarConexion(conn);

		} catch (SQLException se) {
			System.out.println(se.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return vac;
	}
}