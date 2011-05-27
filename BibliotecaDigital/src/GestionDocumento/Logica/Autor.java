package GestionDocumento.Logica;

/**
 * Clase que respresenta un autor.
 * @author Yerminson Gonzalez Munoz 
 * */
public class Autor {

	/**
	 * String con el nombre del autor
	 */
	String nombre;
	/**
	 * String con el apellido del autor
	 */
	String apellido;
	/**
	 * String con el acronimo del autor
	 */
	String acronimo;
	/**
	 * String con el email del autor
	 */
	String correo;
	/**
	 * String con la llave del autor
	 */
	String id;

	/**
	 * Constructor por defecto que inicializa los atributos
	 */
	public Autor() {nombre =""; apellido =""; acronimo =""; correo =""; id = "";
	}

	/**
	 * Constructor que inicializa los atributos con el valor respectivo
	 * @param nombre - String con el nombre del autor
	 * @param apellido - String con el apellido del autor
	 * @param acronimo - String con el acronimo del autor
	 * @param correo - String con el correo del autor
	 * @param id - String con la llave del autor
	 */
	public Autor(String nombre, String apellido, String acronimo,
			String correo, String id) {

		this.nombre = nombre;
		this.apellido = apellido;
		this.acronimo = acronimo;
		this.correo = correo;
		this.id = id;
	}

	/**
	 * Metodo que devuelve el nombre del autor
	 * @return String con el nombre del autor
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo que devuelve el apellido del autor
	 * @return String con el apellido del autor
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * Metodo que devuelve el acronimo del autor
	 * @return String con el acronimo del autor
	 */
	public String getAcronimo() {
		return acronimo;
	}

	/**
	 * Metodo que devuelve el correo del autor
	 * @return String con el correo del autor
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * Metodo que devuelve la llave del autor
	 * @return String con la llave del autor
	 */
	public String getId() {
		return id;
	}

	// Uso de metodos set para construir el Autor cuando este se requiere
	// retornar
	// en consultar autor
	/**
	 * Metodo que modifica el nombre del autor
	 * @param value - String con el nombre del autor
	 */
	public void setNombre(String value) {
		nombre = value;
	}

	/**
	 * Metodo que modifica el apellido del autor
	 * @param value - String con el apellido del autor
	 */
	public void setApellido(String value) {
		apellido = value;
	}

	/**
	 * Metodo que modifica el correo del autor
	 * @param value - String con el correo del autor
	 */
	public void setCorreo(String value) {
		correo = value;
	}

	/**
	 * Metodo que modifica el acrionimo del autor
	 * @param value - String con el acronimo del autor
	 */
	public void setAcronimo(String value) {
		acronimo = value;
	}

	/**
	 * Metodo que modifica la llave del autor
	 * @param value - String con la llave del autor
	 */
	public void setId(String value) {
		id = value;
	}

}