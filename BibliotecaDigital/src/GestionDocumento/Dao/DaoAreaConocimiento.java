/*
 * Nombre: Cristian Ríos.
 * Responsabilidad: Realizar la debida manipulacion de datos referentes a el area de conocimiento
 * entre el controlador y la base de datos.
 * Nombre archivo: DaoAreaConocimiento.java
 * Fecha Creacion: Mayo 03 2011
 * Fecha ultima modificación: Mayo 02 2011
 * */

package GestionDocumento.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.Vector;
import GestionDocumento.Logica.AreaConocimiento;
import GestionDocumento.Logica.Autor;
import Utilidades.FachadaBD;

public class DaoAreaConocimiento
{
	FachadaBD fachada;
	
	public DaoAreaConocimiento()
	{
		fachada = new FachadaBD();
	}
	
	public int guardarAreaConocimiento(AreaConocimiento ac)
	{
		String sqlInsert;
		sqlInsert = "INSERT INTO Area_Conocimiento(id_area, nombre, descripcion, area_padre) VALUES('"
			+ ac.getIdArea()
			+ "','"
			+ ac.getNombre()
			+ "','"
			+ ac.getDescripcion()
			+ "','"
			+ ac.getAreaPadre()
			+ "')";
		
		try
		{
			Connection conn = this.fachada.conectar();
			Statement sentencia = conn.createStatement();
			int numFilas = sentencia.executeUpdate(sqlInsert);
			this.fachada.cerrarConexion(conn);
			return numFilas;
		}catch(SQLException se)
		{
			se.printStackTrace();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return -1;
	}
	
	/*consulta area por id_area, como id_area es pk encontrará un dato*/
	public AreaConocimiento consultarArea(String parametro)
	{
		AreaConocimiento area = new AreaConocimiento();
		String sqlSelect;

		sqlSelect = "SELECT * FROM area_conocimiento WHERE area_conocimiento.id_area='" + parametro
				+ "'";
		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();
			ResultSet tabla = sentencia.executeQuery(sqlSelect);

			if(tabla.next()) {

				area.setIdArea(tabla.getString(1));
				area.setNombre(tabla.getString(2));
				area.setDescripcion(tabla.getString(3));
				area.setIdArea(tabla.getString(4));

				/*probando*/
				System.out.println("Id area: " + tabla.getString(1) +
						" Nombre: " + tabla.getString(2) + 
						"Nombre: " + tabla.getString(3) + 
						"Descripcion: " + tabla.getString(4));

			}
			fachada.cerrarConexion(conn);
		}

		catch (SQLException se)
		{
			se.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	
		return area;
	}
	
	public Vector<AreaConocimiento> consultarTodasAreas()
	{
		return new Vector<AreaConocimiento>();
	}

}