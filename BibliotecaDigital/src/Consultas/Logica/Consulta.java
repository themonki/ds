 /**
 * Consulta.java
 * 
 * Clase que representa una Consulta es decir una representacion
 * de un resultado de una consulta de un Documento de la Biblio-
 * teca digital que contiene el titulo principal, los autores y
 * la llave del documento.
 * 
 * JAVA version "1.6.0"
 * 
 * 
 * Autor:  Edgar Andres Moncada
 * Version:   4.0
 */
package Consultas.Logica;

import java.util.Vector;


/**
* Clase que representa una consulta de un documento
 * @author Edgar Andres Moncada
 *
 */
public class Consulta {
	
	/**
	 * String con la llave del documento
	 */
	String idDocumento;
	/**
	 * String con el titulo del documento
	 */
	String tituloDocumeto ;
	/**
	 * Vector <String> con los nombres de los autores del documento
	 */
	Vector <String> nombresAutoresDocumento;
	
	
	/**
	 * Constructor por defecto que inicializa los atributos
	 */
	public Consulta(){idDocumento=""; tituloDocumeto=""; nombresAutoresDocumento = new Vector<String>(1,1); }
	/**
	 * Constructor que recibe atributo por atributo
	 * @param id_doc - String con la llave del documento consultado
	 * @param titulo - String con el titulo del documento
	 * @param nombreAutor - Vector <String> con los nombres de los autores del documento
	 */
	public Consulta(String id_doc, String titulo, Vector <String> nombreAutor){
		idDocumento = id_doc;
		tituloDocumeto = titulo;
		nombresAutoresDocumento=nombreAutor;
	}
	
	/**
	 * Metodo que retorna la llave del documento consultado
	 * @return String con la llave del documento
	 */
	public String getIdDocumento(){return idDocumento;}
	/**
	 * Metodo que retorna el tiitulo principal del documento consultado
	 * @return String con el titulo del documento
	 */
	public String getTituloDocuemto(){return tituloDocumeto;}
	/**
	 * Metodo que retorna la llave del documento consultado
	 * @return Vector <String> con los nombres de los autores
	 */
	public Vector <String> getNombresAutoresDocumento(){return nombresAutoresDocumento;}
	/**
	 * Metodo que modifica la llave del documento consultado
	 * @param value - String con la llave del documento
	 */
	public void setIdDocumento(String value) {idDocumento=value;}
	/**
	 * Metodo que modifica el titulo del documento consultado
	 * @param value - String con el titulo del documento
	 */
	public void setTituloDocuemto(String value) {tituloDocumeto=value;}
	/**
	 * Metodo que modifica los nombres de los autores del documento consultado
	 * @param value - Vector <String> con los nuevos nombres de los autores del documento
	 */
	public void setNombresAutoresDocumento(Vector <String> value) {nombresAutoresDocumento=value;}
	/**
	 * Metodo sobreescrito para mostrar el resultado de la consulta en 
	 * {@link Consultas.Gui.GuiResultadoConsulta GuiResultadoConsulta}
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String mostrarDatos = "<html><font color = \"blue\"> <u>"
			+tituloDocumeto 
			+ "</u> </font>"
			+"<br>Autor(es): ";
		int cantidad = nombresAutoresDocumento.size();

		for(int i = 0; i < cantidad-1;i++)
		{
			mostrarDatos+=nombresAutoresDocumento.get(i)+", ";
		}
		mostrarDatos+=nombresAutoresDocumento.get(cantidad-1);
		mostrarDatos+="</html>";
		
		
		return mostrarDatos;
		
	}
}
