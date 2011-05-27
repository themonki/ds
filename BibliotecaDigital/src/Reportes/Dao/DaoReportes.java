package Reportes.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import Consultas.Dao.DaoConsulta;

import Utilidades.FachadaBD;
import Reportes.Logica.Reporte;



public class DaoReportes{
	
FachadaBD fachada;
	
	public  DaoReportes() {	
	
		fachada = new FachadaBD();
		
	}
	public void consultaUsuarioBasica(String atributo, String condicion, String especificacion)
	{
		
		String consultaSql;		
		
		consultaSql = "SELECT d.login, d.nombre1 , d.apellido1, d."+atributo+" "+
		"FROM usuario AS d " +
		"WHERE "+atributo+" "+condicion+" '"+especificacion+"' ;";
		
	
		System.out.println(consultaSql);
		ResultSet resultado;
	
		try {
		Connection conn = fachada.conectar();
		Statement sentencia = conn.createStatement();			
		resultado = sentencia.executeQuery(consultaSql);
		while (resultado.next())
		{
			
			
			System.out.print(resultado.getString("login") +" ");
			System.out.print(resultado.getString("nombre1")+" ");			
			System.out.print(resultado.getString("apellido1")+" ");
			System.out.print(resultado.getString(atributo)+" ");
			System.out.println();
			
		}
		conn.close();
		} catch (SQLException e) {			
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);					
		}
	

	}
	
	public void consultaUsuarioEntreFechas(String atributo, String fechaInicial, String fechaFinal)
	{
		
		String consultaSql;		
		
		consultaSql = "SELECT d.login, d.nombre1 , d.apellido1, d."+atributo+" "+
		"FROM usuario AS d " +
		"WHERE "+atributo+" BETWEEN '"+fechaInicial+"' AND '"+fechaFinal+"' ;";
		
	
		System.out.println(consultaSql);
		ResultSet resultado;
	
		try {
		Connection conn = fachada.conectar();
		Statement sentencia = conn.createStatement();			
		resultado = sentencia.executeQuery(consultaSql);
		while (resultado.next())
		{
			
			
			System.out.print(resultado.getString("login")+" ");
			System.out.print(resultado.getString("nombre1")+" ");			
			System.out.print(resultado.getString("apellido1")+" ");
			System.out.print(resultado.getString(atributo)+" ");
			
			System.out.println();
			
		}
		conn.close();
		} catch (SQLException e) {			
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);					
		}
	

	}
	
	
	
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
	
	public Vector<String> consultaAreaAgrupados()
	{
		
		String consultaSql;
		
	
		
		consultaSql = "SELECT B.nombre AS Areas_Padre,count(B.nombre) AS Cantidad from area_conocimiento AS A ,area_conocimiento AS B where A.area_padre = B.id_area GROUP BY Areas_Padre;";
		
	
		System.out.println(consultaSql);
		ResultSet resultado;
		Vector<String> areasAgrupadas = new Vector<String>();
		try {
		Connection conn = fachada.conectar();
		Statement sentencia = conn.createStatement();			
		resultado = sentencia.executeQuery(consultaSql);
		
		while (resultado.next())
		{
			
			
			areasAgrupadas.add(resultado.getString("Areas_Padre"));
			areasAgrupadas.add(resultado.getString("Cantidad"));			
		
		
			areasAgrupadas.add("SEPARADOR");			
			
			
		}
		conn.close();
		} catch (SQLException e) {			
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);					
		}
	

		return areasAgrupadas;
	}
	
	public Reporte consultaUsuariosOrdenadosTotales(String atributo)
	{
		
		String consultaSql;
		
	
		Vector<String> columnas = new Vector<String>();
		columnas.add(atributo);
		columnas.add("Cantidad");
		
		
		Vector<String> registros = new Vector<String>();
		Vector<String> orderBy = new Vector<String>();
		
		
		
		consultaSql = "SELECT "+atributo+", count("+atributo+") as Cantidad "+
		"FROM usuario AS d " +
		"GROUP BY "+atributo+";";
		
	
		System.out.println(consultaSql);
		ResultSet resultado;
		
		try {
		Connection conn = fachada.conectar();
		Statement sentencia = conn.createStatement();			
		resultado = sentencia.executeQuery(consultaSql);
		orderBy.add("Usuarios por "+ atributo);
	
		
		while (resultado.next())
		{			
			registros.add(resultado.getString(atributo)+"|"+resultado.getString("Cantidad"));
			
		}
		conn.close();
		} catch (SQLException e) {			
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);					
		}
		
		System.out.println(registros);
		System.out.println(columnas);
		System.out.println(orderBy);
		Reporte reporte = new Reporte(orderBy,columnas,registros,1,"");
	

		return reporte;
	}
	
	public Reporte consultaUsuariosOrdenados(String atributo)
	{
		
		String consultaSql;
		
	
		Vector<String> columnas = new Vector<String>();
		columnas.add("Login");
		columnas.add("Nombre");
		columnas.add("Apellido");
		columnas.add("Vinculo con Univalle");
		
		Vector<String> registros = new Vector<String>();
		Vector<String> orderBy = new Vector<String>();
		
		
		
		consultaSql = "SELECT login, nombre1 , apellido1,vinculo_univalle,"+atributo+" "+
		"FROM usuario  " +
		"ORDER BY "+atributo+";";
		
	
		System.out.println(consultaSql);
		ResultSet resultado;
		
		try {
		Connection conn = fachada.conectar();
		Statement sentencia = conn.createStatement();			
		resultado = sentencia.executeQuery(consultaSql);
		String atributoAnterior = "";
		
		while (resultado.next())
		{
			
			
			if(!atributoAnterior.equals(resultado.getString(atributo)))
			{
			
				orderBy.add(resultado.getString(atributo));
				registros.add("");
			}
			atributoAnterior = resultado.getString(atributo);
			registros.add(resultado.getString("login")+"|"+resultado.getString("nombre1")+"|"+resultado.getString("apellido1")+"|"+resultado.getString("vinculo_univalle"));
			
			
		
		
				
			
			
		}
		conn.close();
		} catch (SQLException e) {			
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);					
		}
		registros.remove(0);
		System.out.println(registros);
		System.out.println(columnas);
		System.out.println(orderBy);
		Reporte reporte = new Reporte(orderBy,columnas,registros,1,"");
	

		return reporte;
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
	public Vector<String> consultaUsuariosAgrupados(String atributo)
	{
		Reporte reporte = new Reporte();
		String consultaSql;
		
	
		
		consultaSql = "SELECT "+atributo+", count("+atributo+") as Cantidad "+
		"FROM usuario AS d " +
		"GROUP BY "+atributo+";";
		
	
		System.out.println(consultaSql);
		ResultSet resultado;
		Vector<String> usuarioAgrupado = new Vector<String>();
		try {
		Connection conn = fachada.conectar();
		Statement sentencia = conn.createStatement();			
		resultado = sentencia.executeQuery(consultaSql);
		
		while (resultado.next())
		{
			
			
			usuarioAgrupado.add(resultado.getString(atributo));
			usuarioAgrupado.add(resultado.getString("cantidad"));			
		
		
			usuarioAgrupado.add("SEPARADOR");			
			
			
		}
		conn.close();
		} catch (SQLException e) {			
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);					
		}
	

		return usuarioAgrupado;
	}
	
	
	
	
	
	
	
	
	public static void main(String args[])
	{
		
		
		DaoReportes daoReportes = new DaoReportes();
		//daoReportes.consultaUsuarioBasica("vinculo_univalle", "=", "Estudiante de pregrado");
		//daoReportes.consultaDocumentoBasica("titulo_principal", "=", "data base");
		//daoReportes.consultaUsuarioEntreFechas("fecha_registro", "2011-05-20","2011-05-30" );
		//daoReportes.consultaDocumentoEntreFechas("fecha_catalogacion", "2010-01-01", "2011-05-20");
	//System.out.println(daoReportes.consultaUsuariosAgrupados("genero"));
		//System.out.println(daoReportes.consultaAreaAgrupados());
		
		//System.out.println(daoReportes.consultaUsuariosOrdenados("nivel_escolaridad"));
		System.out.println(daoReportes.consultaUsuariosOrdenadosTotales("genero"));
		
	}
	

}
