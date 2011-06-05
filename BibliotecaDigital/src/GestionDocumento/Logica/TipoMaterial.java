/**
 * TipoMaterial.java
 * 
 * Clase que representa un Tipo Material es decir que almacenará
 * los atributos de el tipo de Material de un documento de la Bi-
 * blioteca Digital.
 * 
 * JAVA version "1.6.0"
 * 
 * 
 * Autor:  Cristian Leonardo Rios
 * Version:   4.0
 */
package GestionDocumento.Logica;

/**
 * Clase que representa un tipo de material 
 * @author Cristian Leonardo Rios
 *
 */
public class TipoMaterial {
	/**
	 * String que contiene el nombre del tipo de material
	 */
	private String tipoNombre;
	/**
	 * String que contiene la descripcion del tipo de material
	 */
	private String descripcion;

	/**
	 * Constructor por defecto que inicializa los atributos
	 */
	public TipoMaterial() {
		tipoNombre = descripcion = "";
	}

	/**
	 * Constructor que inicializa los atributos con el valor respectivo
	 * @param nombre - String con el nombre del tipo de material
	 * @param descripcion - String con la descripcion del tipo de material
	 */
	public TipoMaterial(String nombre, String descripcion) {
		this.tipoNombre = nombre;
		this.descripcion = descripcion;
	}

	/**
	 * Metodo que devuelve el nombre del tipo de material
	 * @return String con el nombre del tipo de material
	 */
	public String getNombre() {
		return this.tipoNombre;
	}

	/**
	 * Metodo que devuelve la descriopcion del tipo de material
	 * @return String con la descripcion del tipo de material
	 */
	public String getDescripcion() {
		return this.descripcion;
	}

	/**
	 * Metodo que modifica el nombre del tipo de material
	 * @param nombre - String con el nuevo nombre
	 */
	public void setNombre(String nombre) {
		this.tipoNombre = nombre;
	}

	/**
	 * Metodo que modifica la descripcion del tipo de material
	 * @param descripcion -  String con la nueva descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}