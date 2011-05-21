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
		//******************************************
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
	
	public Vector<Consulta> consultaAvanzada(Vector<String> atributoDocumento, Vector<String> valorDocumento, String atributoPalabra, Vector<String> valorPalabra, String atributoAutor, Vector<String> valorAutor, String areaString)
	{
		Vector<Consulta>  consultas = new Vector<Consulta>();
		
		String consultaSql, consultaDocumentoSql,
		consultaPalabraSql, consultaAreaSql, consultaAutorSql;
		
		// para construir la consulta final
		consultaDocumentoSql = consultaDocumento(atributoDocumento, valorDocumento);
		consultaPalabraSql = consultaPalabra(atributoPalabra, valorPalabra);
		consultaAutorSql = consultaAutor(atributoAutor, valorAutor);

		consultaAreaSql = "SELECT * FROM " +
		"(SELECT d.id_documento, d.titulo_principal FROM documento AS d) AS f " +
		"NATURAL JOIN " + 
		"(SELECT p.id_documento FROM pertenece_documento_area_conocimiento AS p " +
		"NATURAL JOIN " +
		"(SELECT a.id_area FROM area_conocimiento AS a WHERE ";

		if(!areaString.equals(""))
		{
			consultaAreaSql += "a.nombre = '" + areaString + "'";
			consultaAreaSql += ") AS x) AS y";
		}else
		{
			consultaAreaSql = "";
		}
	
		System.out.println(consultaDocumentoSql);
		System.out.println(consultaPalabraSql);
		//System.out.println(consultaDocumentoTituloSql);
		System.out.println(consultaAreaSql);
		System.out.println(consultaAutorSql);
		
		consultaSql = "";
		
		if(!consultaAreaSql.equals(""))
		{
			if(!consultaPalabraSql.equals(""))
			{
				if(!consultaAutorSql.equals(""))
				{
					consultaSql += "((" + consultaAreaSql + ")" + " INTERSECT " +
					"(" + consultaPalabraSql + "))" + " INTERSECT " + 
					"(" + consultaAutorSql + ")";
				}else
				{
					consultaSql += "(" + consultaAreaSql + ")" + " INTERSECT " +
					"(" + consultaPalabraSql + ")";
				}
			
			}else if(!consultaAutorSql.equals(""))
			{
				consultaSql += "(" + consultaAreaSql + ")" + " INTERSECT " +
				"(" + consultaAutorSql + ")";
			}else
			{
				consultaSql += consultaAreaSql;
			}
		
		}else if(!consultaPalabraSql.equals(""))
		{
			if(!consultaAutorSql.equals(""))
			{
				consultaSql += "(" + consultaPalabraSql + ")" + " INTERSECT " +
				"(" + consultaAutorSql + ")";
			
			}else
			{
				consultaSql += consultaPalabraSql;
			}
		}else if(!consultaAutorSql.equals(""))
		{
			consultaSql += consultaAutorSql;
		}
		
		if(consultaSql.equals(""))
		{
			consultaSql = consultaDocumentoSql;
		}else
		{
			consultaSql = "SELECT * FROM (" + consultaSql + 
			" INTERSECT " + consultaDocumentoSql + ") AS x";
		}
		
		//System.out.print(consultaSql);
		
		ResultSet resultado;		
		System.out.println("***************************\n\n");
		System.out.println(consultaSql);
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
	
	private String consultaDocumento(Vector<String> atributoDocumento, Vector<String> valorDocumento)
	{
		String consultaDocumentoSql, consultaDocumentoTituloSql, 
		consultaDocumentoFechaSql, consultaDocumentoFormatoSql, consultaDocumentoIdiomaSql;
		consultaDocumentoSql =  "SELECT documento.id_documento, documento.titulo_principal "+
		"FROM documento " +
		"WHERE ";
		consultaDocumentoTituloSql=	"" ;
		consultaDocumentoFechaSql=	"";
		consultaDocumentoFormatoSql=	"";
		consultaDocumentoIdiomaSql=	"";
		
		int sizeVector = atributoDocumento.size();
		
		for(int i=0; i<sizeVector; i++)
		{
			String at = atributoDocumento.elementAt(i);
			if(at.contains("titulo"))
			{
				boolean esOR = (i!= sizeVector-1) && 
				(atributoDocumento.elementAt(i+1).contains("titulo"));
				
				if(at.contains("sin"))
				{
					consultaDocumentoTituloSql += "documento.titulo_principal NOT LIKE '%" + //*******************************
					valorDocumento.elementAt(i) + "%' AND " +
					"documento.titulo_secundario LIKE '%" +
					valorDocumento.elementAt(i)+ "%'" ;
					if(esOR)
					{
						consultaDocumentoTituloSql += " OR ";
					}
				} else if(at.contains("algunas"))
				{System.out.println(at);
					consultaDocumentoTituloSql += "documento.titulo_principal LIKE '%" +
					valorDocumento.elementAt(i) + "%' OR " +
					"documento.titulo_secundario LIKE '%" +
					valorDocumento.elementAt(i)+ "%'" ;
					if(esOR)
					{
						consultaDocumentoTituloSql += " OR ";
					}
				}else // es exacto
				{
					consultaDocumentoTituloSql += "documento.titulo_principal LIKE '%" +
					valorDocumento.elementAt(i) + "%' OR " +
					"documento.titulo_secundario LIKE '%" +
					valorDocumento.elementAt(i) + "%'";
				}
			}else if (at.contains("idioma"))
			{
				consultaDocumentoIdiomaSql+= "documento.idioma = '"+
				valorDocumento.elementAt(i) + "'";					
			}else if (at.contains("formato"))
			{
				consultaDocumentoFormatoSql+= "documento.formato = '"+
				valorDocumento.elementAt(i) + "'";
			}else //contiene fecha
			{
				if(at.contains("antes"))
				{
					consultaDocumentoFechaSql += "documento.fecha_publicacion < '"+
					valorDocumento.elementAt(i)+"'";
					if((i != sizeVector-1) && atributoDocumento.elementAt(i+1).contains("despues")){
						consultaDocumentoFechaSql += " AND "; 
					}
				}else { // es despues
					{
						consultaDocumentoFechaSql += "documento.fecha_publicacion > '"+
						valorDocumento.elementAt(i)+"'";
					}
				}
			}//fin documento
		}
		
		//construir consultaDocumentoSql
		boolean tituloSql = consultaDocumentoTituloSql.equals("");
		boolean fechaSql = consultaDocumentoFechaSql.equals("");
		boolean formatoSql = consultaDocumentoFormatoSql.equals("");
		boolean idiomaSql = consultaDocumentoIdiomaSql.equals("");
		
		boolean primera= true;
		
		String temptitulo="", tempfecha="", tempformato="", tempidioma="";
		if(tituloSql && fechaSql && formatoSql && idiomaSql)
		{
			consultaDocumentoSql = "SELECT documento.id_documento, documento.titulo_principal "+
			"FROM documento";
		}else
		{
			if(!tituloSql)
			{
				temptitulo = "(" + consultaDocumentoTituloSql + ")";
				primera= false;
			}
			if(!fechaSql)
			{
				if(primera)
				{
					tempfecha = "(" + consultaDocumentoFechaSql + ")";
					primera=false;
				}else
				{
					tempfecha = " AND " + "(" + consultaDocumentoFechaSql + ")";
				}
			}
			if(!formatoSql)
			{
				if(primera)
				{
					tempformato = "(" + consultaDocumentoFormatoSql + ")";
					primera=false;
				}else
				{
					tempformato = " AND " + "(" + consultaDocumentoFormatoSql + ")";
				}
			}
			if(!idiomaSql)
			{
				if(primera)
				{
					tempidioma = "(" + consultaDocumentoIdiomaSql + ")";
				}else
				{
					tempidioma = " AND " + "(" + consultaDocumentoIdiomaSql + ")";
				}
			}
			
			consultaDocumentoSql += temptitulo + tempfecha + tempformato + tempidioma;
			
		}
		
		return consultaDocumentoSql;


	}
	
	private String consultaPalabra(String atributoPalabra, Vector<String> valorPalabra)
	{
		String	consultaPalabraSql, consultaPalabraTempSql;
		
		String consultaPalabraNula = "SELECT DISTINCT id_documento FROM tiene_documento_palabra_clave WHERE ";
		
		consultaPalabraTempSql = "SELECT DISTINCT id_documento FROM tiene_documento_palabra_clave WHERE ";
		consultaPalabraSql = "SELECT d.id_documento, d.titulo_principal "+
		"FROM (SELECT documento.id_documento, documento.titulo_principal FROM documento)as d NATURAL JOIN"; 
		
		int sizeVector = valorPalabra.size();
		
		if(atributoPalabra.equals("sin"))
		{
			for(int i=0; i< sizeVector;i++)
			{
				boolean esOR = i != sizeVector -1 ;
				consultaPalabraTempSql += "tiene_documento_palabra_clave.nombre LIKE '%" +
				valorPalabra.elementAt(i) + "%'";
				if(esOR)
				{
					consultaPalabraTempSql += " OR ";
				}
			}
		} else if (atributoPalabra.equals("algunas"))
		{
			for(int i=0; i< sizeVector;i++)
			{
				boolean esOR = i != sizeVector - 1;
				consultaPalabraTempSql += "tiene_documento_palabra_clave.nombre NOT LIKE '%" +
				valorPalabra.elementAt(i) + "%'";
				if(esOR)
				{
					consultaPalabraTempSql += " OR ";
				}
			}
		}else
		{
			consultaPalabraTempSql += "tiene_documento_palabra_clave.nombre = '" +
			valorPalabra.elementAt(0) + "'";
		}
		
		
		boolean nombreSql = consultaPalabraTempSql.equals(consultaPalabraNula);
		
		if(!nombreSql)
		{
			consultaPalabraSql += "(SELECT id_documento FROM documento EXCEPT "
				+consultaPalabraTempSql+") AS p";
		}else
		{
			consultaPalabraSql="";
		}
		
		return consultaPalabraSql;

	}
	
	private String consultaAutor(String atributoAutor, Vector<String> valorAutor)
	{
		String consultaAutorSql;
		
		consultaAutorSql = "SELECT * FROM " +
		"(SELECT d.id_documento, d.titulo_principal FROM documento AS d) AS x " +
		"NATURAL JOIN " + 
		"(SELECT e.id_documento FROM escribe_autor_documento AS e " + 
		"NATURAL JOIN " + 
		"(SELECT a.id_autor FROM autor AS a WHERE ";

		int sizeVector = valorAutor.size();

		if(atributoAutor.equals("sin"))
		{
			for(int i=0; i<sizeVector; i++)
			{
				boolean esOR = sizeVector!=i+1;

				consultaAutorSql += "a.nombre NOT LIKE '%" +
				valorAutor.elementAt(i) + "%' AND " +
				"a.apellido NOT LIKE '%" +
				valorAutor.elementAt(i)+ "%'" ;
				if(esOR)
				{
					consultaAutorSql += " AND ";
				}
			}

		} else if(atributoAutor.equals("algunas"))
		{
			for(int i=0; i<sizeVector; i++)
			{
				boolean esOR = sizeVector!=i+1;
				
				consultaAutorSql += "a.nombre LIKE '%" +
				valorAutor.elementAt(i) + "%' OR " +
				"a.apellido LIKE '%" +
				valorAutor.elementAt(i)+ "%'" ;
				if(esOR)
				{
					consultaAutorSql += " OR ";
				}
			}

		}else
		{//solo puede conicidir con el nombre o con el apellido
			consultaAutorSql += "a.nombre = '" +
			valorAutor.elementAt(0) + "' OR " +
			"a.apellido = '" +
			valorAutor.elementAt(0)+ "'" ;
		}

		if(!consultaAutorSql.equals("SELECT * FROM " +
				"(SELECT d.id_documento, d.titulo_principal FROM documento AS d) AS x " +
				"NATURAL JOIN " + 
				"(SELECT e.id_documento FROM escribe_autor_documento AS e " + 
				"NATURAL JOIN " + 
				"(SELECT a.id_autor FROM autor AS a WHERE "))
				{
					consultaAutorSql += ") AS c) AS y";
				}else
				{
					consultaAutorSql = "";
				}
		return consultaAutorSql;
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
