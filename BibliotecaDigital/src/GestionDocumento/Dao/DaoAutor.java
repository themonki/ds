package GestionDocumento.Dao;

/*
 * Nombre: Yerminson Gonzalez Munoz
 * Responsabilidad : Permite la inserccion consulta de autores.
 * */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import GestionDocumento.Logica.AreaConocimiento;
import GestionDocumento.Logica.Autor;
import Utilidades.FachadaBD;

public class DaoAutor {

	FachadaBD fachada;

	public DaoAutor() {
		fachada = new FachadaBD();

	}

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
			// Provisional probar resultados
			//System.out.println("IdAutor-Nombre-Email-Apellido-Acronimo");

			while (tabla.next()) {

				autor.setId(tabla.getString(1));
				autor.setNombre(tabla.getString(2));
				autor.setCorreo(tabla.getString(3));
				autor.setApellido(tabla.getString(4));

				autor.setAcronimo(tabla.getString(5));
/*				System.out.println("Id Autor: " + tabla.getString(1)
						+ " Nombre: " + tabla.getString(2) + " Email:"
						+ tabla.getString(3) + "Apellido: "
						+ tabla.getString(4) + "Acronimo: "
						+ tabla.getString(5));
*/
			}
			fachada.cerrarConexion(conn);
			System.out.println("Conexion cerrada");

		}

		catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return autor;
	}

	public Vector<Autor> consultarAutores() {

		Vector<Autor> autores = new Vector<Autor>();
		String sqlSelect;
		// Por ahora se asume que el parametro discriminador es el acronimo
		sqlSelect = "SELECT * FROM autor";
		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();
			ResultSet tabla = sentencia.executeQuery(sqlSelect);
			// Provisional probar resultados
			//System.out.println("IdAutor-Nombre-Email-Apellido-Acronimo");

			while (tabla.next()) {

				Autor autorAux = new Autor();

				autorAux.setId(tabla.getString(1));
				autorAux.setNombre(tabla.getString(2));
				autorAux.setCorreo(tabla.getString(3));
				autorAux.setApellido(tabla.getString(4));
				autorAux.setAcronimo(tabla.getString(5));

				autores.add(autorAux);
/*
				System.out.println("Id Autor: " + tabla.getString(1)
						+ " Nombre: " + tabla.getString(2) + " Email:"
						+ tabla.getString(3) + "Apellido: "
						+ tabla.getString(4) + "Acronimo: "
						+ tabla.getString(5));
*/
			}
			fachada.cerrarConexion(conn);
			System.out.println("Conexion cerrada");

		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return autores;

	}
	
//metodo que devuelve los autores de un documento dado su id_documento
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
				System.out.println(autor.getId());
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


	/* main para prueba OK
	public static void main(String args[]) {
		Autor a = new Autor("Yerminson", "Gonzalez", "yermigon",
				"yermigon@gmail.com", "");
		DaoAutor da = new DaoAutor();
		System.out.println(da.guardarAutor(a));
		da.consultarAutor("yermigon");
		System.out.println(da.consultarAutores().size());

	} */

}
