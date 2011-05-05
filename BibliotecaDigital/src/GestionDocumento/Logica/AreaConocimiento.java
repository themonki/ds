/*
 * Nombre: Cristian R�os.
 * Responsabilidad: Clase que representa un �rea del conocimiento
 * Nombre del archivo: AreaConocimiento.java
 * Fecha creaci�n: Mayo 03 2011
 * Fecha ultima modificaci�n: Mayo 03 2011
 * */

package GestionDocumento.Logica;

public class AreaConocimiento {
	private String idArea;
	private String nombre;
	private String descripcion;
	private String areaPadre;

	public AreaConocimiento() {
		idArea = nombre = descripcion = areaPadre = "";
	}

	public AreaConocimiento(String idArea, String nombre, String descripcion,
			String areaPadre) {
		this.idArea = idArea;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.areaPadre = areaPadre;
	}

	public String getIdArea() {
		return this.idArea;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public String getAreaPadre() {
		return this.areaPadre;
	}

	public void setIdArea(String idArea) {
		this.idArea = idArea;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setAreaPadre(String areaPadre) {
		this.areaPadre = areaPadre;
	}
}