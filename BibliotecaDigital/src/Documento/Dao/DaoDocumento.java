/*
 * DaoDocumento.java
 * 
 * Clase que permite el acceso a la base de datos usando la clase FachadaBD
 * para la inserción, actualizacion y consulta de información que esta rel-
 * acionada con los Documentos de la Biblioteca Digital.
 * 
 *
 * JAVA version "1.6.0"
 * 
 * 
 * Autor:     Edgar Andres Moncada
 * Version:   4.0
 */
package Documento.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import Documento.Logica.Documento;
import Utilidades.FachadaBD;

/**
 * Clase que permite la inserccion, actualizacion y consulta de lo que tenga que ver 
 * con los documentos mediante la Clase {@link Utilidades.FachadaBD FachadaBD}
 * @author Edgar Andres Moncada
 */
public class DaoDocumento {

	/**
	 * Permite la conexion con la base de datos
	 */
	FachadaBD fachada;

	/**
	 * Constructor por defecto que inicia la variable fachada
	 */
	public DaoDocumento() {
		fachada = new FachadaBD();
	}

	
	/**Metodo que permite insertar en la base de datos en la tabla documento
	 * recibiendo todos los datos individualmente
	 * @param id - String con la llave del documento
	 * @param idioma - String con el idioma del documento
	 * @param derechos - String con los derechos de autor del documento
	 * @param descripcion - String con la descripcion del documento
	 * @param software - String con el software recomendado 
	 * @param resolucion - String con la resolucion del documento
	 * @param editorial - String con la editoral del documento
	 * @param formato - String con el formato del documento
	 * @param titulo_principal - String con el titulo principal del documento
	 * @param titulo_secundario - String con el titulo secundario o traducido del documento
	 * @param link - String con el enlace del documento
	 * @param creacion - String con la fecha de creacion del documento
	 * @param publicacion - String con la fecha de publicacion del documento
	 * @param catalogacion - String con la fecha de catalogacion del documento
	 * @param login - String con la llave del usuario catalogador del documento
	 * @param tipo - String con el tipo de material del documento
	 * @return devuelve 1 si se inserto el documento -1 de lo contrario
	 * @autor Edgar Andres Moncada
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


	/**metodo que permite insertar en la base de datos en la tabla documento
	 * recibiendo un objeto de tipo documento
	 * @param d - Documento
	 * @return devuelve 1 si se inserto el documento -1 de lo contrario
	 * @autor Edgar Andres Moncada
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


	/**Metodo que permite actualizar en la base de datos en la tabla documento
	 * recibiendo cada uno de los atributos de la relacion documento
	 * @param id - String con la llave del documento
	 * @param idioma - String con el idioma del documento
	 * @param derechos - String con los derechos de autor del documento
	 * @param descripcion - String con la descripcion del documento
	 * @param software - String con el software recomendado 
	 * @param resolucion - String con la resolucion del documento
	 * @param editorial - String con la editoral del documento
	 * @param formato - String con el formato del documento
	 * @param titulo_principal - String con el titulo principal del documento
	 * @param titulo_secundario - String con el titulo secundario o traducido del documento
	 * @param link - String con el enlace del documento
	 * @param creacion - String con la fecha de creacion del documento
	 * @param publicacion - String con la fecha de publicacion del documento
	 * @param catalogacion - String con la fecha de catalogacion del documento
	 * @param login - String con la llave del usuario catalogador del documento
	 * @param tipo - String con el tipo de material del documento
	 * @return retorna un numero indicando cuantos valores fueron actualizados de 
	 * lo contrario retorna -1
	 * @autor Edgar Andres Moncada
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
	/**
	 * Metodo que permite actualizar en la base de datos en la tabla documento
	 * recibiendo un objeto de tipo documento
	 * @param d - Documento con los datos a insertar en la tabla documento
	 * @return 1 si se actualizo correctamente, -1 de ser lo contrario
	 * @autor Edgar Andres Moncada
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

	/**
	 * Metodo que permite insertar en la tabla Pertenece_Documento_Area_Conocimiento
	 * las areas de conocimiento a las que pertenece el documento
	 * @param id_doc - String con la llave del documento
	 * @param ids_area - Vector<String> con las llaves de las areas de conocimiento
	 * @return int con el numero de areas de conocimiento insertadas, retorna -1 de ser lo contrario
	 * @autor Edgar Andres Moncada
	 */
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

