package Consultas.Dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import Consultas.Logica.Consulta;
import Documento.Logica.Documento;
import Utilidades.FachadaBD;

public class DaoConsulta {
	
	FachadaBD fachada;
	
	public DaoConsulta(){
		fachada = new FachadaBD();
	}
	
	
	//metodo de consulta general
	public Vector<Consulta> consultaGeneral(Vector<String> parametro)
	{
		Vector<Consulta> consultas = new Vector<Consulta>();
		String consultaSql, consultaDocumentoSql, consultaPalabraSql, consultaAreaSql, consultaAutorSql;
		
		consultaDocumentoSql = "SELECT d.id_documento, d.titulo_principal "+
		"FROM Documento AS d " +
		"WHERE ";
		
		for(int i=0; i<parametro.size();i++)
		{
			consultaDocumentoSql += "d.descripcion LIKE '%" + parametro.elementAt(i) + "%' OR ";
		}
		//consultaDocumentoSql += "d.descripcion LIKE '%" + parametro.lastElement() + "%'";
		
		for(int i=0; i<parametro.size();i++)
		{
			consultaDocumentoSql += "d.titulo_principal LIKE '%" + parametro.elementAt(i) + "%' OR ";
		}
		//consultaDocumentoSql += "d.titulo_principal LIKE '%" + parametro.lastElement() + "%'";
	
		for(int i=0; i<parametro.size()-1;i++)
		{
			consultaDocumentoSql += "d.titulo_secundario LIKE '%" + parametro.elementAt(i) + "%' OR ";
		}
		consultaDocumentoSql += "d.titulo_secundario LIKE '%" + parametro.lastElement() + "%'";
		
		consultaPalabraSql = "SELECT * FROM " +
				"(SELECT d.id_documento, d.titulo_principal FROM Documento AS d) AS f " +
				"NATURAL JOIN " +
				"(SELECT t.id_documento FROM tiene_documento_palabra_clave AS t " +
				"NATURAL JOIN " +
				"(SELECT p.nombre FROM palabra_clave AS p WHERE " ;		
		
		for(int i=0; i<parametro.size();i++)
		{
			consultaPalabraSql += "p.nombre LIKE '%" + parametro.elementAt(i) + "%' OR ";
		}
		
		for(int i=0; i<parametro.size()-1;i++)
		{
			consultaPalabraSql += "p.descripcion LIKE '%" + parametro.elementAt(i) + "%' OR ";
		}
		consultaPalabraSql += "p.descripcion LIKE '%" + parametro.lastElement() + "%') AS c) AS e";
		
		consultaAreaSql = "SELECT * FROM " +
				"(SELECT d.id_documento, d.titulo_principal FROM documento AS d) AS f " +
				"NATURAL JOIN " + 
				"(SELECT p.id_documento FROM pertenece_documento_area_conocimiento AS p " +
				"NATURAL JOIN " +
				"(SELECT a.id_area FROM area_conocimiento AS a WHERE ";
		
		for(int i=0; i<parametro.size();i++)
		{
			consultaAreaSql += "a.nombre LIKE '%" + parametro.elementAt(i) + "%' OR ";
		}
		
		for(int i=0; i<parametro.size()-1;i++)
		{
			consultaAreaSql += "a.descripcion LIKE '%" + parametro.elementAt(i) + "%' OR ";
		}
		consultaAreaSql += "a.descripcion LIKE '%" + parametro.lastElement() + "%') AS c) AS e";
		
		consultaAutorSql = "SELECT * FROM " +
		"(SELECT d.id_documento, d.titulo_principal FROM documento AS d) AS x " +
		"NATURAL JOIN " + 
		"(SELECT e.id_documento FROM escribe_autor_documento AS e " + 
		"NATURAL JOIN " + 
		"(SELECT a.id_autor FROM autor AS a WHERE ";
		
		for(int i=0;i<parametro.size();i++)
		{
			consultaAutorSql += "a.nombre LIKE '%" + parametro.elementAt(i) + "%' OR ";
		}
		
		for(int i=0;i<parametro.size()-1;i++)
		{
			consultaAutorSql += "a.apellido LIKE '%" + parametro.elementAt(i) + "%' OR ";
		}
		consultaAutorSql += "a.apellido LIKE '%" + parametro.lastElement() + "%') AS c) AS f";

		//System.out.println(consultaDocumentoSql);
		//System.out.println(consultaPalabraSql);
		//System.out.println(consultaAreaSql);
		//System.out.println(consultaAutorSql);
		
		consultaSql = "SELECT * FROM " +
		"((" + consultaDocumentoSql + " UNION " + consultaPalabraSql + ") UNION " +
		"(" + consultaAreaSql + " UNION " + consultaAutorSql + ")) AS y";
		
		//System.out.println(consultaSql);
		
		ResultSet resultado;		

		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();			
			resultado = sentencia.executeQuery(consultaSql);
			while (resultado.next())
			{
				Consulta consulta = new Consulta();
				
				consulta.setIdDocumento(resultado.getString("id_documento"));
				consulta.setTituloDocuemto(resultado.getString("titulo_principal"));
				consulta.setNombresAutoresDocumento(consultarAutoresDocumento(resultado.getString("id_documento")));
				consultas.add(consulta);
			}
			conn.close();			
			} catch (SQLException e) {			
				System.out.println(e);
			} catch (Exception e) {
				System.out.println(e);					
			}
			System.out.println(consultas);
		return consultas;
	}
	
	Vector<String> consultarAutoresDocumento(String idDocumento)
	{
		Vector<String> autores = new Vector<String>();
		
		String consultaSql;
		consultaSql = "SELECT y.nombre FROM " +
				"(SELECT a.id_autor, a.nombre FROM autor AS a) AS y NATURAL JOIN " +
				"(SELECT d.id_autor FROM escribe_autor_documento AS d WHERE d.id_documento = " + idDocumento + ") AS x";
		
		ResultSet tabla;		

		try
		{
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();			
			tabla = sentencia.executeQuery(consultaSql);
		
			while (tabla.next())
			{
				autores.add(tabla.getString("nombre"));
			}
			conn.close();			
			} catch (SQLException e) {			
				System.out.println(e);
			} catch (Exception e) {
				System.out.println(e);					
			}
					
		return autores;
	}
	
	public Vector<Consulta> consultaAvanzada(Vector<String> atributos, Vector<String> valores)
	{
		Vector<Consulta>  consultas = new Vector<Consulta>();
		
		String consultaSql, consultaDocumentoSql,
		consultaDocumentoTituloSql, consultaDocumentoFechaSql, consultaDocumentoFormatoSql,
		consultaPalabraSql, consultaAreaSql, consultaAutorSql;
		
		//consultaDocumentoSql = 
		consultaDocumentoTituloSql=	"SELECT documento.id_documento, documento.titulo_principal " +
				"FROM documento " +
				"WHERE ";
		
		for(int i=0;i<atributos.size();i++)
		{
			String at = atributos.elementAt(i);
			
			if(at.contains("documento"))
			{
				if(at.contains("titulo"))
				{
					boolean esOR = (atributos.elementAt(i)!=atributos.lastElement()) && 
					(atributos.elementAt(i+1).contains("titulo"));
					if(at.contains("sin"))
					{
						consultaDocumentoTituloSql += "documento.titulo_principal NOT LIKE '%" +
						valores.elementAt(i) + "%' OR " +
						"documento.titulo_secundario NOT LIKE '%" +
						valores.elementAt(i)+ "%'" ;
						if(esOR)
						{
							consultaDocumentoTituloSql += " OR ";
						}
					} else if(at.contains("algunas"))
					{
						consultaDocumentoTituloSql += "documento.titulo_principal LIKE '%" +
						valores.elementAt(i) + "%' OR " +
						"documento.titulo_secundario LIKE '%" +
						valores.elementAt(i)+ "%'" ;
						if(esOR)
						{
							consultaDocumentoTituloSql += " OR ";
						}
					}else
					{
						consultaDocumentoTituloSql += "documento.titulo_principal = '" +
						valores.elementAt(i) + "' OR " +
						"documeno.titulo_secundario = '" +
						valores.elementAt(i) + "'";
					}
					
					
					
					

				}else if (true)
				{
				}
			}
		}
		
		System.out.println(consultaDocumentoTituloSql);
		return consultas;
		
	}
	
	public int guardarConsulta(String id_documento, String login, String fecha, String hora)
	{
		String sql_guardar;
		int numFilas;
		sql_guardar = "INSERT INTO consulta VALUES ('"+id_documento+"', " +
				"'"+login+"', " +
				"'"+fecha+"'," +
				"'"+hora+"');";

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
