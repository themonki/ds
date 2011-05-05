/*
 * Nombre: Cristian R�os.
 * Responsabilidad: Clase que representa una palabra clave.
 * Nombre del archivo: PalabraClave.java
 * Fecha creaci�n: Mayo 03 2011
 * Fecha ultima modificaci�n: Mayo 03 2011
 * */

package GestionDocumento.Logica;

public class PalabraClave {
	private String nombre;
	private String descripcion;

	public PalabraClave() {
		nombre = descripcion = "";
	}

	public PalabraClave(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}