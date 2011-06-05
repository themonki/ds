 /**
 * AreaConocimiento.java
 * 
 * Clase que representa un Area de Conocimiento es decir que alma-
 * cenará los atributos de las areas de conocimiento que se espe-
 * cifican para los diferentes documentos de la Biblioteca Digital.
 * 
 * JAVA version "1.6.0"
 * 
 * 
 * Autor:  Cristian Leonardo Rios
 * Version:   4.0
 */
package GestionDocumento.Logica;

/**
 * Clase que representa un área del conocimiento
 * @author Cristian Leonardo Rios
 *
 */
public class AreaConocimiento {
	/**
	 * String con la llave del area de conocimiento
	 */
	private String idArea;
	/**
	 * String con el nombre del area de conocimiento
	 */
	private String nombre;
	/**
	 * String con la descripcion del area de conocimiento
	 */
	private String descripcion;
	/**
	 * String con la llave del area de conocimiento padre
	 */
	private String areaPadre;

	/**
	 * Constructor por defecto que inicializa los atributos
	 */
	public AreaConocimiento() {
		idArea = nombre = descripcion = areaPadre = "";
	}

	/**
	 * Constructor que inicializa los atributos con el valor respectivo
	 * @param idArea - String con la llave del area de conocimiento
	 * @param nombre - String con el nombre del area de conocimiento
	 * @param descripcion - String con la descripcion del area de conocimiento
	 * @param areaPadre - String con la llave del area de conocimiento padre
	 */
	public AreaConocimiento(String idArea, String nombre, String descripcion,
			String areaPadre) {
		this.idArea = idArea;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.areaPadre = areaPadre;
	}

	/**
	 * Metodo que devuelve la llave del area de conocimiento
	 * @return String con la llave del area de conocimiento
	 */
	public String getIdArea() {
		return this.idArea;
	}

	/**
	 * Metodo que devuelve el nombre del area de conocimiento
	 * @return String con el nombre del area de conocimiento
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * Metodo que devuelve la descripcion del area de conocimiento
	 * @return String con la descripcion del area de conocimiento
	 */
	public String getDescripcion() {
		return this.descripcion;
	}

	/**
	 * Metodo que devuelve la llave del area de conocimiento padre
	 * @return String con la llave del area de conocimiento padre
	 */
	public String getAreaPadre() {
		return this.areaPadre;
	}

	/**
	 * Metodo que modifica la llave de las areas de conocimiento
	 * @param idArea - String con la llave del area de conocimiento
	 */
	public void setIdArea(String idArea) {
		this.idArea = idArea;
	}

	/**
	 * Metodo que modifica la llave de las areas de conocimiento
	 * @param nombre - String con el nombre del area de conocimiento
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Metodo que modifica la llave de las areas de conocimiento
	 * @param descripcion - String con la descripcion del area de conocimiento
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Metodo que modifica la llave de las areas de conocimiento
	 * @param areaPadre - String con la llave del area de conocimiento padre
	 */
	public void setAreaPadre(String areaPadre) {
		this.areaPadre = areaPadre;
	}
}