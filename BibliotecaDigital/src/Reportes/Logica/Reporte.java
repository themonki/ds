package Reportes.Logica;

import java.util.Vector;

public class Reporte {
	
	private Vector<String> vectorOrderBy;
	private Vector<String> vectorColumnas;
	private Vector<String> vectorRegistros;
	private int tipo; //Tipo orderby, tipo condicion.
	private String encabezado;
	
	public Reporte()
	{
		
	}

	public Reporte(Vector<String> vectorOrderBy, Vector<String> vectorColumnas, Vector<String> vectorRegistros, int tipo, String encabezado)
	{
		this.encabezado = encabezado;
		this.tipo = tipo;
		this.vectorColumnas = vectorColumnas;
		this.vectorOrderBy = vectorOrderBy;
		this.vectorRegistros = vectorRegistros;
		
	}
	
	public Vector<String> getVectorColumnas()
	{
		return this.vectorColumnas;
	}
	
	public Vector<String> getVectorOrderBy()
	{
		return this.vectorOrderBy;
	}
	
	public Vector<String> getVectorRegistros()
	{
		return this.vectorRegistros;
	}
	
	public String getEncabezado()
	{
		return this.encabezado;
	}
	
	public int getTipo()
	{
		return this.tipo;
	}
	
	public void setEncabezado(String encabezado)
	{
		this.encabezado= encabezado;
	}
	
}

