package Reportes.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import Utilidades.FachadaBD;
import Utilidades.TableDataSource;



public class DaoReportes{
	
FachadaBD fachada;
	
	public  DaoReportes()
	{	
		fachada = new FachadaBD();
	}
	
		
	/*
	
	public void consultaDocumentoEntreFechas(String atributo, String fechaInicial, String fechaFinal)
	{
		
		String consultaSql;
		
	
		
		consultaSql = "SELECT d.id_documento, d.titulo_principal, d.fecha_publicacion , d.fecha_catalogacion, d."+atributo+" "+
		"FROM documento AS d " +
		"WHERE "+atributo+" BETWEEN '"+fechaInicial+"' AND '"+fechaFinal+"' ;";
		
	
		System.out.println(consultaSql);
		ResultSet resultado;
	
		try {
		Connection conn = fachada.conectar();
		Statement sentencia = conn.createStatement();			
		resultado = sentencia.executeQuery(consultaSql);
		while (resultado.next())
		{
			
			
			System.out.print(resultado.getString("id_documento")+" ");
			System.out.print(resultado.getString("titulo_principal")+" ");			
			System.out.print(resultado.getString("fecha_publicacion")+" ");
			System.out.print(resultado.getString("fecha_Catalogacion")+" ");
			DaoConsulta daoConsulta = new DaoConsulta();
			System.out.print(daoConsulta.consultarAutoresDocumento(resultado.getString("id_documento")));
			
			
			System.out.println();
			
		}
		conn.close();
		} catch (SQLException e) {			
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);					
		}
	

	}
	
	
	public void consultaDocumentoBasica(String atributo, String condicion, String especificacion)
	{
		
		String consultaSql;
		
	
		
		consultaSql = "SELECT d.id_documento, d.titulo_principal, d.fecha_publicacion , d.fecha_catalogacion, d."+atributo+" "+
		"FROM documento AS d " +
		"WHERE "+atributo+" "+condicion+" '"+especificacion+"' ;";
		
	
		System.out.println(consultaSql);
		ResultSet resultado;
	
		try {
		Connection conn = fachada.conectar();
		Statement sentencia = conn.createStatement();			
		resultado = sentencia.executeQuery(consultaSql);
		while (resultado.next())
		{
			
			
			System.out.print(resultado.getString("id_documento")+" ");
			System.out.print(resultado.getString("titulo_principal")+" ");			
			System.out.print(resultado.getString("fecha_publicacion")+" ");
			System.out.print(resultado.getString("fecha_Catalogacion")+" ");
			DaoConsulta daoConsulta = new DaoConsulta();
			System.out.print(daoConsulta.consultarAutoresDocumento(resultado.getString("id_documento")));
			
			
			System.out.println();
			
		}
		conn.close();
		} catch (SQLException e) {			
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);					
		}
	

	}
	 */
	
	public TableDataSource consultaUsuariosAgrupados(String atributoUsuario)
	{
		String consultaSql = "SELECT u." + atributoUsuario + 
		" AS agrupado, u.login, u.nombre1, u.apellido1, u.email, " +
		"u.vinculo_univalle, u.tipo, u.fecha_nacimiento, u.fecha_registro " +
		"FROM usuario AS u ORDER BY agrupado";
		
		return procesarDatosUsuario(consultaSql, atributoUsuario);
	}
	
	public TableDataSource consultaUsuariosAgrupados(String atributoUsuario,String cualFecha, String fechaInicio, String FechaFin)
	{
		String consultaSql = "SELECT u." + atributoUsuario + 
		" AS agrupado, u.login, u.nombre1, u.apellido1, u.email, " +
		"u.vinculo_univalle, u.tipo, u.fecha_nacimiento, u.fecha_registro " +
		 "FROM usuario AS u " +
		"WHERE u." + cualFecha + " BETWEEN '" + fechaInicio + "' AND '" + FechaFin + "' " +
		"ORDER BY agrupado";
		
		return procesarDatosUsuario(consultaSql, atributoUsuario);
	}
	
