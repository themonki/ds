/*
 * ControladorConsulta.java
 * 
 * Clase que permite la comunicación entre la aplicación y la base de datos
 * en el envió de información que este relacionada con las consultas de los
 * documentos de la biblioteca digital involucrando todas las clases en el
 * paquete y subpaquetes de Consultas.
 * 
 *
 * JAVA version "1.6.0"
 * 
 * 
 * Autor:      
 * Version:   4.0
 */
package Consultas.Controlador;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.Vector;

import Documento.Controlador.ControladorDocumento;
import Documento.Logica.Documento;
import GestionDocumento.Controlador.ControladorAreaConocimiento;
import GestionDocumento.Controlador.ControladorAutor;
import GestionDocumento.Controlador.ControladorPalabraClave;
import Consultas.Dao.DaoConsulta;
import Consultas.Logica.Consulta;

/**
 * Clase que permite la comunicación entre la Clase {@link Consultas.Dao.DaoConsulta DaoConsulta} y las Clases
 * de aplicación: {@link Consultas.Gui.GuiConsultaAvanzada GuiConsultaAvanzada}, 
 * {@link Consultas.Gui.GuiConsultaBasica GuiConsultaBasica},
 * {@link Consultas.Gui.GuiResultadoConsulta GuiResultadoConsulta}
 * y {@link Consultas.Gui.GuiVistaDetalladaConsulta GuiVistaDetalladaConsulta} 
 * para el intercambio de datos.
 * @author 
 *
 */
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
			if(!atributo.elementAt(i).equals("idioma")) //No se que pasa con el lowerCase de idioma .. por eso se hace esta comparacion.
			{
				valor.setElementAt(valor.elementAt(i).toLowerCase(), i);
			}
			
		}
		
		Vector<String> valorPalabra = new Vector<String>();
		Vector<String> valorAutor = new Vector<String>();
		
		//Este vector, es un vector de enteros que representa la cantidad de valores de un atributo de la tabla
		//documento, el vector por convencion va a tener este orden.
		//Contenido del vector:
		/* CantTitulo
		 * CantFechaAntes
		 * CantFechaDespues
		 * CantIdioma
		 * CantFormato
		 * */	
		Vector<Integer> atributoDocumento = new Vector<Integer>(5);
		
		//Se inicializa a cero por si no se envio dicho atributo.
		for(int i=0; i<5; i++)
		{
			atributoDocumento.insertElementAt(0, i);
		}
		
		
		
		Vector<String> valorDocumento = new Vector<String>();
		String areaString=""; 
		
		/*opcion 1:sin 2:cualquiera 3:exacta*/
		
		int titulos=0;
		
		for(int i=0; i<atributo.size();i++)
		{
			String elemento = atributo.elementAt(i);
			
			if(elemento.equals("palabra"))
			{
				if(opPalabra == 1 || opPalabra == 2)
				{
					StringTokenizer tk = new StringTokenizer(valor.elementAt(i), " ");
					while(tk.hasMoreElements())
					{
						valorPalabra.add(tk.nextToken());
					}

				}else if(opPalabra == 3)
				{
					
					valorPalabra.add(valor.elementAt(i));
				}
				
			}else if(elemento.equals("autor"))
			{
				if(opAutor == 1 || opAutor == 2)
				{
					StringTokenizer tk = new StringTokenizer(valor.elementAt(i), " ");
					while(tk.hasMoreElements())
					{
						
						valorAutor.add(tk.nextToken());
					}
					
				}else if(opAutor == 3)
				{
					valorAutor.add(valor.elementAt(i));
				}
				
			}else if(elemento.equals("titulo"))
			{
				if(opTitulo == 1 || opTitulo == 2)
				{
					StringTokenizer tk = new StringTokenizer(valor.elementAt(i), " ");
					while(tk.hasMoreElements())
					{
						valorDocumento.add(tk.nextToken());
						titulos++;
					}
					
				}else if(opTitulo == 3)
				{
					valorDocumento.add(valor.elementAt(i));
					titulos++;
				}
			}else if(elemento.equals("fecha_antes"))
			{
				atributoDocumento.setElementAt(1, 1);
				valorDocumento.add(valor.elementAt(i)+"-01-01");
				
			}else if(elemento.equals("fecha_despues"))
			{
				atributoDocumento.setElementAt(1, 2);
				valorDocumento.add(valor.elementAt(i)+"-12-31");
			}else if(elemento.equals("idioma"))
			{
				if(!valor.elementAt(i).equals("Todos")) //se compara con mayuscula por el lowerCase que se dejo de hacer
				{
					atributoDocumento.setElementAt(1, 3);
					valorDocumento.add(valor.elementAt(i));
					//System.out.println("indice: " + i + " valor: " + valor.elementAt(i)+ " atributo en indice3: " + atributoDocumento.elementAt(3));
				}
			}else if(elemento.equals("formato"))
			{
				if(!valor.elementAt(i).equals("todos"))
				{
					atributoDocumento.setElementAt(1, 4);
					valorDocumento.add(valor.elementAt(i));
					//System.out.println("indice: " + i + " valor: " + valor.elementAt(i)+ " atributo en indice4: " + atributoDocumento.elementAt(4));
				}
			}else if(elemento.equals("area"))
			{
				if(!valor.elementAt(i).equals("todas"))
				{
					areaString =valor.elementAt(i);
				}
			}
		}
		
		
		//Inserto CantTitulo, para identificar la cantidad de palabras.
		atributoDocumento.setElementAt(titulos, 0);
		
		
		//lo que me envio la guiConsultaAvanzada
		/*System.out.println(atributo);
		System.out.println(valor);
		
		//Lo que se construyo en controlador para enviar al dao.
		System.out.println(atributoDocumento);
		System.out.println(valorDocumento);
		System.out.println(valorPalabra);
		System.out.println(valorAutor);
		System.out.println(areaString);
		*/
		
		DaoConsulta daoConsulta = new DaoConsulta();
		//No estaban asigando lo de dao a vector xD. Corregido
		Vector<Consulta> v= new Vector<Consulta>();
		v = daoConsulta.consultaAvanzada(opTitulo, atributoDocumento, valorDocumento, opPalabra, valorPalabra, opAutor, valorAutor, areaString);
		return v;
	}
	
	//metodo que actualiza la tabla consulta en el momento de que selecciona un resultado (pasa a vistaDocumento)
	public int insertarConsultaDocumentoUsuario(String id_documento, String login){
		java.util.Date fecha = new java.util.Date();
		SimpleDateFormat formatoFecha= new SimpleDateFormat("yyyy-MM-dd");
		String fechaString = formatoFecha.format(fecha); // la fecha en que se consulto
		
		Calendar calendario = Calendar.getInstance();
		int hora, min, seg;
		hora = calendario.get(Calendar.HOUR_OF_DAY);
		min = calendario.get(Calendar.MINUTE);
		seg = calendario.get(Calendar.SECOND);
		String horaConsulta = hora +":"+min+":"+seg; //la hora en que se consulto
		DaoConsulta daoConsulta = new DaoConsulta();
		return daoConsulta.guardarConsulta(id_documento, login, fechaString, horaConsulta);		
	}
	//*
	public int insertarDescargaDocumento(String id_documento, String login){
		java.util.Date fecha = new java.util.Date();
		SimpleDateFormat formatoFecha= new SimpleDateFormat("yyyy-MM-dd");
		String fechaString = formatoFecha.format(fecha); // la fecha en que se consulto
		
		Calendar calendario = Calendar.getInstance();
		int hora, min, seg;
		hora = calendario.get(Calendar.HOUR_OF_DAY);
		min = calendario.get(Calendar.MINUTE);
		seg = calendario.get(Calendar.SECOND);
		String horaConsulta = hora +":"+min+":"+seg; //la hora en que se consulto
		DaoConsulta daoConsulta = new DaoConsulta();
		return daoConsulta.guardarDescargaUsuarioDocumento(id_documento, login, fechaString, horaConsulta);	
	}
	//
}
