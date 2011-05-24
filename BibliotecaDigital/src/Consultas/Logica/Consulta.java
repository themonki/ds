package Consultas.Logica;

import java.util.Vector;

public class Consulta {

	String idDocumento;
	String tituloDocuemto ;
	Vector <String> nombresAutoresDocumento;
	
	public Consulta(){}
	public Consulta(String id_doc, String titulo, Vector <String> nombreAutor){
		idDocumento = id_doc;
		tituloDocuemto = titulo;
		nombresAutoresDocumento=nombreAutor;
	}

	public String getIdDocumento(){return idDocumento;}
	public String getTituloDocuemto(){return tituloDocuemto;}
	public Vector <String> getNombresAutoresDocumento(){return nombresAutoresDocumento;}
	public void setIdDocumento(String value) {idDocumento=value;}
	public void setTituloDocuemto(String value) {tituloDocuemto=value;}
	public void setNombresAutoresDocumento(Vector <String> value) {nombresAutoresDocumento=value;}
	public String toString(){
		String mostrarDatos = "<html><font color = \"blue\"> <u>"
			+tituloDocuemto 
			+ "</u> </font>"
			+"<br>Autor(es): ";
		int cantidad = nombresAutoresDocumento.size();
		for(int i = 0; i < cantidad;i++)
			mostrarDatos+=nombresAutoresDocumento.get(i)+", ";
		//mostrarDatos+=nombresAutoresDocumento.get(cantidad-1);
		mostrarDatos+="</html>";
		return mostrarDatos;
		
	}
}
