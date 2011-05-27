package Reportes.Logica;

import java.util.Vector;

public class Reporte {
	
	private Vector<String> vectorOrderBy;
	private Vector<String> vectorColumnas;
	private Vector<String> vectorRegistros;
	private int tipo; //Tipo orderby, tipo condicion.
	private String ruta;
	
	public Reporte()
	{
		
	}

	public Reporte(Vector<String> vectorOrderBy, Vector<String> vectorColumnas, Vector<String> vectorRegistros, int tipo, String ruta)
	{
		this.ruta = ruta;
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
	
	public String getRuta()
	{
		return this.ruta;
	}
	
	public int getTipo()
	{
		return this.tipo;
	}
}

