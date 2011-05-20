package Consultas.Controlador;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
		palabra=palabra.toLowerCase();
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
	
	public Vector<Consulta> consultaAvanzada(Vector<String> atributo, Vector<String> valor, int opTitulo, int opPalabra, int opAutor)
	{
		//for para tolowercase para valores
		for(int i=0; i<valor.size();i++)
		{
			valor.setElementAt(valor.elementAt(i).toLowerCase(), i);
		}
		
		Vector<String> atributoConsulta = new Vector<String>();
		Vector<String> valorConsulta = new Vector<String>();
		
		/*opcion 1:sin 2:cualquiera 3:exacta*/
		
		for(int i=0; i<atributo.size();i++)
		{
			String elemento = atributo.elementAt(i);
			
			if(elemento.equals("palabra"))
			{
				if(opPalabra == 1)
				{
					StringTokenizer tk = new StringTokenizer(valor.elementAt(i), " ");
					while(tk.hasMoreElements())
					{
						atributoConsulta.add("palabra.nombre.sin");
						valorConsulta.add(tk.nextToken());
					}
					
				}else if(opPalabra == 2)
				{
					StringTokenizer tk = new StringTokenizer(valor.elementAt(i), " ");
					while(tk.hasMoreElements())
					{
						atributoConsulta.add("palabra.nombre.algunas");
						valorConsulta.add(tk.nextToken());
					}
					
				}else if(opPalabra == 3)
				{
					atributoConsulta.add("palabra.nombre.exacto");
					valorConsulta.add(valor.elementAt(i));
				}
				
			}else if(elemento.equals("autor"))
			{
				if(opAutor == 1)
				{
					StringTokenizer tk = new StringTokenizer(valor.elementAt(i), " ");
					while(tk.hasMoreElements())
					{
						atributoConsulta.add("autor.nombre.sin");
						valorConsulta.add(tk.nextToken());
					}
					
				}else if(opAutor == 2)
				{
					StringTokenizer tk = new StringTokenizer(valor.elementAt(i), " ");
					while(tk.hasMoreElements())
					{
						String v = tk.nextToken();
						
						atributoConsulta.add("autor.nombre.algunas");
						valorConsulta.add(v);
						
					}
					
				}else if(opPalabra == 3)
				{
					atributoConsulta.add("autor.nombre.exacto");
					valorConsulta.add(valor.elementAt(i));
				}
				
			}else if(elemento.equals("titulo"))
			{
				if(opTitulo == 1)
				{
					StringTokenizer tk = new StringTokenizer(valor.elementAt(i), " ");
					while(tk.hasMoreElements())
					{
						atributoConsulta.add("documento.titulo.sin");
						valorConsulta.add(tk.nextToken());
					}
					
				}else if(opTitulo == 2)
				{
					StringTokenizer tk = new StringTokenizer(valor.elementAt(i), " ");
					while(tk.hasMoreElements())
					{
						String v = tk.nextToken();
						
						atributoConsulta.add("documento.titulo.algunas");
						valorConsulta.add(v);
					}
					
				}else if(opTitulo == 3)
				{
					atributoConsulta.add("documento.titulo.exacto");
					valorConsulta.add(valor.elementAt(i));
				}
			}else if(elemento.equals("fecha_antes"))
			{
				atributoConsulta.add("documento.fecha.antes");
				valorConsulta.add(valor.elementAt(i)+"-01-01");
				
			}else if(elemento.equals("fecha_despues"))
			{
				atributoConsulta.add("documento.fecha.despues");
				valorConsulta.add(valor.elementAt(i)+"-01-01");
			}
			else if(elemento.equals("idioma"))
			{
				if(!valor.elementAt(i).equals("Todos"))
				{
					atributoConsulta.add("documento.idioma");
					valorConsulta.add(valor.elementAt(i));
				}
			}
			else if(elemento.equals("formato"))
			{
				if(!valor.elementAt(i).equals("Todos"))
				{
					atributoConsulta.add("documento.formato");
					valorConsulta.add(valor.elementAt(i));
				}
			}else if(elemento.equals("area"))
			{
				if(!valor.elementAt(i).equals("Todos"))
				{
					atributoConsulta.add("area.nombre");
					valorConsulta.add(valor.elementAt(i));
				}
			}
		}
		
		System.out.println(atributo);
		System.out.println(valor);
		System.out.println(atributoConsulta);
		System.out.println(valorConsulta);
		DaoConsulta daoConsulta = new DaoConsulta();
		daoConsulta.consultaAvanzada(atributoConsulta, valorConsulta);
		Vector<Consulta> v= new Vector<Consulta>();
		return v;
	}
	
	//metodo que actualiza la tabla consulta en el momento de que selecciona un resultado (pasa a vistaDocumento)
	public void insertarConsultaDocumentoUsuario(String id_documento, String login){
		java.util.Date fecha = new java.util.Date();
		SimpleDateFormat formatoFecha= new SimpleDateFormat("yyyy-MM-dd");
		String fechaString = formatoFecha.format(fecha); // la fecha en que se consulto
		
		Calendar calendario = Calendar.getInstance();
		int hora, min, seg;
		hora = calendario.get(Calendar.HOUR_OF_DAY);
		min = calendario.get(Calendar.MINUTE);
		seg = calendario.get(Calendar.SECOND);
		String horaConsulta = hora +":"+min+":"+seg; //la hora en que se consulto
		System.out.println(fechaString);
		System.out.println(horaConsulta);
		DaoConsulta daoConsulta = new DaoConsulta();
		daoConsulta.guardarConsulta(id_documento, login, fechaString, horaConsulta);
		
		
	}
	
	
}
