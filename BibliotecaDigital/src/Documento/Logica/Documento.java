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
	
	public String getId_doc(){ return this.idDocumento;}
	public String getIdioma(){ return this.idioma;}
	public String getDerechosDeAutor(){return this.derechosDeAutor;}
	public String getDescripcion(){return this.descripcion;}
	public String getSoftware_recomentado(){return this.softwareRecomendado;}
	public String getResolucion(){return this.resolucion;}
	public String getEditorial(){return this.editorial;}
	public String getFormato(){return this.formato;}
	public String getTituloppal(){return this.tituloPrincipal;}
	public String getTitulo_secundario(){return this.tituloSecundario;}
	public String getUrl(){return this.link;}
	public String getFecha_creacion(){return this.fechaCreacion;}
	public String getFecha_publicacion(){return this.fechaPublicacion;}
	public String getFechaDeCatalogacion(){return this.fechaCatalogacion;}
	public String getCatalogadorLogin(){return this.loginCatalogador;}
	public String getTipoMaterial(){return this.tipoMaterial;}
	public Vector<Autor> getAutores(){return this.autores;}
	public Vector<AreaConocimiento> getAreas(){return this.areas;}
	public Vector<PalabraClave> getPalabrasClave(){return this.palabrasClave;}	
	public void setid_doc(String value){ idDocumento = value;}
	public void setIdioma(String value){ idioma= value;}
	public void setderechosDeAutor(String value){derechosDeAutor= value;}
	public void setDescripcion(String value){descripcion= value;}
	public void setSoftware_recomentado(String value){softwareRecomendado= value;}
	public void setResolucion(String value){resolucion= value;}
	public void setEditorial(String value){editorial= value;}
	public void setFormato(String value){formato= value;}
	public void settituloppal(String value){tituloPrincipal= value;}
	public void setTitulo_secundario(String value){tituloSecundario= value;}
	public void seturl(String value){link= value;}
	public void setFecha_creacion(String value){fechaCreacion= value;}
	public void setFecha_publicacion(String value){fechaPublicacion= value;}
	public void setfechaDeCatalogacion(String value){fechaCatalogacion= value;}	
	public void setcatalogadorLogin(String value){loginCatalogador= value;}
	public void setTipoMaterial(String value){tipoMaterial= value;}
	public void setAutores(Vector<Autor> value){autores= value;}
	public void setAreas(Vector<AreaConocimiento> value){areas= value;}
	public void setPalabrasClave(Vector<PalabraClave> value){palabrasClave= value;}

}
