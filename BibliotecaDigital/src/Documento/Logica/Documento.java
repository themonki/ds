 /**
 * Documento.java
 * 
 * Clase que presenta un Usuario es decir una persona que puede
 * hacer uso de la biblioteca digital,por lo tanto, es importante 
 * guardar sus atributos informacion que nos permita disitinguirlos
 * de otros usuarios y asi mismo manejar su ingreso al sistema 
 * adecuadamente.

 * 
 * JAVA version "1.6.0"
 * 
 * 
 * Autor:  Yerminson Gonzales
 * Version:   4.0
 */
package Documento.Logica;
/*Yermi*/
import java.sql.Date;
import java.util.Vector;

import GestionDocumento.Logica.AreaConocimiento;
import GestionDocumento.Logica.Autor;
import GestionDocumento.Logica.PalabraClave;

/**
 * Representacion de la entidad documento del modelo relacional (tabla documento y relaciones)
 * @author 
 *
 */
public class Documento {

	/**
	 * Contiene la llave del documento
	 */
	String idDocumento;
	/**
	 * Contiene el idioma del documento
	 */
	String idioma;
	/**
	 * Indica si tiene o no derechos de autor
	 */
	String derechosDeAutor;
	/**
	 * Contiene una descripcion breve del documento
	 */
	String descripcion;
	/**
	 * Indica que software se puede usar para editar el documento
	 */
	String softwareRecomendado;
	/**
	 * Indica la resolucion del archivo digital
	 */
	String resolucion;
	/**
	 * Indica la editoral a la que pertenece el documento
	 */
	String editorial;
	/**
	 * Indica el formato del documento
	 */
	String formato;
	/**
	 * Contiene el titulo principal del documento
	 */
	String tituloPrincipal;
	/**
	 * Contiene el titulo secundario del documento
	 */
	String tituloSecundario;
	/**
	 * Contiene la direccion en el repositorio para descargar o la direccion del
	 * archivo para catalogar
	 */
	String link;
	/**
	 * Indica cuando se creo el documento
	 */
	Date fechaCreacion;
	/**
	 * Indica cuando se publico el documento
	 */
	Date fechaPublicacion;
	/**
	 * Indica cuando fue catalogado el documento
	 */
	Date fechaCatalogacion;
	/**
	 * Indica la llave del usuario catalogador del documento
	 */
	String loginCatalogador;
	/**
	 * Contiene el tipo de material del documento
	 */
	String tipoMaterial;
	/**
	 * Contiene los autores que escribieron o trabajaron el documento
	 * @see Autor
	 */
	Vector<Autor> autores;
	/**
	 * Contiene las areas de conocimiento a la que pertenece el documento
	 * @see AreaConocimiento
	 */
	Vector<AreaConocimiento> areas;
	/**
	 * Contiene las palabras clave asociadas al documento
	 * @see PalabraClave
	 */
	Vector<PalabraClave> palabrasClave;

	// Constructor
	/**
	 * Constructo por defecto de la clase Documento
	 */
	public Documento() {
	}