	private TableDataSource procesarDatosUsuario(String consultaSql, String atributoUsuario)
	{
		TableDataSource data = new TableDataSource();
		
		try 
		{
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();			
			ResultSet resultado = sentencia.executeQuery(consultaSql);
			ResultSetMetaData metaData = resultado.getMetaData();
			
			for(int i=0; i<metaData.getColumnCount(); i++)
			{
				data.addColumn(metaData.getColumnName(i+1));
				//System.out.println(metaData.getColumnTypeName(i+1));
			}
		
		while (resultado.next())
		{
			Vector<Object> row = new Vector<Object>(0,1);
			
			String columnOne = resultado.getString(1);
			if(atributoUsuario.equals("genero"))
			{
				if(columnOne.equals("M"))
				{
					row.add("Masculino");
				
				}else
				{
					row.add("Femenino");
				}					
			}else if(atributoUsuario.equals("tipo"))
			{
				if(columnOne.equals("3"))
				{
					row.add("Normal");
				
				}else if(columnOne.equals("2"))
				{
					row.add("Catalogador");
					
				}else if(columnOne.equals("1"))
				{
					row.add("Administrador");
				
				}else
				{
					row.add("Anónimo");
				}
			}else
			{
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
	
	public TableDataSource consultaUsuariosAgrupadosTotales(String atributoUsuario)
	{
		String consultaSql = "SELECT u." + atributoUsuario + 
		" AS agrupado, count(" + atributoUsuario + ") As cuantos " +
		"FROM usuario AS u " +
		"GROUP BY agrupado " +
		"ORDER BY agrupado";
		
		return procesarDatosUsuarioTotales(consultaSql, atributoUsuario);
	}
	
	public TableDataSource consultaUsuariosAgrupadosTotales(String atributoUsuario,String cualFecha, String fechaInicio, String FechaFin)
	{
		String consultaSql = "SELECT u." + atributoUsuario + 
		" AS agrupado, count(" + atributoUsuario + ") AS cuantos " +
		"FROM usuario AS u " +
		"WHERE u." + cualFecha + " BETWEEN '" + fechaInicio + "' AND '" + FechaFin + "' " +
		"GROUP BY agrupado " +
		"ORDER BY agrupado";
		
		return procesarDatosUsuarioTotales(consultaSql, atributoUsuario);
	}
	
	private TableDataSource procesarDatosUsuarioTotales(String consultaSql,String atributoUsuario)
	{
		TableDataSource data = new TableDataSource();
		
		try 
		{
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();			
			ResultSet resultado = sentencia.executeQuery(consultaSql);
			ResultSetMetaData metaData = resultado.getMetaData();
			
			for(int i=0; i<metaData.getColumnCount(); i++)
			{
				data.addColumn(metaData.getColumnName(i+1));
				//System.out.println(metaData.getColumnTypeName(i+1));
			}
		
		while (resultado.next())
		{
			Vector<Object> row = new Vector<Object>(0,1);
			
			String columnOne = resultado.getString(1);
			if(atributoUsuario.equals("genero"))
			{
				if(columnOne.equals("M"))
				{
					row.add("Masculino");
				
				}else
				{
					row.add("Femenino");
				}					
			}else if(atributoUsuario.equals("tipo"))
			{
				if(columnOne.equals("3"))
				{
					row.add("Normal");
				
				}else if(columnOne.equals("2"))
				{
					row.add("Catalogador");
					
				}else if(columnOne.equals("1"))
				{
					row.add("Administrador");
				
				}else
				{
					row.add("Anónimo");
				}
			}else
			{
				row.add(columnOne);
			}
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
	
	public TableDataSource consultarAreasConocimientoAgrupadas()
	{
		String consultaSql = "SELECT A.nombre, B.nombre AS nombre_Area_Padre " +
				"FROM area_conocimiento AS A JOIN area_conocimiento AS B " +
				"ON A.area_padre = B.id_area ORDER BY nombre_Area_Padre";
		
		return procesarDatosAreas(consultaSql);
	}
	
	public TableDataSource consultarAreasConocimientoAgrupadasTotales()
	{
		String consultaSql = "SELECT B.nombre AS Areas_Padre, count(B.nombre) AS Cantidad " +
				"FROM area_conocimiento AS A JOIN area_conocimiento AS B " +
				"ON A.area_padre = B.id_area GROUP BY Areas_Padre ORDER BY Areas_Padre";
		return procesarDatosAreas(consultaSql);
	}
	
	private TableDataSource procesarDatosAreas(String consultaSql)
	{
		TableDataSource data = new TableDataSource();
		
		try 
		{
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();			
			ResultSet resultado = sentencia.executeQuery(consultaSql);
			ResultSetMetaData metaData = resultado.getMetaData();
			
			for(int i=0; i<metaData.getColumnCount(); i++)
			{
				data.addColumn(metaData.getColumnName(i+1));
				//System.out.println(metaData.getColumnTypeName(i+1));
			}
		
		while (resultado.next())
		{
			Vector<Object> row = new Vector<Object>(0,1);
			
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
	
	public TableDataSource consultaDocumentosAgrupadosArea()
	{
		String consultaSql = "SELECT doc.id_documento, doc.titulo_principal, doc.editorial, area.nombre_area " +
				"FROM (SELECT d.id_documento, d.titulo_principal, d.editorial FROM documento AS d) AS doc " +
				"NATURAL JOIN " +
				"(SELECT y.id_documento, y.nombre AS nombre_area FROM " +
				"(pertenece_documento_area_conocimiento NATURAL JOIN " +
				"(SELECT a.id_area, a.nombre FROM area_conocimiento AS a) AS t) AS y)AS area " +
				"ORDER BY area.nombre_area";
		
		return procesarDatosDocumento(consultaSql);
	}
	
	public TableDataSource consultaDocumentosAgrupadosTipo()
	{
		String consultaSql = "SELECT doc.id_documento, doc.titulo_principal, doc.editorial, doc.tipo_nombre " +
				"FROM documento AS doc ORDER BY doc.tipo_nombre";

		return procesarDatosDocumento(consultaSql);
	}
	
	public TableDataSource consultaDocumentosAgrupadosFormato()
	{
		String consultaSql = "SELECT doc.id_documento, doc.titulo_principal, doc.editorial, doc.formato " +
		"FROM documento AS doc ORDER BY doc.formato";

		return procesarDatosDocumento(consultaSql);
	}
	
	public TableDataSource consultaDocumentosAgrupadosAutor()
	{
		String consultaSql = "SELECT doc.titulo_principal, doc.editorial, autor.nombre_autor " +
				"FROM (SELECT d.id_documento, d.titulo_principal, d.editorial FROM documento AS d) AS doc " +
				"NATURAL JOIN " +
				"(SELECT x.id_documento, x.nombre AS nombre_autor FROM (escribe_autor_documento " +
				"NATURAL JOIN (SELECT a.id_autor, a.nombre FROM autor AS a) AS s) AS x) AS autor " +
				"ORDER BY autor.nombre_autor";
		
		return procesarDatosDocumento2(consultaSql);
	}
	
	private TableDataSource procesarDatosDocumento(String consultaSql)
	{
		TableDataSource data = new TableDataSource();
		
		try 
		{
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();			
			ResultSet resultado = sentencia.executeQuery(consultaSql);
			ResultSetMetaData metaData = resultado.getMetaData();
		
		for(int i=1; i<metaData.getColumnCount(); i++)
		{
			data.addColumn(metaData.getColumnName(i+1));
		}
		data.addColumn("nombre_autor");
		
		while (resultado.next())
		{
			Vector<Object> row = new Vector<Object>(0,1);
			
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
		
		//System.out.println(data);
		return data;
	}
	
	private TableDataSource procesarDatosDocumento2(String consultaSql)
	{
		TableDataSource data = new TableDataSource();
		
		try 
		{
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();			
			ResultSet resultado = sentencia.executeQuery(consultaSql);
			ResultSetMetaData metaData = resultado.getMetaData();
		
		for(int i=1; i<metaData.getColumnCount(); i++)
		{
			data.addColumn(metaData.getColumnName(i+1));
		}
		data.addColumn("areas");
		
		while (resultado.next())
		{
			Vector<Object> row = new Vector<Object>(0,1);
			
			String columnOne = resultado.getString(1);
			row.add(resultado.getString(2));
			row.add(resultado.getString(3));
			row.add(resultado.getString(4));
			row.add(obtenerAreasDocumento(columnOne));
			
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
		
		//System.out.println(data);
		return data;
	}
	
	/*metodo usado para obtener los autores de un documento en forma de string*/
	private String obtenerAutoresDocumento(String id_documento)
	{
		String consultaSql = "SELECT s.nombre AS nombre_autor FROM " +
				"(SELECT e.id_autor FROM escribe_autor_documento AS e " +
				"WHERE e.id_documento = " + id_documento + ") AS t " +
				"NATURAL JOIN (SELECT a.id_autor, a.nombre FROM autor AS a) AS s";

		String autores = "";
		
		try 
		{
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();			
			ResultSet resultado = sentencia.executeQuery(consultaSql);
				
		while (resultado.next())
		{	
			autores += resultado.getString(1) + ", ";		
		}
		fachada.cerrarConexion(conn);
		conn = null;
		//fachada = null;
		sentencia = null;
		resultado = null;
		} catch (SQLException e) {			
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);					
		}
		return autores;
	}
	
	public TableDataSource consultaDocumentosDescargadosFecha()
	{
		return null;
	}
	
	public TableDataSource consultaDocumentosDescargadosFecha(String fechaI, String fechaF)
	{
		return null;
	}
	
	public TableDataSource consultaDocumentosDescargadosArea()
	{
		return null;
	}
	
	public TableDataSource consultaDocumentosDescargadosUsuario()
	{
		return null;
	}
	
	
	/*metodo para obtener las areas de un documento en forma de string*/
	private String obtenerAreasDocumento(String id_documento)
	{
		return null;
	}
	
	public static void main(String args[])
	{
		
		
		//DaoReportes daoReportes = new DaoReportes();
		//daoReportes.consultaUsuarioBasica("vinculo_univalle", "=", "Estudiante de pregrado");
		//daoReportes.consultaDocumentoBasica("titulo_principal", "=", "data base");
		//daoReportes.consultaUsuarioEntreFechas("fecha_registro", "2011-05-20","2011-05-30" );
		//daoReportes.consultaDocumentoEntreFechas("fecha_catalogacion", "2010-01-01", "2011-05-20");
	//System.out.println(daoReportes.consultaUsuariosAgrupados("genero"));
		//System.out.println(daoReportes.consultaAreaAgrupados());
		
		//System.out.println(daoReportes.consultaUsuariosOrdenados("nivel_escolaridad"));
		//System.out.println(daoReportes.consultaUsuariosOrdenadosTotales("genero"));
		
	}
	

}
