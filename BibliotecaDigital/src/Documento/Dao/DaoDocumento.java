/*
 * AUTOR: EDGAR ANDRES MONCADA
 * 
 * */

package Documento.Dao;

import java.sql.*;
import java.util.Vector;

import Utilidades.FachadaBD;
import Documento.Logica.*;

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
	
	public int guardarDocumentoAreas(String id_doc, Vector <String> ids_area){		
		
		String sql_guardar;
		int numFilas, cantidad = ids_area.size();
		sql_guardar = "INSERT INTO Pertenece_Documento_Area_Conocimiento VALUES ";
		
		for(int i = 0; i < cantidad; i++){			
			if(i==cantidad-1){
				sql_guardar+="('"+ ids_area.get(i) + "', '"+id_doc +"' )";
			}else {
				sql_guardar+="('"+ ids_area.get(i) + "', '"+id_doc +"' ),";
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
public int guardarDocumentoPalabrasClave(String id_doc, Vector <String> ids_palabras){		
		
		String sql_guardar;
		int numFilas, cantidad = ids_palabras.size();
		sql_guardar = "INSERT INTO Tiene_Documento_Palabra_Clave VALUES ";
		
		for(int i = 0; i < cantidad; i++){			
			if(i==cantidad-1){
				sql_guardar+="('"+ ids_palabras.get(i) + "', '"+id_doc +"' )";
			}else {
				sql_guardar+="('"+ ids_palabras.get(i) + "', '"+id_doc +"' ),";
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

public int guardarDocumentoAutores(String id_doc, Vector <String> ids_autores){		
	
	String sql_guardar;
	int numFilas, cantidad = ids_autores.size();
	sql_guardar = "INSERT INTO Escribe_Autor_Documento VALUES ";
	
	for(int i = 0; i < cantidad; i++){			
		if(i==cantidad-1){
			sql_guardar+="('"+ ids_autores.get(i) + "', '"+id_doc +"' )";
		}else {
			sql_guardar+="('"+ ids_autores.get(i) + "', '"+id_doc +"' ),";
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

}
