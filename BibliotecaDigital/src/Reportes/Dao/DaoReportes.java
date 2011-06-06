package Reportes.Dao;

import java.awt.ScrollPane;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Utilidades.FachadaBD;
import Utilidades.TableDataSource;

public class DaoReportes {

	FachadaBD fachada;

	JTable tabla;
	DefaultTableModel modelo;

	public DaoReportes() {
		fachada = new FachadaBD();
	}

	/*   ***********************Reportes relacionados con usuarios************** */
	public TableDataSource consultaUsuariosAgrupados(String atributoUsuario) {
		String consultaSql = "SELECT u."
				+ atributoUsuario
				+ " AS agrupado, u.login, u.nombre1, u.apellido1, u.email, "
				+ "u.vinculo_univalle, u.tipo, u.fecha_nacimiento, u.fecha_registro "
				+ "FROM usuario AS u ORDER BY agrupado";

		return procesarDatosUsuario(consultaSql, atributoUsuario);
	}

	public TableDataSource consultaUsuariosAgrupados(String atributoUsuario,
			String cualFecha, String fechaInicio, String FechaFin) {
		String consultaSql = "SELECT u."
				+ atributoUsuario
				+ " AS agrupado, u.login, u.nombre1, u.apellido1, u.email, "
				+ "u.vinculo_univalle, u.tipo, u.fecha_nacimiento, u.fecha_registro "
				+ "FROM usuario AS u " + "WHERE u." + cualFecha + " BETWEEN '"
				+ fechaInicio + "' AND '" + FechaFin + "' "
				+ "ORDER BY agrupado";

		return procesarDatosUsuario(consultaSql, atributoUsuario);
	}

	private TableDataSource procesarDatosUsuario(String consultaSql,
			String atributoUsuario) {
		TableDataSource data = new TableDataSource();

		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();
			ResultSet resultado = sentencia.executeQuery(consultaSql);
			ResultSetMetaData metaData = resultado.getMetaData();

			for (int i = 0; i < metaData.getColumnCount(); i++) {
				data.addColumn(metaData.getColumnName(i + 1));
				// System.out.println(metaData.getColumnTypeName(i+1));
			}

			while (resultado.next()) {
				Vector<Object> row = new Vector<Object>(0, 1);

				String columnOne = resultado.getString(1);
				if (atributoUsuario.equals("genero")) {
					row.add(convertirGenero(resultado.getString(1)));

				} else if (atributoUsuario.equals("tipo")) {
					row.add(convertirTipo(resultado.getInt(1)));
				} else {
					row.add(columnOne);
				}
				row.add(resultado.getString(2));
				row.add(resultado.getString(3));
				row.add(resultado.getString(4));
				row.add(resultado.getString(5));
				row.add(resultado.getString(6));
				row.add(resultado.getString(7));
				row.add(resultado.getString(8));
				row.add(resultado.getString(9));

				data.addRow(row);
			}
			fachada.cerrarConexion(conn);
			conn = null;
			fachada = null;
			sentencia = null;
			resultado = null;
			metaData = null;
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}

