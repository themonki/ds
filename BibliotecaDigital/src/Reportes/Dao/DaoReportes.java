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
		
		return procesarDatos(consultaSql, atributoUsuario);
	}
	
	public TableDataSource consultaUsuariosAgrupados(String atributoUsuario,String cualFecha, String fechaInicio, String FechaFin)
	{
		String consultaSql = "SELECT u." + atributoUsuario + 
		" AS agrupado, u.login, u.nombre1, u.apellido1, u.email, " +
		"u.vinculo_univalle, u.tipo, u.fecha_nacimiento, u.fecha_registro " +
		 "FROM usuario AS u " +
		"WHERE u." + cualFecha + " BETWEEN '" + fechaInicio + "' AND '" + FechaFin + "' " +
		"ORDER BY agrupado";
		
		return procesarDatos(consultaSql, atributoUsuario);
	}
	
	private TableDataSource procesarDatos(String consultaSql, String atributoUsuario)
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
		
		return procesarDatosTotales(consultaSql, atributoUsuario);
	}
	
	public TableDataSource consultaUsuariosAgrupadosTotales(String atributoUsuario,String cualFecha, String fechaInicio, String FechaFin)
	{
		String consultaSql = "SELECT u." + atributoUsuario + 
		" AS agrupado, count(" + atributoUsuario + ") AS cuantos " +
		"FROM usuario AS u " +
		"WHERE u." + cualFecha + " BETWEEN '" + fechaInicio + "' AND '" + FechaFin + "' " +
		"GROUP BY agrupado " +
		"ORDER BY agrupado";
		
		return procesarDatosTotales(consultaSql, atributoUsuario);
	}
	
	private TableDataSource procesarDatosTotales(String consultaSql,String atributoUsuario)
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
		
		return procesarDatos(consultaSql);
	}
	
	public TableDataSource consultarAreasConocimientoAgrupadasTotales()
	{
		String consultaSql = "SELECT B.nombre AS Areas_Padre, count(B.nombre) AS Cantidad " +
				"FROM area_conocimiento AS A JOIN area_conocimiento AS B " +
				"ON A.area_padre = B.id_area GROUP BY Areas_Padre ORDER BY Areas_Padre";
		return procesarDatos(consultaSql);
	}
	
	private TableDataSource procesarDatos(String consultaSql)
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