	/**
	 * Metodo que permite insertar en la tabla Tiene_Documento_Palabra_Clave
	 * las palabras clave a las que pertenece el documento
	 * @param id_doc - String con la llave del documento
	 * @param ids_palabras - Vector<String> con las llaves de las palabras claves
	 * @return int con el numero de palabras claves insertadas, retorna -1 de ser lo contrario
	 * @autor Edgar Andres Moncada
	 */
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

	/**
	 * Metodo que permite insertar en la tabla Escribe_Autor_Documento
	 * los autores a las que pertenece el documento
	 * @param id_doc - String con la llave del documento
	 * @param ids_autores - Vector<String> con las llaves de los autores
	 * @return int con el numero de autores insertados, retorna -1 de ser lo contrario
	 * @autor Edgar Andres Moncada
	 */
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

	/**
	 * Metodo que se usa para obtener el login del documento que acaba de ser catalogado
	 * para poder insertar las areas de conocimiento, las palabras clave y los autores
	 * del documento
	 * @return String con la llave del documento catalogado
	 * @author Edgar Andres Moncada
	 */
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

	/**
	 * Metodo para comporbar que un link de un documento, que debe ser unico, se encuentre ya registrado
	 * @param url - String
	 * @return boolean, true si se encuentra en la tabla y false de ser lo contrario
	 * @author Edgar Andres Moncada
	 */
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

	
	/**
	 * Metodo que retorna los datos de un documento de acuerdo a su login, pero no 
	 * devuelve las areas de conocimiento, las palabras clave y los autores del documento
	 * @param id_documento - String con la llave del documento
	 * @return Documento con los datos de la tabla documento, sin las areas de conocimiento, las palabras clave y los autores
	 */
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

	/**
	 * Metodo que actualiza las areas de conocimiento de un documento
	 * @param id_documento - String con la llave del documento
	 * @param ids_areas - Vector<String> con las llaves de las areas de conocimiento
	 * @return int con el numero de areas de conocimiento actualizadas correctamente, 
	 * -1 de ser lo contrario
	 * @author Edgar Andres Moncada
	 */
	public int actualizarDocumentoAreas(String id_documento,
			Vector<String> ids_areas) {
		this.eliminarDocumentoAreas(id_documento);
		return this.guardarDocumentoAreas(id_documento, ids_areas);
	}

	/**
	 * Metodo que actualiza las palabras clave de un documento
	 * @param id_documento - String con la llave del documento
	 * @param ids_palabras - Vector<String> con las llaves de las palabras clave
	 * @return int con el numero de palabras clave actualizadas correctamente, 
	 * -1 de ser lo contrario
	 * @author Edgar Andres Moncada
	 */
	public int actualizarDocumentoPalabrasClave(String id_documento,
			Vector<String> ids_palabras) {
		this.eliminarDocumentoPalabrasClave(id_documento);
		return this.guardarDocumentoPalabrasClave(id_documento, ids_palabras);
	}

	/**
	 * Metodo que actualiza los autores de un documento
	 * @param id_documento - String con la llave del documento
	 * @param ids_autores - Vector<String> con las llaves de los autores
	 * @return int con el numero de los autores actualizadas correctamente, 
	 * -1 de ser lo contrario
	 * @author Edgar Andres Moncada
	 */
	public int actualizarDocumentoAutores(String id_documento,
			Vector<String> ids_autores) {
		this.eliminarDocumentoAutores(id_documento);
		return this.guardarDocumentoAutores(id_documento, ids_autores);
	}

	/**
	 * Metodo que elimina todos los autores que esten relacionados al documento
	 * @param id_documento - String con la llave del documento
	 * @return int con el numero de autores eliminados, -1 de ser lo contrario
	 * @author Edgar Andres Moncada
	 */
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

	/**
	 * Metodo que elimina todos las palabras clave que esten relacionados al documento
	 * @param id_documento - String con la llave del documento
	 * @return int con el numero de palabras clave eliminadas, -1 de ser lo contrario
	 * @author Edgar Andres Moncada
	 */
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

	/**
	 * Metodo que elimina todos las areas de conocimiento que esten relacionados al documento
	 * @param id_documento - String con la llave del documento
	 * @return int con el numero de areas de conocimiento eliminadas, -1 de ser lo contrario
	 * @author Edgar Andres Moncada
	 */
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
