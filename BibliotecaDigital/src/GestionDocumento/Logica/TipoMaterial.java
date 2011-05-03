/*
 * Nombre: Cristian Ríos.
 * Responsabilidad: Clase que representa un tipo de material.
 * Nombre del archivo: TipoMaterial.java
 * Fecha creación: Mayo 03 2011
 * Fecha ultima modificación: Mayo 03 2011
 * */

package GestionDocumento.Logica;

public class TipoMaterial
{
	private String tipoNombre;
	private String descripcion;
	
	public TipoMaterial()
	{
		tipoNombre = descripcion = "";
	}
	
	public TipoMaterial(String nombre, String descripcion)
	{
		this.tipoNombre = nombre;
		this.descripcion = descripcion;
	}
	
	public String getNombre()
	{
		return this.tipoNombre;
	}
	
	public String getDescripcion()
	{
		return this.descripcion;
	}
	
	public void setNombre(String nombre)
	{
		this.tipoNombre = nombre;
	}

	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
}