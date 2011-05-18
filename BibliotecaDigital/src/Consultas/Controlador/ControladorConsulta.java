package Consultas.Controlador;

import java.util.StringTokenizer;
import java.util.Vector;

import Documento.Controlador.ControladorDocumento;
import Documento.Logica.Documento;
import GestionDocumento.Controlador.ControladorAreaConocimiento;
import GestionDocumento.Controlador.ControladorAutor;
import GestionDocumento.Controlador.ControladorPalabraClave;
import Consultas.Dao.DaoConsulta;
import Consultas.Logica.Consulta;

public class ControladorConsulta
{
//metodo que llama a otros controladores para obtener todos los datos de un documento dado su llave
	public Documento obtenerDatosDocumento(String id_documento){
		ControladorDocumento conDoc = new ControladorDocumento();
		ControladorAreaConocimiento conArea= new ControladorAreaConocimiento();
		ControladorAutor conAutor = new ControladorAutor();
		ControladorPalabraClave conPalabra = new ControladorPalabraClave();
		Documento d = conDoc.obtenerDocumento(id_documento);
		d.setAreas(conArea.obtenerAreasDocumento(id_documento));
		d.setAutores(conAutor.obtenerAutoresDocumento(id_documento));
		d.setPalabrasClave(conPalabra.obtenerPalabrasClaveDocumento(id_documento));
		
		return d;
	}
	
	public Vector<Consulta> consultaGeneral(String palabra, boolean modo)
	{
		Vector<String> palabras = new Vector<String>();
		StringTokenizer tk = new StringTokenizer(palabra," ");
		
		if(modo)
		{
			palabras.add(palabra);
		}
		else
		{
			while(tk.hasMoreTokens())
			{
				palabras.add(tk.nextToken());
			}
		}
		
		DaoConsulta consulta = new DaoConsulta();
		Vector<Consulta> resultado = consulta.consultaGeneral(palabras);
		
		return resultado;
	}
}