		return data;
	}

	public TableDataSource consultaUsuariosAgrupadosTotales(
			String atributoUsuario) {
		String consultaSql = "SELECT u." + atributoUsuario
				+ " AS agrupado, count(" + atributoUsuario + ") As cuantos "
				+ "FROM usuario AS u " + "GROUP BY agrupado "
				+ "ORDER BY agrupado";

		return procesarDatosUsuarioTotales(consultaSql, atributoUsuario);
	}

	public TableDataSource consultaUsuariosAgrupadosTotales(
			String atributoUsuario, String cualFecha, String fechaInicio,
			String FechaFin) {
		String consultaSql = "SELECT u." + atributoUsuario
				+ " AS agrupado, count(" + atributoUsuario + ") AS cuantos "
				+ "FROM usuario AS u " + "WHERE u." + cualFecha + " BETWEEN '"
				+ fechaInicio + "' AND '" + FechaFin + "' "
				+ "GROUP BY agrupado " + "ORDER BY agrupado";

		return procesarDatosUsuarioTotales(consultaSql, atributoUsuario);
	}

	private TableDataSource procesarDatosUsuarioTotales(String consultaSql,
			String atributoUsuario) {
		TableDataSource data = new TableDataSource();

		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();
			ResultSet resultado = sentencia.executeQuery(consultaSql);
			ResultSetMetaData metaData = resultado.getMetaData();

			for (int i = 0; i < metaData.getColumnCount(); i++) {
				data.addColumn(metaData.getColumnName(i + 1));
				// System.out.println(metaData.getColumnTypeName(i+1));
			}

			while (resultado.next()) {
				Vector<Object> row = new Vector<Object>(0, 1);

				String columnOne = resultado.getString(1);
				if (atributoUsuario.equals("genero")) {
					row.add(convertirGenero(resultado.getString(1)));

				} else if (atributoUsuario.equals("tipo")) {
					row.add(convertirTipo(resultado.getInt(1)));

				} else {
					row.add(columnOne);
				}
				row.add(resultado.getInt(2));

				data.addRow(row);
			}
			fachada.cerrarConexion(conn);
			conn = null;
			fachada = null;
			sentencia = null;
			resultado = null;
			metaData = null;
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}

		return data;
	}

	public TableDataSource consultaUsuariosAnio(String tipoAnio) {
		String consultaSql = "SELECT EXTRACT(YEAR FROM u."
				+ tipoAnio
				+ ") AS anio, "
				+ "EXTRACT(MONTH FROM u."
				+ tipoAnio
				+ ") AS mes, "
				+ "u.login, u.nombre1, u.apellido1, u.email, u.vinculo_univalle, u.tipo "
				+ "FROM usuario AS u " + "ORDER BY date_trunc('year', u."
				+ tipoAnio + "), " + "date_trunc('month', u." + tipoAnio + ")";

		return procesarDatosUsuariosFecha(consultaSql);
	}

	public TableDataSource consultaUsuariosAnio(String tipoAnio, String anioI,
			String anioF) {
		String consultaSql = "SELECT EXTRACT(YEAR FROM u."
				+ tipoAnio
				+ ") AS anio, "
				+ "EXTRACT(MONTH FROM u."
				+ tipoAnio
				+ ") AS mes, "
				+ "u.login, u.nombre1, u.apellido1, u.email, u.vinculo_univalle, u.tipo "
				+ "FROM usuario AS u " + "WHERE EXTRACT(YEAR FROM u."
				+ tipoAnio + ") BETWEEN " + anioI + " AND " + anioF
				+ " ORDER BY date_trunc('year', u." + tipoAnio + "), "
				+ "date_trunc('month', u." + tipoAnio + ")";

		return procesarDatosUsuariosFecha(consultaSql);
	}

	public TableDataSource consultaUsuariosAnioMes(String tipoAnio,
			String mesI, String mesF) {
		String consultaSql = "SELECT EXTRACT(YEAR FROM u."
				+ tipoAnio
				+ ") AS anio, "
				+ "EXTRACT(MONTH FROM u."
				+ tipoAnio
				+ ") AS mes, "
				+ "u.login, u.nombre1, u.apellido1, u.email, u.vinculo_univalle, u.tipo "
				+ "FROM usuario AS u " + "WHERE EXTRACT(MONTH FROM u."
				+ tipoAnio + ") BETWEEN " + mesI + " AND " + mesF
				+ " ORDER BY date_trunc('year', u." + tipoAnio + "), "
				+ "date_trunc('month',  u." + tipoAnio + ")";

		return procesarDatosUsuariosFecha(consultaSql);
	}

	public TableDataSource consultaUsuariosAnioMes(String tipoAnio,
			String anioI, String anioF, String mesI, String mesF) {
		String consultaSql = "SELECT EXTRACT(YEAR FROM u."
				+ tipoAnio
				+ ") AS anio, "
				+ "EXTRACT(MONTH FROM u."
				+ tipoAnio
				+ ") AS mes, "
				+ "u.login, u.nombre1, u.apellido1, u.email, u.vinculo_univalle, u.tipo "
				+ "FROM usuario AS u " + "WHERE EXTRACT(MONTH FROM u."
				+ tipoAnio + ") BETWEEN " + mesI + " AND " + mesF
				+ " AND EXTRACT(YEAR FROM u." + tipoAnio + ") BETWEEN " + anioI
				+ " AND " + anioF + " ORDER BY date_trunc('year', u."
				+ tipoAnio + "), " + "date_trunc('month',  u." + tipoAnio + ")";

		return procesarDatosUsuariosFecha(consultaSql);
	}

	private TableDataSource procesarDatosUsuariosFecha(String consultaSql) {
		TableDataSource data = new TableDataSource();

		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();
			ResultSet resultado = sentencia.executeQuery(consultaSql);
			ResultSetMetaData metaData = resultado.getMetaData();

			for (int i = 0; i < metaData.getColumnCount(); i++) {
				data.addColumn(metaData.getColumnName(i + 1));
				// System.out.println(metaData.getColumnTypeName(i+1));
			}

			while (resultado.next()) {
				Vector<Object> row = new Vector<Object>(0, 1);

				row.add(resultado.getString(1));
				row.add(convertirMes(resultado.getInt(2)));
				row.add(resultado.getString(3));
				row.add(resultado.getString(4));
				row.add(resultado.getString(5));
				row.add(resultado.getString(6));
				row.add(resultado.getString(7));
				row.add(convertirTipo(resultado.getInt(8)));

				data.addRow(row);
			}
			fachada.cerrarConexion(conn);
			conn = null;
			fachada = null;
			sentencia = null;
			resultado = null;
			metaData = null;
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}

		return data;
	}

	/*
	 * ************reportes relacionados con areas de ciencias de la
	 * computacion***********
	 */
	public TableDataSource consultarAreasConocimientoAgrupadas() {
		String consultaSql = "SELECT A.nombre, B.nombre AS nombre_Area_Padre "
				+ "FROM area_conocimiento AS A JOIN area_conocimiento AS B "
				+ "ON A.area_padre = B.id_area ORDER BY nombre_Area_Padre";

		TableDataSource data = new TableDataSource();

		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();
			ResultSet resultado = sentencia.executeQuery(consultaSql);
			ResultSetMetaData metaData = resultado.getMetaData();

			for (int i = 0; i < metaData.getColumnCount(); i++) {
				data.addColumn(metaData.getColumnName(i + 1));
				// System.out.println(metaData.getColumnTypeName(i+1));
			}

			while (resultado.next()) {
				Vector<Object> row = new Vector<Object>(0, 1);

				row.add(resultado.getString(1));
				row.add(resultado.getString(2));

				data.addRow(row);
			}
			fachada.cerrarConexion(conn);
			conn = null;
			fachada = null;
			sentencia = null;
			resultado = null;
			metaData = null;
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}

		return data;
	}

	public TableDataSource consultarAreasConocimientoAgrupadasTotales() {
		String consultaSql = "SELECT B.nombre AS Areas_Padre, count(B.nombre) AS Cantidad "
				+ "FROM area_conocimiento AS A JOIN area_conocimiento AS B "
				+ "ON A.area_padre = B.id_area GROUP BY Areas_Padre ORDER BY Areas_Padre";

		TableDataSource data = new TableDataSource();

		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();
			ResultSet resultado = sentencia.executeQuery(consultaSql);
			ResultSetMetaData metaData = resultado.getMetaData();

			for (int i = 0; i < metaData.getColumnCount(); i++) {
				data.addColumn(metaData.getColumnName(i + 1));
				// System.out.println(metaData.getColumnTypeName(i+1));
			}

			while (resultado.next()) {
				Vector<Object> row = new Vector<Object>(0, 1);

				row.add(resultado.getString(1));
				row.add(resultado.getInt(2));

				data.addRow(row);
			}
			fachada.cerrarConexion(conn);
			conn = null;
			fachada = null;
			sentencia = null;
			resultado = null;
			metaData = null;
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}

		return data;
	}

	private String convertirMes(int mes) {
		switch (mes) {
		case 1:
			return "Enero";
		case 2:
			return "Febrero";
		case 3:
			return "Marzo";
		case 4:
			return "Abril";
		case 5:
			return "Mayo";
		case 6:
			return "Junio";
		case 7:
			return "Julio";
		case 8:
			return "Agosto";
		case 9:
			return "Septiembre";
		case 10:
			return "Octubre";
		case 11:
			return "Noviembre";
		default:
			return "Dicciembre";
		}
	}

	private String convertirTipo(int tipo) {
		switch (tipo) {
		case 3:
			return "Normal";
		case 2:
			return "Catalogador";
		case 1:
			return "Administrador";
		default:
			return "Anónimo";
		}
	}

	private String convertirGenero(String genero) {
		if (genero.equals("M")) {
			return "Masculino";

		} else {
			return "Femenino";
		}
	}

	/*
	 * ******************Reporte relacionados con
	 * documento**********************
	 */
	public TableDataSource consultaDocumentosAgrupadosArea() {
		String consultaSql = "SELECT doc.id_documento, doc.titulo_principal, doc.editorial, area.nombre_area AS agrupado "
				+ "FROM (SELECT d.id_documento, d.titulo_principal, d.editorial FROM documento AS d) AS doc "
				+ "NATURAL JOIN "
				+ "(SELECT y.id_documento, y.nombre AS nombre_area FROM "
				+ "(pertenece_documento_area_conocimiento NATURAL JOIN "
				+ "(SELECT a.id_area, a.nombre FROM area_conocimiento AS a) AS t) AS y)AS area "
				+ "ORDER BY area.nombre_area";

		return procesarDatosDocumento(consultaSql);
	}

	public TableDataSource consultaDocumentosAgrupadosTipo() {
		String consultaSql = "SELECT doc.id_documento, doc.titulo_principal, doc.editorial, doc.tipo_nombre AS agrupado "
				+ "FROM documento AS doc ORDER BY doc.tipo_nombre";

		return procesarDatosDocumento(consultaSql);
	}

	public TableDataSource consultaDocumentosAgrupadosFormato() {
		String consultaSql = "SELECT doc.id_documento, doc.titulo_principal, doc.editorial, doc.formato AS agrupado "
				+ "FROM documento AS doc ORDER BY doc.formato";

		return procesarDatosDocumento(consultaSql);
	}

	public TableDataSource consultaDocumentosAgrupadosAutor() {
		String consultaSql = "SELECT doc.id_documento, doc.titulo_principal, doc.editorial, autor.nombre_autor AS agrupado "
				+ "FROM (SELECT d.id_documento, d.titulo_principal, d.editorial FROM documento AS d) AS doc "
				+ "NATURAL JOIN "
				+ "(SELECT x.id_documento, x.nombre AS nombre_autor FROM (escribe_autor_documento "
				+ "NATURAL JOIN (SELECT a.id_autor, a.nombre FROM autor AS a) AS s) AS x) AS autor "
				+ "ORDER BY autor.nombre_autor";

		return procesarDatosDocumento2(consultaSql);
	}

	private TableDataSource procesarDatosDocumento(String consultaSql) {
		TableDataSource data = new TableDataSource();

		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();
			ResultSet resultado = sentencia.executeQuery(consultaSql);
			ResultSetMetaData metaData = resultado.getMetaData();

			for (int i = 1; i < metaData.getColumnCount(); i++) {
				data.addColumn(metaData.getColumnName(i + 1));
			}
			data.addColumn("opcion");

			while (resultado.next()) {
				Vector<Object> row = new Vector<Object>(0, 1);

				String columnOne = resultado.getString(1);
				row.add(resultado.getString(2));
				row.add(resultado.getString(3));
				row.add(resultado.getString(4));
				row.add(obtenerAutoresDocumento(columnOne));

				data.addRow(row);
			}
			fachada.cerrarConexion(conn);
			conn = null;
			fachada = null;
			sentencia = null;
			resultado = null;
			metaData = null;
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}

		// System.out.println(data);
		return data;
	}

	/*
	 * NOTA: si pra el reporte de documentos agrupados por autor se decide no
	 * agregar nada mas, quitar este metodo
	 */
	private TableDataSource procesarDatosDocumento2(String consultaSql) {
		TableDataSource data = new TableDataSource();

		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();
			ResultSet resultado = sentencia.executeQuery(consultaSql);
			ResultSetMetaData metaData = resultado.getMetaData();

			for (int i = 1; i < metaData.getColumnCount(); i++) {
				data.addColumn(metaData.getColumnName(i + 1));
			}
			data.addColumn("opcion");

			while (resultado.next()) {
				Vector<Object> row = new Vector<Object>(0, 1);

				// String columnOne = resultado.getString(1);
				row.add(resultado.getString(2));
				row.add(resultado.getString(3));
				row.add(resultado.getString(4));
				// row.add(obtenerAreasDocumento(columnOne));
				row.add("");

				data.addRow(row);
			}
			fachada.cerrarConexion(conn);
			conn = null;
			fachada = null;
			sentencia = null;
			resultado = null;
			metaData = null;
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}

		// System.out.println(data);
		return data;
	}

	public TableDataSource consultaDocumentosAgrupadosFormatoTotales() {
		String consultaSql = "SELECT d.formato AS agrupado, count(d.formato) AS cantidad "
				+ "FROM documento as d "
				+ "GROUP BY agrupado "
				+ "ORDER BY agrupado";

		return procesarDatosDocumentoTotales(consultaSql);
	}

	public TableDataSource consultaDocumentosAgrupadosTipoTotales() {
		String consultaSql = "SELECT d.tipo_nombre AS agrupado, count(d.tipo_nombre) AS cantidad "
				+ "FROM documento AS d "
				+ "GROUP BY agrupado "
				+ "ORDER BY agrupado";

		return procesarDatosDocumentoTotales(consultaSql);
	}

	public TableDataSource consultaDocumentosAgrupadosAreaTotales() {
		String consultaSql = "SELECT area.nombre_area AS agrupado, count(area.nombre_area) AS cantidad "
				+ "FROM (SELECT d.id_documento FROM documento AS d) AS doc "
				+ "NATURAL JOIN (SELECT y.id_documento, y.nombre AS nombre_area "
				+ "FROM (pertenece_documento_area_conocimiento "
				+ "NATURAL JOIN (SELECT a.id_area, a.nombre "
				+ "FROM area_conocimiento AS a) AS t) AS y)AS area "
				+ "GROUP BY agrupado " + "ORDER BY agrupado";

		return procesarDatosDocumentoTotales(consultaSql);
	}

	public TableDataSource consultaDocumentosAgrupadosAutorTotales() {
		String consultaSql = "SELECT autor.nombre_autor AS agrupado, count(autor.nombre_autor) AS cantidad "
				+ "FROM (SELECT d.id_documento FROM documento AS d) AS doc "
				+ "NATURAL JOIN (SELECT x.id_documento, x.nombre AS nombre_autor "
				+ "FROM (escribe_autor_documento "
				+ "NATURAL JOIN (SELECT a.id_autor, a.nombre FROM autor AS a) AS s) AS x) AS autor "
				+ "GROUP BY agrupado " + "ORDER BY agrupado";

		return procesarDatosDocumentoTotales(consultaSql);
	}

	private TableDataSource procesarDatosDocumentoTotales(String consultaSql) {
		TableDataSource data = new TableDataSource();

		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();
			ResultSet resultado = sentencia.executeQuery(consultaSql);
			ResultSetMetaData metaData = resultado.getMetaData();

			for (int i = 0; i < metaData.getColumnCount(); i++) {
				data.addColumn(metaData.getColumnName(i + 1));
			}

			while (resultado.next()) {
				Vector<Object> row = new Vector<Object>(0, 1);

				row.add(resultado.getString(1));
				row.add(resultado.getInt(2));

				data.addRow(row);
			}
			fachada.cerrarConexion(conn);
			conn = null;
			fachada = null;
			sentencia = null;
			resultado = null;
			metaData = null;
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}

		// System.out.println(data);
		return data;
	}

	public TableDataSource consultaDocumentosDescargadosFecha() {
		return null;
	}

	public TableDataSource consultaDocumentosDescargadosFecha(String fechaI,
			String fechaF) {
		return null;
	}

	public TableDataSource consultaDocumentosDescargadosArea() {
		return null;
	}

	public TableDataSource consultaDocumentosDescargadosUsuario() {
		return null;
	}

	/*
	 * ********************************** metodos de utilidad
	 * *************************
	 */

	/* metodo usado para obtener los autores de un documento en forma de string */
	private String obtenerAutoresDocumento(String id_documento) {
		String consultaSql = "SELECT s.nombre AS nombre_autor FROM "
				+ "(SELECT e.id_autor FROM escribe_autor_documento AS e "
				+ "WHERE e.id_documento = "
				+ id_documento
				+ ") AS t "
				+ "NATURAL JOIN (SELECT a.id_autor, a.nombre FROM autor AS a) AS s";

		String autores = "";

		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();
			ResultSet resultado = sentencia.executeQuery(consultaSql);

			while (resultado.next()) {
				autores += resultado.getString(1) + ", ";
			}
			fachada.cerrarConexion(conn);
			conn = null;
			// fachada = null;
			sentencia = null;
			resultado = null;
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return autores;
	}

	/* metodo para obtener las areas de un documento en forma de string */
	private String obtenerAreasDocumento(String id_documento) {
		String consultaSql = "SELECT a.nombre FROM area_conocimiento AS a "
				+ "NATURAL JOIN pertenece_documento_area_conocimiento AS p "
				+ "WHERE p.id_documento = " + id_documento;

		String areas = "";

		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();
			ResultSet resultado = sentencia.executeQuery(consultaSql);

			while (resultado.next()) {
				areas += resultado.getString(1) + ", ";
			}
			fachada.cerrarConexion(conn);
			conn = null;
			// fachada = null;
			sentencia = null;
			resultado = null;
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return areas;
	}

	public Vector<String> obtenerNombreTablas() {
		String consultaSql = "select table_name from information_schema.tables where table_schema = 'public'";

		Vector<String> tablas = new Vector<String>();

		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();
			ResultSet resultado = sentencia.executeQuery(consultaSql);

			int primerElemento = 1;
			while (resultado.next()) {
				tablas.add(resultado.getString(primerElemento));
			}

			fachada.cerrarConexion(conn);
			conn = null;
			// fachada = null;
			sentencia = null;
			resultado = null;
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}

		return tablas;
	}

	public Vector<String> obtenerAtributosTabla(String nombreTabla) {
		String consultaSql = "SELECT column_name FROM information_schema.columns WHERE table_name = '"
				+ nombreTabla + "' ;";

		Vector<String> atributos = new Vector<String>();

		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();
			ResultSet resultado = sentencia.executeQuery(consultaSql);

			int primerElemento = 1;
			while (resultado.next()) {
				atributos.add(resultado.getString(primerElemento));
			}

			fachada.cerrarConexion(conn);
			conn = null;
			// fachada = null;
			sentencia = null;
			resultado = null;
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}

		return atributos;
	}
	
	public JTable consultaGenerica(String consultaSql) {

		
		

		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();
			ResultSet resultado = sentencia.executeQuery(consultaSql);

			crearTabla(resultado);
			
			fachada.cerrarConexion(conn);
			conn = null;
			// fachada = null;
			sentencia = null;
			resultado = null;
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}

		return tabla;
	}

	private void configurarTabla() {

		modelo = new DefaultTableModel();
		tabla = new JTable(modelo);
		

	}

	private void crearTabla(ResultSet rs) {
		try {

			ResultSetMetaData rsmd = rs.getMetaData();

			int columnas = rsmd.getColumnCount();

			// Se crea un array de etiquetas para rellenar
			Vector<String> etiquetas = new Vector<String>();

			// Se obtiene cada una de las etiquetas para cada columna
			for (int i = 0; i < columnas; i++) {
				// Nuevamente, para ResultSetMetaData la primera columna es la
				// 1.
				etiquetas.add(rsmd.getColumnName(i + 1));
				//System.out.println(etiquetas.get(i));
			}
		
			configurarTabla();
			

			modelo.setColumnIdentifiers(etiquetas);
			
			String[] fila = new String[columnas];

			for (int i = 0; i < columnas; i++) {
				// Nuevamente, para ResultSetMetaData la primera columna es la
				// 1.
				String nombreColumna = rsmd.getColumnName(i + 1);
				fila[i] = nombreColumna;
			}
			
			modelo.addRow(fila);

			while (rs.next()) {

				// Se crea un array que será una de las filas de la tabla.

				// Se rellena cada posición del array con una de las columnas de
				// la tabla en base de datos.
				for (int i = 0; i < columnas; i++) {
					fila[i] = rs.getString(i + 1); // El primer indice en rs es
													// el 1, no el cero, por eso
													// se suma 1.
					//System.out.println(fila[i]);
				}
				// Se añade al modelo la fila completa.

				modelo.addRow(fila);

			}
			// se suma 1.
			System.out.println(modelo.getRowCount());
			tabla.setRowHeight(20);

		} catch (SQLException e) {

			
		}
	}
	
	public void setAnchoColumnas(){        
	 

		
		
		for(int i=0; i< tabla.getColumnCount(); i++){
	
			
			TableColumn columna = tabla.getColumn(tabla.getColumnName(i));
			//System.out.println(columna.getIdentifier());
			columna.setPreferredWidth(200);
		}
		
	}  

	public static void main(String args[]) {

		DaoReportes daoReportes = new DaoReportes();
		// daoReportes.consultaUsuarioBasica("vinculo_univalle", "=",
		// "Estudiante de pregrado");
		// daoReportes.consultaDocumentoBasica("titulo_principal", "=",
		// "data base");
		// daoReportes.consultaUsuarioEntreFechas("fecha_registro",
		// "2011-05-20","2011-05-30" );
		// daoReportes.consultaDocumentoEntreFechas("fecha_catalogacion",
		// "2010-01-01", "2011-05-20");
		// System.out.println(daoReportes.consultaUsuariosAgrupados("genero"));
		// System.out.println(daoReportes.consultaAreaAgrupados());

		// System.out.println(daoReportes.consultaUsuariosOrdenados("nivel_escolaridad"));
		// System.out.println(daoReportes.consultaUsuariosOrdenadosTotales("genero"));
		
		JFrame a = new JFrame();
		JPanel panel =  new JPanel();
		panel.add(daoReportes.consultaGenerica("select * from usuario where login ='monki'"));
		JScrollPane asd = new JScrollPane(panel);
		daoReportes.setAnchoColumnas();
		a.add(asd);
	
		a.setVisible(true);
		a.setSize(400,400);
		a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
	}

}
