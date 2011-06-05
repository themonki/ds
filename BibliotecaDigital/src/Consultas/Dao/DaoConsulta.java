package Consultas.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import Consultas.Logica.Consulta;
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
	
	public Vector<String> consultarAutoresDocumento(String idDocumento)
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
	
	public Vector<Consulta> consultaAvanzada(int opTitulo, Vector<Integer> atributoDocumento, Vector<String> valorDocumento, int opPalabra, Vector<String> valorPalabra, int opAutor, Vector<String> valorAutor, String areaString)
	{
		Vector<Consulta>  consultas = new Vector<Consulta>();
		
		String consultaSql, consultaDocumentoSql,
		consultaPalabraSql, consultaAreaSql, consultaAutorSql;
		
		// para construir la consulta final
		consultaDocumentoSql = consultaDocumento(opTitulo, atributoDocumento, valorDocumento);
		consultaPalabraSql = consultaPalabra(opPalabra, valorPalabra);
		consultaAutorSql = consultaAutor(opAutor, valorAutor);

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
		
		
		//Construir consulta final.
		consultaSql = "";
		
		boolean area= consultaAreaSql.equals(""),
				palabra= consultaPalabraSql.equals(""),
				documento= consultaDocumentoSql.equals(""),
				autor= consultaAutorSql.equals("");
		
		if(area && palabra && documento && autor)
		{
			consultaSql="SELECT documento.id_documento, documento.titulo_principal "+
			"FROM documento";
		} 
		else
		{
			Vector<Boolean> cuales= new Vector<Boolean>();
			cuales.add(!documento);
			cuales.add(!palabra);
			cuales.add(!autor);
			cuales.add(!area);
			
			Vector<String> consultasSql = new Vector<String>();
			consultasSql.add(consultaDocumentoSql);
			consultasSql.add(consultaPalabraSql);
			consultasSql.add(consultaAutorSql);
			consultasSql.add(consultaAreaSql);
			
			int cuantas = (!documento? 1:0) + (!palabra? 1:0) + (!autor? 1:0) + (!area? 1:0);
			
			if(cuantas == 1)
			{
				consultaSql= consultasSql.elementAt(cuales.indexOf(true));
			} else if(cuantas == 2)
			{
				int primeraOcurrencia = cuales.indexOf(true);
				consultaSql= "((" + consultasSql.elementAt(primeraOcurrencia)+ ")" +
				" INTERSECT " + "(" + consultasSql.elementAt(cuales.lastIndexOf(true)) + "))"; 
			} else if(cuantas == 3)
			{
				int primeraOcurrencia = cuales.indexOf(true);
				consultaSql= "((" + consultasSql.elementAt(primeraOcurrencia) + ")" +
				" INTERSECT " + "(" + consultasSql.elementAt(cuales.indexOf(true, primeraOcurrencia + 1)) + "))"+
				" INTERSECT " + "(" + consultasSql.elementAt(cuales.lastIndexOf(true)) + ")";
			} else 
			{
				consultaSql = "((" + consultaDocumentoSql + ")" + " INTERSECT " +
				"(" + consultaPalabraSql + "))" + " INTERSECT "+
				"((" + consultaAutorSql + ")" + " INTERSECT " +
				"(" + consultaAreaSql + "))";
			}
			

			consultaSql = "SELECT * FROM ("+ consultaSql + ") AS y";
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
	
	private String consultaDocumento(int opTitulo, Vector<Integer> atributoDocumento, Vector<String> valorDocumento)
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
		
		int sizeTitulo = atributoDocumento.elementAt(0);
		int elementosIngresados = sizeTitulo;
		if(opTitulo==1)
		{
			for(int i=0;i<sizeTitulo; i++)
			{
				boolean esOR = (i!= sizeTitulo-1);

				consultaDocumentoTituloSql += "documento.titulo_principal NOT LIKE '%" + //*******************************
				valorDocumento.elementAt(i) + "%' AND " +
				"documento.titulo_secundario NOT LIKE '%" +
				valorDocumento.elementAt(i)+ "%'" ;
				if(esOR)
				{
					consultaDocumentoTituloSql += " AND ";
				}	 
			}
		}else if(opTitulo == 2)
		{
			for(int i=0;i<sizeTitulo; i++)
			{
				boolean esOR = (i!= sizeTitulo-1);
				consultaDocumentoTituloSql += "documento.titulo_principal LIKE '%" +
				valorDocumento.elementAt(i) + "%' OR " +
				"documento.titulo_secundario LIKE '%" +
				valorDocumento.elementAt(i)+ "%'" ;
				if(esOR)
				{
					consultaDocumentoTituloSql += " OR ";
				}

			}
		}else if(opTitulo==3 && !valorDocumento.isEmpty())
		{
			consultaDocumentoTituloSql += "documento.titulo_principal LIKE '%" +
			valorDocumento.elementAt(0) + "%' OR " +
			"documento.titulo_secundario LIKE '%" +
			valorDocumento.elementAt(0) + "%'";
		}

		if(atributoDocumento.elementAt(1)!=0)
		{
			consultaDocumentoFechaSql += "documento.fecha_publicacion < '"+
			valorDocumento.elementAt(elementosIngresados)+"'";
			elementosIngresados++; //sumo porque acabe de ingresar un elemento al vector.
			if(atributoDocumento.elementAt(2)==1){
				consultaDocumentoFechaSql += " AND "; 
			}
		}
		
		if(atributoDocumento.elementAt(2) == 1)
		{
			consultaDocumentoFechaSql += "documento.fecha_publicacion > '"+
			valorDocumento.elementAt(elementosIngresados)+"'";
			elementosIngresados++;
		}
		
		if(atributoDocumento.elementAt(3) == 1)
		{
			consultaDocumentoIdiomaSql+= "documento.idioma = '"+
			valorDocumento.elementAt(elementosIngresados) + "'";	
			elementosIngresados++;
		}
		
		if(atributoDocumento.elementAt(4) == 1)
		{
			consultaDocumentoFormatoSql+= "documento.formato = '"+
			valorDocumento.elementAt(elementosIngresados) + "'";
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
			consultaDocumentoSql = "";
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
	
	private String consultaPalabra(int opPalabra, Vector<String> valorPalabra)
	{
		String	consultaPalabraSql, consultaPalabraTempSql;
		
		String consultaPalabraNula = "SELECT DISTINCT id_documento FROM tiene_documento_palabra_clave WHERE ";
		
		consultaPalabraTempSql = "SELECT DISTINCT id_documento FROM tiene_documento_palabra_clave WHERE ";
		consultaPalabraSql = "SELECT d.id_documento, d.titulo_principal "+
		"FROM (SELECT documento.id_documento, documento.titulo_principal FROM documento)as d NATURAL JOIN"; 
		
		int sizeVector = valorPalabra.size();
		
		if(opPalabra == 1)
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
		} else if (opPalabra == 2)
		{
			for(int i=0; i< sizeVector;i++)
			{
				boolean esOR = i != sizeVector - 1;
				consultaPalabraTempSql += "tiene_documento_palabra_clave.nombre LIKE '%" +
				valorPalabra.elementAt(i) + "%'";
				if(esOR)
				{
					consultaPalabraTempSql += " OR ";
				}
			}
		}else if(opPalabra==3 && !valorPalabra.isEmpty())
		{
			consultaPalabraTempSql += "tiene_documento_palabra_clave.nombre = '" +
			valorPalabra.elementAt(0) + "'";
		}
		
		
		boolean nombreSql = consultaPalabraTempSql.equals(consultaPalabraNula);
		
		if(!nombreSql)
		{
			if(opPalabra == 1)
			{
				consultaPalabraSql += "(SELECT id_documento FROM documento EXCEPT "
					+consultaPalabraTempSql+") AS p";
			}else
			{
				consultaPalabraSql += "("
					+consultaPalabraTempSql+") AS p";
			}
			
		}else
		{
			consultaPalabraSql="";
		}
		
		return consultaPalabraSql;

	}
	
	private String consultaAutor(int opAutor, Vector<String> valorAutor)
	{
		String consultaAutorSql, consultaAutorTemp;
		
		consultaAutorTemp = "SELECT e.id_documento FROM escribe_autor_documento AS e " + 
		"NATURAL JOIN (SELECT a.id_autor FROM autor AS a WHERE ";
		
		consultaAutorSql = "SELECT * FROM " +
		"(SELECT d.id_documento, d.titulo_principal FROM documento AS d) AS x " +
		"NATURAL JOIN ";
		

		int sizeVector = valorAutor.size();

		if(opAutor == 1)
		{
			for(int i=0; i<sizeVector; i++)
			{
				boolean esOR = sizeVector!=i+1;

				consultaAutorTemp += "a.nombre LIKE '%" +
				valorAutor.elementAt(i) + "%' OR " +
				"a.apellido LIKE '%" +
				valorAutor.elementAt(i)+ "%'" ;
				if(esOR)
				{
					consultaAutorTemp += " OR ";
				}
			}

		} else if(opAutor == 2)
		{
			for(int i=0; i<sizeVector; i++)
			{
				boolean esOR = sizeVector!=i+1;
				
				consultaAutorTemp += "a.nombre LIKE '%" +
				valorAutor.elementAt(i) + "%' OR " +
				"a.apellido LIKE '%" +
				valorAutor.elementAt(i)+ "%'" ;
				if(esOR)
				{
					consultaAutorTemp += " OR ";
				}
			}

		}else if(opAutor==3 && !valorAutor.isEmpty())
		{//solo puede conicidir con el nombre o con el apellido
			consultaAutorTemp += "a.nombre = '" +
			valorAutor.elementAt(0) + "' OR " +
			"a.apellido = '" +
			valorAutor.elementAt(0)+ "'" ;
		}

		if(sizeVector != 0)
				{
					if(opAutor==1)
					{
						consultaAutorSql += "(SELECT au.id_documento FROM escribe_autor_documento AS au EXCEPT " + consultaAutorTemp + ") AS c) AS y";
					} else
					{
						consultaAutorSql += "(" + consultaAutorTemp + ") AS c) AS y";
					}
					
				}else
				{
					consultaAutorSql = "";
				}
		
		//System.out.println("Consulta autor: " + consultaAutorSql);
		
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
	
	public int guardarDescargaUsuarioDocumento(String id_documento, String login, String fecha, String hora)
	{
		String sql_guardar;
		int numFilas;
		sql_guardar = "INSERT INTO descarga_usuario_documento VALUES ('"+fecha+"', " +
				"'"+hora+"', " +
				"'"+login+"'," +
				"'"+id_documento+"');";

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
