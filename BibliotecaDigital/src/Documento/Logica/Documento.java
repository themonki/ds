package Documento.Logica;

import java.util.Vector;
import GestionDocumento.Logica.AreaConocimiento;
import GestionDocumento.Logica.Autor;
import GestionDocumento.Logica.PalabraClave;

public class Documento {
	
		
	String idDocumento;
	String idioma;
	String derechosDeAutor;
	String descripcion;
	String softwareRecomendado;
	String resolucion;
	String editorial;
	String formato;	
	String tituloPrincipal;	
	String tituloSecundario;
	String link;	
	String fechaCreacion;
	String fechaPublicacion;
	String fechaCatalogacion;
	String loginCatalogador;
	String tipoMaterial;		
	Vector<Autor> autores;
	Vector<AreaConocimiento> areas;
	Vector<PalabraClave> palabrasClave;
	
	//Constructor
	public Documento(String idDocumento, String idioma, String derechosDeAutor,
			String descripcion, String softwareRecomendado, String resolucion,
			String editorial, String formato, String tituloPrincipal,
			String tituloSecundario, String link, String fechaCreacion,
			String fechaPublicacion, String fechaCatalogacion,
			String loginCatalogador, String tipoMaterial,
			Vector<Autor> autores, Vector<AreaConocimiento> areas,
			Vector<PalabraClave> palabrasClave) {
		
		this.idDocumento = idDocumento;
		this.idioma = idioma;
		this.derechosDeAutor = derechosDeAutor;
		this.descripcion = descripcion;
		this.softwareRecomendado = softwareRecomendado;
		this.resolucion = resolucion;
		this.editorial = editorial;
		this.formato = formato;
		this.tituloPrincipal = tituloPrincipal;
		this.tituloSecundario = tituloSecundario;
		this.link = link;
		this.fechaCreacion = fechaCreacion;
		this.fechaPublicacion = fechaPublicacion;
		this.fechaCatalogacion = fechaCatalogacion;
		this.loginCatalogador = loginCatalogador;
		this.tipoMaterial = tipoMaterial;
		this.autores = autores;
		this.areas = areas;
		this.palabrasClave = palabrasClave;
	}	
	
	public String getid_doc(){ return this.idDocumento;}
	public String getIdioma(){ return this.idioma;}
	public String getderechosDeAutor(){return this.derechosDeAutor;}
	public String getDescripcion(){return this.descripcion;}
	public String getSoftware_recomentado(){return this.softwareRecomendado;}
	public String getResolucion(){return this.resolucion;}
	public String getEditorial(){return this.editorial;}
	public String getFormato(){return this.formato;}
	public String gettituloppal(){return this.tituloPrincipal;}
	public String getTitulo_secundario(){return this.tituloSecundario;}
	public String geturl(){return this.link;}
	public String getFecha_creacion(){return this.fechaCreacion;}
	public String getFecha_publicacion(){return this.fechaPublicacion;}
	public String getfechaDeCatalogacion(){return this.fechaCatalogacion;}
	public String getcatalogadorLogin(){return this.loginCatalogador;}
	public String getTipoMaterial(){return this.tipoMaterial;}
	public Vector<Autor> getAutores(){return this.autores;}
	public Vector<AreaConocimiento> getAreas(){return this.areas;}
	public Vector<PalabraClave> getPalabrasClave(){return this.palabrasClave;}	
	public void getid_doc(String value){ idDocumento = value;}
	public void getIdioma(String value){ idioma= value;}
	public void getderechosDeAutor(String value){derechosDeAutor= value;}
	public void getDescripcion(String value){descripcion= value;}
	public void getSoftware_recomentado(String value){softwareRecomendado= value;}
	public void getResolucion(String value){resolucion= value;}
	public void getEditorial(String value){editorial= value;}
	public void getFormato(String value){formato= value;}
	public void gettituloppal(String value){tituloPrincipal= value;}
	public void getTitulo_secundario(String value){tituloSecundario= value;}
	public void geturl(String value){link= value;}
	public void getFecha_creacion(String value){fechaCreacion= value;}
	public void getFecha_publicacion(String value){fechaPublicacion= value;}
	public void getfechaDeCatalogacion(String value){fechaCatalogacion= value;}	
	public void getcatalogadorLogin(String value){loginCatalogador= value;}
	public void getTipoMaterial(String value){tipoMaterial= value;}
	public void getAutores(Vector<Autor> value){autores= value;}
	public void getAreas(Vector<AreaConocimiento> value){areas= value;}
	public void getPalabrasClave(Vector<PalabraClave> value){palabrasClave= value;}

}
