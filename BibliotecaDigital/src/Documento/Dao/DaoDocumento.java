/*
 * AUTOR: EDGAR ANDRES MONCADA
 * 
 * */

package Documento.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import Documento.Logica.Documento;
import Utilidades.FachadaBD;

public class DaoDocumento {

	FachadaBD fachada;

	public DaoDocumento() {
		fachada = new FachadaBD();
	}

	/*
	 * metodo que permite insertar en la base de datos en la tabla documento
	 * recibiendo todos los datos individualmente
	 */
	public int guardarDocumento(String id, String idioma, String derechos,
			String descripcion, String software, String resolucion,
			String editorial, String formato, String titulo_principal,
			String titulo_secundario, String link, String creacion,
			String publicacion, String catalogacion, String login, String tipo) {

		String sql_guardar;
		int numFilas;
		sql_guardar = "INSERT INTO Documento (idioma, derechos_autor, descripcion, software_recomendado, "
				+ "resolucion, editorial, formato, titulo_principal, titulo_secundario, link, "
				+ "fecha_creacion, fecha_publicacion, tipo_nombre, login_catalogador, fecha_catalogacion)"
				+ "VALUES ('"
				+ idioma
				+ "', '"
				+ derechos
				+ "', '"
				+ descripcion
				+ "', '"
				+ software
				+ "', '"
				+ resolucion
				+ "', '"
				+ editorial
				+ "', '"
				+ formato
				+ "', '"
				+ titulo_principal
				+ "', '"
				+ titulo_secundario
				+ "', '"
				+ link
				+ "', '"
				+ creacion
				+ "', '"
				+ publicacion
				+ "', '"
				+ tipo
				+ "', '"
				+ login
				+ "', '" + catalogacion + "');";

		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();

			numFilas = sentencia.executeUpdate(sql_guardar);
			conn.close();
			return numFilas;
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}

	/*
	 * metodo que permite insertar en la base de datos en la tabla documento
	 * recibiendo un objeto de tipo documento
	 */
	public int guardarDocumento(Documento d) {
		int value = guardarDocumento(d.getId_doc(), d.getIdioma(), d
				.getDerechosDeAutor(), d.getDescripcion(), d
				.getSoftware_recomentado(), d.getResolucion(),
				d.getEditorial(), d.getFormato(), d.getTituloppal(), d
						.getTitulo_secundario(), d.getUrl(), d
						.getFecha_creacion().toString(), d
						.getFecha_publicacion().toString(), d
						.getFechaDeCatalogacion().toString(), d
						.getCatalogadorLogin(), d.getTipoMaterial());
		return value;
	}

	/*
	 * metodo que permite actualizar en la base de datos en la tabla documento
	 * recibiendo cada uno de los atributos de la relacion documento
	 */
	public int modificarDocumento(String id, String idioma, String derechos,
			String descripcion, String software, String resolucion,
			String editorial, String formato, String titulo_principal,
			String titulo_secundario, String link, String creacion,
			String publicacion, String catalogacion, String login, String tipo) {

		String sql_actualizar;
		int numFilas;
		sql_actualizar = "UPDATE Documento SET " + "idioma = '" + idioma
				+ "', derechos_autor = '" + derechos + "', descripcion = '"
				+ descripcion + "', software_recomendado = '" + software
				+ "', resolucion = '" + resolucion + "', editorial = '"
				+ editorial + "', formato = '" + formato
				+ "', titulo_principal = '" + titulo_principal
				+ "', titulo_secundario = '" + titulo_secundario
				+ "', link = '" + link + "', fecha_creacion = '" + creacion
				+ "', fecha_publicacion = '" + publicacion
				+ "', tipo_nombre = '" + tipo + "', login_catalogador = '"
				+ login + "', fecha_catalogacion = '" + catalogacion
				+ "' WHERE id_Documento = '" + id + "';";

		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();

			numFilas = sentencia.executeUpdate(sql_actualizar);
			conn.close();
			return numFilas;
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}

