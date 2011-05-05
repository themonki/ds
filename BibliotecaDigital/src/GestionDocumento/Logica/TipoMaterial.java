/*
 * Nombre: Cristian R�os.
 * Responsabilidad: Clase que representa un tipo de material.
 * Nombre del archivo: TipoMaterial.java
 * Fecha creaci�n: Mayo 03 2011
 * Fecha ultima modificaci�n: Mayo 03 2011
 * */

package GestionDocumento.Logica;

public class TipoMaterial {
	private String tipoNombre;
	private String descripcion;

	public TipoMaterial() {
		tipoNombre = descripcion = "";
	}

	public TipoMaterial(String nombre, String descripcion) {
		this.tipoNombre = nombre;
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.tipoNombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setNombre(String nombre) {
		this.tipoNombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}