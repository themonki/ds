package Documento.Logica;

import java.util.Vector;

public class Documento {
	
	String id_doc;
	String titulo_secundario;
	String tipoMaterial;
	String fechaDeCatalogacion;
	String tituloppal;
	String derechosDeAutor;
	String editorial;
	String idioma;
	String tamano;
	String url;	
	String software_recomendado;
	String resolucion;
	String formato;
	String resumen;
	String catalogadorLogin;

	String descripcion;


	String fecha_creacion;
	String fecha_publicacion;
	String fechaDeCatalogacion;

	

	


	Vector autores;
	Vector areas;
	Vector palabrasClave;
	
	public String getid_doc(){ return this.id_doc;}
	public String getIdioma(){ return this.idioma;}
	public String getderechosDeAutor(){return this.derechosDeAutor;}
	public String getDescripcion(){return this.descripcion;}
	public String getSoftware_recomentado(){return this.software_recomendado;}
	public String getResolucion(){return this.resolucion;}
	public String getEditorial(){return this.editorial;}
	public String getFormato(){return this.formato;}
	public String gettituloppal(){return this.tituloppal;}
	public String getTitulo_secundario(){return this.titulo_secundario;}
	public String geturl(){return this.url;}
	public String getFecha_creacion(){return this.fecha_creacion;}
	public String getFecha_publicacion(){return this.fecha_publicacion;}
	public String getfechaDeCatalogacion(){return this.fechaDeCatalogacion;}
	public String getcatalogadorLogin(){return this.catalogadorLogin;}
	public String getTipoMaterial(){return this.tipoMaterial;}
	public Vector getAutores(){return this.autores;}
	public Vector getAreas(){return this.areas;}
	public Vector getPalabrasClave(){return this.palabrasClave;}
	
	public void getid_doc(String value){ id_doc= value;}
	public void getIdioma(String value){ idioma= value;}
	public void getderechosDeAutor(String value){derechosDeAutor= value;}
	public void getDescripcion(String value){descripcion= value;}
	public void getSoftware_recomentado(String value){software_recomendado= value;}
	public void getResolucion(String value){resolucion= value;}
	public void getEditorial(String value){editorial= value;}
	public void getFormato(String value){formato= value;}
	public void gettituloppal(String value){tituloppal= value;}
	public void getTitulo_secundario(String value){titulo_secundario= value;}
	public void geturl(String value){url= value;}
	public void getFecha_creacion(String value){fecha_creacion= value;}
	public void getFecha_publicacion(String value){fecha_publicacion= value;}
	public void getfechaDeCatalogacion(String value){fechaDeCatalogacion= value;}
	public void getcatalogadorLogin(String value){catalogadorLogin= value;}
	public void getTipoMaterial(String value){tipoMaterial= value;}
	public void getAutores(Vector value){autores= value;}
	public void getAreas(Vector value){areas= value;}
	public void getPalabrasClave(Vector value){palabrasClave= value;}

}