	/**
	 * Constructor que inicializa el documento con los valores entrantes
	 * @param idDocumento - String 
	 * @param idioma - String 
	 * @param derechosDeAutor - String 
	 * @param descripcion - String 
	 * @param softwareRecomendado - String 
	 * @param resolucion - String 
	 * @param editorial - String 
	 * @param formato - String 
	 * @param tituloPrincipal - String 
	 * @param tituloSecundario - String 
	 * @param link - String 
	 * @param fechaCreacion - String 
	 * @param fechaPublicacion - String 
	 * @param fechaCatalogacion - String 
	 * @param loginCatalogador - String 
	 * @param tipoMaterial - String 
	 * @param autores - Vector<Autor> 
	 * @param areas - Vector<AreaConocimiento> 
	 * @param palabrasClave - Vector<PalabraClave>
	 */
	public Documento(String idDocumento, String idioma, String derechosDeAutor,
			String descripcion, String softwareRecomendado, String resolucion,
			String editorial, String formato, String tituloPrincipal,
			String tituloSecundario, String link, Date fechaCreacion,
			Date fechaPublicacion, Date fechaCatalogacion,
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

	/**
	 * @return un String con la llave del documento
	 */
	public String getId_doc() {
		return this.idDocumento;
	}

	/**
	 * @return un String con el idioma del documento 
	 */
	public String getIdioma() {
		return this.idioma;
	}

	/**
	 * @return un String con el derecho de autor del documento 
	 */
	public String getDerechosDeAutor() {
		return this.derechosDeAutor;
	}

	/**
	 * @return un String con la descripcion del documento
	 */
	public String getDescripcion() {
		return this.descripcion;
	}

	/**
	 * @return un String con el software para edicion del documento
	 */
	public String getSoftware_recomentado() {
		return this.softwareRecomendado;
	}

	/**
	 * @return un String con la resolucion del documento
	 */
	public String getResolucion() {
		return this.resolucion;
	}

	/**
	 * @return un String con la editorial del documento
	 */
	public String getEditorial() {
		return this.editorial;
	}

	/**
	 * @return un String con el formato del documento
	 */
	public String getFormato() {
		return this.formato;
	}

	/**
	 * @return un String con el titulo principal del documento
	 */
	public String getTituloppal() {
		return this.tituloPrincipal;
	}

	/**
	 * @return un String con el titulo secundario del documento
	 */
	public String getTitulo_secundario() {
		return this.tituloSecundario;
	}

	/**
	 * @return un String con el link del documento
	 */
	public String getUrl() {
		return this.link;
	}

	/**
	 * @return un String con la fecha de creacion del documento
	 */
	public Date getFecha_creacion() {
		return this.fechaCreacion;
	}

	/**
	 * @return un String con la fecha de publicacion del documento
	 */
	public Date getFecha_publicacion() {
		return this.fechaPublicacion;
	}

	/**
	 * @return un String con la fecha de catalogacion del documento
	 */
	public Date getFechaDeCatalogacion() {
		return this.fechaCatalogacion;
	}

	/**
	 * @return un String con la llave del usuario catalogador del documento
	 */
	public String getCatalogadorLogin() {
		return this.loginCatalogador;
	}

	/**
	 * @return un String con el tipo de material del documento
	 */
	public String getTipoMaterial() {
		return this.tipoMaterial;
	}

	/**
	 * @return un String con los autores que escribieron el documento
	 */
	public Vector<Autor> getAutores() {
		return this.autores;
	}

	/**
	 * @return un String con las areas de conocimeinto del documento
	 */
	public Vector<AreaConocimiento> getAreas() {
		return this.areas;
	}

	/**
	 * @return un String con las palabras claves asociadas al documento
	 */
	public Vector<PalabraClave> getPalabrasClave() {
		return this.palabrasClave;
	}

	/**
	 * @param value - String con el nuevo valor para la llave del documento
	 */
	public void setId_doc(String value) {
		idDocumento = value;
	}

	/**
	 * @param value - String con el nuevo valor para el idioma del documento
	 */
	public void setIdioma(String value) {
		idioma = value;
	}

	/**
	 * @param value - String con el nuevo valor para los derechos de autor del documento
	 */
	public void setDerechosDeAutor(String value) {
		derechosDeAutor = value;
	}

	/**
	 * @param value - String con el nuevo valor para la descripcion del documento
	 */
	public void setDescripcion(String value) {
		descripcion = value;
	}

	/**
	 * @param value - String con el nuevo valor para el software para edicion del documento
	 */
	public void setSoftware_recomentado(String value) {
		softwareRecomendado = value;
	}

	/**
	 * @param value - String con el nuevo valor para la resolucion del archivo
	 */
	public void setResolucion(String value) {
		resolucion = value;
	}

	/**
	 * @param value - String con el nuevo valor para la editorial del documento
	 */
	public void setEditorial(String value) {
		editorial = value;
	}

	/**
	 * @param value - String con el nuevo valor para el formato del documento
	 */
	public void setFormato(String value) {
		formato = value;
	}

	/**
	 * @param value - String con el nuevo valor para el titulo principal del documento
	 */
	public void setTituloppal(String value) {
		tituloPrincipal = value;
	}

	/**
	 * @param value - String con el nuevo valor para el titulo secundario del documento
	 */
	public void setTitulo_secundario(String value) {
		tituloSecundario = value;
	}

	/**
	 * @param value - String con el nuevo valor para el link del documento
	 */
	public void setUrl(String value) {
		link = value;
	}

	/**
	 * @param value - Date con el nuevo valor para la fecha de creacion del documento 
	 */
	public void setFecha_creacion(Date value) {
		fechaCreacion = value;
	}

	/**
	 * @param value - Date con el nuevo valor para la fecha de publicacion del documento
	 */
	public void setFecha_publicacion(Date value) {
		fechaPublicacion = value;
	}

	/**
	 * @param value - Date con el nuevo valor para la fecha de catalogacion del documento 
	 */
	public void setFechaDeCatalogacion(Date value) {
		fechaCatalogacion = value;
	}

	/**
	 * @param value - String con el nuevo valor para la llave del usuario catalogador del documento
	 */
	public void setCatalogadorLogin(String value) {
		loginCatalogador = value;
	}

	/**
	 * @param value - String con el nuevo valor para el tipo de material del documento
	 */
	public void setTipoMaterial(String value) {
		tipoMaterial = value;
	}

	/**
	 * @param value - Vector<Autor> con el nuevo valor para los autores del documento 
	 */
	public void setAutores(Vector<Autor> value) {
		autores = value;
	}

	/**
	 * @param value - Vector<AreaConocimiento> con el nuevo valor para las areas de conocimiento 
	 * del documento 
	 */
	public void setAreas(Vector<AreaConocimiento> value) {
		areas = value;
	}

	/**
	 * @param value - Vector<PalabraClave> con el nuevo valor para las palabras clave del documento 
	 */
	public void setPalabrasClave(Vector<PalabraClave> value) {
		palabrasClave = value;
	}

}
