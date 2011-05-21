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
		
		String atributoPalabra="";
		Vector<String> valorPalabra = new Vector<String>();
		String atributoAutor="";
		Vector<String> valorAutor = new Vector<String>();
		Vector<String> atributoDocumento = new Vector<String>();
		Vector<String> valorDocumento = new Vector<String>();
		String areaString=""; 
		
		/*opcion 1:sin 2:cualquiera 3:exacta*/
		
		for(int i=0; i<atributo.size();i++)
		{
			String elemento = atributo.elementAt(i);
			
			if(elemento.equals("palabra"))
			{
				if(opPalabra == 1)
				{
					atributoPalabra= "sin";
					StringTokenizer tk = new StringTokenizer(valor.elementAt(i), " ");
					while(tk.hasMoreElements())
					{
						
						valorPalabra.add(tk.nextToken());
					}
					
				}else if(opPalabra == 2)
				{
					atributoPalabra="algunas";
					StringTokenizer tk = new StringTokenizer(valor.elementAt(i), " ");
					while(tk.hasMoreElements())
					{
						
						valorPalabra.add(tk.nextToken());
					}
					
				}else if(opPalabra == 3)
				{
					atributoPalabra="exacto";
					valorPalabra.add(valor.elementAt(i));
				}
				
			}else if(elemento.equals("autor"))
			{
				if(opAutor == 1)
				{
					atributoAutor="sin";
					StringTokenizer tk = new StringTokenizer(valor.elementAt(i), " ");
					while(tk.hasMoreElements())
					{
						
						valorAutor.add(tk.nextToken());
					}
					
				}else if(opAutor == 2)
				{
					atributoAutor="algunas";
					StringTokenizer tk = new StringTokenizer(valor.elementAt(i), " ");
					while(tk.hasMoreElements())
					{
						String v = tk.nextToken();
						valorAutor.add(v);
						
					}
					
				}else if(opAutor == 3)
				{
					atributoAutor="exacto";
					valorAutor.add(valor.elementAt(i));
				}
				
			}else if(elemento.equals("titulo"))
			{
				if(opTitulo == 1)
				{
					StringTokenizer tk = new StringTokenizer(valor.elementAt(i), " ");
					while(tk.hasMoreElements())
					{
						atributoDocumento.add("titulo.sin");
						valorDocumento.add(tk.nextToken());
					}
					
				}else if(opTitulo == 2)
				{
					StringTokenizer tk = new StringTokenizer(valor.elementAt(i), " ");
					while(tk.hasMoreElements())
					{
						String v = tk.nextToken();
						
						atributoDocumento.add("titulo.algunas");
						valorDocumento.add(v);
					}
					
				}else if(opTitulo == 3)
				{
					atributoDocumento.add("titulo.exacto");
					valorDocumento.add(valor.elementAt(i));
				}
			}else if(elemento.equals("fecha_antes"))
			{
				atributoDocumento.add("fecha.antes");
				valorDocumento.add(valor.elementAt(i)+"-01-01");
				
			}else if(elemento.equals("fecha_despues"))
			{
				atributoDocumento.add("documento.fecha.despues");
				valorDocumento.add(valor.elementAt(i)+"-01-01");
			}
			else if(elemento.equals("idioma"))
			{
				if(!valor.elementAt(i).equals("todos"))
				{
					atributoDocumento.add("idioma");
					valorDocumento.add(valor.elementAt(i));
				}
			}
			else if(elemento.equals("formato"))
			{
				if(!valor.elementAt(i).equals("todos"))
				{
					atributoDocumento.add("formato");
					valorDocumento.add(valor.elementAt(i));
				}
			}else if(elemento.equals("area"))
			{
				if(!valor.elementAt(i).equals("todas"))
				{
					areaString =valor.elementAt(i);
				}
			}
		}
		
		System.out.println(atributo);
		System.out.println(valor);
		System.out.println(atributoDocumento);
		System.out.println(valorDocumento);
		System.out.println(atributoPalabra);
		System.out.println(valorPalabra);
		System.out.println(atributoAutor);
		System.out.println(valorAutor);
		System.out.println(areaString);
		DaoConsulta daoConsulta = new DaoConsulta();
		//No estaban asigando lo de dao a vector xD. Corregido
		Vector<Consulta> v= new Vector<Consulta>();
		v = daoConsulta.consultaAvanzada(atributoDocumento, valorDocumento, atributoPalabra, valorPalabra, atributoAutor, valorAutor, areaString);
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
