package GestionDocumento.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.Vector;
import GestionDocumento.Logica.Autor;
import Utilidades.FachadaBD;

public class DaoAutor {

	FachadaBD fachada;

	public DaoAutor() {
		fachada = new FachadaBD();

	}

	int guardarAutor(Autor autor) {
		String sqlGuardar;
		sqlGuardar = "INSERT INTO autor(id_autor, nombre, email, apellido,acronimo) VALUES ('"
				+ "NEXTVAL(id_autor_seq),"
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

	Autor consultarAutor(String parametro) {

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
			System.out.println("IdAutor-Nombre-Email-Apellido-Acronimo");

			while (tabla.next()) {

				autor.setId(tabla.getString(1));
				autor.setNombre(tabla.getString(2));
				autor.setCorreo(tabla.getString(3));
				autor.setApellido(tabla.getString(4));

				autor.setAcronimo(tabla.getString(5));
				System.out.println("Id Autor: " + tabla.getString(1)
						+ " Nombre: " + tabla.getString(2) + " Email:"
						+ tabla.getString(3) + "Apellido: "
						+ tabla.getString(4) + "Acronimo: "
						+ tabla.getString(5));

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

	Vector<Autor> consultarAutores() {

		Vector<Autor> autores = new Vector<Autor>();
		String sqlSelect;
		// Por ahora se asume que el parametro discriminador es el acronimo
		sqlSelect = "SELECT * FROM autor";
		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();
			ResultSet tabla = sentencia.executeQuery(sqlSelect);
			// Provisional probar resultados
			System.out.println("IdAutor-Nombre-Email-Apellido-Acronimo");

			while (tabla.next()) {

				Autor autorAux = new Autor();

				autorAux.setId(tabla.getString(1));
				autorAux.setNombre(tabla.getString(2));
				autorAux.setCorreo(tabla.getString(3));
				autorAux.setApellido(tabla.getString(4));
				autorAux.setAcronimo(tabla.getString(5));
				
				autores.add(autorAux);

				System.out.println("Id Autor: " + tabla.getString(1)
						+ " Nombre: " + tabla.getString(2) + " Email:"
						+ tabla.getString(3) + "Apellido: "
						+ tabla.getString(4) + "Acronimo: "
						+ tabla.getString(5));

			}
			conn.close();
			System.out.println("Conexion cerrada");

		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return autores;

	}

}