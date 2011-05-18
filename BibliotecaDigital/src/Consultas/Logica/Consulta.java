package Consultas.Logica;

public class Consulta {

	String idDocumento;
	String tituloDocuemto ;
	String nombreAutorDocumento;
	
	public Consulta(){}
	public Consulta(String id_doc, String titulo, String nombreAutor){
		idDocumento = id_doc;
		tituloDocuemto = titulo;
		nombreAutorDocumento=nombreAutor;
	}

	public String getIdDocumento(){return idDocumento;}
	public String getTituloDocuemto(){return tituloDocuemto;}
	public String getNombreAutorDocumento(){return nombreAutorDocumento;}
	public void setIdDocumento(String value) {idDocumento=value;}
	public void setTituloDocuemto(String value) {tituloDocuemto=value;}
	public void setNombreAutorDocumento(String value) {nombreAutorDocumento=value;}
	public String toString(){
		String mostrarDatos = tituloDocuemto + "\n Autor(es): "+nombreAutorDocumento;
		return mostrarDatos;
	}
}
