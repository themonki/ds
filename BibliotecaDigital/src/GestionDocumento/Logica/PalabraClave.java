 /**
 * PalabraClave.java
 * 
 * Clase que representa una Palbra Clave es decir que almacenará
 * los atributos de las palabras calve que se especifican en los
 * documentos de la Biblioteca Digital.
 * 
 * JAVA version "1.6.0"
 * 
 * 
 * Autor:  Cristian Leonardo Rios
 * Version:   4.0
 */
package GestionDocumento.Logica;

/**
 * Clase que representa una palabra clave
 * @author Cristian Leonardo Rios
 */
public class PalabraClave {
	/**
	 * String con el nombre de la palabra clave
	 */
	private String nombre;
	/**
	 * String con la descripcion de la palabra clave
	 */
	private String descripcion;

	/**
	 * Constructor por defecto que inicializa los atributos
	 */
	public PalabraClave() {
		nombre = descripcion = "";
	}

	/**
	 * Constructor que inicializa los atributos con el valor respectivo
	 * @param nombre - String con el nombre de la palabra clave
	 * @param descripcion - String con la descripcion de la palabra clave
	 */
	public PalabraClave(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	/**
	 * Metodo que retorna el nombre de la palabra clave
	 * @return String con el nombre de la palabra clave
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo que retorna la descripcion de la palabra clave
	 * @return String con la descripcion de la palabra clave
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Metodo que modifica el nombre de la palabra clave
	 * @param nombre - String con el nuevo nombre de la palabra clave
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Metodo que modifica la descripcion de la palabra clave
	 * @param descripcion - String con la nueva descripcion de la palabra clave
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}