	/*
	 * metodo que permite actualizar en la base de datos en la tabla documento
	 * recibiendo un objeto de tipo documento
	 */
	public int modificarDocumento(Documento d) {
		int value = modificarDocumento(d.getId_doc(), d.getIdioma(), d
				.getDerechosDeAutor(), d.getDescripcion(), d
				.getSoftware_recomentado(), d.getResolucion(),
				d.getEditorial(), d.getFormato(), d.getTituloppal(), d
						.getTitulo_secundario(), d.getUrl(), d
						.getFecha_creacion().toString(), d
						.getFecha_publicacion().toString(), d
						.getFechaDeCatalogacion().toString(), d
						.getCatalogadorLogin(), d.getTipoMaterial());
		return value;
	}

	public int guardarDocumentoAreas(String id_doc, Vector<String> ids_area) {

		String sql_guardar;
		int numFilas, cantidad = ids_area.size();
		sql_guardar = "INSERT INTO Pertenece_Documento_Area_Conocimiento VALUES ";
		if (cantidad == 0) {
			return 0;
		}
		for (int i = 0; i < cantidad; i++) {
			if (i == cantidad - 1) {
				sql_guardar += "('" + ids_area.get(i) + "', '" + id_doc + "' )";
			} else {
				sql_guardar += "('" + ids_area.get(i) + "', '" + id_doc
						+ "' ),";
			}
		}
		sql_guardar += ";";
		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();

			numFilas = sentencia.executeUpdate(sql_guardar);
			conn.close();
			return numFilas;
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}

	public int guardarDocumentoPalabrasClave(String id_doc,
			Vector<String> ids_palabras) {

		String sql_guardar;
		int numFilas, cantidad = ids_palabras.size();
		sql_guardar = "INSERT INTO Tiene_Documento_Palabra_Clave VALUES ";

		if (cantidad == 0) {
			return 0;
		}

		for (int i = 0; i < cantidad; i++) {
			if (i == cantidad - 1) {
				sql_guardar += "('" + ids_palabras.get(i) + "', '" + id_doc
						+ "' )";
			} else {
				sql_guardar += "('" + ids_palabras.get(i) + "', '" + id_doc
						+ "' ),";
			}
		}
		sql_guardar += ";";

		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();

			numFilas = sentencia.executeUpdate(sql_guardar);
			conn.close();
			return numFilas;
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}

	public int guardarDocumentoAutores(String id_doc, Vector<String> ids_autores) {

		String sql_guardar;
		int numFilas, cantidad = ids_autores.size();
		sql_guardar = "INSERT INTO Escribe_Autor_Documento VALUES ";
		if (cantidad == 0) {
			return 0;
		}
		for (int i = 0; i < cantidad; i++) {
			if (i == cantidad - 1) {
				sql_guardar += "('" + ids_autores.get(i) + "', '" + id_doc
						+ "' )";
			} else {
				sql_guardar += "('" + ids_autores.get(i) + "', '" + id_doc
						+ "' ),";
			}
		}
		sql_guardar += ";";

		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();

			numFilas = sentencia.executeUpdate(sql_guardar);
			conn.close();
			return numFilas;
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}

	public String obtenerLoginDocumento() {
		String login = "0";
		String consulta_sql = "SELECT MAX(d.id_documento) FROM Documento d";
		ResultSet resultado;
		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();
			resultado = sentencia.executeQuery(consulta_sql);
			while (resultado.next()) {
				login = resultado.getString(1);
			}
			conn.close();

		} catch (SQLException e) {
			System.out.println(e);

		} catch (Exception e) {
			System.out.println(e);
		}

