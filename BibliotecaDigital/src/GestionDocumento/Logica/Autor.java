package GestionDocumento.Logica;

/*
 * Nombre: Yerminson Gonzalez Munoz
 * Responsabilidad: Clase que respresenta un autor.
 * 
 * */
public class Autor {

	String nombre;
	String apellido;
	String acronimo;
	String correo;
	String id;

	// Constructor
	public Autor() {
		// TODO Auto-generated constructor stub
	}

	public Autor(String nombre, String apellido, String acronimo,
			String correo, String id) {

		this.nombre = nombre;
		this.apellido = apellido;
		this.acronimo = acronimo;
		this.correo = correo;
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getAcronimo() {
		return acronimo;
	}

	public String getCorreo() {
		return correo;
	}

	public String getId() {
		return id;
	}

	// Uso de metodos set para construir el Autor cuando este se requiere
	// retornar
	// en consultar autor
	public void setNombre(String value) {
		nombre = value;
	}

	public void setApellido(String value) {
		apellido = value;
	}

	public void setCorreo(String value) {
		correo = value;
	}

	public void setAcronimo(String value) {
		acronimo = value;
	}

	public void setId(String value) {
		id = value;
	}

}