		return login;

	}

	public boolean comprobarURL(String url) {
		boolean estado = false;
		String consulta_sql = "SELECT d.id_documento, d.link FROM Documento d "
				+ "WHERE link='" + url + "';";
		ResultSet resultado;
		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();
			resultado = sentencia.executeQuery(consulta_sql);
			while (resultado.next()) {
				estado = true;
			}
			conn.close();
			return estado;
		} catch (SQLException e) {
			System.out.println(e);

		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	// metodo que retorna los datos de un documento de acuerdo a su login, no
	// devuelve areas, palabras y autores
	public Documento consultarDatosDocumento(String id_documento) {
		String consulta_sql = "SELECT * " + "FROM documento "
				+ "WHERE id_documento = '" + id_documento + "';";
		ResultSet resultado;
		Documento d = new Documento();
		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();
			resultado = sentencia.executeQuery(consulta_sql);
			if (resultado.next()) {// debe devolver uno solo
				d.setDerechosDeAutor(resultado.getString("derechos_autor"));
				d.setCatalogadorLogin(resultado.getString("login_catalogador"));
				d.setDescripcion(resultado.getString("descripcion"));
				d.setEditorial(resultado.getString("editorial"));
				d.setFecha_creacion(java.sql.Date.valueOf(resultado
						.getString("fecha_creacion")));
				d.setFecha_publicacion(java.sql.Date.valueOf(resultado
						.getString("fecha_publicacion")));
				d.setFechaDeCatalogacion(java.sql.Date.valueOf(resultado
						.getString("fecha_catalogacion")));
				d.setFormato(resultado.getString("formato"));
				d.setId_doc(resultado.getString("id_documento"));
				d.setIdioma(resultado.getString("idioma"));
				d.setResolucion(resultado.getString("resolucion"));
				d.setSoftware_recomentado(resultado
						.getString("software_recomendado"));
				d.setTipoMaterial(resultado.getString("tipo_nombre"));
				d
						.setTitulo_secundario(resultado
								.getString("titulo_secundario"));
				d.setTituloppal(resultado.getString("titulo_principal"));
				d.setUrl(resultado.getString("link"));
			}
			conn.close();

		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return d;
	}

	// *******ACTUALIZAR
	public int actualizarDocumentoAreas(String id_documento,
			Vector<String> ids_areas) {
		this.eliminarDocumentoAreas(id_documento);
		return this.guardarDocumentoAreas(id_documento, ids_areas);
	}

	public int actualizarDocumentoPalabrasClave(String id_documento,
			Vector<String> ids_palabras) {
		this.eliminarDocumentoPalabrasClave(id_documento);
		return this.guardarDocumentoPalabrasClave(id_documento, ids_palabras);
	}

	public int actualizarDocumentoAutores(String id_documento,
			Vector<String> ids_autores) {
		this.eliminarDocumentoAutores(id_documento);
		return this.guardarDocumentoAutores(id_documento, ids_autores);
	}

	public int eliminarDocumentoAutores(String id_documento) {
		String sql_eliminar;
		int numFilas;
		sql_eliminar = "DELETE FROM escribe_autor_documento WHERE id_documento = '"
				+ id_documento + "';";

		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();
			numFilas = sentencia.executeUpdate(sql_eliminar);
			conn.close();
			return numFilas;
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}

	public int eliminarDocumentoPalabrasClave(String id_documento) {
		String sql_eliminar;
		int numFilas;
		sql_eliminar = "DELETE FROM tiene_documento_palabra_clave WHERE id_documento = '"
				+ id_documento + "';";

		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();
			numFilas = sentencia.executeUpdate(sql_eliminar);
			conn.close();
			return numFilas;
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}

	public int eliminarDocumentoAreas(String id_documento) {
		String sql_eliminar;
		int numFilas;
		sql_eliminar = "DELETE FROM pertenece_documento_area_conocimiento WHERE id_documento = '"
				+ id_documento + "';";

		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();
			numFilas = sentencia.executeUpdate(sql_eliminar);
			conn.close();
			return numFilas;
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
}
